package com.example.ancho.mobtimetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//Sample framework for the Contact List development
public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        final Toast delSel = Toast.makeText(getApplicationContext(), "Select a day and event to delete",
                             Toast.LENGTH_LONG); //message for deletion
        //to create the add button on the homepage
        Button contactEdit = (Button) findViewById(R.id.add);
        contactEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddEntry.class);
                startActivity(intent);
            }
        });
        //to create the delete button on the homepage
        Button deleteModCode = (Button) findViewById(R.id.remove);
        deleteModCode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DayOfWeek.class);
                startActivity(intent);
                delSel.show();
            }
        });
        //to create the view button on the homepage
        Button viewTime = (Button) findViewById(R.id.viewTimeTable);
        viewTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DayOfWeek.class);
                startActivity(intent);
            }
        });
    }
}