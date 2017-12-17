package com.example.android.quizapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /**
     * Some variables
     */
    String questionOneCorrect = "Albus"; // Correct answer on the first question


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Floating "Reset" button logic
         */
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Ok, let's try again...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // First question reset
                String questionOneNull = "";
                TextView questionOneInput = findViewById(R.id.questionOneInput);
                questionOneInput.setText(questionOneNull);
            }
        });
    }

    /**
     * This method calculating and displaying result of the quiz via toast
     */
    public void getResult(View view) {
        int showResult = 0; // Initial number of right answers
        // First question
        EditText inputText = findViewById(R.id.questionOneInput);
        String questionOneUser = inputText.getText().toString();
        if (questionOneUser.equals(questionOneCorrect)) {
            showResult = showResult + 1;
        }
        // Second question
    Toast.makeText(MainActivity.this, "You got " +showResult+ " questions right!", Toast.LENGTH_LONG).show();
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
}
