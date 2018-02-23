package com.example.rescue.week7finalexams;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    String[] courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* setup the actionbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_action_examination);

        /* setup the listview */


        /* setup the autocompletetextview */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.test_location));

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(
                R.id.actLocationList);

        autoCompleteTextView.setHint(getResources().getString(R.string.enter_location));
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
    }

}
