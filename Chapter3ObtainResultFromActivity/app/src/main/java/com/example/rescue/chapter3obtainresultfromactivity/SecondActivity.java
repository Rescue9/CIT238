package com.example.rescue.chapter3obtainresultfromactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by rescue on 1/26/18.
 */

public class SecondActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void onClick(View view) {
        Intent data = new Intent();

        //-- get the EditText view --
        EditText txt_username = (EditText) findViewById(R.id.txtUsername);
        //-- create a bundle for the data
        Bundle extras = new Bundle();
        extras.putString("person", txt_username.getText().toString());
        extras.putInt("age", 21);
        data.putExtras(extras);

        setResult(RESULT_OK, data);
        //-- close the activity
        finish();
    }
}
