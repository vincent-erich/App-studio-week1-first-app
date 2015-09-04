package com.example.vincent.randomnumberguessinggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Properties of the class...
    private EditText userInput;
    private TextView textNumberOfGuesses;
    private TextView hintForUser;
    private Random randomGenerator;

    private int randomGeneratedNumber;
    private int numberOfGuesses;

    private boolean numberGuessed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = (EditText) findViewById (R.id.user_input);
        textNumberOfGuesses = (TextView) findViewById(R.id.number_of_guesses);
        hintForUser = (TextView) findViewById (R.id.hint_for_user);

        // Generate a random int between 0 and 1000 (inclusive).
        randomGenerator = new Random ();
        randomGeneratedNumber = randomGenerator.nextInt(1001);

        numberOfGuesses = 0;
        numberGuessed = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // This method is called when the 'MAKE A GUESS!' button is clicked.
    public void onGuessButtonClick(View view) {

        String guessedNumberString = String.valueOf(userInput.getText());
        int guessedNumberInt;

        try {
            // Parse the user input to an int.
            guessedNumberInt = Integer.parseInt(guessedNumberString);

            if (guessedNumberInt < 0 || guessedNumberInt > 1000) {
                hintForUser.setText(R.string.text_out_of_range);
            }
            else if (guessedNumberInt < randomGeneratedNumber) {
                numberOfGuesses++;
                //hintForUser.setText(R.string.text_your_guess + guessedNumberString + R.string.text_higher);
                hintForUser.setText("Your guess: " + guessedNumberString + ". The generated number is HIGHER.");
            }
            else if (guessedNumberInt > randomGeneratedNumber) {
                numberOfGuesses++;
                //hintForUser.setText(R.string.text_your_guess + guessedNumberString + R.string.text_lower);
                hintForUser.setText("Your guess: " + guessedNumberString + ". The generated number is LOWER.");
            }
            else {
                if (!numberGuessed) {
                    numberOfGuesses++;
                }
                numberGuessed = true;
                hintForUser.setText(R.string.text_win);
            }
        }
        catch (NumberFormatException e){
            hintForUser.setText(R.string.text_invalid_input);
        }

        //textNumberOfGuesses.setText(R.string.text_number_of_guesses + numberOfGuesses);
        textNumberOfGuesses.setText("Number of guesses: " + numberOfGuesses);
    }

    // This method is called when the 'HELP' button is clicked.
    public void onHelpButtonClick(View view) {
        // Go to 'Activity2.java'.
        startActivity(new Intent(getApplicationContext(), Activity2.class));
    }

    // This method is called when the 'RESET' button is clicked.
    public void onResetButtonClick(View view) {
        numberOfGuesses = 0;
        textNumberOfGuesses.setText(R.string.text_initial_number_of_guesses);
        hintForUser.setText(R.string.text_hint);
        randomGeneratedNumber = randomGenerator.nextInt(1001);
    }
}
