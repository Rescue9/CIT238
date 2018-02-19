package com.example.rescue.week6sterlingsilver;

/*
    Name: Andrew Buskov
    Class: CIT 238
    Date: 0/15/2018
    Purpose: To create an application that tracks sales of sterling silver items.
        This app should display a receipt to the current customer, as well as
        track the daily customer total and daily dollar total sales.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    /* declare and instantiate objects */
    TextView date;
    EditText firstName;
    EditText lastName;
    Spinner itemsPurchased;
    Spinner cardType;
    Spinner grouponCode;
    EditText cardNumber;
    TextView custTotal;

    double itemPrice = 45.00;
    int totalCustomers = 0;
    double subtotal = 0.00;
    double tax = 0.00;
    double total = 0.00;
    double dailyTotal = 0.00;

    DecimalFormat currency = new DecimalFormat("$###,###.00");


    String wholeName;
    String last4;

    Bundle bundle = new Bundle();

    Boolean submitted = false;

    /**
     * Creates the layout, assigns objects to various variables, and starts the activity
     * for user interaction
     * @param savedInstanceState the bundle containing the previously saved state
     */
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
        custTotal = (TextView) findViewById(R.id.txtCustTotal);


        /* automatically populate date TextView with current date */
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();

        date = (TextView) findViewById(R.id.date);
        date.setText(simpleDateFormat.format(calendar.getTime()));

    }

    /**
     * Generates the menu on the action bar.
     * @param menu the menu object that was pressed
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles click events for various menu items. Some error checking is provided as well
     * so that intents don't start unless conditions are met.
     * @param item the specific menu item clicked
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.display_receipt) {
            if (submitted == true && !cardNumber.getText().toString().equals("")) {
                Intent intent = new Intent(this, DisplayReceiptActivity.class);
                bundleExtras();
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.submit_before_receipt,
                        Toast.LENGTH_LONG).show();
            }
        }

        if (id == R.id.display_totals) {
            Intent intent = new Intent(this, DisplayTotalsActivity.class);
            bundleExtras();
            intent.putExtras(bundle);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is called when the Submit button in the main activity layout is clicked.
     * Error checking is implemented to check various EditText fields for content.
     * @param view the button object
     */
    public void onClickSubmit(View view) {

        // make sure the first and last name have more than 1 character
        if (firstNameLength() && lastNameLength()) {
            wholeName = firstName.getText() + " " + lastName.getText();
        } else {
            Toast.makeText(this, R.string.name_character_length,
                    Toast.LENGTH_LONG).show();
            return;
        }

        // make sure the card number contains exactly 16 digits
        if (cardNumberLength()) {
            last4 = TextUtils.substring(cardNumber.getText().toString(),
                    cardNumber.length() - 4, cardNumber.length());
        } else {
            Toast.makeText(this, R.string.card_number_length,
                    Toast.LENGTH_LONG).show();
            return;
        }

        subtotal = calculatePurchase(Integer.valueOf(itemsPurchased.getSelectedItem().toString()));
        tax = calculateTax(subtotal);
        total = subtotal + tax;
        dailyTotal += total;

        custTotal.setText(currency.format(total));

        //Log.d("subtotal", String.valueOf(subtotal));
        //Log.d("tax", String.valueOf(tax));
        //Log.d("total", String.valueOf(total));
        //Log.d("dailyTotal", String.valueOf(dailyTotal));


        totalCustomers += 1;
        //Log.d("Total Customers", String.valueOf(totalCustomers));

        submitted = true;

    }

    /**
     * This method is called when the Clear button in the main activity layout is clicked. All
     * EditText fields are cleared and spinners are returned to their default values.
     * @param view
     */
    public void onClickClear(View view) {
        firstName.setText("");
        lastName.setText("");
        itemsPurchased.setSelection(0, true);
        cardType.setSelection(0, true);
        grouponCode.setSelection(0, true);
        cardNumber.setText("");
        custTotal.setText(R.string.zero_dollar_placeholder);

        firstName.requestFocus();
    }

    /**
     * Ensures that the first name has a character value greater than 1.
     * @return
     */
    public boolean firstNameLength() {
        if (firstName.length() < 2) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Ensures that the last name has a character value greater than 1.
     * @return
     */
    public boolean lastNameLength() {
        if (lastName.length() < 2) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Ensures that the credit card number is exactly 16 characters in length.
     * @return
     */
    public boolean cardNumberLength() {
        if (cardNumber.length() == 16) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method serves to calculate the purchase price of various items based upon the input
     * in the UI fields. Error checking is implemented to verify that the correct number of items
     * is selected if various Groupon codes are used.
     * @param numItemsPurchased the number of items listed in the UI spinner
     * @return the total price as calculated
     */
    public double calculatePurchase(int numItemsPurchased){
        int discountItems;
        double grouponItemPrice;
        double regularPrice;
        double totalPrice = 0;

        /* perform check to make sure that 2 items are selected if groupon code is Silver2018 */
        if (grouponCode.getSelectedItemPosition() == 2 && numItemsPurchased < 2){
            Toast.makeText(this, R.string.two_item_groupon,
                    Toast.LENGTH_LONG).show();
            return 0;
        }


        /* calculate the total based upon the groupon code and number of items purchased */
        switch (grouponCode.getSelectedItemPosition()) {
            case 0: totalPrice = itemPrice * numItemsPurchased;
                //Log.d("Total Price", String.valueOf(totalPrice));
                break;

            case 1: discountItems = 1;
                    numItemsPurchased -= discountItems;
                    grouponItemPrice = 30.00;
                    regularPrice = itemPrice * numItemsPurchased;
                    totalPrice = grouponItemPrice + regularPrice;
                //Log.d("Groupon Item Price", String.valueOf(grouponItemPrice));
                //Log.d("Regular Price", String.valueOf(regularPrice));
                //Log.d("Total Price", String.valueOf(totalPrice));
                break;

            case 2: discountItems = 2;
                    numItemsPurchased -= discountItems;
                    grouponItemPrice = 55.00;
                    regularPrice = itemPrice * numItemsPurchased;
                totalPrice = grouponItemPrice + regularPrice;
                //Log.d("Groupon Item Price", String.valueOf(grouponItemPrice));
                //Log.d("Regular Price", String.valueOf(regularPrice));
                //Log.d("Total Price", String.valueOf(totalPrice));
                break;
        }
        return totalPrice;
    }

    /**
     * Creates a bundle for use in passing extras to the various intents.
     * @return the bundle object
     */
    public Bundle bundleExtras() {
        /* Since these are non-translatable, I didn't put these in strings.xml */
        bundle.putString("datePurchased", date.getText().toString());
        bundle.putString("wholeName", wholeName);
        bundle.putString("cardType", cardType.getSelectedItem().toString());
        bundle.putString("last4", last4);
        bundle.putInt("itemQuantity", Integer.valueOf(itemsPurchased.getSelectedItem().toString()));
        bundle.putString("grouponCode", grouponCode.getSelectedItem().toString());
        bundle.putDouble("subtotal", subtotal);
        bundle.putDouble("tax", tax);
        bundle.putDouble("total", total);
        bundle.putInt("customersServed", totalCustomers);
        bundle.putDouble("dailySalesTotal", dailyTotal);

        return bundle;
    }

    /**
     * Calculates taxes based upon the subtotal for the purchase.
     * @param subtotal the total cost of the items before tax is added
     * @return the amount of tax based upon 6% of the subtotal
     */
    public double calculateTax(Double subtotal) {
        Double tax = subtotal * 0.06;
        return tax;
    }

}
