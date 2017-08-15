package com.example.ancho.mobtimetable;

/**
 * Created by Andrew on 25/07/2017.
 */

public class EnterClass {
    // Labels table name
    public static final String TABLE = "timetable";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_code = "code";
    public static final String KEY_day = "day";
    public static final String KEY_time = "time";
    public static final String KEY_duration = "duration";
    public static final String KEY_type = "type";
    public static final String KEY_room = "room";

    // property help us to keep data
    public int id;
    public String code;
    public String day;
    public int time;
    public int duration;
    public String type;
    public String room;
}
