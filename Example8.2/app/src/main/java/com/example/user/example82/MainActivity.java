package com.example.user.example82;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText commentEditText;
    private static final int BLOCK_SIZE = 128;

    //Here we define file path on the internal memory
    String path;

    //Here we define full file name with its path
    String commentFileName = "comments.txt";

    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        path = getFilesDir().getAbsolutePath() + "/";
        //commentFileName = path + commentFileName;

        commentEditText =(EditText) findViewById(R.id.et_comment);
        Button saveButton =(Button) findViewById(R.id.btn_save);
        Button loadButton =(Button) findViewById(R.id.btn_load);


        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String text = commentEditText.getText().toString();
                try {

                    file = new File(commentFileName);

                    //Here we make the file readable by other applications too.
                    file.setReadable(true, false);

                    FileOutputStream fileOutputStream = openFileOutput(commentFileName, MODE_PRIVATE);

                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

                    //Here we write the text to the file
                    outputStreamWriter.write(text);
                    outputStreamWriter.close();


                    Toast.makeText(getApplicationContext(), getString(R.string.file_save_fb), Toast.LENGTH_LONG).show();


                    commentEditText.setText("");


                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



                // TODO Auto-generated method stub

            }
        });



        loadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FileInputStream fileInputStream;
                InputStreamReader inputStreamReader;

                try {
                    fileInputStream = openFileInput(commentFileName);

                    inputStreamReader = new InputStreamReader(fileInputStream);


                    char[] inputBuffer = new char[BLOCK_SIZE];
                    String fileContent="";


                    int charRead;

                    while((charRead = inputStreamReader.read(inputBuffer))>0) {

                        //Here we convert chars to string
                        String readString =String.copyValueOf(inputBuffer, 0, charRead);
                        fileContent+=readString;


                        //Here we re-initialize the inputBuffer array to remove its content
                        inputBuffer = new char[BLOCK_SIZE];

                    }


                    //Here we set the text of the commentEditText to the one, which has been read
                    commentEditText.setText(fileContent);


                    Toast.makeText(getApplicationContext(), getString(R.string.file_load_fb), Toast.LENGTH_LONG).show();





                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
        });
    }

}
