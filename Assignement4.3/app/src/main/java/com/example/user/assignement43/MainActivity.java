package com.example.user.assignement43;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public int val1;
    public int val2;
    public int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button _btn7 = (Button) findViewById(R.id.btn_7);
        _btn7.setOnClickListener(btn7_);
        Button _btn8 = (Button) findViewById(R.id.btn_8);
        _btn8.setOnClickListener(btn8_);

        Button _btn9 = (Button) findViewById(R.id.btn_9);
        _btn9.setOnClickListener(btn9_);

        Button _btn4 = (Button) findViewById(R.id.btn_4);
        _btn4.setOnClickListener(btn4_);

        Button _btn5 = (Button) findViewById(R.id.btn_5);
        _btn5.setOnClickListener(btn5_);

        Button _btn6 = (Button) findViewById(R.id.btn_6);
        _btn6.setOnClickListener(btn6_);

        Button _btn1 = (Button) findViewById(R.id.btn_1);
        _btn1.setOnClickListener(btn1_);

        Button _btn2 = (Button) findViewById(R.id.btn_2);
        _btn2.setOnClickListener(btn2_);

        Button _btn3 = (Button) findViewById(R.id.btn_3);
        _btn3.setOnClickListener(btn3_);

        Button _btn0 = (Button) findViewById(R.id.btn_0);
        _btn0.setOnClickListener(btn0_);

        Button _btnC = (Button) findViewById(R.id.clearScreen);
        _btnC.setOnClickListener(btnC_);
    }

    private View.OnClickListener btnC_ = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText _val1 = (EditText) findViewById(R.id._val2);
            _val1.setText("");
        }
    };

    private View.OnClickListener btn0_ = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText _val1 = (EditText) findViewById(R.id._val2);
            _val1.setText(_val1.getText().toString() + 0);
        }
    };

    private View.OnClickListener btn3_ = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText _val1 = (EditText) findViewById(R.id._val2);
            _val1.setText(_val1.getText().toString() + 3);
        }
    };

    private View.OnClickListener btn2_ = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText _val1 = (EditText) findViewById(R.id._val2);
            _val1.setText(_val1.getText().toString() + 2);
        }
    };
    private View.OnClickListener btn1_ = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText _val1 = (EditText) findViewById(R.id._val2);
            _val1.setText(_val1.getText().toString() + 1);
        }
    };

    private View.OnClickListener btn6_ = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText _val1 = (EditText) findViewById(R.id._val2);
            _val1.setText(_val1.getText().toString() + 6);
        }
    };
    private View.OnClickListener btn5_ = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText _val1 = (EditText) findViewById(R.id._val2);
            _val1.setText(_val1.getText().toString() + 5);
        }
    };

    private View.OnClickListener btn4_ = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText _val1 = (EditText) findViewById(R.id._val2);
            _val1.setText(_val1.getText().toString() + 4);
        }
    };
    private View.OnClickListener btn7_ = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText _val1 = (EditText) findViewById(R.id._val2);
            _val1.setText(_val1.getText().toString() + 7);
        };
    };
    private View.OnClickListener btn8_ = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText _val1 = (EditText) findViewById(R.id._val2);

            _val1.setText(_val1.getText().toString() + 8);
        }
    };
    private View.OnClickListener btn9_ = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText _val1 = (EditText) findViewById(R.id._val2);
            _val1.setText(_val1.getText().toString() + 9);
        }
    };

    public void addValues(View view){
        EditText tempVal = (EditText) findViewById(R.id._val2);
        val1 = Integer.parseInt(tempVal.getText().toString());
        result = 1;
        tempVal.setText("");

    }
    public void equalValues(View view){
        EditText tempVal = (EditText) findViewById(R.id._val2);
        val2 = Integer.parseInt(tempVal.getText().toString());
        if(result == 1){
            tempVal.setText(Integer.toString(val1 + val2));
        }
        else if(result == 2){
            tempVal.setText(Integer.toString(val1 - val2));
        }
        else if(result == 3){
            tempVal.setText(Integer.toString(val1 * val2));
        }
        else if(result == 4){
            tempVal.setText(Integer.toString(val1 / val2));
        }
        else if(result == 5){
            tempVal.setText(Integer.toString(val1 % val2));
        }
    }
    public void minusValues(View view){
        EditText tempVal = (EditText) findViewById(R.id._val2);
        val1 = Integer.parseInt(tempVal.getText().toString());
        result = 2;
        tempVal.setText("");
    }
    public void multiplyValues(View view){
        EditText tempVal = (EditText) findViewById(R.id._val2);
        val1 = Integer.parseInt(tempVal.getText().toString());
        result = 3;
        tempVal.setText("");
    }
    public void divideValues(View view){
        EditText tempVal = (EditText) findViewById(R.id._val2);
        val1 = Integer.parseInt(tempVal.getText().toString());
        result = 4;
        tempVal.setText("");
    }
    public void modulusValues(View view){
        EditText tempVal = (EditText) findViewById(R.id._val2);
        val1 = Integer.parseInt(tempVal.getText().toString());
        result = 5;
        tempVal.setText("");
    }
}
