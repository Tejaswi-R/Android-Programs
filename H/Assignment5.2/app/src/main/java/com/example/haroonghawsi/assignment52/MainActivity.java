package com.example.haroonghawsi.assignment52;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    Vector<String> nameVector;
    Vector<String> phoneVector;
    String namePInfo;
    String phonePInfo;
    EditText edit_uName, edit_uHobbies, edit_searchName;
    RadioButton edit_uMale, edit_uFemale;
    EditText edit_uPhone, edit_searchPhone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme);
        setContentView(R.layout.activity_main);

        nameVector = new Vector<String>();
        phoneVector = new Vector<String>();

         edit_uName = (EditText) findViewById(R.id.edit_name);
         edit_uHobbies = (EditText) findViewById(R.id.edit_hobbies);
         edit_uMale = (RadioButton) findViewById(R.id.user_male);
         edit_uFemale = (RadioButton) findViewById(R.id.user_female);
         edit_uPhone = (EditText) findViewById(R.id.edit_phone);


        Button btnSub = (Button) findViewById(R.id.btn_submit);

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                namePInfo = edit_uName.getText().toString() + " " + edit_uHobbies.getText().toString() + " " + edit_uMale.getText().toString() +
                        " " + edit_uFemale.getText().toString() + " " + edit_uPhone.getText().toString() ;

                phonePInfo = edit_uPhone.getText().toString() + " " + edit_uName.getText().toString() + " " + edit_uHobbies.getText().toString() + " " + edit_uMale.getText().toString() +
                        " " + edit_uFemale.getText().toString() ;

                nameVector.add(namePInfo);
                //Here we define an array adapter with a style and a content list
                ArrayAdapter<String> nameArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,nameVector );

                //Here we define the AutoCompleteTextView object
                AutoCompleteTextView nameAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.edit_nameSearch);


                //Here we define the required number of letters to be typed in the AutoCompleteTextView
                nameAutoCompleteTextView.setThreshold(1);

                //Here we set the array adapter for the AutoCompleteTextView
                nameAutoCompleteTextView.setAdapter(nameArrayAdapter);

                phoneVector.add(phonePInfo);

                //Here we define an array adapter with a style and a content list
                ArrayAdapter<String> phoneArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,phoneVector);

                //Here we define the AutoCompleteTextView object
                AutoCompleteTextView phoneAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.edit_phoneSearch);

                //Here we define the required number of letters to be typed in the AutoCompleteTextView
                phoneAutoCompleteTextView.setThreshold(1);

                //Here we set the array adapter for the AutoCompleteTextView
                phoneAutoCompleteTextView.setAdapter(phoneArrayAdapter);







            }


        });
    }
}

