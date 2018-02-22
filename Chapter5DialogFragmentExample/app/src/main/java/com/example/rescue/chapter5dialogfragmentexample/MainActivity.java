package com.example.rescue.chapter5dialogfragmentexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment1 dialogFragment = Fragment1.newInstance(
                "Are you sure you want to do this?");
        dialogFragment.show(getFragmentManager(), "dialog");
    }
    public void doPositiveClick() {
//---perform steps when user clicks on OK---
        Log.d("DialogFragmentExample", "User clicks on OK");
    }
    public void doNegativeClick() {
//---perform steps when user clicks on Cancel---
        Log.d("DialogFragmentExample", "User clicks on Cancel");
    }
}
