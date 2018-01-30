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
         get the value of the spinner
         we must strip the % from the spinner first
         */
        String tip_string = tip.getSelectedItem().toString();
        tip_string = tip_string.replace("%", "");
        //Log.d("tip_string", tip_string);
        //tip_amount = calculateTip(bundle.getDouble("meal_cost"), Double.valueOf(tip_string));
        //Log.d("tip_amount", String.valueOf(tip_amount));

        /*
         setup bundle to pass data back to main activity
         */
        Bundle returnbundle = new Bundle();
        returnbundle.putDouble("meal_cost", bundle.getDouble("meal_cost"));
        returnbundle.putDouble("tip_amount", calculateTip(bundle.getDouble("meal_cost"),
                Double.valueOf(tip_string)));
        returnbundle.putDouble("total_amount", calculateTotal(bundle.getDouble("meal_cost"),
                calculateTip(bundle.getDouble("meal_cost"), Double.valueOf(tip_string))));
        //Log.d("total_amount", String.valueOf(calculateTotal(bundle.getDouble("meal_cost"), tip_amount)));

        intent.putExtras(returnbundle);
        setResult(RESULT_OK, intent);
        finish();
    }
    public double calculateTip(Double cost, Double tip_percentage) {
        double tip_amount;

        tip_amount = cost * (tip_percentage / 100);
        return  tip_amount;
    }

    public double calculateTotal(Double cost, Double tip) {
        double total_amount;

        total_amount = cost + tip;
        return total_amount;
    }
}
