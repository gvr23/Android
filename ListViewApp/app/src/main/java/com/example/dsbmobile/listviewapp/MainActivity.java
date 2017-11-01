package com.example.dsbmobile.listviewapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.lvMyListView)
    ListView lvMyListView;
    ArrayList<String> myFamily;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        myFamily = new ArrayList<String>();

        myFamily.add("Giovani");
        myFamily.add("Dany");
        myFamily.add("Virgilio");
        myFamily.add("Francesca");
        myFamily.add("Marie Pierre");

        //parameters for the adapter are, the context, the custom layout format we want, and the array where we are getting
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myFamily);

        //apply to listView
        lvMyListView.setAdapter(arrayAdapter);

        eventos();
    }

    private void eventos() {
        lvMyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //first parameter is a generic view
            //secoond paramater is the actual row that was clicked
            //third parameter is the position or number of the row that has been tapped
            //the fourth parameter is the same
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, myFamily.get(i), Toast.LENGTH_LONG).show();
            }
        });
    }
}
