package com.example.greiser.uebenlollipop5_0.SQLite.user;

import android.content.Context;

import java.util.ArrayList;

public class UserDS {

    private final UserDBHelper dbHelper;

    public UserDS(Context context) {
        dbHelper = new UserDBHelper(context);
    }

    public long addUser(String name) {
        return dbHelper.addUser(name);
    }

    public User getUser(String name) {
        return dbHelper.getUser(name);
    }

    public ArrayList<String> getAllExistingUsernames() {
        return dbHelper.getAllExistingUsernames();
    }
}
