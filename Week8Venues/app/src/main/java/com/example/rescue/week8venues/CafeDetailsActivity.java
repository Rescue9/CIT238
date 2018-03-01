package com.example.rescue.week8venues;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CafeDetailsActivity extends AppCompatActivity {

    // declare ui objects //
    TextView txtCafeTitle;
    TextView txtCafeAddy;
    TextView txtCafePhone;
    TextView txtCafeHours;
    TextView txtCafeHtml;
    TextView txtCafeMap;
    TextView txtCafeHistory;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_details);

        bundle = getIntent().getExtras();

        // instantiate objects //
        txtCafeTitle = (TextView) findViewById(R.id.txtCafeTitle);
        txtCafeAddy = (TextView) findViewById(R.id.txtCafeAddy);
        txtCafePhone = (TextView) findViewById(R.id.txtCafePhone);
        txtCafeHours = (TextView) findViewById(R.id.txtCafeHours);
        txtCafeHtml = (TextView) findViewById(R.id.txtCafeHtml);
        txtCafeMap = (TextView) findViewById(R.id.txtCafeMap);
        txtCafeHistory = (TextView) findViewById(R.id.txtCafeHistory);

        // assign bundle keys to objects //
        txtCafeTitle.setText(bundle.getString("cafeName"));
        txtCafeAddy.setText(bundle.getString("cafeAddress"));
        txtCafePhone.setText(bundle.getString("cafePhone"));
        txtCafeHours.setText(bundle.getString("cafeHours"));
        txtCafeHtml.setText(bundle.getString("cafeHtml"));
        txtCafeMap.setText(bundle.getString("cafeMapAddr"));
        txtCafeHistory.setText(bundle.getString("cafeHistory"));
    }
}
