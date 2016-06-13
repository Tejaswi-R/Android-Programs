package com.example.user.assignment72;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by User on 25/05/2016.
 */
public class LoginActivity extends Activity {
    EditText usernameEditText = null;
    EditText passwordEditText = null;
    Hashtable<String, String> login = new Hashtable<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String data = "";

        // Here we access the incoming Intenet object
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            data = extras.getString("data");

        usernameEditText = (EditText) findViewById(R.id.et_username);
        passwordEditText = (EditText) findViewById(R.id.et_password);

        // Here we set the hint value of the edit text to the data
        // sent by calling activity
        usernameEditText.setHint(data);

    /*    String[] user = {"user","user1","user2"};
        String[] pass = {"pass","pass1","pass2"};*/

       // Hashtable<String, String> login = new Hashtable<String, String>();

        login.put("admin","user");
        login.put("tej","tej");
        login.put("abc","xyz");


        Button btn = (Button) findViewById(R.id.btn_login);

        btn.setOnClickListener(new View.OnClickListener() {

                                   @Override
                                   public void onClick(View v) {

                                       // In the following we prepare data to be sent back to
                                       // the calling activity.
                                       Intent data = new Intent();

                                       // Here we set the data to pass back


                                       if(login.get(usernameEditText.getText().toString()).equals(passwordEditText.getText().toString() )){
                                           Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                                           // Here we set the data to pass back
                                           data.setData(Uri.parse(usernameEditText.getText().toString()));

                                           setResult(RESULT_OK, data);
                                       }
                                       else{
                                           Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();

                                     /*      tx1.setVisibility(View.VISIBLE);
                                           tx1.setBackgroundColor(Color.RED);*/

                                         /*  data.setData(Uri.parse("Failure"));
                                           setResult(RESULT_CANCELED, data);*/
                                       }
                                       // Here we close the activity
                                       finish();
                                   }
                               }

        );

    }


}
