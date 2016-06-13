package com.example.user.assignment71;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Vector;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    EditText uName;
    Button btnDatePicker, btnTimePicker, submitForm;
    TextView txtDate, txtTime,places ;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Vector<String> formData;

    String info;
    CharSequence[] itemsId = new CharSequence[]{"Berlin", "Munich", "Hamburg", "Kiel", "Frankfurt"};

    boolean[] itemsChecked = new boolean[itemsId.length];

    Vector<String> selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formData = new Vector<String>();

        uName = (EditText) findViewById(R.id.edit_eventName);
        btnDatePicker = (Button) findViewById(R.id.event_date);
        btnTimePicker = (Button) findViewById(R.id.event_time);
        txtDate = (TextView) findViewById(R.id.edit_date);
        txtTime = (TextView) findViewById(R.id.edit_time);
        submitForm = (Button) findViewById(R.id.btn_Submit);
        places = (TextView) findViewById(R.id.edit_places);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        submitForm.setOnClickListener(this);

        selected = new Vector<String>();
        Button dialogButton = (Button) findViewById(R.id.select_places);
        dialogButton.setOnClickListener(buttonListener);
    }


    private void displayDialog() {


//For Blank Activity Project!!!
//Builder dialogBuilder = new AlertDialog.Builder(this);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setIcon(R.drawable.ic_launcher);
        dialogBuilder.setTitle(R.string.dialog_title);
        dialogBuilder.setPositiveButton(getString(R.string.dialog_positive_txt), dialogPositiveListener);
        dialogBuilder.setNegativeButton(getString(R.string.dialog_negative_txt), dialogNegativeListener);
        dialogBuilder.setMultiChoiceItems(itemsId, itemsChecked, dialogMultichoiceListener);

        //Here we create and show the dialog
        dialogBuilder.create().show();
    }


    private void displayToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }


    private DialogInterface.OnMultiChoiceClickListener dialogMultichoiceListener = new DialogInterface.OnMultiChoiceClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            displayToast(itemsId[which] + " " + getString(R.string.check_txt) + " " + isChecked);

            if (isChecked)
                selected.add(itemsId[which].toString());
            else
                selected.remove(itemsId[which]);
        }
    };
    //Here we define listener for when the dialog OK button is pressed
    private DialogInterface.OnClickListener dialogPositiveListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {

            //displayToast(getString(R.string.dialog_positive_txt) + " " + getString(R.string.feedback_txt));

            places.setText(getString(R.string.edit_eventName));

            for (String item : selected)
                places.append("\n" + item);
        }
    };

    //Here we define listener for when the dialog OK button is pressed
    private DialogInterface.OnClickListener dialogNegativeListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
/*
                displayToast(getString(R.string.dialog_negative_txt) + " " + getString(R.string.feedback_txt));

                summaryTextView.append(getString(R.string.tw_summary_txt));*/
        }
    };


    private View.OnClickListener buttonListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Button clickedButton = (Button) v;
            if (clickedButton.getText().toString().equals(getString(R.string.btn_dialog))) {
                displayDialog();
            }

        }
    };


    @Override
    public void onClick(View v) {

        if( v == btnDatePicker){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                    txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        else if(v == btnTimePicker){
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){

                public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                    txtTime.setText(hourOfDay + ":" + minute);
                }
            }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        else if(v == submitForm){

            info = uName.getText().toString() + " " + places.getText().toString() + " " + txtDate.getText().toString() + " " + txtTime.getText().toString();

            Spinner formDateSpinner = (Spinner) findViewById(R.id.show_data);
            formData.add(info);
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, formData);

            formDateSpinner.setAdapter(adapter1);
        }
    }

    }

