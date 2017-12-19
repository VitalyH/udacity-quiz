package com.example.android.quizapp;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Saving state before rotation
     */
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        // Saving answer on the first question
        TextView questionOneInput = findViewById(R.id.questionOneInput);
        String questionOneUser = questionOneInput.getText().toString();
        bundle.putString("questionOneUser", questionOneUser);
        super.onSaveInstanceState(bundle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Restoration after scree rotation
         */
        if (savedInstanceState != null) {
            // Restoring first answer
            String questionOneNull = savedInstanceState.getString("questionOneUser");;
            TextView questionOneInput = findViewById(R.id.questionOneInput);
            questionOneInput.setText(questionOneNull);
        }

        /**
         * Floating "Reset" button logic
         */
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reset = getString(R.string.reset);
                String action_message = getString(R.string.action_message);
                Snackbar.make(view, reset, Snackbar.LENGTH_LONG)
                        .setAction(action_message, null).show();

                // First question reset
                String questionOneNull = "";
                TextView questionOneInput = findViewById(R.id.questionOneInput);
                questionOneInput.setText(questionOneNull);

                // Second question reset
                CheckBox tom = findViewById(R.id.tom);
                tom.setChecked(false);
                CheckBox john = findViewById(R.id.john);
                john.setChecked(false);
                CheckBox marvolo = findViewById(R.id.marvolo);
                marvolo.setChecked(false);

                // Third question reset
                CheckBox lily = findViewById(R.id.lily);
                lily.setChecked(false);
                CheckBox liliana = findViewById(R.id.liliana);
                liliana.setChecked(false);
                CheckBox george = findViewById(R.id.george);
                george.setChecked(false);
                CheckBox james = findViewById(R.id.james);
                james.setChecked(false);

                // Fourth question reset
                RadioGroup fourthRadioGroup = findViewById(R.id.fourthQuestion);
                fourthRadioGroup.clearCheck();

                // Fifth question reset
                RadioGroup fifthRadioGroup = findViewById(R.id.fifthQuestion);
                fifthRadioGroup.clearCheck();
            }
        });
    }

    /**
     * This method calculating and displaying result of the quiz via toast
     */
    public void getResult(View view) {
        int showResult = 0; // Initial number of right answers
        String questionOneCorrect = "Albus"; // Correct answer on the first question

        // First question
        EditText inputText = findViewById(R.id.questionOneInput);
        String questionOneUser = inputText.getText().toString();
        if (questionOneUser.equals(questionOneCorrect)) {
            showResult = showResult + 1;
        }

        // Second question
        // Correct answer: Tom and Marvolo
        CheckBox tom = findViewById(R.id.tom);
        CheckBox john = findViewById(R.id.john);
        CheckBox marvolo = findViewById(R.id.marvolo);
        if (tom.isChecked()) {
            if (marvolo.isChecked()) {
                showResult = showResult + 1;
                if (john.isChecked()) {
                    showResult = showResult - 1;
                }
            }
        }

        // Third question
        // Correct answer: Lily and James
        CheckBox lily = findViewById(R.id.lily);
        CheckBox liliana = findViewById(R.id.liliana);
        CheckBox george = findViewById(R.id.george);
        CheckBox james = findViewById(R.id.james);
        if (lily.isChecked()) {
            if (james.isChecked()) {
                showResult = showResult + 1;
                if (liliana.isChecked()) {
                    showResult = showResult - 1;
                } else if (george.isChecked()) {
                    showResult = showResult - 1;
                }
            }
        }

        // Fourth question
        // Correct answer: Minerva
        RadioButton minerva = findViewById(R.id.minerva);
        if (minerva.isChecked()) {
            showResult = showResult + 1;
        }
        // Fifth question
        // Correct answer: Bellatrix Lestrange
        RadioButton bellatrix = findViewById(R.id.bellatrix);
        if (bellatrix.isChecked()) {
            showResult = showResult + 1;
        }

        // Toast with results
        String result_first = getString(R.string.result_first);
        String result_second = getString(R.string.result_second);
        Toast.makeText(this, result_first + " "
                        + showResult + " " + result_second, Toast.LENGTH_LONG).show();
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
