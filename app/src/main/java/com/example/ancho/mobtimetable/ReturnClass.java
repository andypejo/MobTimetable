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

        Intent intent = getIntent();
        String day = intent.getStringExtra("day");

        ArrayList<HashMap<String, String>> entryList =  repo.getEntryList(day); //assigns result
        if(entryList.size()!=0) { //on return
            ListView lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    entry_Id = (TextView) view.findViewById(R.id.entry_ID);
                    String Id = entry_Id.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(),deleteEntry.class);
                    objIndent.putExtra("entry_Id", Integer.parseInt( Id));
                    startActivity(objIndent);
                }
            });

            //Use Adapter to display the results in a list //
            ListAdapter adapter = new SimpleAdapter(this, entryList, R.layout.adapter_values,
                                  new String[] {"code", "room", "time", "id"},
                                  new int[] {R.id.text1, R.id.text2, R.id.text3, R.id.entry_ID});
            setListAdapter(adapter);
        }else{
            Toast.makeText(this,"No events today",Toast.LENGTH_LONG).show();
        }
    }
}