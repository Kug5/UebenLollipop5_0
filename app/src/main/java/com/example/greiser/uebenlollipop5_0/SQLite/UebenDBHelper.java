package com.example.greiser.uebenlollipop5_0.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.greiser.uebenlollipop5_0.SQLite.math.task.Task;
import com.example.greiser.uebenlollipop5_0.SQLite.user.UserDBHelper;

import java.util.ArrayList;

import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.DB_NAME;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.DB_VERSION;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_DIVIDE;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_MINUS;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_MULT;
import static com.example.greiser.uebenlollipop5_0.SQLite.Constants.OPERATION_SIGN_PLUS;
import static com.example.greiser.uebenlollipop5_0.SQLite.math.operation.OperationDBHelper.COLUMN_OPERATION_OPERATION;
import static com.example.greiser.uebenlollipop5_0.SQLite.math.operation.OperationDBHelper.LOG_TAG_OPERATION;
import static com.example.greiser.uebenlollipop5_0.SQLite.math.operation.OperationDBHelper.SQL_CREATE_OPERATION;
import static com.example.greiser.uebenlollipop5_0.SQLite.math.operation.OperationDBHelper.TABLE_OPERATION;
import static com.example.greiser.uebenlollipop5_0.SQLite.math.task.TaskDBHelper.LOG_TAG_TASK;
import static com.example.greiser.uebenlollipop5_0.SQLite.math.task.TaskDBHelper.SQL_CREATE_TASK;
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

            Log.d(LOG_TAG_OPERATION, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_OPERATION + " angelegt.");
            db.execSQL(SQL_CREATE_OPERATION);
            fillOperationTable(db);

            Log.d(LOG_TAG_TASK, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_TASK + " angelegt.");
            db.execSQL(SQL_CREATE_TASK);
            fillTaskTable(db);

        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    private void fillTaskTable(SQLiteDatabase db) {
        // TODO create all task for +, -, * and :
        Task[] plus = getAllPlusTask();
    }

    private Task[] getAllPlusTask() {
        ArrayList<Task> plusTasks= new ArrayList<>();
        for (int left = 0; left <= 100; left++) {
            for (int right = 0; right <= 100; right++) {
                plusTasks.add(new Task(left, ));
            }
        }
    }

    private void fillOperationTable(SQLiteDatabase db) {
        String[] operations = {OPERATION_SIGN_PLUS, OPERATION_SIGN_MINUS, OPERATION_SIGN_MULT, OPERATION_SIGN_DIVIDE};
        for (String op: operations) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_OPERATION_OPERATION, op);
            long rowID = db.insert(TABLE_OPERATION, null, values);
            Log.i(LOG_TAG_OPERATION, "Newly inserted row id: " + rowID);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
