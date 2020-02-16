package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onNewExperience(View view){

    }

    public void onViewPast(View view){
        Intent intent = new Intent(this, ViewHistory.class);
        startActivity(intent);
    }
}
