package com.example.user.assignment72;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends Activity {

    int loginRequestCode = 1;
    TextView usernameTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameTextView = (TextView) findViewById(R.id.tw_usrname);
        usernameTextView.setVisibility(View.INVISIBLE);
        Button loginButton = (Button) findViewById(R.id.btn_login);

        loginButton.setOnClickListener(buttonClickListener);
    }

    //This method starts the activity.
    private void startActivity() {

        //This would start LoginActivity without expecting any result.
        //startActivity(new Intent(this, LoginActivity.class));
        //startActivity(new Intent("mylogin.LoginActivity"));


        //Here we start LoginActivity and expect results from it.
  /*startActivityForResult(new Intent(this, LoginActivity.class), request_code);*/
        //startActivityForResult(new Intent("mylogin.LoginActivity"), request_code);

        //In the following we pass some data to the invoked activity
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("data",new Date());
        startActivityForResult(intent, loginRequestCode);

    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            //Here we initialize the content of the text view
            usernameTextView.setText(getString(R.string.username_txt));
            startActivity();
        }
    };


    //This method processes data sent back from the activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == loginRequestCode) {
            if (resultCode == RESULT_OK) {
                //Toast.makeText(this, data.getData().toString(), Toast.LENGTH_LONG).show();

                usernameTextView.setVisibility(View.VISIBLE);
                usernameTextView.append(" " + data.getDataString());

                Intent intent = new Intent(getApplicationContext(),EventInfo.class);
                startActivity(intent);


            } else {
                if (resultCode == RESULT_CANCELED) {
                    //Toast.makeText(this, data.getData().toString(), Toast.LENGTH_LONG).show();

                    usernameTextView.setVisibility(View.VISIBLE);
                    usernameTextView.append(" " + data.getDataString());

                }
            }

        }
    }
}
