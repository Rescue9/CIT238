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
    TextView txt_meal_cost;
    TextView txt_tip_amount;
    TextView txt_total_amount;
    LinearLayout totals_text;
    LinearLayout totals_amt;

    EditText meal_cost;

    DecimalFormat currency_format = new DecimalFormat("$###,###.00");

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
        txt_meal_cost = (TextView) findViewById(R.id.textMealCost);
        txt_tip_amount = (TextView) findViewById(R.id.textTipAmt);
        txt_total_amount = (TextView) findViewById(R.id.textTotalAmt);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                /*
                 show hidden ui elements
                 */
                calculation_totals.setVisibility(View.VISIBLE);
                totals_text.setVisibility(View.VISIBLE);
                totals_amt.setVisibility(View.VISIBLE);

                /*
                 put the correct bundle data into the textviews
                 */
                txt_meal_cost.setText(currency_format.format(data.getDoubleExtra(
                        "meal_cost", 0.00)));

                txt_tip_amount.setText(currency_format.format(data.getDoubleExtra(
                        "tip_amount", 0.00)));

                txt_total_amount.setText(currency_format.format(data.getDoubleExtra(
                        "total_amount", 0.00)));
            }

        }
    }
}
