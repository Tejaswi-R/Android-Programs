package com.example.haroonghawsi.assignment62;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText uName;
    Button btnDatePicker, btnTimePicker, submitForm, changeDisplay;
    TextView txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Vector<String> formData;

    String[] places;
    String info, place;
    Vector<View> views;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rootLayout =(RelativeLayout) findViewById(R.id.RelativeLayout);
        rootLayout.setOnCreateContextMenuListener(this);

        views=new Vector<View>();
        formData = new Vector<String>();

        uName = (EditText) findViewById(R.id.edit_eventName);
        btnDatePicker = (Button) findViewById(R.id.event_date);
        btnTimePicker = (Button) findViewById(R.id.event_time);
        txtDate = (TextView) findViewById(R.id.edit_date);
        txtTime = (TextView) findViewById(R.id.edit_time);
        submitForm = (Button) findViewById(R.id.btn_Submit);
        //changeDisplay = (Button) findViewById(R.id.configure_display);
        //registerForContextMenu(changeDisplay);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        submitForm.setOnClickListener(this);

        views.add(btnDatePicker);
        views.add(btnTimePicker);
        views.add(submitForm);


        places = getResources().getStringArray(R.array.event_placeArray);

        Spinner placeSpinner = (Spinner) findViewById(R.id.place_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, places);

        placeSpinner.setAdapter(adapter);


        placeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                place = arg0.getSelectedItem().toString();
                // int index = arg0.getSelectedItemPosition();

                // Toast.makeText(getApplicationContext(), "Selected item: " + places[index], Toast.LENGTH_SHORT).show();;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        } else if (v == btnTimePicker) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    txtTime.setText(hourOfDay + ":" + minute);
                }
            }, mHour, mMinute, false);
            timePickerDialog.show();
        } else if (v == submitForm) {

            info = uName.getText().toString() + " " + place + " " + txtDate.getText().toString() + " " + txtTime.getText().toString();

            Spinner formDateSpinner = (Spinner) findViewById(R.id.show_data);
            formData.add(info);
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, formData);

            formDateSpinner.setAdapter(adapter1);
        }
        //Assignment6.1 Ends Here..

    }
        //Asignment6.2 starts here..
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Change Background Colors");
        menu.add(0, v.getId(), 0, "Red");
        menu.add(0, v.getId(), 0, "Green");
        menu.add(0, v.getId(), 0, "Blue");

        SubMenu textSizeSubMenu = menu.addSubMenu("Font Size");

               MenuItem tinySubMenuItem = textSizeSubMenu.add(0,5,0,"8");
        MenuItem normalSubMenuItem = textSizeSubMenu.add(0,6,0,"12");
        MenuItem largeSubMenuItem = textSizeSubMenu.add(0,6,0,"18");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        View view = findViewById(R.id.RelativeLayout);

        if(item.getTitle() == "Red"){
            Toast.makeText(this, "Red invoked", Toast.LENGTH_SHORT).show();
            view.setBackgroundColor(Color.RED);
        }
        else if(item.getTitle() == "Green"){
            Toast.makeText(this, "Green invoked", Toast.LENGTH_SHORT).show();
            view.setBackgroundColor(Color.GREEN);
        }
        else if(item.getTitle() == "Blue"){
            Toast.makeText(this, "Blue invoked", Toast.LENGTH_SHORT).show();
            view.setBackgroundColor(Color.BLUE);
        }

        else if(item.getTitle() == "18"){
            for(View v : views){
                if (v instanceof TextView)
                    ((TextView)v).setTextSize(18);
                else if(v instanceof Button)
                    ((Button)v).setTextSize(18);
            }
        } else if (item.getTitle() == "8") {

            for(View v : views){
                if(v instanceof TextView){
                    ((TextView) v).setTextSize(8);
                }
                else if(v instanceof Button){
                    ((Button) v).setTextSize(8);
                }
            }
        } else if(item.getTitle() == "12"){
            for(View v : views){
                if(v instanceof TextView){
                    ((TextView) v).setTextSize(12);
                }
            }
        }
        else {
            return false;
        }
        return true;
    }

    private void createMenu(Menu menu){

        MenuItem mItem1 = menu.add(0,2,0,"Red");
        MenuItem mItem2 = menu.add(0,3,1,"Green");
        MenuItem mItem3 = menu.add(0,4,2,"Blue");
    }

}
