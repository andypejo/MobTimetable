package com.example.ancho.mobtimetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Andrew on 25/07/2017.
 */
        //for viewing days of the week
public class DayOfWeek extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_view);

        /*gets class for given days*/
        Button monday_button = (Button) findViewById(R.id.monday_button); //locate button
        monday_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReturnClass.class);
                intent.putExtra("day","Monday"); //selected day is returned from the database
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