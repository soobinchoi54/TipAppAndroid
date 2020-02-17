package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public static final String DB_NAME = "TipsDataBase";
    public static final int DB_VERSION = 1;

    DataBaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(0, DB_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(oldVersion, newVersion);
    }

    private void updateMyDatabase(int oldVersion, int newVersion) {
        this.db.execSQL("CREATE TABLE TipsDataBase (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "NAME TEXT, "
                + "LOCATION TEXT, "
                + "CATEGORY TEXT, "
                + "PRICE TEXT, "
                + "TOTAL_BILL TEXT, "
                + "TIP_PERCENTAGE TEXT, "
                + "TIME TEXT, "
                + "CRITERIA1 TEXT, "
                + "CRITERIA2 TEXT, "
                + "CRITERIA3 TEXT);");
    }

}
