package com.example.user.test;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by User on 27/05/2016.
 */
public class Fragment extends Activity {

    DBAdapter dbAdapter=null;
    EditText nameEditText,idEditText,titleEditText;
    Button btnDatePicker, btnTimePicker;
    String dbPath = Environment.getExternalStorageDirectory() + "/data/databases/";

    //Here we define the name of the database and the table
    String dbName="firm.db";
    String tableName="emp";

    Boolean dbCreated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        idEditText =(EditText) findViewById(R.id.et_id);
        nameEditText = (EditText) findViewById(R.id.et_name);
        titleEditText = (EditText) findViewById(R.id.et_title);


        Button addButton = (Button) findViewById(R.id.btn_add);
        addButton.setOnClickListener(clickListener);


        Button deleteButton = (Button) findViewById(R.id.btn_delete);
        deleteButton.setOnClickListener(clickListener);

        Button updateButton = (Button) findViewById(R.id.btn_update);
        updateButton.setOnClickListener(clickListener);

        Button getSingleButton = (Button) findViewById(R.id.btn_get_single);

        getSingleButton.setOnClickListener(clickListener);


        Button getAllButton = (Button) findViewById(R.id.btn_get_all);
        getAllButton.setOnClickListener(clickListener);


        Button copyDBFile = (Button) findViewById(R.id.btn_copy_db_file);
        copyDBFile.setOnClickListener(clickListener);

    }
    private void copyDBFile() {


        File dbAbsolutePathFile = new File(dbPath + dbName);


        File dbPathFile = new File(dbPath);

        //Here we make sure that the dorectory path for the database exists
        if(!dbPathFile.exists()) {
            dbPathFile.mkdirs();

        }



        File dbNameFile = new File(dbName);

        //Here we check whether the database file exists or not. If not, we then create it
        if(!dbNameFile.exists()) {

            try {

                //Here we call the copyDB() method.
                copyDB(getApplicationContext().getAssets().open(dbName), new FileOutputStream(dbPath + dbName));

            } catch (FileNotFoundException e) {
                displayToast("FileNotFound: " + e.getLocalizedMessage());
            } catch (IOException e) {
                displayToast("IOException: " + e.getLocalizedMessage());
            }

        }


        //Here we display feedback on whether the database file has been successfully copied or not.
        displayToast("File exists? " + dbAbsolutePathFile.exists() + "\n" + dbAbsolutePathFile.getAbsolutePath());

        //Here we initialize the dbAdapter object
        dbAdapter = new DBAdapter(getApplicationContext(), dbPath, dbName, tableName );

        //Here we indicate that the database file has been copied successfully.
        dbCreated=true;

        //Here we call getAllUsers() method to show that the database
        //file has been successfully copied.
        getAllUsers();

        dbAdapter.close();

    }



    private void copyDB(InputStream inputStream, OutputStream outputStream) {

        //Copying 1K bytes at a time
        byte[] buffer = new byte[1024];
        int length;
        try {
            while((length=inputStream.read(buffer))>0) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            outputStream.close();

        } catch (IOException e) {
            displayToast("IOException: " + e.getLocalizedMessage());
        }
    }


    //Here we define getAllUsers() method
    private void getAllUsers() {

        //Here we add a new user data
        dbAdapter.open();
        Cursor cursor = dbAdapter.getAllUsers();
        if(cursor.moveToFirst()) {
            do {
                displayData(cursor);
            } while(cursor.moveToNext());
        }

        //Here we close database connection
        dbAdapter.close();
    }

    View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {



            Button clickedButton = (Button) v;

            if(clickedButton.getText().equals(getString(R.string.btn_copy_db_file_txt))) {
                copyDBFile();

            } else if(!dbCreated) {
                displayToast("Copy database file first!");

                return;
            }

            else if(clickedButton.getText().equals(getString(R.string.btn_add_txt))) {


                if(idEditText.getText().length()==0 || nameEditText.getText().length()==0 || titleEditText.getText().length()==0){

                    displayToast("Some fields are empty!");

                } else {

                    //Here we add a new customer data
                    dbAdapter.open();
                    long id = dbAdapter.addUser(Integer.parseInt(idEditText.getText().toString()),nameEditText.getText().toString());

                    displayResult(id);

                    //Here we close database connection
                    dbAdapter.close();
                }

            } else if(clickedButton.getText().equals(getString(R.string.btn_get_all_txt))) {

                //Here we call getAllUsers method
                getAllUsers();

            }

            else if(clickedButton.getText().equals(getString(R.string.btn_get_single_txt))) {


                if(idEditText.getText().length()==0){

                    idEditText.setBackgroundColor(Color.rgb(100, 150, 150));
                    displayToast("No ID is given!");

                } else {


                    //Here we add a new customer data
                    dbAdapter.open();

                    long id= Integer.parseInt(idEditText.getText().toString());
                    Cursor cursor = dbAdapter.getUsers(id);

                    if(cursor.moveToFirst()) {
                        displayData(cursor);

                    } else {
                        displayToast("User ID: " + id + " not found!");
                    }

                    //Here we close database connection
                    dbAdapter.close();

                }



            } else if(clickedButton.getText().equals(getString(R.string.btn_update_txt))) {

                if(idEditText.getText().length()==0 || nameEditText.getText().length()==0 || phoneEditText.getText().length()==0){

                    displayToast( "Some fields are empty!");

                } else {

                    //Here we add a new customer data
                    dbAdapter.open();

                    long id= Integer.parseInt(idEditText.getText().toString());

                    if(dbAdapter.updateCustomer(id, nameEditText.getText().toString(), phoneEditText.getText().toString()))
                        displayToast("Updating " + id + " was successful!");
                    else
                        displayToast("Updating " + id + " failed");

                    //Here we close database connection
                    dbAdapter.close();

                }

            } else if(clickedButton.getText().equals(getString(R.string.btn_delete_txt))) {

                if(idEditText.getText().length()==0){

                    idEditText.setBackgroundColor(Color.rgb(100, 150, 150));

                    displayToast("No ID is given!");

                } else {

                    //Here we add a new customer data
                    dbAdapter.open();

                    long id= Integer.parseInt(idEditText.getText().toString());

                    if(dbAdapter.deleteUser(id))
                        displayToast( "Deleting " + id + " was successful!");

                    else
                        displayToast("Deleting " + id + " failed");

                    //Here we close database connection
                    dbAdapter.close();
                }
            }

        }
    };

    private void displayToast(String text) {
        Toast.makeText(getApplicationContext(), text , Toast.LENGTH_LONG).show();
    }

    private void displayData(Cursor cursor){
        Toast.makeText(getApplicationContext(), "id: " + cursor.getString(0) + "\nName: " + cursor.getString(1) + "\nPhone: " + cursor.getString(2) ,Toast.LENGTH_LONG).show();
    }

    private void displayResult(long id) {
        Toast.makeText(getApplicationContext(), "Returned value: " + id, Toast.LENGTH_LONG).show();

    }
}
