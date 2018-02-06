package com.example.rescue.week5souvenirs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    CheckBox penguin;
    CheckBox tiger;
    CheckBox bear;
    CheckBox lion;
    Spinner creditCard;
    EditText cardNumber;
    Spinner date;
    Button submit;
    Button clear;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = (EditText) findViewById(R.id.edtFirstName);
        lastName = (EditText) findViewById(R.id.edtLastName);
        penguin = (CheckBox) findViewById(R.id.chkPenguin);
        tiger = (CheckBox) findViewById(R.id.chkTiger);
        bear = (CheckBox) findViewById(R.id.chkBear);
        lion = (CheckBox) findViewById(R.id.chkLion);
        creditCard = (Spinner) findViewById(R.id.spnCreditCard);
        cardNumber = (EditText) findViewById(R.id.edtCardNumber);
        date = (Spinner) findViewById(R.id.spnDate);
        submit = (Button) findViewById(R.id.btnSubmit);
        clear = (Button) findViewById(R.id.btnClear);
        output = (TextView) findViewById(R.id.txtOutput);

    }

    public void onClickClear(View view) {
        firstName.setText("");
        lastName.setText("");
        penguin.setChecked(false);
        tiger.setChecked(false);
        lion.setChecked(false);
        bear.setChecked(false);
        creditCard.setSelection(0, true);
        cardNumber.setText("");
        date.setSelection(0, true);
        output.setText("");
    }
}
