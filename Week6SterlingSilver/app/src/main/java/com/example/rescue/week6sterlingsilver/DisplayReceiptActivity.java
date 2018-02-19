package com.example.rescue.week6sterlingsilver;

/*
    Name: Andrew Buskov
    Class: CIT 238
    Date: 0/15/2018
    Purpose: To create an application that tracks sales of sterling silver items.
        This app should display a receipt to the current customer, as well as
        track the daily customer total and daily dollar total sales.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class DisplayReceiptActivity extends AppCompatActivity {

    /* declare objects */
    Bundle bundle;

    String date;
    String wholeName;
    String cardType;
    String last4;
    int itemQuantity;
    String grouponCode;
    Double subtotal;
    Double tax;
    Double total;

    TextView txtDate;
    TextView txtCustomerName;
    TextView txtCardType;
    TextView txtLast4;
    TextView txtQuantityPurchased;
    TextView txtGrouponSavingsCode;
    TextView txtSubtotal;
    TextView txtTax;
    TextView txtTotal;

    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);

    /**
     * Creates the activity, and sets various ActionBar options.
     * @param savedInstanceState the bundle containing the previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_receipt);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        instantiateObjects();
        createReceipt();
    }

    /**
     * This method overrides the standard actions performed when the up/home button on the
     * ActionBar is pressed. The standard action is to go home and clear all the data that was
     * input into the UI. This method changes that action to mimic the back button being pressed,
     * which preserves the UI input.
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * This method instantiates the bundle object and assigns various bundled data to variables.
     */
    public void instantiateObjects() {
        /* instantiate objects */
        bundle = getIntent().getExtras();

        date = bundle.getString("datePurchased");
        wholeName = bundle.getString("wholeName");
        cardType = bundle.getString("cardType");
        last4 = bundle.getString("last4");
        itemQuantity = bundle.getInt("itemQuantity");
        grouponCode = bundle.getString("grouponCode");
        subtotal = bundle.getDouble("subtotal");
        tax = bundle.getDouble("tax");
        total = bundle.getDouble("total");

        txtDate = (TextView) findViewById(R.id.txtDate);
        txtCustomerName = (TextView) findViewById(R.id.txtCustomerName);
        txtCardType = (TextView) findViewById(R.id.txtCardType);
        txtLast4 = (TextView) findViewById(R.id.txtCardLast4);
        txtQuantityPurchased = (TextView) findViewById(R.id.txtQuantityPurchased);
        txtGrouponSavingsCode = (TextView) findViewById(R.id.txtGrouponSavingsCode);
        txtSubtotal = (TextView) findViewById(R.id.txtSubtotal);
        txtTax = (TextView) findViewById(R.id.txtTax);
        txtTotal = (TextView) findViewById(R.id.txtTotal);
    }

    /**
     * This method assigns various string variables to the UI objects for display.
     */
    public void createReceipt() {
        txtDate.setText(date);
        txtCustomerName.setText(wholeName);
        txtCustomerName.setAllCaps(true);
        txtCardType.setText(cardType);
        txtLast4.setText(last4);
        txtQuantityPurchased.setText(String.valueOf(itemQuantity));
        txtGrouponSavingsCode.setText(grouponCode);
        txtSubtotal.setText(currency.format(subtotal));
        txtTax.setText(currency.format(tax));
        txtTotal.setText(currency.format(total));
    }

}
