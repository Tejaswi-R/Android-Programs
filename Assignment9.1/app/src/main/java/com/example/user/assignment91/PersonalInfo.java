package com.example.user.assignment91;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 26/05/2016.
 */
public class PersonalInfo extends AppCompatActivity {
    DBAdapter dbAdapter=null;
    EditText fnameEditText, lnameEditText ,unameEditText, passEditText,phoneEditText,hobbiesEditText, idEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_xml);

        dbAdapter = new DBAdapter(this);


        idEditText =(EditText) findViewById(R.id.et_id);
        fnameEditText = (EditText) findViewById(R.id.et_firstname);
        lnameEditText = (EditText) findViewById(R.id.et_lastname);
        unameEditText = (EditText) findViewById(R.id.et_username);
        passEditText = (EditText) findViewById(R.id.et_password);
        phoneEditText = (EditText) findViewById(R.id.et_phone);


        Button addButton = (Button) findViewById(R.id.btn_save);
        addButton.setOnClickListener(clikcListener);
    }

    View.OnClickListener clikcListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Button clickedButton = (Button) v;

            if (clickedButton.getText().equals(getString(R.string.btn_save))) {


                if (idEditText.getText().length() == 0 || fnameEditText.getText().length() == 0 || lnameEditText.getText().length() == 0 ||
                        hobbiesEditText.getText().length() == 0 ||phoneEditText.getText().length() == 0) {

                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.empty_fields_txt), Toast.LENGTH_LONG).show();

                } else {


                    //Here we add a new customer data
                    dbAdapter.open();

                    long id = dbAdapter.addCustomer(Integer.parseInt(idEditText.getText().toString()), fnameEditText.getText().toString(),
                            lnameEditText.getText().toString(),hobbiesEditText.getText().toString(),phoneEditText.getText().toString());


                   // displayResult(id);

                    //Here we close database connection
                    dbAdapter.close();
                }
            }
        }



    }
}




