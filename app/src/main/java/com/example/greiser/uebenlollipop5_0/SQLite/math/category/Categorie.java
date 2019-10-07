package com.example.greiser.uebenlollipop5_0.SQLite.math.category;

public class Category {

    private long id;
    private String operation;

    public Category(long id, String operation) {
        this.id = id;
        this.operation = operation;
    }

    public long getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }
}
