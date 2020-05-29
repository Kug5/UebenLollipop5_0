package com.example.greiser.uebenlollipop5_0.SQLite.math.task;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.greiser.uebenlollipop5_0.SQLite.UebenDBHelper;
import com.example.greiser.uebenlollipop5_0.SQLite.math.operation.Operation;
import com.example.greiser.uebenlollipop5_0.SQLite.trial.Trial;

import java.util.ArrayList;

import static com.example.greiser.uebenlollipop5_0.SQLite.math.operation.OperationDBHelper.COLUMN_ID_OPERATION;
import static com.example.greiser.uebenlollipop5_0.SQLite.math.operation.OperationDBHelper.TABLE_OPERATION;
import static com.example.greiser.uebenlollipop5_0.SQLite.trial.Trial.COLUMN_ID_TRIAL;
import static com.example.greiser.uebenlollipop5_0.SQLite.trial.Trial.COLUMN_POSITIVE_TRIAL;
import static com.example.greiser.uebenlollipop5_0.SQLite.trial.Trial.COLUMN_TIMESTAMP_TRIAL;
import static com.example.greiser.uebenlollipop5_0.SQLite.trial.Trial.TABLE_TRIAL;

public class TaskDBHelper extends UebenDBHelper {

    public static  final String LOG_TAG_TASK = TaskDBHelper.class.getSimpleName();

    private static final String TABLE_TASK = "Task";
    private static final String COLUMN_ID_TASK = "_id";
    private static final String COLUMN_LEFT = "left";
    private static final String COLUMN_RIGHT = "right";
    private static final String COLUMN_RESULT = "result";
    private static final String COLUMN_OPERATION_ID = "operation_id";
    private static final String COLUMN_TRIALS_ID = "trials_id_list";

    public TaskDBHelper(Context context) {
        super(context);
    }

    public static final String SQL_CREATE_TASK = "CREATE TABLE " + TABLE_TASK + " ("
            + COLUMN_ID_TASK + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LEFT + "INTEGER NOT NULL,"
            + COLUMN_RIGHT + "INTEGER NOT NULL,"
            + COLUMN_RESULT + "INTEGER NOT NULL,"
            + COLUMN_OPERATION_ID + "INTEGER,"
            + COLUMN_TRIALS_ID + "TEXT," // 1 2 3 4 -> space separates list
            + "FOREIGN KEY("+ COLUMN_OPERATION_ID +") REFERENCES "+ TABLE_OPERATION +"("+ COLUMN_ID_OPERATION +")"
//            + "FOREIGN KEY("+ COLUMN_TRIALS_ID +") REFERENCES "+TABLE_TRIALS+"("+ COLUMN_ID_TRIALS +")"
            +");";

    ArrayList<Task> getTasksByOperation(String operation) {
        SQLiteDatabase readable = this.getReadableDatabase();
        Cursor cursor = readable.rawQuery("", null);
        ArrayList<Task> tasks = new ArrayList<>();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID_TASK));
            int left = cursor.getInt(cursor.getColumnIndex(COLUMN_LEFT));
            int right = cursor.getInt(cursor.getColumnIndex(COLUMN_RIGHT));
            int result = cursor.getInt(cursor.getColumnIndex(COLUMN_RESULT));
            String trialsIDs = cursor.getString(cursor.getColumnIndex(COLUMN_TRIALS_ID));
            ArrayList<Trial> trials = getTrials(trialsIDs.split(" "));
            tasks.add(new Task(left, right, result, operation, trials));
        }
        readable.close();
        return tasks;
    }

    private ArrayList<Trial> getTrials(String[] trialIDs) {
        ArrayList<Trial> trials = new ArrayList<>();

        if (trialIDs == null || trialIDs.length == 0) {
            return trials;
        }

        StringBuilder select = getSQLSelectTrials(trialIDs);
        SQLiteDatabase readable = this.getReadableDatabase();
        Cursor cursor = readable.rawQuery(select.toString(), null);
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID_TRIAL));
            String timestamp = cursor.getString(cursor.getColumnIndex(COLUMN_TIMESTAMP_TRIAL));
            boolean positive = cursor.getInt(cursor.getColumnIndex(COLUMN_POSITIVE_TRIAL)) == 1;
            trials.add(new Trial(id, timestamp, positive));
        }

        readable.close();
        return trials;

    }

    @NonNull
    private StringBuilder getSQLSelectTrials(String[] trialIDs) {
        StringBuilder select = new StringBuilder("SELECT * FROM " + TABLE_TRIAL + " WHERE ID = ");

        for (int i=0; i< trialIDs.length; i++) {
            try {
                int id = Integer.parseInt(trialIDs[i]);
                if (i > 0) {
                    select.append(" OR id = ");
                }
                select.append(id);
            } catch (NumberFormatException e) {
                throw e;
            }

        }
        select.append(";");
        return select;
    }

}
