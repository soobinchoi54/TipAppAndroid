package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**************************************************************************************************
 *           An activity for displaying all the history experience records to UI
 ***************************************************************************************************/
public class ViewHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private HistoryListAdapter adapter;
    private List<Experience> experiences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        experiences = new ArrayList<>();
        adapter = new HistoryListAdapter(experiences);
        fetchData();
        recyclerView = findViewById(R.id.experienceList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
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
}


