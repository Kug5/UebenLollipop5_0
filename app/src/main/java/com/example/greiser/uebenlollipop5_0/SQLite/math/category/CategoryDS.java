package com.example.greiser.uebenlollipop5_0.SQLite.math.category;

import android.content.Context;

public class CategoryDS {

    private final CategoryDBHelper dbHelper;

    public CategoryDS(Context context) {
        dbHelper = new CategoryDBHelper(context);
    }

    public long getID(String operation) {
        return dbHelper.getID(operation);
    }

    public String getOperation(long ID) {
        return dbHelper.getOperation(ID);
    }
}