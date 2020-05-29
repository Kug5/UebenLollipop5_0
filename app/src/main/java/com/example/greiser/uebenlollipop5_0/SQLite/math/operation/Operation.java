package com.example.greiser.uebenlollipop5_0.SQLite.math.operation;

public class Operation {

    private long id;
    private String operation;

    public Operation(long id, String operation) {
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
