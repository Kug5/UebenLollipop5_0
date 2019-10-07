package com.example.greiser.uebenlollipop5_0.SQLite.math.category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.greiser.uebenlollipop5_0.SQLite.UebenDBHelper;

public class CategoryDBHelper extends UebenDBHelper {

    private static final String LOG_TAG = CategoryDBHelper.class.getSimpleName();

    private static final String TABLE_CATEGORY = "Category";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_OPERATION = "operation";

    private static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_CATEGORY +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
    COLUMN_OPERATION + " TEXT NOT NULL);";
    private static final String SQL_GET = "SELECT * FROM " + TABLE_CATEGORY;

    CategoryDBHelper(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db, SQL_CREATE, LOG_TAG);
        fillTable();
    }

    private void fillTable() {
        String[] operations = {"+", "-", "*", ":"};
        SQLiteDatabase writable = this.getWritableDatabase();
        for (String op: operations) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_OPERATION, op);
            long rowID = writable.insert(TABLE_CATEGORY, null, values);
            Log.i("", "Newly inserted row id: " + rowID);
        }
        writable.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    long getID(String operation) {
        SQLiteDatabase readable = this.getReadableDatabase();
        Cursor cursor = readable.rawQuery(SQL_GET +  "WHERE " + COLUMN_OPERATION + "=" + operation + ";", null);
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
            return id;
        }
        return -1;
    }

    String getOperation(long id) {
        SQLiteDatabase readable = this.getReadableDatabase();
        Cursor cursor = readable.rawQuery(SQL_GET +  "WHERE " + COLUMN_ID + "=" + id + ";", null);
        while (cursor.moveToNext()) {
            String operation = cursor.getString(cursor.getColumnIndex(COLUMN_OPERATION));
            return operation;
        }
        return null;
    }
}
