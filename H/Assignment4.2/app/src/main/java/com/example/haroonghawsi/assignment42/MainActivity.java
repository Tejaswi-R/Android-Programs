package com.example.haroonghawsi.assignment42;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    int userEntryCount = 0;
    String userNameInput;
    String userCommentInput;
    String commentDate;
    String allEntries="";

    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yy HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button _submit = (Button) findViewById(R.id.btn_submit);
        _submit.setOnClickListener(__submit);
    }
    private View.OnClickListener __submit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            EditText userName = (EditText) findViewById(R.id.eName);
            EditText userComment = (EditText) findViewById(R.id.eComment);
            Button __submit = (Button) v;

            //User input validation start here..

            int uLen = userName.getText().toString().length();
            int cLen = userComment.getText().toString().length();

            if(uLen == 0){

                userName.setError("User Name is required!");
            }
            else if(cLen == 0){

                userComment.setError("Comment is required!");
            }
            //User Input Validation ended here..
            else{

                //implement adding comment to the page.

                allEntries += ++userEntryCount + ". " + sdf.format(new Date()) + " " + userName.getText().toString() + " " + userComment.getText().toString() + "\n";

                EditText allEntriesText = (EditText) findViewById(R.id.AllEntriesView);
                allEntriesText.setText(allEntries);


            }

        }
    };

}


