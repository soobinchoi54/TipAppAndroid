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

/************************************************************************************************
 * The SplitBillActivity class provides users an option to split the bill and send messages to
 * their party if they want. User will input party size and the app will return the amount that
 * each person owes the user. The user can then remind his/her party know by sending distributing
 * messages to the party.
 ************************************************************************************************/

public class SplitBillActivity extends AppCompatActivity {

    private EditText partySize;
    private TextView amountOwed;
    private Button splitButton;

    double size;
    double owedPP;
    double totalBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                amountOwed.setText(f.format(owedPP));
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
        Toast.makeText(SplitBillActivity.this,"Experience Completed!",Toast.LENGTH_SHORT).show();

        // submit to tip result activity
        Intent intent = new Intent(getApplicationContext(), ViewHistoryActivity.class);
        startActivity(intent);
    }
}
