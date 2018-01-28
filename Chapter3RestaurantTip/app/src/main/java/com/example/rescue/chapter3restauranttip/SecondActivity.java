package com.example.rescue.chapter3restauranttip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    /*
     setup variables
     */
    TextView meal_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        meal_cost = (TextView) findViewById(R.id.textView4);

        Bundle bundle = getIntent().getExtras();;
        meal_cost.setText(bundle.getString("meal_cost", "$0.00"));
    }
}
