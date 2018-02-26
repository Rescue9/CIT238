package com.example.rescue.week7finalexams;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {

    TextView wholeName;
    TextView studentNum;
    TextView emailAddress;
    TextView location;
    TextView course;
    CheckBox confirmation;
    TextView date;
    TextView time;
    TextView toEmail;

    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        bundle = getIntent().getExtras();

        wholeName = (TextView) findViewById(R.id.txtWholeName);
        studentNum = (TextView) findViewById(R.id.txtStudentNum);
        emailAddress = (TextView) findViewById(R.id.txtEmailAddress);
        location = (TextView) findViewById(R.id.txtLocation);
        course = (TextView) findViewById(R.id.txtCourse);
        confirmation = (CheckBox) findViewById(R.id.chkConfirmation);
        date = (TextView) findViewById(R.id.txtDate);
        time = (TextView) findViewById(R.id.txtTime);
        toEmail = (TextView) findViewById(R.id.txtEmailAddress);

        wholeName.setText(bundle.getString("wholeName"));
        studentNum.setText(bundle.getString("studentId"));
        emailAddress.setText(bundle.getString("emailAddress"));
        location.setText(bundle.getString("location"));
        course.setText(bundle.getString("course"));
        date.setText(bundle.getString("date"));
        time.setText(bundle.getString("time"));
        toEmail.setText(bundle.getString("emailAddress"));


    }

    public void onClickSend(View view) {
        confirmation.setChecked(true);
    }

    public void onClickReturn(View view) {
        super.onBackPressed();
    }
}
