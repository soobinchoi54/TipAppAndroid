package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/************************************************************************************************
 * Database System that provides API for storing all the experience records for further usages
 ************************************************************************************************/
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "TipsDataBase";
    public static final int DB_VERSION = 2;

    DataBaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db,0, DB_VERSION);
        System.out.print("Database created");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TipsDataBase");
        db.execSQL("CREATE TABLE TipsDataBase (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "NAME TEXT, "
                + "LOCATION TEXT, "
                + "CATEGORY TEXT, "
                + "PRICE TEXT, "
                + "TOTAL_BILL TEXT, "
                + "TIP_PERCENTAGE TEXT, "
                + "TIME TEXT, "
                + "CRITERIA1 TEXT, "
                + "CRITERIA2 TEXT, "
                + "CRITERIA3 TEXT, "
                + "CUSTOM TEXT, "
                + "CUSTOM_RATE TEXT, "
                + "IMAGE TEXT);");
    }

    // add an experience to database (inner logic)
    private static void add(SQLiteDatabase db, String NAME, String LOCATION, String CATEGORY, String PRICE, String TOTAL_BILL, String TIP_PERCENTAGE, String TIME, String CRITERIA1, String CRITERIA2, String CRITERIA3, String CUSTOM, String CUSTOM_RATE, String IMAGE){
        ContentValues experience = new ContentValues();
        experience.put("NAME", NAME);
        experience.put("LOCATION", LOCATION);
        experience.put("CATEGORY", CATEGORY);
        experience.put("PRICE", PRICE);
        experience.put("TOTAL_BILL", TOTAL_BILL);
        experience.put("TIP_PERCENTAGE", TIP_PERCENTAGE);
        experience.put("TIME", TIME);
        experience.put("CRITERIA1", CRITERIA1);
        experience.put("CRITERIA2", CRITERIA2);
        experience.put("CRITERIA3", CRITERIA3);
        experience.put("CUSTOM", CUSTOM);
        experience.put("CUSTOM_RATE", CUSTOM_RATE);
        experience.put("IMAGE", IMAGE);
        db.insert(DB_NAME, null, experience);
        System.out.println("Record Written");
    }

    // query experiences and return a List
    private static List<Experience> get(SQLiteDatabase db){
        List<Experience> experiences = new ArrayList<>();
        boolean firstData = true;
        try {
            Cursor cursor = db.query (DB_NAME,
                    new String[] {"NAME", "LOCATION", "CATEGORY","PRICE", "TOTAL_BILL", "TIP_PERCENTAGE", "TIME", "CRITERIA1", "CRITERIA2", "CRITERIA3", "CUSTOM", "CUSTOM_RATE", "IMAGE"},
                    null,
                    null,
                    null, null, null);
            System.out.println("Size: " + cursor.getCount());
            if (cursor.moveToFirst()){
                System.out.println("Got data");
                while (firstData || cursor.moveToNext()){
                    String NAME = cursor.getString(0);
                    String LOCATION = cursor.getString(1);
                    String CATEGORY = cursor.getString(2);
                    String PRICE = cursor.getString(3);
                    String TOTAL_BILL = cursor.getString(4);
                    String TIP_PERCENTAGE = cursor.getString(5);
                    String TIME = cursor.getString(6);
                    String CRITERIA1 = cursor.getString(7);
                    String CRITERIA2 = cursor.getString(8);
                    String CRITERIA3 = cursor.getString(9);
                    String CUSTOM = cursor.getString(10);
                    String CUSTOM_RATE = cursor.getString(11);
                    String IMAGE = cursor.getString(12);
                    experiences.add(new Experience(NAME, LOCATION, CATEGORY, PRICE, TOTAL_BILL, TIP_PERCENTAGE, TIME, CRITERIA1, CRITERIA2, CRITERIA3, CUSTOM, CUSTOM_RATE, IMAGE));
                    firstData = false;
                }
            }
        } catch (SQLiteException e){
            throw(e);
        }
        return experiences;
    }

    // API: add an experience to database
    public static void addExperience(Context context, String NAME, String LOCATION, String CATEGORY, String PRICE, String TOTAL_BILL, String TIP_PERCENTAGE, String TIME, String CRITERIA1, String CRITERIA2, String CRITERIA3, String CUSTOM, String CUSTOM_RATE, String IMAGE){
        SQLiteOpenHelper dataBaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        try {
            DataBaseHelper.add(db, NAME, LOCATION, CATEGORY, PRICE, TOTAL_BILL, TIP_PERCENTAGE, TIME, CRITERIA1, CRITERIA2, CRITERIA3, CUSTOM, CUSTOM_RATE, IMAGE);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(context, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    // API: retrieve experiences from database
    public static List<Experience> getExperiences(Context context){
        SQLiteOpenHelper dataBaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        List<Experience> experiences = new ArrayList<>();
        try {
            experiences = DataBaseHelper.get(db);
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(context, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        return experiences;
    }

    // API: delete all data from database
    public static void deleteTable(Context context){
        SQLiteOpenHelper dataBaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        try {
            db.execSQL("delete from TipsDataBase");
            System.out.println("Database Table deleted");
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(context, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public static void testAdd2Experience(Context context){
        addExperience(context, "BCD", "Irvine", "Korean", "$$", "25$", "15%", "30:00", "-1%", "0%", "+1%", "Good Restroom#Not enough fish cake  ", "+1%#-1%", "http://i.imgur.com/DvpvklR.png");
        addExperience(context, "HaiDiLao", "Irvine", "Chinese", "$$$", "100$", "15%", "50:00", "-1%", "0%", "+1%", "Good restroom#Good sneak", "+1%#+1%", "http://i.imgur.com/DvpvklR.png");
    }
}
