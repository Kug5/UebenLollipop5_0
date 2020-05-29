package com.example.greiser.uebenlollipop5_0.SQLite.math.operation;

import android.content.Context;

public class OperationDS {

    private final OperationDBHelper dbHelper;

    public OperationDS(Context context) {
        dbHelper = new OperationDBHelper(context);
    }

    public long getID(String operation) {
        return dbHelper.getID(operation);
    }

    public String getOperation(long ID) {
        return dbHelper.getOperation(ID);
    }
}
