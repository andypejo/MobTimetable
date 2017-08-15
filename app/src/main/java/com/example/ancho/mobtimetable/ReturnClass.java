package com.example.ancho.mobtimetable;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ReturnClass extends ListActivity {

    TextView entry_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_time_table);
        SQL repo = new SQL(this); //new SQL object

        Intent intent = getIntent(); // gets the previously created intent
        String day = intent.getStringExtra("day");

        ArrayList<HashMap<String, String>> entryList =  repo.getEntryList(day); //assigns result(s) to map
        if(entryList.size()!=0) { //if a results is returned
            ListView lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //on click method for items in ListView
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    entry_Id = (TextView) view.findViewById(R.id.entry_ID);     //gets the ID which is hidden in the XML
                    String Id = entry_Id.getText().toString();  //assigns ID to a string variable
                    Intent objIndent = new Intent(getApplicationContext(),deleteEntry.class); //new intent to activate delete entry
                    objIndent.putExtra("entry_Id", Integer.parseInt( Id));  //passes ID in order to display the entry
                    startActivity(objIndent);
                }
            });

            /*Uses SimpleAdapter to display the results in the list view. Only code, room and time are displayed to minimise clutter and ID is a hidden value used for intent in the above method*/
            ListAdapter adapter = new SimpleAdapter(this, entryList, R.layout.helper, new String[] {"code", "room", "time", "id"}, new int[] {R.id.text1, R.id.text2, R.id.text3, R.id.entry_ID});
            setListAdapter(adapter);
        }else{ //if no results
            Toast.makeText(this,"No events today",Toast.LENGTH_LONG).show(); //toast message
        }

    }
}