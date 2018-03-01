package com.example.rescue.week8venues;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CafeDetailsActivity extends AppCompatActivity {

    // declare ui objects //
    TextView txtCafeTitle;
    TextView txtCafeAddy;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_details);

        bundle = getIntent().getExtras();

        // instantiate objects //
        txtCafeTitle = (TextView) findViewById(R.id.txtCafeTitle);
        txtCafeAddy = (TextView) findViewById(R.id.txtCafeAddy);

        // assign bundle keys to objects //
        txtCafeTitle.setText(bundle.getString("cafeName"));
        txtCafeAddy.setText(bundle.getString("cafeAddress", "EMPTY ADDRESS"));
    }
}
