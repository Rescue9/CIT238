package com.example.rescue.week6sterlingsilver;

/*
    Name: Andrew Buskov
    Class: CIT 238
    Date: 0/15/2018
    Purpose: To create an application that tracks sales of sterling silver items.
        This app should display a receipt to the current customer, as well as
        track the daily customer total and daily dollar total sales.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class DisplayTotalsActivity extends AppCompatActivity {

    /* declare objects */
    Bundle bundle;

    TextView customersServed;
    TextView dailySalesTotal;

    /* create a format for currency strings */
    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);

    /**
     * Creates the activity, sets various ActionBar options, instantiates the bundle object, and
     * sets view object strings to display data.
     * @param savedInstanceState the bundle containing the previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_totals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        bundle = getIntent().getExtras();

        customersServed = (TextView) findViewById(R.id.txtCustomersServed);
        dailySalesTotal = (TextView) findViewById(R.id.txtDailySalesTotal);

        customersServed.setText(String.valueOf(bundle.getInt("customersServed")));
        dailySalesTotal.setText(currency.format(bundle.getDouble("dailySalesTotal")));

    }

    /**
     * This method overrides the standard actions performed when the up/home button on the
     * ActionBar is pressed. The standard action is to go home and clear all the data that was
     * input into the UI. This method changes that action to mimic the back button being pressed,
     * which preserves the UI input.
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
