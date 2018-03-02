package com.example.rescue.week8venues;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CafeDetailsActivity extends AppCompatActivity {

    // declare ui objects //
    TextView txtCafeTitle;
    TextView txtCafeAddy;
    TextView txtCafePhone;
    TextView txtCafeHours;
    Button btnCafeHtml;
    TextView txtCafeHistory;
    ImageView imgCafeImage;

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
        btnCafeHtml = (Button) findViewById(R.id.btnCafeHtml);
        txtCafeHistory = (TextView) findViewById(R.id.txtCafeHistory);
        imgCafeImage = (ImageView) findViewById(R.id.imgCafeImage);

        // assign bundle keys to objects //
        txtCafeTitle.setText(bundle.getString("cafeName"));
        txtCafeAddy.setText(bundle.getString("cafeAddress"));
        txtCafePhone.setText(bundle.getString("cafePhone"));
        txtCafeHours.setText(bundle.getString("cafeHours"));
        btnCafeHtml.setText(bundle.getString("cafeHtmlLabel"));
        txtCafeHistory.setText(bundle.getString("cafeHistory"));
        imgCafeImage.setImageResource(bundle.getInt("cafeImage"));

        // create onclick event for web //
        btnCafeHtml.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                bundle.putString("cafeWebView", bundle.getString("cafeHtml"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
