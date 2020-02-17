package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;


/************************************************************************************************
 * The ExperienceActivity provides an interface for the users to create and submit a new dining
 * experience. The interface allows users to rate their experience based on several preset
 * criteria, which will be calculated to suggest a tip percentage. For example, if the user
 * rates "ok" for the service criteria, 0% will be added to a preset tip rate of 15%. If the
 * user rates "good", 1% will be added, resulting in a 16% tip for that meal. The increments
 * at which the tip percentage changes is as below:
 * "poor": -2%     "meh": -1%     "ok": 0%     "good": +1%     "great": +2%
 ************************************************************************************************/

public class ExperienceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private RadioGroup radioGroup;
    private Button submitButton;

    private static float tipPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        /*
         * CREATE SPINNER ELEMENT
         */
        // add spinner element
        spinner = findViewById(R.id.spinner_criteria_1);

        // add spinner onItemSelected listener
        spinner.setOnItemSelectedListener(this);

        // add spinner dropdown elements
        List<String> criteria = new ArrayList<>();
        criteria.add("Service");
        criteria.add("Food Quality");
        criteria.add("Timeliness");

        // add spinner adapter
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, criteria);

        // add spinner dropdown layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attach data adapter to spinner
        spinner.setAdapter(dataAdapter);

        /*
         * CREATE RADIO BUTTON ELEMENT
         */
        // add radio group element
        radioGroup= findViewById(R.id.radio_group_rate);

        // add onClickedChange listener
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // set starting tip percentage
                tipPercentage = 0.15f;

                switch (checkedId) {
                    case R.id.rate_poor:
                            // -2%
                            tipPercentage -= 0.02f;
                        // Showing selected spinner item
                        Toast.makeText(ExperienceActivity.this, "Tip Percentage: " + floor(tipPercentage*100), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rate_meh:
                            // -1%
                            tipPercentage -= 0.01f;
                        // Showing selected spinner item
                        Toast.makeText(ExperienceActivity.this, "Tip Percentage: " + floor(tipPercentage*100), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rate_ok:
                            // 0%
                            tipPercentage -= 0.00f;
                        // Showing selected spinner item
                        Toast.makeText(ExperienceActivity.this, "Tip Percentage: " + floor(tipPercentage*100), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rate_good:
                            // +1%
                            tipPercentage += 0.01f;
                        // Showing selected spinner item
                        Toast.makeText(ExperienceActivity.this, "Tip Percentage: " + floor(tipPercentage*100), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rate_great:
                            // +2%
                            tipPercentage += 0.02;
                        // Showing selected spinner item
                        Toast.makeText(ExperienceActivity.this, "Tip Percentage: " + floor(tipPercentage*100), Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        /*
         * CREATE SUBMIT BUTTON ELEMENT
         */
        // add submit button element
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ExperienceActivity.this,"You're Tipping: " + floor(tipPercentage*100) + " %",Toast.LENGTH_SHORT).show();

                // submit to tip result activity
                Intent intent = new Intent(getApplicationContext(), TipResultActivity.class);
                startActivity(intent);


            }
        });

    }

    /*
     * Select call back methods on SPINNER
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    public static float getTipPercentage() {
        return tipPercentage;
    }

}
