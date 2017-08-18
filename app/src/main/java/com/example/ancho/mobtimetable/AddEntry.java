package com.example.ancho.mobtimetable;

import android.content.Intent; //important statements
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEntry extends AppCompatActivity implements android.view.View.OnClickListener {

    //variables
    EditText moduleCode;
    EditText eventTime;
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
        moduleCode = (EditText)findViewById(R.id.modCode);
        daySelect = (EditText) findViewById(R.id.daySelect);
        eventTime = (EditText)findViewById(R.id.startTime);
        duration = (EditText)findViewById(R.id.duration);
        room = (EditText)findViewById(R.id.room);
        sessionType = (EditText)findViewById(R.id.sessionType);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);
    }

    public void onClick(View v) {
        /*Validation for inputs*/
        if( moduleCode.getText().toString().length() == 0 ) {      //Checks module field isn't empty
            moduleCode.setError("Enter correct  Module Code");
            return;
        }
        if( eventTime.getText().toString().length() == 0 ) {    //Checks start time isn't empty
            eventTime.setError("Please enter start time");
            return;
        }
        if( Integer.parseInt(eventTime.getText().toString()) < 900  //Checks time is between 0900 - 1700
                || Integer.parseInt(eventTime.getText().toString()) > 1700 ) {
            eventTime.setError("Enter time between 0900 - 1700");
            return;
        }
        if( duration.getText().toString().length() == 0 ) {     //Checks duration isn't empty
            duration.setError("Enter the duration of the event");
            return;
        }
        if( room.getText().toString().length() == 0 ) {         //Checks room isn't empty
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
        /*creating objects*/
        if (v == findViewById(R.id.add)) {
            SQL repo = new SQL(this);
            EnterClass class_data = new EnterClass();
            class_data.code = moduleCode.getText().toString();
            class_data.day = daySelect.getText().toString();
            class_data.time = Integer.parseInt(eventTime.getText().toString());
            class_data.duration = Integer.parseInt(duration.getText().toString());
            class_data.type = sessionType.getText().toString();
            class_data.room = room.getText().toString();
            class_data.id = _entry_id;

            _entry_id = repo.insert(class_data);   //adds to database
            Intent intent = new Intent(v.getContext(), DayOfWeek.class);
            startActivity(intent);      //Starts new activity
            Toast.makeText(this, "Event added", Toast.LENGTH_LONG).show();    // Displays confirmation message
        }
    }
}