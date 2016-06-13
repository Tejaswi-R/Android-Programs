package com.example.user.assignment61;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TimePickerDialog timePickerDialog;

    DatePickerDialog datePickerDialog;

    Button showButton = null

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int hour, minute;
        int day, month, year;

        Calendar today = Calendar.getInstance();


        minute = today.get(Calendar.MINUTE);
        hour = today.get(Calendar.HOUR);
        day=today.get(Calendar.DAY_OF_MONTH);
        month=today.get(Calendar.MONTH);
        year=today.get(Calendar.YEAR);


        //Here we create TimePickerDialog object.
        timePickerDialog = new TimePickerDialog(this, timeSetListener, hour, minute, false);


        //Here we create DatePickerDialog
        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);


        Button selectDateButton = (Button) findViewById(R.id.btn_select_date);
        selectDateButton.setOnClickListener(buttonListener);

        Button selectTimeButton = (Button) findViewById(R.id.btn_select_time);
        selectTimeButton.setOnClickListener(buttonListener);

        showButton = (Button) rootView.findViewById(R.id.btn_show);
        showButton.setOnCreateContextMenuListener(this);
        //showButton.set


   //Here we get the background of the view
   drawable = (PaintDrawable) rootView.getBackground();

   //Here we get the background color of the view
   viewBackgroundColor = drawable.getPaint().getColor();


    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Button button = (Button) v;

            if(button.getText().toString().equals(getString(R.string.date)))

                datePickerDialog.show();

            else if(button.getText().toString().equals(getString(R.string.time)))
                timePickerDialog.show();
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minutOfHour) {


        }
    };

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {


        }
    };

}

