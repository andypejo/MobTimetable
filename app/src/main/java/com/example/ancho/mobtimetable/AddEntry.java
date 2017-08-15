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


    private String[] sessionTypeArray; //array for the session types
    private String[] dayArray; //array for list of weekdays
    EditText modCode;
    Spinner weekDaySpinner;
    EditText startTime;
    EditText duration;
    Spinner sessionTypeSpinner;
    EditText room;
    private int _entry_id = 0;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        /*Gives the variables data*/
        modCode = (EditText)findViewById(R.id.modCode);
        weekDaySpinner = (Spinner) findViewById(R.id.weekDaySpinner);
        startTime = (EditText)findViewById(R.id.startTime);
        duration = (EditText)findViewById(R.id.duration);
        sessionTypeSpinner = (Spinner)findViewById(R.id.sessionTypeSpinner);
        room = (EditText)findViewById(R.id.room);

        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);


        //array data
        this.sessionTypeArray = new String[]{
                "Lecture", "Practical", "Seminar"
        };
        //adapts array to spinner
        Spinner s = (Spinner) findViewById(R.id.sessionTypeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sessionTypeArray);
        s.setAdapter(adapter);

        //array data
        this.dayArray = new String[]{
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
        };
        //adapts array to spinner
        Spinner s2 = (Spinner) findViewById(R.id.weekDaySpinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dayArray);
        s2.setAdapter(adapter2);
    }


    public void onClick(View v) {
        /*Entry Validation*/
        if( modCode.getText().toString().length() == 0 ) {      //Checks module code is entered
            modCode.setError("Please enter the Module Code");
            return;
        }
        if( startTime.getText().toString().length() == 0 ) {    //Checks start time is entered
            startTime.setError("Please enter a start time for this event");
            return;
        }
        if( Integer.parseInt(startTime.getText().toString()) < 900 || Integer.parseInt(startTime.getText().toString()) > 1700 ) {   //Checks that the start time is within given range
            startTime.setError("Start time must be between 0900 and 1700");
            return;
        }
        if( duration.getText().toString().length() == 0 ) {     //Checks duration is entered
            duration.setError("Please enter the event duration");
            return;
        }
        if( room.getText().toString().length() == 0 ) {         //Checks room is entered
            room.setError("Please enter a room");
            return;
        }

        /*Building the database object*/
        if (v == findViewById(R.id.add)) {
            SQL repo = new SQL(this);
            EnterClass u_class = new EnterClass();
            u_class.code = modCode.getText().toString();
            u_class.day = weekDaySpinner.getSelectedItem().toString();
            u_class.time = Integer.parseInt(startTime.getText().toString());
            u_class.duration = Integer.parseInt(duration.getText().toString());
            u_class.type = sessionTypeSpinner.getSelectedItem().toString();
            u_class.room = room.getText().toString();
            u_class.id = _entry_id;

            _entry_id = repo.insert(u_class);   //Inserts into database
            Intent intent = new Intent(v.getContext(), DayOfWeek.class);
            startActivity(intent);      //Starts new activity intent to open week view
            Toast.makeText(this, "New event added", Toast.LENGTH_LONG).show();    // Displays toast message for entry confirmation
        }
    }
}
