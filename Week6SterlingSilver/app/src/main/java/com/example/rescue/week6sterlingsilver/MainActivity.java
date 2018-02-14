package com.example.rescue.week6sterlingsilver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    /* declare objects */
    TextView date;
    EditText firstName;
    EditText lastName;
    Spinner itemsPurchased;
    Spinner cardType;
    Spinner grouponCode;
    EditText cardNumber;

    double itemPrice = 45.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* instantiate objects */
        firstName = (EditText) findViewById(R.id.edtFirstName);
        lastName = (EditText) findViewById(R.id.edtLastName);
        itemsPurchased = (Spinner) findViewById(R.id.spnNumberItems);
        cardType = (Spinner) findViewById(R.id.spnCardType);
        grouponCode = (Spinner) findViewById(R.id.spnGrouponCode);
        cardNumber = (EditText) findViewById(R.id.edtCardNumber);


        /* automatically populate date textview with current date */
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();

        date = (TextView) findViewById(R.id.date);
        date.setText(simpleDateFormat.format(calendar.getTime()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickSubmit(View view) {
        String wholeName;
        String last4;


        // make sure the first and last name have more than 1 character
        if (firstNameLength() && lastNameLength()) {
            wholeName = firstName.getText() + " " + lastName.getText();
        } else {
            Toast.makeText(this, "Your First Name and Last Name must be longer than 1 character", Toast.LENGTH_LONG).show();
            return;
        }

        // make sure the card number contains exactly 16 digits
        if (cardNumberLength()) {
            last4 = TextUtils.substring(cardNumber.getText().toString(), cardNumber.length() - 4, cardNumber.length());
        } else {
            Toast.makeText(this, "Your Card Number must be exactly 16 numbers in length", Toast.LENGTH_LONG).show();
            return;
        }

        calculatePurchase(Integer.valueOf(itemsPurchased.getSelectedItem().toString()));

    }

    public void onClickClear(View view) {
        firstName.setText("");
        lastName.setText("");
        itemsPurchased.setSelection(0, true);
        cardType.setSelection(0, true);
        grouponCode.setSelection(0, true);
        cardNumber.setText("");
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

    public double calculatePurchase(int numItemsPurchased){
        int discountItems;
        double grouponItemPrice;
        double regularPrice;
        double totalPrice = 0;

        /* perform check to make sure that 2 items are selected if groupon code is Silver2018 */
        if (grouponCode.getSelectedItemPosition() == 2 && numItemsPurchased < 2){
            Toast.makeText(this,
                    "You must select two or more items when using this Groupon code",
                    Toast.LENGTH_LONG).show();
            return 0;
        }


        /* calculate the total based upon the groupon code and number of items purchased */
        switch (grouponCode.getSelectedItemPosition()) {
            case 0: totalPrice = itemPrice * numItemsPurchased;
                Log.d("Total Price", String.valueOf(totalPrice));
                break;

            case 1: discountItems = 1;
                    numItemsPurchased -= discountItems;
                    grouponItemPrice = 30.00;
                    regularPrice = itemPrice * numItemsPurchased;
                    totalPrice = grouponItemPrice + regularPrice;
                Log.d("Groupon Item Price", String.valueOf(grouponItemPrice));
                Log.d("Regular Price", String.valueOf(regularPrice));
                Log.d("Total Price", String.valueOf(totalPrice));
                break;

            case 2: discountItems = 2;
                    numItemsPurchased -= discountItems;
                    grouponItemPrice = 55.00;
                    regularPrice = itemPrice * numItemsPurchased;
                totalPrice = grouponItemPrice + regularPrice;
                Log.d("Groupon Item Price", String.valueOf(grouponItemPrice));
                Log.d("Regular Price", String.valueOf(regularPrice));
                Log.d("Total Price", String.valueOf(totalPrice));
                break;
        }
        Log.d("calculatePurchase()", String.valueOf(totalPrice));
        return totalPrice;
    }

}
