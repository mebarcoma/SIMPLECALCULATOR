package com.example.tipcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TipCalculator tipCalculator;
    EditText amount;
    TextView tipAmount;
    SeekBar tipSeeker;
    double tip = 0.01;
    private  static  final DecimalFormat decFormat =
            new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tipCalculator = new TipCalculator();
        amount = (EditText) findViewById(R.id.costOfService);
        tipAmount = (TextView) findViewById(R.id.tipAmount);
        tipSeeker = (SeekBar) findViewById(R.id.tip_seeker);

        //set seekbar min and max values
        tipSeeker.setMax(100);
        tipSeeker.setMin(10);


        //event listener for seekbar
        tipSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                double tipPercent= progressValue / 10.0;
                Toast.makeText(MainActivity.this,
                        "Service Tip is at " + tipPercent + "%", Toast.LENGTH_SHORT).show();
                tip = tipPercent / 100;
            }
        });
    }

    private void calculateTip(double tip, double total_amount){
        double tipAmountString = tipCalculator.calculateTip(tip, total_amount);
        tipAmount.setText(decFormat.format(tipAmountString));
    }

    public void calculateTip(View view) {
        if (amount.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this,
                    "Please fill in the cost of service", Toast.LENGTH_SHORT).show();
            return;
        }
        double serviceAmount = Double.parseDouble(amount.getText().toString());
        calculateTip(tip, serviceAmount);
    }
}
