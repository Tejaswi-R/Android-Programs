package com.example.user.example41;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendButton = (Button) findViewById(R.id.btn_send);
        sendButton.setOnClickListener(headPhoneX100);

        Button resetButton = (Button) findViewById(R.id.btn_reset);
        resetButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Button sendButton = (Button) findViewById(R.id.btn_send);

                if(sendButton.isShown())
                    sendButton.setVisibility(View.INVISIBLE);
                else
                    sendButton.setVisibility(View.VISIBLE);

                EditText commentEditText = (EditText) findViewById(R.id.et_comment);

                commentEditText.setText("");

            }
        });


    }

    private OnClickListener headPhoneX100 = new OnClickListener() {

        @Override
        public void onClick(View v) {

            Button sendButton = (Button) v;

            if(sendButton.getText().equals("Send"))
                sendButton.setText("Re-send");
            else
                sendButton.setText("Send");


            EditText commentEditText = (EditText) findViewById(R.id.et_comment);
            TextView summaryTextView =(TextView) findViewById(R.id.summary_tw);
            if(commentEditText.getText().length()==0)
                summaryTextView.setText(R.string.empty_edit_txt);
            else {

                if(summaryTextView.getText().toString().indexOf(R.string.empty_edit_txt)!=0){
                    summaryTextView.setText("");
                    summaryTextView.append(commentEditText.getText());

                }

                else
                    summaryTextView.append(commentEditText.getText());


            }


        }
    };


}