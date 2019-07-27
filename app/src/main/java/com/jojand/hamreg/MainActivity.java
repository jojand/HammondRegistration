package com.jojand.hamreg;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = findViewById(R.id.calculate);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calculateRegisters();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    private void calculateRegisters() {
        EditText edit1 = findViewById(R.id.editText_0_0);
        EditText edit2 = findViewById(R.id.editText_1_0);

        String text1 = edit1.getText().toString();
        String text2 = edit2.getText().toString();

        double a = Double.parseDouble(text1);
        double b = Double.parseDouble(text2);

        Log.d(TAG, String.format("calculateRegisters: input: %f, %f", a, b));

        a = a == 0 ? 0 : Math.pow(2, a-1);
        b = b == 0 ? 0 : Math.pow(2, b-1);
        Log.d(TAG, String.format("calculateRegisters: power: %f, %f", a, b));

        double res = a + b;
        Log.d(TAG, String.format("calculateRegisters: power sum: %f", res));

        res = res == 0 ? 0 : Math.round(1 + Math.log(res)/Math.log(2));
        Log.d(TAG, String.format("calculateRegisters: result: %f", res));
    }
}
