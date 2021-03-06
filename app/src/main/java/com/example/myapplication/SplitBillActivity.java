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
 * The SplitBillActivity class provides users an option to split the bill and send messages to
 * their party if they want. User will input party size and the app will return the amount that
 * each person owes the user. The user can then remind his/her party know by sending distributing
 * messages to the party.
 ************************************************************************************************/

public class SplitBillActivity extends AppCompatActivity {

    private EditText partySize;
    private TextView amountOwed;
    private TextView splitButton;

    double size;
    double owedPP;
    double totalBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_split_bill);

        partySize = findViewById(R.id.party_size);
        amountOwed = findViewById(R.id.per_person);
        splitButton = findViewById(R.id.split_bill_button);

        // add addTextChanged listener to edit text field for bill total to grab double value of total before tip
        partySize.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    size = Double.parseDouble(String.valueOf(s));
                }

                totalBill = TipResultActivity.getTotalPlusTip();
                owedPP = totalBill/size;

                DecimalFormat f = new DecimalFormat("##.00");

                // set amount owed per person
                amountOwed.setText(f.format(owedPP) + "   per person");
            }
        });

    }

    public void onSendMessage (View view) {
        DecimalFormat f = new DecimalFormat("##.00");
        String amountOwed = f.format(owedPP);
        String message = ("Your meal cost " + amountOwed);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        String chooserTitle = getString(R.string.chooser);
        Intent chosenIntent = Intent.createChooser(intent, chooserTitle);
        startActivity(chosenIntent);
    }

    public void onClickGoBack(View view) {
        onBackPressed();
    }

    public void onClickComplete(View view) {
        // submit to tip result activity
        Intent intent = new Intent(getApplicationContext(), ViewHistoryActivity.class);
        // Create bundle containing data from previous activity
        Bundle bundle = getIntent().getExtras();
        // putExtra review data to pass to next intent
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);

        // ADD DATA FROM BUNDLE TO DATABASE
        Object NAME = bundle.get("NAME");
        Object LOCATION = bundle.get("LOCATION");
        Object CATEGORY = bundle.get("CATEGORY");
        Object PRICE = bundle.get("PRICE");
        Object TOTAL_BILL = bundle.get("TOTAL_BILL");
        Object TIP_PERCENTAGE = bundle.get("TIP_PERCENTAGE");
        Object TIME = bundle.get("TIME");
        Object CRITERIA1 = bundle.get("CRITERIA1");
        Object CRITERIA2 = bundle.get("CRITERIA2");
        Object CRITERIA3 = bundle.get("CRITERIA3");
        Object IMAGE = bundle.get("IMAGE TEXT");

        Experience experience = new Experience.ExperienceBuilder(NAME.toString())
                .location(LOCATION.toString())
                .category(CATEGORY.toString())
                .price(PRICE.toString())
                .totalBill("$" + TOTAL_BILL.toString())
                .tipPercentage(TIP_PERCENTAGE.toString() + "%")
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
        for (String key : bundle.keySet()) {
            string += " " + key + " => " + bundle.get(key) + ";";
        }
        string += " }Bundle";
        System.out.println(string);
    }
}
