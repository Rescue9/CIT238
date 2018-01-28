package com.example.rescue.chapter3restauranttip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    /*
     setup variables to hide UI
     */
    TextView calculation_totals;
    LinearLayout totals_text;
    LinearLayout totals_amt;

    EditText meal_cost;

    DecimalFormat currency_format = new DecimalFormat("$###,###.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         associate objects with variables
         */
        calculation_totals = (TextView) findViewById(R.id.textCaluclationTotals);
        totals_text = (LinearLayout) findViewById(R.id.layoutTotalsTxt);
        totals_amt = (LinearLayout) findViewById(R.id.layoutTotalsNum);
        meal_cost = (EditText) findViewById(R.id.editMealCost);
    }

    public void onClick(View view) {
        Intent intent = new Intent("com.example.rescue.chapter3restauranttip.SecondActivity");

        /*
         bundle meal cost into intent
         */
        Bundle extras = new Bundle();
        extras.putDouble("meal_cost", Double.valueOf(meal_cost.getText().toString()));

        intent.putExtras(extras);

        startActivityForResult(intent, 1);
    }
}
