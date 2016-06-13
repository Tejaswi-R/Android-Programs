package com.example.user.example85;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 26/05/2016.
 */
public class DBAdapter {
    public static final String KEY_ROW_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";

    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "MyDB";
    private static final String TABLE_NAME = "cusomer";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CREATE = "create table " + TABLE_NAME + " (" + KEY_ROW_ID + " integer primary key, " + KEY_NAME + " text not null, " + KEY_PHONE + " text not null);";

    private static final String TABLE_DELETE = "drop table if exists " + TABLE_NAME;

    private Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqlLiteDb;

    public DBAdapter(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);

    }

    // Here we define the DatabaseHelper class
    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(TABLE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ". All old data will be deleted! ");

// Here we remove the table
            db.execSQL(TABLE_DELETE);

// Here we create the table again
            onCreate(db);

        }
    }

    // This method will open the database
    public DBAdapter open() {
        sqlLiteDb = dbHelper.getWritableDatabase();
        return this;
    }

    // This method will close the database
    public void close() {
        dbHelper.close();
    }

    // Here we add a customer to the database
    public long addCustomer(int id, String name, String phone) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ROW_ID, id);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_PHONE, phone);

        return sqlLiteDb.insert(TABLE_NAME, null, initialValues);
    }

    public boolean deleteCustomer(long rowID) {

        return (sqlLiteDb.delete(TABLE_NAME, KEY_ROW_ID + "=" + rowID, null) > 0);
    }

    // This method will retrieve all customers
    public Cursor getAllCustomers() {

        return sqlLiteDb.query(TABLE_NAME, new String[] { KEY_ROW_ID, KEY_NAME,
                KEY_PHONE }, null, null, null, null, null);
    }

// This method will retrieve a particular customer

    public Cursor getCustomer(long rowID) {

        Cursor cursor = sqlLiteDb.query(true, TABLE_NAME, new String[] {
                        KEY_ROW_ID, KEY_NAME, KEY_PHONE }, KEY_ROW_ID + "=" + rowID,
                null, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    // This method will update a customer
    public boolean updateCustomer(long rowID, String name, String phone) {

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PHONE, phone);

        return (sqlLiteDb.update(TABLE_NAME, values, KEY_ROW_ID + "=" + rowID,
                null) > 0);
    }
}
