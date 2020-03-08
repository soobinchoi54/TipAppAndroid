package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**************************************************************************************************
 *                                        Main Menu
 ***************************************************************************************************/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    //moves to restaurant chooser activity
    public void onNewExperience(View view){
        // TEMP
        Intent intent = new Intent(this, RestaurantChooserActivity.class);
        startActivity(intent);
    }

    public void onViewPast(View view){
        Intent intent = new Intent(this, ViewHistoryActivity.class);
        startActivity(intent);
    }
}