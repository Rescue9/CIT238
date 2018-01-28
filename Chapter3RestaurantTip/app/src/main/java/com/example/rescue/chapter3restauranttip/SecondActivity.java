package com.example.rescue.chapter3restauranttip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class SecondActivity extends AppCompatActivity {

    /*
     setup variables
     */
    TextView meal_cost;

    DecimalFormat currency_format = new DecimalFormat("$###,###.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        meal_cost = (TextView) findViewById(R.id.textView4);

        Bundle bundle = getIntent().getExtras();;
        meal_cost.setText(currency_format.format(bundle.getDouble("meal_cost", 0.00)));
    }

    void onClick(View view) {
        /*
         create a new intent to pass data back to main activity
         */
        Intent intent = new Intent();

        Bundle bundle = new Bundle();
    }
}
