package com.example.ancho.mobtimetable;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class deleteEntry extends AppCompatActivity {

    private int _entry_Id=0;
    TextView code_view;
    TextView day_view;
    TextView time_view;
    TextView duration_view;
    TextView type_view;
    TextView room_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_time_table);

        final Toast delConfirm = Toast.makeText(getApplicationContext(), "Event Deleted", Toast.LENGTH_SHORT); //Event deletion toast message

        /*Assigning values to variables*/
        code_view = (TextView)findViewById(R.id.code_view);
        day_view = (TextView)findViewById(R.id.day_view);
        time_view = (TextView)findViewById(R.id.time_view);
        duration_view = (TextView)findViewById(R.id.duration_view);
        type_view = (TextView)findViewById(R.id.type_view);
        room_view = (TextView)findViewById(R.id.room_view);

        _entry_Id =0;
        Intent intent = getIntent(); //gets intent from ReturnClass class with database ID
        _entry_Id  =intent.getIntExtra("entry_Id", 0);
        SQL repo = new SQL(this);
        EnterClass entry_info = new EnterClass();
        entry_info = repo.getEntryById(_entry_Id ); //finds row from database to display

        /*Displaying the data from the database*/
        code_view.setText(String.valueOf(entry_info.code));
        day_view.setText(String.valueOf(entry_info.day));
        time_view.setText(String.valueOf(entry_info.time));
        duration_view.setText(String.valueOf(entry_info.duration));
        type_view.setText(String.valueOf(entry_info.type));
        room_view.setText(String.valueOf(entry_info.room));

        AlertDialog.Builder builder = new AlertDialog.Builder(this); //instantiating a new alert dialog

        builder.setTitle("Delete Event");
        builder.setMessage("Are you sure you want to delete this event?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() { //if you user confirms deletion on alert dialog

            public void onClick(DialogInterface dialog, int which) {
                SQL repo = new SQL(getApplicationContext()); //new SQL object
                repo.delete(_entry_Id); //delete from database
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent); //return to home
                finish();
                dialog.dismiss(); //dismiss alert dialog
                delConfirm.show(); //show deletion confirmation toast message
            }

        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() { //if user doesn't confirm deletion on alert dialog

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //closes dialog window
            }
        });

        final AlertDialog alert = builder.create(); //creates alert dialog

        Button remove = (Button) findViewById(R.id.remove);
        remove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //if delete button is clicked

                alert.show(); //shows alert dialog

            }
        });

    }


}