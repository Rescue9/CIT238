package com.example.rescue.chapter3obtainresultfromactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int request_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        startActivityForResult(new Intent("com.example.rescue.chapter3obtainresultfromactivity.SecondActivity"), request_Code);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK) {
                //-- Create a bundle to receive data and Toast it

                Bundle extras = data.getExtras();

                if (extras != null) {
                    String personName = extras.getString("person", "");
                    int personAge = extras.getInt("age", 0);

                    Toast.makeText(this, personName + " " + personAge, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
