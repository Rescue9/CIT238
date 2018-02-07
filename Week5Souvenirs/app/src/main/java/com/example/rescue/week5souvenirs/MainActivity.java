package com.example.rescue.week5souvenirs;
/**
 * Author: Andrew Buskov
 * Date: 2/7/2018
 * Class: CIT 238 (Android)
 * Purpose: To create an application that allows the sale of souvenirs
 *   during the 2018 Olympic Games
 */

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

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    /* instantiate variables to be used throughout the class */
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
    int totalBoxesChecked;
    final double costEachItem = 31.95;
    DecimalFormat currency = new DecimalFormat("$###,###.00");

    /**
     * Create the instance and declare variables
     * @param savedInstanceState Used for saving data should the instance need to be
     *                           recreated. For Ex.; during orientation changes
     */
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

    /**
     * Method is called when the Clear button is clicked
     * All views are reset to default values
     * @param view
     */
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

    /**
     * Method is called when the Submit button is clicked
     * Various error checking is performed
     * Output information, or receipt, is sent to TextView
     * @param view
     */
    public void onClickSubmit(View view) {
        String wholeName;
        String last4;
        itemsSelected = "";

        /* make sure the first and last name have more than 1 character */
        if (firstNameLength() && lastNameLength()){
            wholeName = firstName.getText() + " " + lastName.getText();
        } else {
            Toast.makeText(this, "Your First Name and Last Name must be longer than 1 character", Toast.LENGTH_LONG).show();
            output.setText("");
            return;
        }

        /* make sure the card number contains exactly 16 digits */
        if (cardNumberLength()) {
            last4 = TextUtils.substring(cardNumber.getText().toString(), cardNumber.length()-4, cardNumber.length());
        } else {
            Toast.makeText(this, "Your Card Number must be exactly 16 numbers in length", Toast.LENGTH_LONG).show();
            output.setText("");
            return;
        }

        /* make sure that at least one checkbox is checked and format string for toys purchased */
        if (checkCheckBoxes() == 0){
            Toast.makeText(this, "You must select at least one item with checkbox", Toast.LENGTH_LONG).show();
            output.setText("");
            return;
        } else {
            totalBoxesChecked = checkCheckBoxes();
            int boxesStillChecked = checkCheckBoxes();
            if (checkBox1.isChecked()){
                itemsSelected += checkBox1.getText();
                if (boxesStillChecked > 1){
                    itemsSelected += " and ";
                    boxesStillChecked -= 1;
                }
            }

            if (checkBox2.isChecked()){
                itemsSelected += checkBox2.getText();
                if (boxesStillChecked > 1){
                    itemsSelected += " and ";
                    boxesStillChecked -= 1;
                }
            }

            if (checkBox3.isChecked()){
                itemsSelected += checkBox3.getText();
                if (boxesStillChecked > 1){
                    itemsSelected += " and ";
                    boxesStillChecked -= 1;
                }
            }

            if (checkBox4.isChecked()){
                itemsSelected += checkBox4.getText();
            }
        }

        /* make output string for information box */
        String outputString = "Receipt for " + wholeName + "\n" +
                "Purchased on " + date.getSelectedItem().toString() + "\n" +
                "Last 4 digits of " + creditCard.getSelectedItem().toString() + " credit card: " +
                last4 + "\n" + "Toys Purchased: " + itemsSelected + "\n" +
                "Total Paid: " + currency.format(totalCost(totalBoxesChecked));

        output.setText(outputString);
    }

    /**
     * Method checks to make sure first name is longer than two characters
     * @return true or false
     */
    public boolean firstNameLength() {
        if (firstName.length() < 2) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method checks to make sure last name is longer than two characters
     * @return true or false
     */
    public boolean lastNameLength() {
        if (lastName.length() < 2) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method checks to make sure that credit card number is exactly 16 numbers in length
     * @return true or false
     */
    public boolean cardNumberLength() {
        if (cardNumber.length() == 16) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method calculates the number of checkboxes that are checked
     * @return number of checked checkboxes
     */
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

    /**
     *Method calculates the total cost of the order by multiplying the total checked boxes *
     * the cost of each item
     * @param totalBoxesChecked the number of items checked
     * @return the total cost for the order
     */
    public double totalCost(int totalBoxesChecked) {
        return costEachItem * totalBoxesChecked;
    }
}
