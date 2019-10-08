package com.example.greiser.uebenlollipop5_0.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.greiser.uebenlollipop5_0.SQLite.user.UserDBHelper;

import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.DB_NAME;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.DB_VERSION;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_DIVIDE;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_MINUS;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_MULT;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_PLUS;
import static com.example.greiser.uebenlollipop5_0.SQLite.math.category.CategoryDBHelper.COLUMN_OPERATION_CATEGORY;
import static com.example.greiser.uebenlollipop5_0.SQLite.math.category.CategoryDBHelper.LOG_TAG_CATEGORY;
import static com.example.greiser.uebenlollipop5_0.SQLite.math.category.CategoryDBHelper.SQL_CREATE_CATEGORY;
import static com.example.greiser.uebenlollipop5_0.SQLite.math.category.CategoryDBHelper.TABLE_CATEGORY;
import static com.example.greiser.uebenlollipop5_0.SQLite.user.UserDBHelper.LOG_TAG_USER;
import static com.example.greiser.uebenlollipop5_0.SQLite.user.UserDBHelper.SQL_CREATE_USER;

public abstract class UebenDBHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = UserDBHelper.class.getSimpleName();

    public UebenDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            Log.d(LOG_TAG_USER, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_USER + " angelegt.");
            db.execSQL(SQL_CREATE_USER);

            Log.d(LOG_TAG_CATEGORY, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_CATEGORY + " angelegt.");
            db.execSQL(SQL_CREATE_CATEGORY);
            fillTable(db);

        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    private void fillTable(SQLiteDatabase db) {
        String[] operations = {OPERATION_SIGN_PLUS, OPERATION_SIGN_MINUS, OPERATION_SIGN_MULT, OPERATION_SIGN_DIVIDE};
        for (String op: operations) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_OPERATION_CATEGORY, op);
            long rowID = db.insert(TABLE_CATEGORY, null, values);
            Log.i(LOG_TAG_CATEGORY, "Newly inserted row id: " + rowID);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
