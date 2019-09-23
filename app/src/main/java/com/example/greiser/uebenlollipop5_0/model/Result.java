package com.example.greiser.uebenlollipop5_0.model;

import android.support.annotation.NonNull;

public class Result implements Comparable {
    private final String date;
    private final String result;

    public Result(String date, String result) {
        this.date = date;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int compareTo(@NonNull Object o) {

        if (o instanceof Result) {
            return Integer.parseInt(((Result) o).getDate()) < Integer.parseInt(this.getDate()) ? 1 : 0;
        }

        return -1;

    }
}