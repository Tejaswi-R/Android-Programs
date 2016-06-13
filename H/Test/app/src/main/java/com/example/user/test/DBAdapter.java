package com.example.user.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Time;
import java.util.Date;

public class DBAdapter {

    public static final String KEY_ROW_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_NAME = "name";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";

    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME="MyDB";

    private static final String TABLE_NAME="customer";

    private static String tableName;

    private static final int DATABASE_VERSION = 1;

    private static String TABLE_CREATE = "create table " + tableName
            + " (id integer "
            + "primary key, title text not null ,name text not null, date number not null,time number not null);";
    private static String TABLE_DELETE = "DROP TABLE IF EXISTS " + tableName;

    private Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqlLiteDb;
    private String dbPath;
    private static String dbName;

    // Here we define the constructor
    public DBAdapter(Context context, String dbPath, String dbName,
                     String tableName) {
        this.context = context;
        this.dbPath = dbPath;
        DBAdapter.dbName = dbName;
        DBAdapter.tableName = tableName;

        dbHelper = new DatabaseHelper(context);

    }

    // Here we define the DatabaseHelper class
    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            // super(context, DATABASE_NAME, null, DATABASE_VERSION);
            super(context, dbName, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

   /*
    * try { db.execSQL(TABLE_CREATE); }catch(SQLException e) {
    * e.printStackTrace(); }
    */
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
        // sqlLiteDb=dbHelper.getWritableDatabase();

        sqlLiteDb = SQLiteDatabase.openDatabase(dbPath + dbName, null,
                SQLiteDatabase.OPEN_READWRITE);
        return this;
    }

    // This method will close the database
    public void close() {
        dbHelper.close();
    }

    // Here we add a customer to the database
    public long addUser(int id, String name) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ROW_ID, id);
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_DATE, String.valueOf(date));
        initialValues.put(KEY_TIME, String.valueOf(time));


        return sqlLiteDb.insert(tableName, null, initialValues);
    }

    public boolean deleteUser(long rowID) {

        return (sqlLiteDb.delete(tableName, KEY_ROW_ID + "=" + rowID, null) > 0);
    }

    // This method will retrieve all customers
    public Cursor getAllUsers() {

        return sqlLiteDb.query(tableName, new String[] { KEY_ROW_ID, KEY_TITLE,KEY_NAME,
                KEY_DATE,KEY_TIME }, null, null, null, null, null);

    }

    // This method will retrieve a particular customer

    public Cursor getUsers(long rowID) {
        Cursor cursor = sqlLiteDb.query(true, tableName, new String[] {
                        KEY_ROW_ID, KEY_TITLE,KEY_NAME, KEY_DATE, KEY_TIME }, KEY_ROW_ID + "=" + rowID,
                null, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    // This method will update a customer
    public boolean updateCustomer(long rowID,  String title, String name, Date date, Time time) {

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_NAME, name);
        values.put(KEY_DATE, String.valueOf(date));
        values.put(KEY_TIME, String.valueOf(time));
        return (sqlLiteDb.update(tableName, values, KEY_ROW_ID + "=" + rowID,
                null) > 0);
    }

}