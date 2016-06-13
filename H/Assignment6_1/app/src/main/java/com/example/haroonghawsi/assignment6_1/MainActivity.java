package com.example.haroonghawsi.assignment6_1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText uName;
    Button btnDatePicker, btnTimePicker, submitForm;
    TextView txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Vector<String> formData;

    String[] places;
    String info, place;

    Button showButton = null;
    int viewBackgroundColor;

    //This is to get the background color of the view later
    PaintDrawable drawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setTheme(android.R.style.Theme);

        formData= new Vector<String>();

        uName = (EditText) findViewById(R.id.edit_eventName);
        btnDatePicker = (Button) findViewById(R.id.event_date);
        btnTimePicker = (Button) findViewById(R.id.event_time);
        txtDate = (TextView) findViewById(R.id.edit_date);
        txtTime = (TextView) findViewById(R.id.edit_time);
        submitForm=(Button) findViewById(R.id.btn_Submit) ;

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        submitForm.setOnClickListener(this);

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

        showButton = (Button) findViewById(R.id.btn_show);
        showButton.setOnCreateContextMenuListener(this);
        //showButton.set

    }

    @Override
    public void onClick(View v) {

        if( v == btnDatePicker){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
                public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth){
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

            info = uName.getText().toString() + " " + place + " " + txtDate.getText().toString() + " " + txtTime.getText().toString();

            Spinner formDateSpinner = (Spinner) findViewById(R.id.show_data);
            formData.add(info);
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, formData);

            formDateSpinner.setAdapter(adapter1);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //This method is responsible for creating the context menu.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, view, menuInfo);
        createMenu(menu);
    }



    //This method is responsible for processing selection from
    //the context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case 0:
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);

                break;
            case 1:
                getWindow().getDecorView().setBackgroundColor(viewBackgroundColor);
                break;

            case 2:
                getWindow().getDecorView().setBackgroundColor(Color.RED);
                break;

            case 3:
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                break;

            case 4:
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                break;

            case 5:
            case 6:
            case 7:
                showButton.setTextSize(Float.parseFloat(item.getTitle().toString()));
                break;

        }
        return true;
    }

    //Here we add items to the menu.
    private void createMenu(Menu menu) {

        // Here we enable the shortcut keys
        menu.setQwertyMode(true);


        //Here we define menu items
        MenuItem menuItem1 = menu.add(0, 0, 1, "Default");
        menuItem1.setAlphabeticShortcut('d');
        menuItem1.setEnabled(true);
        //menuItem2.setIcon(R.drawable.pic2);
        menuItem1.setEnabled(true);

        MenuItem menuItem2 = menu.add(0, 1, 2, "Clear");
        menuItem2.setAlphabeticShortcut('c');
        // menuItem3.setIcon(R.drawable.pic3);


        //Here we define submenus and their items
        SubMenu colorsSubMenu = menu.addSubMenu("Colors");

        MenuItem redSubmenuItem = colorsSubMenu.add(0, 2, 0, "Red");
        redSubmenuItem.setAlphabeticShortcut('r');

        MenuItem greenSubmenuItem = colorsSubMenu.add(0, 3, 1, "Green");
        greenSubmenuItem.setAlphabeticShortcut('r');

        MenuItem blueSubmenuItem = colorsSubMenu.add(0, 4, 2, "Blue");
        blueSubmenuItem.setAlphabeticShortcut('r');


        SubMenu textSizeSubMenu = menu.addSubMenu("Text Size");

        MenuItem tinySubmenuItem = textSizeSubMenu.add(0, 5, 0, "8");
        tinySubmenuItem.setAlphabeticShortcut('t');

        MenuItem normalSubmenuItem = textSizeSubMenu.add(0, 6, 0, "12");
        normalSubmenuItem.setAlphabeticShortcut('n');

        MenuItem bigSubmenuItem = textSizeSubMenu.add(0, 7, 0, "16");
        bigSubmenuItem.setAlphabeticShortcut('b');

    }

    private void displayToast(String text) {

        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}


