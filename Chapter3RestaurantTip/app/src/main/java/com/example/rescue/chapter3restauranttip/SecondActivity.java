package com.example.rescue.chapter3restauranttip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class SecondActivity extends AppCompatActivity {

    /*
     setup variables
     */
    TextView meal_cost;
    Spinner tip;

    DecimalFormat currency_format = new DecimalFormat("$###,###.##");

    Bundle bundle;

    Double tip_amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        meal_cost = (TextView) findViewById(R.id.textView4);
        tip = (Spinner) findViewById(R.id.spinner);

        bundle = getIntent().getExtras();

        meal_cost.setText(currency_format.format(bundle.getDouble("meal_cost", 0.00)));
    }

    void onClick(View view) {
        /*
         create a new intent to pass data back to main activity
         */
        Intent intent = new Intent();

        /*
         get the value of the spinner to pass back to the main activity
         we must strip the % from the spinner first
         */
        String tip_string = tip.getSelectedItem().toString();
        tip_string = tip_string.replace("%", "");
        //Log.d("tip_string", tip_string);
        tip_amount = Double.valueOf(tip_string) / 100;
        //Log.d("tip_amount", String.valueOf(tip_amount));

        Bundle returnbundle = new Bundle();
        returnbundle.putDouble("meal_cost", bundle.getDouble("meal_cost"));
        returnbundle.putDouble("tip_amount", tip_amount);
        returnbundle.putDouble("total_amount", calculateTotal(bundle.getDouble("meal_cost"), tip_amount));
        //Log.d("total_amount", String.valueOf(calculateTotal(bundle.getDouble("meal_cost"), tip_amount)));
    }

    public double calculateTotal(Double cost, Double tip) {
        double total_amount;

        total_amount = (cost * tip) + cost;
        return total_amount;
    }
}
