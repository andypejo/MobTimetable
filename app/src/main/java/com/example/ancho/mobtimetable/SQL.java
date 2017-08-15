package com.example.ancho.mobtimetable;

/**
 * Created by Andrew on 25/07/2017
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


public class SQL {
    private DBHelp dbHelp;

    public SQL(Context context) {
        dbHelp = new DBHelp(context);
    }

    public int insert (EnterClass u_class) {

        //Open connection to write data
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EnterClass.KEY_code, u_class.code);
        values.put(EnterClass.KEY_day, u_class.day);
        values.put(EnterClass.KEY_time, u_class.time);
        values.put(EnterClass.KEY_duration, u_class.duration);
        values.put(EnterClass.KEY_type, u_class.type);
        values.put(EnterClass.KEY_room, u_class.room);

        // Inserting Row
        long u_class_Id = db.insert(EnterClass.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) u_class_Id;
    }

    public void delete (int u_class_Id) {

        SQLiteDatabase db = dbHelp.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(EnterClass.TABLE, EnterClass.KEY_ID + "= ?", new String[]{String.valueOf(u_class_Id)});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getEntryList(String day) {
        //Open connection to read only
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        String selectQuery = "SELECT  " +
                EnterClass.KEY_ID + "," +
                EnterClass.KEY_code + "," +
                EnterClass.KEY_day + "," +
                EnterClass.KEY_time + "," +
                EnterClass.KEY_duration + "," +
                EnterClass.KEY_type + "," +
                EnterClass.KEY_room +
                " FROM " + EnterClass.TABLE +
                " WHERE day ='" + day + "' ORDER BY time ASC";


        ArrayList<HashMap<String, String>> entryList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            if (!cursor.isAfterLast()) {
                do {
                    HashMap<String, String> record = new HashMap<String, String>();
                    record.put("time", cursor.getString(cursor.getColumnIndex(EnterClass.KEY_time)));
                    record.put("code", cursor.getString(cursor.getColumnIndex(EnterClass.KEY_code)));
                    record.put("room", cursor.getString(cursor.getColumnIndex(EnterClass.KEY_room)));
                    record.put("id", cursor.getString(cursor.getColumnIndex(EnterClass.KEY_ID)));
                    entryList.add(record);

                } while (cursor.moveToNext());
            }
            else {
                Log.e("Error: ", "No entries");
            }
        }

        cursor.close();
        db.close();
        return entryList;

    }

    public EnterClass getEntryById (int Id) {

        /*Returns database result for given ID and sorts by ascending time order for display in ListView*/
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        String selectQuery = "SELECT  " +
                EnterClass.KEY_ID + "," +
                EnterClass.KEY_code + "," +
                EnterClass.KEY_day + "," +
                EnterClass.KEY_time + "," +
                EnterClass.KEY_duration + "," +
                EnterClass.KEY_type + "," +
                EnterClass.KEY_room +
                " FROM " + EnterClass.TABLE
                + " WHERE " +
                EnterClass.KEY_ID + "=?";

        int iCount = 0;
        EnterClass u_class = new EnterClass();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});

        if (cursor.moveToFirst()) {
            if (!cursor.isAfterLast()) {
                do {
                    u_class.id = cursor.getInt(cursor.getColumnIndex(EnterClass.KEY_ID));
                    u_class.code = cursor.getString(cursor.getColumnIndex(EnterClass.KEY_code));
                    u_class.day = cursor.getString(cursor.getColumnIndex(EnterClass.KEY_day));
                    u_class.time = cursor.getInt(cursor.getColumnIndex(EnterClass.KEY_time));
                    u_class.duration = cursor.getInt(cursor.getColumnIndex(EnterClass.KEY_duration));
                    u_class.type = cursor.getString(cursor.getColumnIndex(EnterClass.KEY_type));
                    u_class.room = cursor.getString(cursor.getColumnIndex(EnterClass.KEY_room));

                } while (cursor.moveToNext());
            }
        }

        cursor.close();
        db.close();
        return u_class;
    }
}