package com.example.greiser.uebenlollipop5_0.SQLite.math.category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.greiser.uebenlollipop5_0.SQLite.UebenDBHelper;

import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_DIVIDE;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_MINUS;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_MULT;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_PLUS;

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
        fillTable(db);
    }

    private void fillTable(SQLiteDatabase db) {
        String[] operations = {OPERATION_SIGN_PLUS, OPERATION_SIGN_MINUS, OPERATION_SIGN_MULT, OPERATION_SIGN_DIVIDE};
        for (String op: operations) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_OPERATION, op);
            long rowID = db.insert(TABLE_CATEGORY, null, values);
            Log.i("", "Newly inserted row id: " + rowID);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    long getID(String operation) {
        SQLiteDatabase readable = this.getReadableDatabase();
        Cursor cursor = readable.rawQuery(SQL_GET +  " WHERE " + COLUMN_OPERATION + "=\"" + operation + "\";", null);
        long id = -1;
        while (cursor.moveToNext()) {
            id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        }
        readable.close();
        return id;
    }

    String getOperation(long id) {
        SQLiteDatabase readable = this.getReadableDatabase();
        Cursor cursor = readable.rawQuery(SQL_GET +  " WHERE " + COLUMN_ID + "=" + id + ";", null);
        String operation = null;
        while (cursor.moveToNext()) {
            operation = cursor.getString(cursor.getColumnIndex(COLUMN_OPERATION));
        }
        readable.close();
        return operation;
    }
}
