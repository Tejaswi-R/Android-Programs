package com.example.haroonghawsi.assignment9;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Hashtable;

public class login extends AppCompatActivity {

    Hashtable aut=new Hashtable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        aut.put("user","admin");
        aut.put("pass","admin");

        Button login = (Button) findViewById(R.id.button2);

        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                EditText user = (EditText) findViewById(R.id.editText);
                EditText password = (EditText) findViewById(R.id.editText2);

                String abc=user.getText().toString();
                String def=password.getText().toString();
                String user1=aut.get("user").toString();
                String pass1=aut.get("pass").toString();
                // if(abc.equals("admin")) {

                if(user1.equals(abc) && pass1.equals(def) ){

                    Intent intent1 = new Intent(getBaseContext(), MainActivity.class);
                    intent1.putExtra("userWelc1", user1);
                    startActivity(intent1);

                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(login.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Wrong User Name/Password");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent2 = new Intent(getBaseContext(), login.class);
                                    startActivity(intent2);
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }


            }
        });

    }
}
