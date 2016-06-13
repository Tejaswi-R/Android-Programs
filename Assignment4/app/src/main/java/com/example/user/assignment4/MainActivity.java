package com.example.user.assignment4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendButton = (Button) findViewById(R.id.btn_send);
        sendButton.setOnClickListener(display);

        Button deleteButton = (Button) findViewById(R.id.btn_reset);
        deleteButton.setOnClickListener(delete);

    }

    private View.OnClickListener display = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String disdate = java.text.DateFormat.getDateTimeInstance().format(new Date());
            TextView tv = (TextView) findViewById(R.id.et_comment);
            tv.setText(disdate);
        }
    };

    private View.OnClickListener delete = new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             TextView tv = (TextView) findViewById(R.id.et_comment);
              tv.setText("");
        }
    };
}

