package com.example.haroonghawsi.assignment81;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Vector;

public class MainActivity extends AppCompatActivity  {


    private SharedPreferences sharedPreferences;
    private String prefsFileName;
    private EditText uName;
    private EditText uhobbies;
    private EditText uPhone;
    private Button uSubmitButton, uButtonSave, uButtonChangeColor;
    private SeekBar seekBar;
    private EditText uSearchName, uSearchPhone;
    private String uSearchNameData, uSearchPhoneData;
    Vector<String> uNameSearch;
    Vector<String> uPhoneSearch;

    private static final int BLOCK_SIZE = 128;
    String path;
    String fileName = "data.txt";

    File file;

    Vector<View> views;

    private static final String FONT_SIZE = "font_size";
    private static final String TEXT_CONTENT = "text_content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme);
        setContentView(R.layout.activity_main);

        prefsFileName = "prefs";
        uName = (EditText) findViewById(R.id.edit_name);
        uhobbies = (EditText) findViewById(R.id.edit_hobbies);
        //uMGender = (RadioButton) findViewById(R.id.user_male);
        //uFGender = (RadioButton) findViewById(R.id.user_female);
        uPhone = (EditText) findViewById(R.id.edit_phone);
        uSubmitButton = (Button) findViewById(R.id.btn_submit);
        uSearchName = (EditText) findViewById(R.id.edit_nameSearch);
        uSearchPhone = (EditText) findViewById(R.id.edit_phoneSearch);

        uButtonSave = (Button) findViewById(R.id.btn_Save);
        uButtonChangeColor = (Button) findViewById(R.id.btn_changeColor);

        uNameSearch = new Vector<String>();
        uPhoneSearch = new Vector<String>();

        views = new Vector<View>();

        views.add(uButtonChangeColor);
        views.add(uButtonSave);
        views.add(uSubmitButton);

        seekBar = (SeekBar) findViewById(R.id.seekbar);


        path = getFilesDir().getAbsolutePath() + "/";

        uSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uSearchNameData = uName.getText().toString() + " " + uhobbies.getText().toString() + " " + uPhone.getText().toString();
                uSearchPhoneData = uPhone.getText().toString() + " " + uName.getText().toString() + " " + uhobbies.getText().toString();

                uNameSearch.add(uSearchNameData);


                ArrayAdapter<String> nameArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, uNameSearch);
                AutoCompleteTextView nameAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.edit_nameSearch);
                nameAutoCompleteTextView.setThreshold(1);
                nameAutoCompleteTextView.setAdapter(nameArrayAdapter);


                uPhoneSearch.add(uSearchPhoneData);

                ArrayAdapter<String> phoneArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, uPhoneSearch);
                AutoCompleteTextView phoneAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.edit_phoneSearch);
                phoneAutoCompleteTextView.setThreshold(1);
                phoneAutoCompleteTextView.setAdapter(phoneArrayAdapter);
            }
        });

        uButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = uName.getText().toString();
                try {
                    file = new File(fileName);
                    file.setReadable(true, false);

                    FileOutputStream fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

                    outputStreamWriter.write(text);
                    outputStreamWriter.close();

                    Toast.makeText(getApplicationContext(), getString(R.string.file_save_fb) + " " + path, Toast.LENGTH_LONG).show();

                    uName.setText("");

                }catch (FileNotFoundException e){
                    e.printStackTrace();

                }catch (IOException e){
                    e.printStackTrace();
                }

                sharedPreferences = getSharedPreferences(prefsFileName, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putFloat(FONT_SIZE, uName.getTextSize());
                editor.putString(TEXT_CONTENT, uName.getText().toString());

                editor.putFloat(FONT_SIZE, uhobbies.getTextSize());
                editor.putString(TEXT_CONTENT, uhobbies.getText().toString());

                //editor.putFloat(FONT_SIZE, uMGender.getTextSize());
                //editor.putString(TEXT_CONTENT, uMGender.getText().toString());

                //editor.putFloat(FONT_SIZE, uFGender.getTextSize());
                //editor.putString(TEXT_CONTENT, uFGender.getText().toString());

                editor.putFloat(FONT_SIZE, uPhone.getTextSize());
                editor.putString(TEXT_CONTENT, uPhone.getText().toString());



                editor.commit();

                Toast.makeText(getApplicationContext(), "File Saved Successfully", Toast.LENGTH_LONG).show();
            }
        });


        SharedPreferences loadedSharedPrefs = getSharedPreferences(prefsFileName, MODE_PRIVATE);
        float fontSize = loadedSharedPrefs.getFloat(FONT_SIZE, 14.0f);

        seekBar.setProgress((int) fontSize);

        uName.setText(loadedSharedPrefs.getString(TEXT_CONTENT, ""));
        uName.setTextSize(seekBar.getProgress());

        uhobbies.setText(loadedSharedPrefs.getString(TEXT_CONTENT, ""));
        uhobbies.setTextSize(seekBar.getProgress());

        //uMGender.setText(loadedSharedPrefs.getString(TEXT_CONTENT, ""));
        //uMGender.setTextSize(seekBar.getProgress());

        //uFGender.setText(loadedSharedPrefs.getString(TEXT_CONTENT, ""));
        //uFGender.setTextSize(seekBar.getProgress());

        uPhone.setText(loadedSharedPrefs.getString(TEXT_CONTENT, ""));
        uPhone.setTextSize(seekBar.getProgress());


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
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                uName.setTextSize(progress);
                uhobbies.setTextSize(progress);
                //uMGender.setTextSize(progress);
                //uFGender.setTextSize(progress);
                uPhone.setTextSize(progress);
            }
        });

        uButtonChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerForContextMenu(uButtonChangeColor);
                openContextMenu(uButtonChangeColor);

            }
        });

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Change Font Colors");
        menu.add(0, v.getId(), 0, "Red");
        menu.add(0, v.getId(), 0, "Green");
        menu.add(0, v.getId(), 0, "Blue");

        SubMenu textSizeSubMenu = menu.addSubMenu("Font Size");

        MenuItem smallFont = textSizeSubMenu.add(0,5,0,"8");
        MenuItem mediumFont = textSizeSubMenu.add(0,6,0,"12");
        MenuItem largeFont = textSizeSubMenu.add(0,6,0,"24");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        TextView textView = (TextView) findViewById(R.id.edit_name);
        TextView textView1 = (TextView) findViewById(R.id.edit_hobbies);
        TextView textView2 = (TextView) findViewById(R.id.edit_phone);
        TextView textView3 = (TextView) findViewById(R.id.edit_nameSearch);
        TextView textView4 = (TextView) findViewById(R.id.edit_phoneSearch);

        if (item.getTitle() == "Red") {
            Toast.makeText(this, "Red invoked", Toast.LENGTH_SHORT).show();
            textView.setTextColor(Color.RED);
            textView1.setTextColor(Color.RED);
            textView2.setTextColor(Color.RED);
            textView3.setTextColor(Color.RED);
            textView4. setTextColor(Color.RED);

        } else if (item.getTitle() == "Green") {
            Toast.makeText(this, "Green invoked", Toast.LENGTH_SHORT).show();
            textView.setTextColor(Color.GREEN);
            textView1.setTextColor(Color.GREEN);
            textView2.setTextColor(Color.GREEN);
            textView3.setTextColor(Color.GREEN);
            textView4.setTextColor(Color.GREEN);


        } else if (item.getTitle() == "Blue") {
            Toast.makeText(this, "Blue invoked", Toast.LENGTH_SHORT).show();
            textView.setTextColor(Color.BLUE);
            textView1.setTextColor(Color.BLUE);
            textView2.setTextColor(Color.BLUE);
            textView3.setTextColor(Color.BLUE);
            textView4.setTextColor(Color.BLUE);

        }
        else if(item.getTitle() == "24"){

            textView.setTextSize(24);
            textView1.setTextSize(24);
            textView2.setTextSize(24);
            textView3.setTextSize(24);
            textView4.setTextSize(24);


    } else if (item.getTitle() == "8") {

            textView.setTextSize(8);
            textView1.setTextSize(8);
            textView2.setTextSize(8);
            textView3.setTextSize(8);
            textView4.setTextSize(8);


    } else if(item.getTitle() == "12"){
            textView.setTextSize(12);
            textView1.setTextSize(12);
            textView2.setTextSize(12);
            textView3.setTextSize(12);
            textView4.setTextSize(12);
    }
    else {
        return false;
    }
    return true;
}
    private void createMenu(Menu menu){

        MenuItem mItem1 = menu.add(0,2,0,"Red");
        MenuItem mItem2 = menu.add(0,3,1,"Green");
        MenuItem mItem3 = menu.add(0,4,2,"Blue");
    }

}

