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
        LinearLayout firstRow = findViewById(R.id.linearLayout_firstRow);
        LinearLayout secondRow = findViewById(R.id.linearLayout_secondRow);
        LinearLayout resultRow = findViewById(R.id.linearLayout_resultRow);

        for (int i=0; i<REGISTERS; i++) {
            EditText inA = (EditText) firstRow.getChildAt(i);
            EditText inB = (EditText) secondRow.getChildAt(i);
            TextView resView = (TextView) resultRow.getChildAt(i);

            String textA = inA.getText().toString();
            String textB = inB.getText().toString();

            double a=0, b=0;

            try {
                a = Double.parseDouble(textA);
                b = Double.parseDouble(textB);
            } catch (Exception e) {
                Log.w(TAG, "some of the infput is invalid, using default value: 0");
            }

            double res = LogSum.logSum(a, b);

            Log.d(TAG, String.format("calculateRegisters %d: result: %f", i, res));
            resView.setText(String.format("%d", (int)res));
        }
    }
}
