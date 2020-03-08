package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.model.Experience;
/************************************************************************************************
 * Database System that provides API for storing all the experience records for further usages
 ************************************************************************************************/
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "TipsDataBase";
    public static final int DB_VERSION = 3;

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
                + "SERVICE TEXT, "
                + "TIMELINESS TEXT, "
                + "FOOD TEXT, "
                + "CUSTOM TEXT, "
                + "CUSTOM_RATE TEXT, "
                + "IMAGE TEXT);");
    }

    // add an experience to database (inner logic)
    private static void add(SQLiteDatabase db, Experience experienceToAdd){
        ContentValues experience = new ContentValues();
        experience.put("NAME", experienceToAdd.getName());
        experience.put("LOCATION", experienceToAdd.getLocation());
        experience.put("CATEGORY", experienceToAdd.getCategory());
        experience.put("PRICE", experienceToAdd.getPrice());
        experience.put("TOTAL_BILL", experienceToAdd.getTotalBill());
        experience.put("TIP_PERCENTAGE", experienceToAdd.getTipPercentage());
        experience.put("TIME", experienceToAdd.getTime());
        experience.put("SERVICE", experienceToAdd.getService());
        experience.put("TIMELINESS", experienceToAdd.getTimeliness());
        experience.put("FOOD", experienceToAdd.getFood());
        experience.put("CUSTOM", experienceToAdd.getCustom());
        experience.put("CUSTOM_RATE", experienceToAdd.getCUSTOM_RATE());
        experience.put("IMAGE", experienceToAdd.getImage());
        db.insert(DB_NAME, null, experience);
        System.out.println("Record Written");
    }

    // query experiences and return a List
    private static List<Experience> get(SQLiteDatabase db){
        List<Experience> experiences = new ArrayList<>();
        boolean firstData = true;
        try {
            Cursor cursor = db.query (DB_NAME,
                    new String[] {"NAME", "LOCATION", "CATEGORY","PRICE", "TOTAL_BILL", "TIP_PERCENTAGE", "TIME", "SERVICE", "TIMELINESS", "FOOD", "CUSTOM", "CUSTOM_RATE", "IMAGE"},
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
                    String SERVICE = cursor.getString(7);
                    String TIMELINESS = cursor.getString(8);
                    String FOOD = cursor.getString(9);
                    String CUSTOM = cursor.getString(10);
                    String CUSTOM_RATE = cursor.getString(11);
                    String IMAGE = cursor.getString(12);
                    Experience experience = new Experience.ExperienceBuilder(NAME)
                            .location(LOCATION)
                            .category(CATEGORY)
                            .price(PRICE)
                            .totalBill(TOTAL_BILL)
                            .tipPercentage(TIP_PERCENTAGE)
                            .time(TIME)
                            .service(SERVICE)
                            .timeliness(TIMELINESS)
                            .food(FOOD)
                            .custom(CUSTOM)
                            .customRate(CUSTOM_RATE)
                            .image(IMAGE)
                            .build();
                    experiences.add(experience);
                    firstData = false;
                }
            }
        } catch (SQLiteException e){
            throw(e);
        }
        return experiences;
    }

    // API: add an experience to database
    public static void addExperience(Context context, Experience experience){
        SQLiteOpenHelper dataBaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        try {
            DataBaseHelper.add(db, experience);
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
        Experience experience1 = new Experience.ExperienceBuilder("BCD")
                .location("Irvine")
                .category("Korean")
                .price("$$")
                .totalBill("25$")
                .tipPercentage("15$")
                .time("30:00")
                .service("-1%")
                .timeliness("0%")
                .food("+1%")
                .custom("Good Restroom#Not enough fish cake  ")
                .customRate("+1%#-1%")
                .image("https://i.imgur.com/DvpvklR.png")
                .build();
        Experience experience2 = new Experience.ExperienceBuilder("HaiDiLao")
                .location("Irvine")
                .category("Chinese")
                .price("$$$")
                .totalBill("100$")
                .tipPercentage("17$")
                .time("50:00")
                .service("-1%")
                .timeliness("0%")
                .food("+1%")
                .custom("Good restroom#Good sneak")
                .customRate("+1%#+1%")
                .image("https://i.imgur.com/DvpvklR.png")
                .build();
        Experience experience3 = new Experience.ExperienceBuilder("HaiDiLao")
                .location("Irvine")
                .category("Chinese")
                .price("$$$")
                .totalBill("100$")
                .tipPercentage("17$")
                .time("50:00")
                .service("-1%")
                .timeliness("0%")
                .food("+1%")
                .custom("")
                .customRate("")
                .image("https://i.imgur.com/DvpvklR.png")
                .build();
        addExperience(context, experience1);
        addExperience(context, experience2);
        addExperience(context, experience3);
    }
}
