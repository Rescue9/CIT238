package com.example.rescue.week7finalexams;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String[] locationArray;
    String wholeName;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText studentId;
    Spinner courses;
    AutoCompleteTextView locations;
    CheckBox confirmEmail;
    Button dateButton;
    Button timeButton;

    int hour, minute;

    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* setup the actionbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_action_examination);

        /* setup the objects */
        firstName = (EditText) findViewById(R.id.edtFirstName);
        lastName = (EditText) findViewById(R.id.edtLastName);
        email = (EditText) findViewById(R.id.edtEmail);
        studentId = (EditText) findViewById(R.id.edtStudentId);
        courses = (Spinner) findViewById(R.id.spnCourseList);
        confirmEmail = (CheckBox) findViewById(R.id.chkEmailConfirm);
        locationArray = getResources().getStringArray(R.array.test_location);
        timeButton = (Button) findViewById(R.id.btnTime);
        dateButton = (Button) findViewById(R.id.btnDate);

        /* setup the autocompletetextview */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.test_location));

        locations = (AutoCompleteTextView) findViewById(
                R.id.actLocationList);

        locations.setHint(getResources().getString(R.string.enter_location));
        locations.setThreshold(1);
        locations.setAdapter(adapter);

    }

    public void onClickTimeButton(View v) {

        final TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                new OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {

                        switch (hourOfDay) {
                            case 8: break;
                            case 12: break;
                            case 15: break;
                            case 18: break;
                            default: {
                                Toast.makeText(getApplicationContext(), R.string.valid_times,
                                        Toast.LENGTH_LONG).show();
                                timeButton.setText(getResources().getString(R.string.time_button));
                                return;

                            }
                        }

                        switch (minuteOfDay) {
                            case 0: break;
                            default: {
                                Toast.makeText(getApplicationContext(), R.string.valid_times,
                                        Toast.LENGTH_LONG).show();
                                timeButton.setText(getResources().getString(R.string.time_button));
                                return;
                            }
                        }

                        timeButton.setText(hour + ":00");
                    }
                }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE) , true);
        timePickerDialog.show();
    }

    public void onClickDateButton(View v) {

        final DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d("Year", String.valueOf(year));
                Log.d("Month", String.valueOf(month));
                Log.d("Day", String.valueOf(dayOfMonth));
                Log.d("Current Date", String.valueOf(Calendar.getInstance()));

                switch (year) {
                    case 2018: break;
                    default: {
                        Toast.makeText(MainActivity.this, getResources().getString(
                                R.string.valid_dates), Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                switch (month) {
                    case 3:{
                        if (dayOfMonth == 30) {
                            break;
                        } else {
                            Toast.makeText(MainActivity.this, getResources().getString(
                                    R.string.valid_dates), Toast.LENGTH_LONG).show();
                            return;
                        }

                    }
                    case 4:{
                        if (dayOfMonth < 5) {
                            break;
                        } else {
                            Toast.makeText(MainActivity.this, getResources().getString(
                                    R.string.valid_dates), Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    default: {
                        Toast.makeText(MainActivity.this, getResources().getString(
                                R.string.valid_dates), Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                dateButton.setText(month + "/" + dayOfMonth + "/" + year);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    public void onClickSubmit(View view) {

        /* make sure the first and last name have more than 1 character */
        if (firstNameLength() && lastNameLength()){
            wholeName = firstName.getText() + " " + lastName.getText();
        } else {
            Toast.makeText(this, getResources().getString(
                    R.string.name_length), Toast.LENGTH_LONG).show();
            return;
        }

        /* verify that the email is in a valid format */
        if (!isValidEmail(email.getText().toString())) {
            return;
        }

        /* verify location is valid */
        if (!isValidLocation(locations.getText().toString())) {
            return;
        }

        /* verify student id is 8 digits long */
        if (!isValidStudentId(studentId.getText().toString())) {
            return;
        }

    }

    public void onClickClear(View view) {

        /* clear all edittext fields and reset spinner & picker objects */
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        studentId.setText("");
        courses.setSelection(0);
        locations.setText("");
        confirmEmail.setChecked(false);

    }

    public boolean firstNameLength() {
        if (firstName.length() < 2) {
            return false;
        } else {
            return true;
        }
    }

    public boolean lastNameLength() {
        if (lastName.length() < 2) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isValidEmail(CharSequence email) {
        if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        } else {
            Toast.makeText(this, getResources().getString(
                    R.string.valid_email), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean isValidLocation(CharSequence location) {
        for (int l=0; l<locationArray.length; l++) {
            if (location.equals(locationArray[l].toString())) {
                return true;
            }
        }
        Toast.makeText(this, getResources().getString(
                R.string.valid_location), Toast.LENGTH_LONG).show();
        return false;

    }

    public boolean isValidStudentId(String studentIdNum) {
        Log.d("length", String.valueOf(studentIdNum.length()));
        if (studentIdNum.length() != 8) {
            Toast.makeText(this, getResources().getString(
                    R.string.valid_student_id), Toast.LENGTH_LONG).show();
            return false;
        } return true;
    }

}
