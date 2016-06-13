package com.example.user.assignment91;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 26/05/2016.
 */
public class DBAdapter {

    public static final String KEY_ROW_ID = "id";
    public static final String KEY_FNAME = "fname";
    public static final String KEY_LNAME = "lname";
    public static final String KEY_UNAME = "uname";
    public static final String KEY_PASS = "pass";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_HOBBIES = "hobbies";

    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "Phone";
    private static final String TABLE_NAME = "userinfo";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CREATE = "create table " + TABLE_NAME + " (" + KEY_ROW_ID + " integer primary key, "
            + KEY_FNAME + " text not null, "
            + KEY_LNAME + " text not null, "
            + KEY_UNAME + " text not null, "
            + KEY_PASS + " text not null, "
            + KEY_HOBBIES + " text not null, "
            + KEY_PHONE + " text not null);";

    private static final String TABLE_DELETE = "drop table if exists " + TABLE_NAME;


}
