package com.example.greiser.uebenlollipop5_0.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.DB_NAME;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.DB_VERSION;

public abstract class UebenDBHelper extends SQLiteOpenHelper {

    public UebenDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db, String SQL_CREATE, String LOG_TAG) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }
}
