package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RestaurantChooserActivity extends AppCompatActivity {


    //REST API will be implemented here
    //Looking for restaurant db with locations to draw 5 restaurants within the area based on
    //user location
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_chooser);
    }

    //Moves to our current experience activity
    public void startExperience(View view){
        // TEMP
        Intent intent = new Intent(this, ExperienceActivity.class);
        startActivity(intent);
    }
}