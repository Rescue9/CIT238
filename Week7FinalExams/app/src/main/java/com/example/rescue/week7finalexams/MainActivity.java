package com.example.rescue.week7finalexams;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    /* declare objects */
    String[] locationArray;
    String[] courseArray;
    String selectedCourse = "";
    String selectedLocation = "";
    String wholeName;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText studentId;
    ListView courses;
    AutoCompleteTextView locations;
    CheckBox confirmEmail;
    Button dateButton;
    Button timeButton;
    Bundle bundle = new Bundle();

    int hour, minute;

    Calendar calendar = Calendar.getInstance();

    /**
     * Create the application
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* setup the actionbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_action_examination);

        /* setup the objects */
        firstName = findViewById(R.id.edtFirstName);
        lastName = findViewById(R.id.edtLastName);
        email = findViewById(R.id.edtEmail);
        studentId = findViewById(R.id.edtStudentId);
        courses = findViewById(R.id.lstCourseList);
        confirmEmail = findViewById(R.id.chkEmailConfirm);
        locationArray = getResources().getStringArray(R.array.test_location);
        timeButton = findViewById(R.id.btnTime);
        dateButton = findViewById(R.id.btnDate);

        /* setup the courses listview */
        courseArray = getResources().getStringArray(R.array.course_list);
        courses.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, courseArray));
        courses.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "You have selected " + courseArray[position],
                        Toast.LENGTH_SHORT).show();
                selectedCourse = courseArray[position];
            }
        });

        /* setup the autocompletetextview */
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item,
                getResources().getStringArray(R.array.test_location));

        locations = findViewById(
                R.id.actLocationList);

        locations.setHint(getResources().getString(R.string.enter_location));
        locations.setThreshold(1);
        locations.setAdapter(locationAdapter);

    }

    /**
     * Create a time picker
     * @param v
     */
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

                        timeButton.setText(hourOfDay + ":00");
                    }
                }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE) , true);
        timePickerDialog.show();
    }

    /**
     * Create a date picker
     * @param v
     */
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

    /**
     * validate information, create a bundle, and start the second activity
     * @param view
     */
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

        /* verify that a course is selected */
        if (!isCourseSelected()) {
            return;
        }

        /* create email if Confirmation Email selected */
        if (confirmEmail.isChecked()) {
            Intent intent = new Intent(this, ConfirmationActivity.class);
            bundleExtras();
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

    /**
     * Clear all the fields for new information
     * @param view
     */
    public void onClickClear(View view) {

        /* clear all edittext fields and reset spinner & picker objects */
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        studentId.setText("");
        courses.setSelection(0);
        locations.setText("");
        confirmEmail.setChecked(false);
        courses.setItemChecked(-1, true);
    }

    /**
     * Check first name length is greater than 2
     * @return
     */
    public boolean firstNameLength() {
        return firstName.length() >= 2;
    }

    /**
     * Check last name length is greater than 2
     * @return
     */
    public boolean lastNameLength() {
        return lastName.length() >= 2;
    }

    /**
     * Validate email has propper address structure
     * @param email
     * @return
     */
    public boolean isValidEmail(CharSequence email) {
        if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        } else {
            Toast.makeText(this, getResources().getString(
                    R.string.valid_email), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /**
     * Check that the location is one of the valid options
     * @param location
     * @return
     */
    public boolean isValidLocation(CharSequence location) {
        for (int l=0; l<locationArray.length; l++) {
            if (location.equals(locationArray[l].toString())) {
                selectedLocation = locationArray[l].toString();
                return true;
            }
        }
        Toast.makeText(this, getResources().getString(
                R.string.valid_location), Toast.LENGTH_LONG).show();
        return false;

    }

    /**
     * Make sure the student id is only 8 characters
     * @param studentIdNum
     * @return
     */
    public boolean isValidStudentId(String studentIdNum) {
        Log.d("length", String.valueOf(studentIdNum.length()));
        if (studentIdNum.length() != 8) {
            Toast.makeText(this, getResources().getString(
                    R.string.valid_student_id), Toast.LENGTH_LONG).show();
            return false;
        } return true;
    }

    /**
     * Make sure that a course is selected
     * @return
     */
    public boolean isCourseSelected() {
        Log.d("Course Selected", selectedCourse);
        if (selectedCourse == "") {
            Toast.makeText(this, getResources().getString(
                    R.string.valid_course), Toast.LENGTH_LONG).show();
            return false;
        } return true;
    }

    /**
     * Creates a bundle for use in passing extras to the various intents.
     * @return the bundle object
     */
    public Bundle bundleExtras() {
        /* Since these are non-translatable, I didn't put these in strings.xml */
        bundle.putString("wholeName", wholeName);
        bundle.putString("emailAddress", email.getText().toString());
        bundle.putString("studentId", studentId.getText().toString());
        bundle.putString("course", selectedCourse);
        bundle.putString("location", selectedLocation);
        bundle.putString("date", dateButton.getText().toString());
        bundle.putString("time", timeButton.getText().toString());

        return bundle;
    }


}
