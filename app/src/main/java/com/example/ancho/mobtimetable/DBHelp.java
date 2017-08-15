package com.example.ancho.mobtimetable;

/**
 * Created by Andrew on 25/07/2017
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelp extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "assign.db";

    public DBHelp(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_TIMETABLE = "CREATE TABLE " + EnterClass.TABLE  + "("
                + EnterClass.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + EnterClass.KEY_code + " TEXT, "
                + EnterClass.KEY_day + " TEXT, "
                + EnterClass.KEY_time + " TEXT, "
                + EnterClass.KEY_duration + " INTEGER, "
                + EnterClass.KEY_type + " TEXT, "
                + EnterClass.KEY_room + " TEXT )";

        db.execSQL(CREATE_TABLE_TIMETABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + EnterClass.TABLE);

        // Create tables again
        onCreate(db);

    }

}