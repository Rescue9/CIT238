package com.example.rescue.chapter5basicviews4;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TimePickerDialog.OnTimeSetListener dialogListener = new TimePickerDialog
            .OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            Log.d("int1", String.valueOf(i));
            Log.d("int2", String.valueOf(i1));
        }
    };
    Calendar cal = Calendar.getInstance();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showTimeDialog();
    }
    public void showTimeDialog(){
        new TimePickerDialog(MainActivity.this,dialogListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show(); {};
    }
}