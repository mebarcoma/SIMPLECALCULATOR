package com.example.highandlow;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNumber, attempts;
    TextView resultText, scoreText;
    EditText inputNumber;
    Button guessBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link UI elements
        resultText = findViewById(R.id.resultText);
        scoreText = findViewById(R.id.scoreText);
        inputNumber = findViewById(R.id.inputNumber);
        guessBtn = findViewById(R.id.guessBtn);

        // Generate initial number
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
                attempts++;
                scoreText.setText("Attempts: " + attempts);

                if (guess > randomNumber) {
                    resultText.setText("Too High!");
                } else if (guess < randomNumber) {
                    resultText.setText("Too Low!");
                } else {
                    resultText.setText("Correct! 🎉");
                    Toast.makeText(MainActivity.this,
                            "You guessed in " + attempts + " attempts!",
                            Toast.LENGTH_LONG).show();
                    generateNumber(); // reset game
                    inputNumber.setText("");
                }
            }
        });
    }

    // Generate random number between 1-100
    private void generateNumber() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attempts = 0;
        if (scoreText != null) scoreText.setText("Attempts: 0");
    }
}