package com.example.user.example81;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private String prefsFileName;
    private EditText commentEditText;
    private SeekBar seekBar;
    private Button saveButton;

    private static final String FONT_SIZE="font_size";
    private static final String TEXT_CONTENT="text_content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Here we get the name of the shared preferences file defined in strings.xml file.
        prefsFileName= getResources().getString(R.string.shared_prefs_file_name);

        commentEditText = (EditText) findViewById(R.id.et_comment);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        saveButton = (Button) findViewById(R.id.btn_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Here we initialize the SharedPreferences object
                sharedPreferences = getSharedPreferences(prefsFileName, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();


                //Here we save the values in the EditText view to preferences
                editor.putFloat(FONT_SIZE, commentEditText.getTextSize());
                editor.putString(TEXT_CONTENT, commentEditText.getText().toString());


                //Here we save the values
                editor.commit();

                Toast.makeText(getApplicationContext(), getString(R.string.file_save_fb), Toast.LENGTH_LONG).show();


            }
        });

        //Here we load the SharedPreferences object
        SharedPreferences loadedSharedPrefs = getSharedPreferences(prefsFileName, MODE_PRIVATE);


        //Here we set the TextView font size to the previously saved values
        float fontSize = loadedSharedPrefs.getFloat(FONT_SIZE, 14.0f);


        //Here we initialize the SeekBar and EditText objects
        seekBar.setProgress((int) fontSize);
        commentEditText.setText(loadedSharedPrefs.getString(TEXT_CONTENT, ""));
        commentEditText.setTextSize(seekBar.getProgress());


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                commentEditText.setTextSize(progress);

            }
        });
    }

}
