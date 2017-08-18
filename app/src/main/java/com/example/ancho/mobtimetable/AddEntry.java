package com.example.ancho.mobtimetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEntry extends AppCompatActivity implements android.view.View.OnClickListener {


    EditText modCode;
    EditText startTime;
    EditText duration;
    EditText sessionType;
    EditText daySelect;
    EditText room;
    private int _entry_id = 0;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        /*Gives the variables data*/
        modCode = (EditText)findViewById(R.id.modCode);
        daySelect = (EditText) findViewById(R.id.daySelect);
        startTime = (EditText)findViewById(R.id.startTime);
        duration = (EditText)findViewById(R.id.duration);
        room = (EditText)findViewById(R.id.room);
        sessionType = (EditText)findViewById(R.id.sessionType);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);
    }

    public void onClick(View v) {
        /*Entry Validation*/
        if( modCode.getText().toString().length() == 0 ) {      //Checks module code is entered
            modCode.setError("Enter correct  Module Code");
            return;
        }
        if( startTime.getText().toString().length() == 0 ) {    //Checks start time is entered
            startTime.setError("Please enter start time");
            return;
        }
        if( Integer.parseInt(startTime.getText().toString()) < 900 || Integer.parseInt(startTime.getText().toString()) > 1700 ) {   //Checks that the start time is within given range
            startTime.setError("Enter time between 0900 - 1700");
            return;
        }
        if( duration.getText().toString().length() == 0 ) {     //Checks duration is entered
            duration.setError("Enter the duration of the event");
            return;
        }
        if( room.getText().toString().length() == 0 ) {         //Checks room is entered
            room.setError("Enter a room");
            return;
        }
        if (!(sessionType.getText().toString().equals("lecture") //Checks type of session is correct
                || sessionType.getText().toString().equals("seminar")
                || sessionType.getText().toString().equals("practical"))){
              sessionType.setError("Please enter the type of session (lower case)");
            return;
        }
        if (!(daySelect.getText().toString().equals("Monday") //Checks type of session is correct
                || daySelect.getText().toString().equals("Tuesday")
                || daySelect.getText().toString().equals("Wednesday"))
                || daySelect.getText().toString().equals("Thursday")
                || daySelect.getText().toString().equals("Friday") ){
            daySelect.setError("Please enter day of week(first letter upper case)");
            return;
        }
        /*Building the database object*/
        if (v == findViewById(R.id.add)) {
            SQL repo = new SQL(this);
            EnterClass u_class = new EnterClass();
            u_class.code = modCode.getText().toString();
            u_class.day = daySelect.getText().toString();
            u_class.time = Integer.parseInt(startTime.getText().toString());
            u_class.duration = Integer.parseInt(duration.getText().toString());
            u_class.type = sessionType.getText().toString();
            u_class.room = room.getText().toString();
            u_class.id = _entry_id;

            _entry_id = repo.insert(u_class);   //Inserts into database
            Intent intent = new Intent(v.getContext(), DayOfWeek.class);
            startActivity(intent);      //Starts new activity intent to open week view
            Toast.makeText(this, "Event added", Toast.LENGTH_LONG).show();    // Displays toast message for entry confirmation
        }
    }
}