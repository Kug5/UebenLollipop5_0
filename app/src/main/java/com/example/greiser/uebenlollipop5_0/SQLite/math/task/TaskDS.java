package com.example.greiser.uebenlollipop5_0.SQLite.math.task;

import android.content.Context;

import java.util.ArrayList;

public class TaskDS {

    private final TaskDBHelper dbHelper;

    public TaskDS(Context context) {
        dbHelper = new TaskDBHelper(context);
    }

    public ArrayList<Task> getTaskByCategory(String operation) {
        return dbHelper.getTasksByOperation(operation);
    }
}
