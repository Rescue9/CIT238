package com.example.rescue.week7finalexams;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
