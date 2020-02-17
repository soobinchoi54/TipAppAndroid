package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**************************************************************************************************
 *                                        Main Menu
 ***************************************************************************************************/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onNewExperience(View view){
        DataBaseHelper.addExperience(this, "BCD Tofu", "Irvine", "Korean", "$$", "25$", "15%", "20:00", "-1", "0", "+1");
        DataBaseHelper.addExperience(this, "HaiDiLao", "Irvine", "Chinese", "$$$$", "100$", "15%", "50:00", "-1", "0", "+1");
    }

    public void onViewPast(View view){
        Intent intent = new Intent(this, ViewHistoryActivity.class);
        startActivity(intent);
    }
}
