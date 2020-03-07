package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewRestaurantActivity extends AppCompatActivity {

    private String RestaurantName;
    private String RestaurantLocation;
    private EditText etRestaurantName;
    private EditText etRestaurantLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_restaurant);
    }

    public void startExperience(View view) {
        etRestaurantName = findViewById(R.id.new_restaurant_name);
        etRestaurantLocation = findViewById(R.id.new_restaurant_location);
        RestaurantName = etRestaurantName.getText().toString();
        RestaurantLocation = etRestaurantLocation.getText().toString();

        if(RestaurantName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a restaurant name",
                    Toast.LENGTH_SHORT).show();
        }
        else if(RestaurantLocation.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter the restaurant's location",
                    Toast.LENGTH_SHORT).show();
        }
        else{

            Intent intent = new Intent(this, ExperienceActivity.class);
            // Create a bundle object
            Bundle extras = new Bundle();

            // Attach key value pair using putExtra to this intent

            extras.putString("NAME", RestaurantName);
            extras.putString("LOCATION", RestaurantLocation);
            extras.putString("IMAGE TEXT", "");
            extras.putString("CATEGORY", "");
            extras.putString("PRICE", "");

            intent.putExtras(extras);

            //System.out.println("Sending Name: " + RestaurantName + "| Location: " + RestaurantLocation);

            //Start ExperienceActivity
            startActivity(intent);
        }
    }


}
