package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.model.Experience;
import com.example.myapplication.database.DataBaseHelper;
import com.example.myapplication.util.HistoryListAdapter;

/**************************************************************************************************
 *           An activity for displaying all the history experience records to UI
 ***************************************************************************************************/
public class ViewHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RelativeLayout buttons;
    private HistoryListAdapter adapter;
    private List<Experience> experiences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_view_history);
        experiences = new ArrayList<>();
        adapter = new HistoryListAdapter(experiences);
        fetchData();
        recyclerView = findViewById(R.id.experienceList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        buttons = findViewById(R.id.buttons);
        buttons.bringToFront();
    }

    private void fetchData(){
        experiences = DataBaseHelper.getExperiences(this);
        adapter.setData(experiences);
    }

    public void onClickDeleteTable(View view) {
        DataBaseHelper.deleteTable(this);
        fetchData();
    }

    public void onClickTest(View view) {
        DataBaseHelper.testAdd2Experience(this);
        fetchData();
    }

    @Override
    public void onBackPressed(){
        // go back to main menu to prevent data overwriting
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void onClickHome(View view) {
        onBackPressed();
    }
}


