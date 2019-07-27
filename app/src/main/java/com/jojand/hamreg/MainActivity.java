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
import android.widget.TextView;

import com.jojand.hamreg.logsum.LogSum;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int REGISTERS = 9;

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
        EditText input = findViewById(R.id.input);
        TextView resultView = findViewById(R.id.result);
        String text = input.getText().toString();

        LogSum logSum = new LogSum();
        logSum.parseText(text);

        input.setText(logSum.getText());

        Double[] result = logSum.getResultSum();

        StringBuilder resultString = new StringBuilder();
        for (Double registration: result) {
            resultString.append(registration.intValue());
        }

        resultView.setText(resultString.toString());
    }
}
