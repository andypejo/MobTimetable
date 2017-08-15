package com.example.ancho.mobtimetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Andrew on 25/07/2017.
 */

public class DayOfWeek extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_view);

        /*Starts the ReturnClass class for the given day for each button*/
        Button monday_button = (Button) findViewById(R.id.monday_button); //find button
        monday_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReturnClass.class); //New intent for ReturnClass class
                intent.putExtra("day","Monday"); //Passes the given day to ReturnClass so only that day is returned from the database
                startActivity(intent);
            }
        });

        Button tuesday_button = (Button) findViewById(R.id.tuesday_button);
        tuesday_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReturnClass.class);
                intent.putExtra("day","Tuesday");
                startActivity(intent);
            }
        });

        Button wednesday_button = (Button) findViewById(R.id.wednesday_button);
        wednesday_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReturnClass.class);
                intent.putExtra("day","Wednesday");
                startActivity(intent);
            }
        });

        Button thursday_button = (Button) findViewById(R.id.thursday_button);
        thursday_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReturnClass.class);
                intent.putExtra("day","Thursday");
                startActivity(intent);
            }
        });

        Button friday_button = (Button) findViewById(R.id.friday_button);
        friday_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReturnClass.class);
                intent.putExtra("day","Friday");
                startActivity(intent);
            }
        });
    }
}