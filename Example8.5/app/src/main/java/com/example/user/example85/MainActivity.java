package com.example.user.example85;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// This is project has methods for adding , delete ,update and retrieve customer details.

public class MainActivity extends AppCompatActivity {
    DBAdapter dbAdapter=null;
    EditText nameEditText, phoneEditText, idEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Here we create an instance of DBAdapter class
        dbAdapter = new DBAdapter(this);


        idEditText =(EditText) findViewById(R.id.et_id);
        nameEditText = (EditText) findViewById(R.id.et_name);
        phoneEditText = (EditText) findViewById(R.id.et_phone);


        Button addButton = (Button) findViewById(R.id.btn_add);
        addButton.setOnClickListener(clikcListener);


        Button deleteButton = (Button) findViewById(R.id.btn_delete);
        deleteButton.setOnClickListener(clikcListener);


        Button updateButton = (Button) findViewById(R.id.btn_update);
        updateButton.setOnClickListener(clikcListener);

        Button getSingleButton = (Button) findViewById(R.id.btn_get_single);

        getSingleButton.setOnClickListener(clikcListener);


        Button getAllButton = (Button) findViewById(R.id.btn_get_all);
        getAllButton.setOnClickListener(clikcListener);

    }

    View.OnClickListener clikcListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Button clickedButton = (Button) v;

            if(clickedButton.getText().equals(getString(R.string.btn_add_txt))) {


                if(idEditText.getText().length()==0 || nameEditText.getText().length()==0 || phoneEditText.getText().length()==0){

                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.empty_fields_txt), Toast.LENGTH_LONG).show();

                } else {


                    //Here we add a new customer data
                    dbAdapter.open();

                    long id = dbAdapter.addCustomer(Integer.parseInt(idEditText.getText().toString()), nameEditText.getText().toString(), phoneEditText.getText().toString());


                    displayResult(id);

                    //Here we close database connection
                    dbAdapter.close();
                }

            } else if(clickedButton.getText().equals(getString(R.string.btn_get_all_txt))) {

                //Here we add a new customer data
                dbAdapter.open();
                Cursor cursor = dbAdapter.getAllCustomers();
                if(cursor.moveToFirst()) {
                    do {
                        displayData(cursor);
                    } while(cursor.moveToNext());

                }


                //Here we close database connection
                dbAdapter.close();


            } else if(clickedButton.getText().equals(getString(R.string.btn_get_single_txt))) {


                if(idEditText.getText().length()==0){

                    idEditText.setBackgroundColor(Color.rgb(100, 150, 150));

                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_id_txt), Toast.LENGTH_LONG).show();

                } else {


                    //Here we add a new customer data
                    dbAdapter.open();

                    long id= Integer.parseInt(idEditText.getText().toString());
                    Cursor cursor = dbAdapter.getCustomer(id);

                    if(cursor.moveToFirst()) {
                        displayData(cursor);

                    } else {

                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.customer_id_txt) + id + getResources().getString(R.string.not_found_txt), Toast.LENGTH_LONG).show();
                    }

                    //Here we close database connection
                    dbAdapter.close();

                }



            } else if(clickedButton.getText().equals(getString(R.string.btn_update_txt))) {

                if(idEditText.getText().length()==0 || nameEditText.getText().length()==0 || phoneEditText.getText().length()==0){

                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.empty_fields_txt), Toast.LENGTH_LONG).show();

                } else {

                    //Here we add a new customer data
                    dbAdapter.open();

                    long id= Integer.parseInt(idEditText.getText().toString());

                    if(dbAdapter.updateCustomer(id, nameEditText.getText().toString(), phoneEditText.getText().toString()))

                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.update_txt) + id +getResources().getString(R.string.success_txt), Toast.LENGTH_LONG).show();

                    else

                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.update_txt) + id + getResources().getString(R.string.failure_txt), Toast.LENGTH_LONG).show();

                    //Here we close database connection
                    dbAdapter.close();

                }


            } else if(clickedButton.getText().equals(getString(R.string.btn_delete_txt))) {

                if(idEditText.getText().length()==0){

                    idEditText.setBackgroundColor(Color.rgb(100, 150, 150));

                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_id_txt), Toast.LENGTH_LONG).show();

                } else {

                    //Here we add a new customer data
                    dbAdapter.open();

                    long id= Integer.parseInt(idEditText.getText().toString());

                    if(dbAdapter.deleteCustomer(id))

                        Toast.makeText(getApplicationContext(),  getResources().getString(R.string.delete_txt) + id + getResources().getString(R.string.success_txt), Toast.LENGTH_LONG).show();
                    else

                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.delete_txt) + id + getResources().getString(R.string.failure_txt), Toast.LENGTH_LONG).show();

                    //Here we close database connection
                    dbAdapter.close();
                }
            }

        }
    };

     // This method displays customer data
    private void displayData(Cursor cursor){

        Toast.makeText(getApplicationContext(), getResources().getString(R.string.id_txt) + cursor.getString(0) + "\n" + getResources().getString(R.string.name_txt) + cursor.getString(1) + "\n" + getResources().getString(R.string.phone_txt) + cursor.getString(2) ,Toast.LENGTH_LONG).show();

    }

    private void displayResult(long id) {

        Toast.makeText(getApplicationContext(), getResources().getString(R.string.returned_value_txt)+ id, Toast.LENGTH_LONG).show();

    }
}

