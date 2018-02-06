package com.example.rescue.week5souvenirs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    Spinner creditCard;
    EditText cardNumber;
    Spinner date;
    Button submit;
    Button clear;
    TextView output;
    String itemsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = (EditText) findViewById(R.id.edtFirstName);
        lastName = (EditText) findViewById(R.id.edtLastName);
        checkBox1 = (CheckBox) findViewById(R.id.chkPenguin);
        checkBox2 = (CheckBox) findViewById(R.id.chkTiger);
        checkBox3 = (CheckBox) findViewById(R.id.chkBear);
        checkBox4 = (CheckBox) findViewById(R.id.chkLion);
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
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
        creditCard.setSelection(0, true);
        cardNumber.setText("");
        date.setSelection(0, true);
        output.setText("");
    }

    public void onClickSubmit(View view) {
        String wholeName;
        String last4;
        itemsSelected = "";

        // make sure the first and last name have more than 1 character
        if (firstNameLength() && lastNameLength()){
            wholeName = firstName.getText() + " " + lastName.getText();
        } else {
            Toast.makeText(this, "Your First Name and Last Name must be longer than 1 character", Toast.LENGTH_LONG).show();
            output.setText("");
            return;
        }

        // make sure the card number contains exactly 16 digits
        if (cardNumberLength()) {
            last4 = TextUtils.substring(cardNumber.getText().toString(), cardNumber.length()-4, cardNumber.length());
        } else {
            Toast.makeText(this, "Your Card Number must be exactly 16 numbers in length", Toast.LENGTH_LONG).show();
            output.setText("");
            return;
        }

        // make sure that at least one checkbox is checked
        if (checkCheckBoxes() == 0){
            Toast.makeText(this, "You must select at least one item with checkbox", Toast.LENGTH_LONG).show();
            output.setText("");
            return;
        } else {
            int boxesChecked = checkCheckBoxes();
            if (checkBox1.isChecked()){
                itemsSelected += checkBox1.getText();
                if (boxesChecked > 1){
                    itemsSelected += " and ";
                    boxesChecked -= 1;
                }
            }

            if (checkBox2.isChecked()){
                itemsSelected += checkBox2.getText();
                if (boxesChecked > 1){
                    itemsSelected += " and ";
                    boxesChecked -= 1;
                }
            }

            if (checkBox3.isChecked()){
                itemsSelected += checkBox3.getText();
                if (boxesChecked > 1){
                    itemsSelected += " and ";
                    boxesChecked -= 1;
                }
            }

            if (checkBox4.isChecked()){
                itemsSelected += checkBox4.getText();
            }
        }


        String outputString = "Receipt for " + wholeName + "\n" +
                "Purchased on " + date.getSelectedItem().toString() + "\n" +
                "Last 4 digits of " + creditCard.getSelectedItem().toString() + " credit card: " +
                last4 + "\n" + "Toys Purchased: " + itemsSelected + "\n" +
                "Total Paid: " + "WORK THIS OUT";

        output.setText(outputString);
    }

    public boolean firstNameLength() {
        if (firstName.length() < 2) {
            return false;
        } else {
            return true;
        }
    }

    public boolean lastNameLength() {
        if (lastName.length() < 2) {
            return false;
        } else {
            return true;
        }
    }

    public boolean cardNumberLength() {
        if (cardNumber.length() == 16) {
            return true;
        } else {
            return false;
        }
    }

    public int checkCheckBoxes() {
        int checked = 0;
        if (checkBox1.isChecked())
            checked += 1;
        if (checkBox2.isChecked())
            checked += 1;
        if (checkBox3.isChecked())
            checked += 1;
        if (checkBox4.isChecked())
            checked += 1;

        return checked;
    }
}
