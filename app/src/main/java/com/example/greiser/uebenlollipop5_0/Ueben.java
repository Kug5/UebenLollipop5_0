package com.example.greiser.uebenlollipop5_0;

import android.app.Application;

public class Ueben extends Application {

    public static final String OPEARATION_PLUSMINUS = "plusminus";
    public static final String OPEARATION_MULT = "mult";

    private String username;
    private String operation;
    private int max;
    private int many;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setMany(int many) {
        this.many = many;
    }

    public int getMany() {
        return many;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
