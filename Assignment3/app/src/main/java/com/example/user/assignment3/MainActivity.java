package com.example.user.assignment3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String disdate = java.text.DateFormat.getDateTimeInstance().format(new Date());
        outState.putString("currentValue", disdate);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        TextView tv = (TextView) findViewById(R.id.txt_date);
        super.onRestoreInstanceState(savedInstanceState);
        String currentValue = savedInstanceState.getString("currentValue");
        tv.setText(currentValue);
    }
}
