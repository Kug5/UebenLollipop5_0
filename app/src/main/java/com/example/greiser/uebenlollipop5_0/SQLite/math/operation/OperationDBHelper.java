package com.example.greiser.uebenlollipop5_0.SQLite.math.operation;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.greiser.uebenlollipop5_0.SQLite.UebenDBHelper;

public class OperationDBHelper extends UebenDBHelper {

    public static final String LOG_TAG_OPERATION = OperationDBHelper.class.getSimpleName();

    public static final String TABLE_OPERATION = "Operation";
    public static final String COLUMN_ID_OPERATION = "_id";
    public static final String COLUMN_OPERATION_OPERATION = "operation";

    public static final String SQL_CREATE_OPERATION =
            "CREATE TABLE " + TABLE_OPERATION +
            "(" + COLUMN_ID_OPERATION + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_OPERATION_OPERATION + " TEXT NOT NULL);";
    private static final String SQL_GET = "SELECT * FROM " + TABLE_OPERATION;

    OperationDBHelper(Context context) {
        super(context);
    }

    long getID(String operation) {
        SQLiteDatabase readable = this.getReadableDatabase();
        Cursor cursor = readable.rawQuery(SQL_GET +  " WHERE " + COLUMN_OPERATION_OPERATION + "=\"" + operation + "\";", null);
        long id = -1;
        while (cursor.moveToNext()) {
            id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID_OPERATION));
        }
        readable.close();
        return id;
    }

    String getOperation(long id) {
        SQLiteDatabase readable = this.getReadableDatabase();
        Cursor cursor = readable.rawQuery(SQL_GET +  " WHERE " + COLUMN_ID_OPERATION + "=" + id + ";", null);
        String operation = null;
        while (cursor.moveToNext()) {
            operation = cursor.getString(cursor.getColumnIndex(COLUMN_OPERATION_OPERATION));
        }
        readable.close();
        return operation;
    }
}
