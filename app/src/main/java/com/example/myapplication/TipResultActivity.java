package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static java.lang.Math.floor;

/************************************************************************************************
 * The TipResultActivity provides an interface for the users to input the total bill amount from
 * their dining experience. The activity will fetch the tip percentage from the ExperienceActivity
 * and calculate a new bill total for the user. The user then has the option to split the bill,
 * which will send an intent to the SplitBillActivity, or the user can simply end the dining
 * experience, which will store the experience data in a SQLite database.
 ************************************************************************************************/

public class TipResultActivity extends AppCompatActivity {

    private EditText billTotal;
    private TextView tipPercentage;
    private TextView yourTotal;
    private Button completeButton;
    private Button splitButton;

    double total;
    double tip;
    double totalPlusTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_result);

        // add elements for calculating your total bill including tip (total before tip, tip %, total after tip)
        billTotal = findViewById(R.id.bill_total_value);
        tipPercentage = findViewById(R.id.tip_percentage_value);
        yourTotal = findViewById(R.id.your_total_value);

        // set text for tip percentage text view
        tip = (double) ExperienceActivity.getTipPercentage();
        tipPercentage.setText(String.valueOf((int)floor(tip*100)));


        // add addTextChanged listener to edit text field for bill total to grab double value of total before tip
        billTotal.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    total = Double.parseDouble(String.valueOf(s));
                }

                // calculate total bill plus tip
                totalPlusTip = total * (1 + tip);

                DecimalFormat f = new DecimalFormat("##.00");

                // set text for your total bill after tip text view
                yourTotal.setText(f.format(totalPlusTip));
            }
        });

        /*
         * CREATE COMPLETE EXPERIENCE BUTTON ELEMENT
         */
        // add submit button element
        completeButton = findViewById(R.id.complete_experience_button);

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TipResultActivity.this,"Experience Completed!",Toast.LENGTH_SHORT).show();

                // submit to tip result activity
                Intent intent = new Intent(getApplicationContext(), ViewHistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
