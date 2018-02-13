package com.example.rescue.chapter4orientations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("StateInfo", "onCreate");
    }

    @Override
    public void onStart() {
        Log.d("StateInfo", "onStart");
        super.onStart();
    }
    @Override
    public void onResume() {
        Log.d("StateInfo", "onResume");
        super.onResume();
    }
    @Override
    public void onPause() {
        Log.d("StateInfo", "onPause");
        super.onPause();
    }
    @Override
    public void onStop() {
        Log.d("StateInfo", "onStop");
        super.onStop();
    }
    @Override
    public void onDestroy() {
        Log.d("StateInfo", "onDestroy");
        super.onDestroy();
    }
    @Override
    public void onRestart() {
        Log.d("StateInfo", "onRestart");
        super.onRestart();
    }
}
