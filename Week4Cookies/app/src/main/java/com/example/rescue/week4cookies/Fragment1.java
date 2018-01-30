package com.example.rescue.week4cookies;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Fragment1 extends Fragment {

    double boxCost = 5.25;
    double custTotalValue;
    double runningTotalValue;

    DecimalFormat currency = new DecimalFormat("$###,###.00");

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
    //---Inflate the layout for this fragment---
        return inflater.inflate(
                R.layout.fragment_fragment1, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // first fragment objects
        Button btnSubmit = (Button) getActivity().findViewById(R.id.btn_submit);
        Button btnClear = (Button) getActivity().findViewById(R.id.btn_clear);

        final EditText firstName = (EditText) getActivity().findViewById(R.id.editFirstName);
        final EditText lastName = (EditText) getActivity().findViewById(R.id.editLastName);
        final Spinner boxCount = (Spinner) getActivity().findViewById(R.id.spn_box_count);

        // second fragment objects
        final TextView fullName = (TextView) getActivity().findViewById(R.id.txt_full_name);
        final TextView custTotal = (TextView) getActivity().findViewById(R.id.txt_customer_total);
        final TextView runningTotal = (TextView) getActivity().findViewById(R.id.txt_running_total);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fullName.setText(firstName.getText() + " " + lastName.getText());
                //Log.d("Box Count", boxCount.getSelectedItem().toString());
                custTotal.setText(currency.format(caclCustTotal(boxCost,
                        Integer.valueOf(boxCount.getSelectedItem().toString()))));
                runningTotal.setText(currency.format(calcRunningTotal(custTotalValue)));
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                firstName.setText("");
                lastName.setText("");
                boxCount.setSelection(0, true);
                fullName.setText("");
                custTotal.setText("");

                custTotalValue = 0;
                //Log.d("cust value", String.valueOf(custTotalValue));
            }
        });

    }

    public double caclCustTotal(Double d, Integer i) {
        custTotalValue = d*i;
        return custTotalValue;
    }

    public double calcRunningTotal(Double d) {
        runningTotalValue = runningTotalValue + d;
        return runningTotalValue;
    }
}