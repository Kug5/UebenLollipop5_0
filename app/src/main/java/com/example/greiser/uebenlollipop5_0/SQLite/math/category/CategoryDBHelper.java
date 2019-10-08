package com.example.greiser.uebenlollipop5_0.SQLite.math.category;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.greiser.uebenlollipop5_0.SQLite.UebenDBHelper;

public class CategoryDBHelper extends UebenDBHelper {

    public static final String LOG_TAG_CATEGORY = CategoryDBHelper.class.getSimpleName();

    public static final String TABLE_CATEGORY = "Category";
    private static final String COLUMN_ID = "_id";
    public static final String COLUMN_OPERATION_CATEGORY = "operation";

    public static final String SQL_CREATE_CATEGORY =
            "CREATE TABLE " + TABLE_CATEGORY +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
    COLUMN_OPERATION_CATEGORY + " TEXT NOT NULL);";
    private static final String SQL_GET = "SELECT * FROM " + TABLE_CATEGORY;

    CategoryDBHelper(Context context) {
        super(context);
    }

    long getID(String operation) {
        SQLiteDatabase readable = this.getReadableDatabase();
        Cursor cursor = readable.rawQuery(SQL_GET +  " WHERE " + COLUMN_OPERATION_CATEGORY + "=\"" + operation + "\";", null);
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
            operation = cursor.getString(cursor.getColumnIndex(COLUMN_OPERATION_CATEGORY));
        }
        readable.close();
        return operation;
    }
}
