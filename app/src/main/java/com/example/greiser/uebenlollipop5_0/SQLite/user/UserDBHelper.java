package com.example.greiser.uebenlollipop5_0.SQLite.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.greiser.uebenlollipop5_0.SQLite.UebenDBHelper;

import java.util.ArrayList;

public class UserDBHelper extends UebenDBHelper {

    public static final String LOG_TAG_USER = UserDBHelper.class.getSimpleName();

    private static final String TABLE_USER = "User";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "username";

    public static final String SQL_CREATE_USER = "Create TABLE " + TABLE_USER + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERNAME + " TEXT NOT NULL UNIQUE "

//            + "..."

            + ");";

    UserDBHelper(Context context) { super(context); }

    long addUser(String name) {
        Log.e(LOG_TAG_USER, "will add " + name);
        SQLiteDatabase writable = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, name);
        long rowID = writable.insert(TABLE_USER, null, values);
        Log.i(LOG_TAG_USER, "Newly inserted row id: " + rowID);
        writable.close();
        return rowID;
    }


    User getUser(String name) {
        SQLiteDatabase readable = this.getReadableDatabase();
        Cursor cursor = readable.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USERNAME + "=\"" + name + "\";", null);
        String username = null;
        long id = -1;
        if (cursor.moveToNext()) {
            username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        }
        readable.close();
        return  username != null ? new User(id, username) : null;
    }

    ArrayList<String> getAllExistingUsernames() {
        SQLiteDatabase readable = this.getReadableDatabase();
        Cursor cursor = readable.rawQuery("SELECT " + COLUMN_USERNAME +" FROM " + TABLE_USER + ";", null);
        ArrayList<String> usernames = new ArrayList<>();
        while (cursor.moveToNext()) {
            usernames.add(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
        }
        readable.close();
        return  usernames;
    }
}
