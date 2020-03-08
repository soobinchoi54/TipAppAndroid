package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import com.example.myapplication.model.Experience;
import com.example.myapplication.database.DataBaseHelper;

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
    private static double totalPlusTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_tip_result);

        // add elements for calculating your total bill including tip (total before tip, tip %, total after tip)
        billTotal = findViewById(R.id.bill_total_value);
        tipPercentage = findViewById(R.id.tip_percentage_value);
        yourTotal = findViewById(R.id.your_total_value);

        // set text for tip percentage text view
        tip = (double) ExperienceActivity.getTipPercentage();
        System.out.println(tip);
        DecimalFormat f = new DecimalFormat("##");
        tipPercentage.setText((f.format(tip*100)));

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
                yourTotal.setText(f.format(totalPlusTip) + "$");
            }
        });

        /*
         * CREATE BUTTON ELEMENTS
         */
        // add submit button element
        splitButton = findViewById(R.id.split_bill_button);

        splitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TipResultActivity.this,"Experience Completed!",Toast.LENGTH_SHORT).show();

                // submit to tip result activity
                Intent intent = new Intent(getApplicationContext(), SplitBillActivity.class);
                // Create bundle containing data from previous activity
                Bundle bundle = getIntent().getExtras();
                // putExtra review data to pass to next intent
                if (bundle != null) {
                    intent.putExtras(bundle);
                    intent.putExtra("TOTAL_BILL", yourTotal.getText());
                    intent.putExtra("TIP_PERCENTAGE", tipPercentage.getText());
                }
                startActivity(intent);

                System.out.println(yourTotal.getText() + " " + tipPercentage.getText());
            }
        });

        completeButton = findViewById(R.id.complete_experience_button);

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TipResultActivity.this,"Experience Completed!",Toast.LENGTH_SHORT).show();

                // submit to tip result activity
                Intent intent = new Intent(getApplicationContext(), ViewHistoryActivity.class);
                // Create bundle containing data from previous activity
                Bundle bundle2 = getIntent().getExtras();
                // putExtra review data to pass to next intent
                if (bundle2 != null) {
                    intent.putExtras(bundle2);
                    intent.putExtra("TOTAL_BILL", yourTotal.getText());
                    intent.putExtra("TIP_PERCENTAGE", tipPercentage.getText());
                }
                startActivity(intent);

                // ADD DATA FROM BUNDLE TO DATABASE
                Object NAME = bundle2.get("NAME");
                Object LOCATION = bundle2.get("LOCATION");
                Object CATEGORY = bundle2.get("CATEGORY");
                Object PRICE = bundle2.get("PRICE");
                Object TIME = bundle2.get("TIME");
                Object CRITERIA1 = bundle2.get("CRITERIA1");
                Object CRITERIA2 = bundle2.get("CRITERIA2");
                Object CRITERIA3 = bundle2.get("CRITERIA3");
                Object IMAGE = bundle2.get("IMAGE TEXT");

                Experience experience = new Experience.ExperienceBuilder(NAME.toString())
                        .location(LOCATION.toString())
                        .category(CATEGORY.toString())
                        .price(PRICE.toString())
                        .totalBill("$" + yourTotal.getText().toString())
                        .tipPercentage(tipPercentage.getText().toString() + "%")
                        .time(TIME.toString())
                        .service(CRITERIA1.toString() + "%")
                        .timeliness(CRITERIA2.toString() + "%")
                        .food(CRITERIA3.toString() + "%")
                        .custom("")
                        .customRate("")
                        .image(IMAGE.toString())
                        .build();
                DataBaseHelper.addExperience(getApplicationContext(), experience);

                // Test bundle key=>value pair for all data passed
                String string = "Bundle{ ";
                for (String key : bundle2.keySet()) {
                    string += " " + key + " => " + bundle2.get(key) + ";";
                }
                string += " TOTAL BILL => " + yourTotal.getText() + ";" + " TIP PERCENTAGE => " + tipPercentage.getText() + "; }Bundle";
                System.out.println(string);
            }
        });
    }

    public static double getTotalPlusTip() {
        return totalPlusTip;
    }

    public void onClickEdit(View view) {
        onBackPressed();
    }
}

