package com.example.haroon.assignment5;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


       // LinearLayout.LayoutParams viewLayoutParams = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        /*
        viewLayoutParams.leftMargin=40;
        viewLayoutParams.rightMargin=40;
        viewLayoutParams.topMargin=10;
        viewLayoutParams.bottomMargin=10;
        */


        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        Button b1 = new Button(this);
        b1.setText("First Button");
        b1.setLayoutParams(lParams);
        b1.setOnClickListener(fButton1);
        layout.addView(b1);


        Button b2 = new Button(this);
        b2.setText("Second Button");
        b2.setLayoutParams(lParams);
        b2.setOnClickListener(fButton2);
        layout.addView(b2);

        LinearLayout.LayoutParams layoutp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.addContentView(layout, layoutp);
    }

    private View.OnClickListener fButton1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            
        }
    };

    private View.OnClickListener fButton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
};
