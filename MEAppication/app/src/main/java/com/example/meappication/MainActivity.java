package com.example.meappication;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNumber;
    TextView resultText;
    EditText inputNumber;
    Button guessBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.resultText);
        inputNumber = findViewById(R.id.inputNumber);
        guessBtn = findViewById(R.id.guessBtn);

        generateNumber();

        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = inputNumber.getText().toString();

                if (input.isEmpty()) {
                    resultText.setText("Enter a number!");
                    return;
                }

                int guess = Integer.parseInt(input);

                if (guess > randomNumber) {
                    resultText.setText("Too High!");
                } else if (guess < randomNumber) {
                    resultText.setText("Too Low!");
                } else {
                    resultText.setText("Correct! 🎉");
                }
            }
        });
    }

    private void generateNumber() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
    }
}