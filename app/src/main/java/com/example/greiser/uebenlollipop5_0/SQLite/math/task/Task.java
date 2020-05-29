package com.example.greiser.uebenlollipop5_0.SQLite.math.task;

import com.example.greiser.uebenlollipop5_0.SQLite.math.operation.Operation;
import com.example.greiser.uebenlollipop5_0.SQLite.trial.Trial;

import java.util.ArrayList;

public class Task {
    private long id;
    private int left;
    private int right;
    private int result;
    private Operation operation;
    private ArrayList<Trial> trials;

    public Task(int left, int right, int result, String operation) {
        this.left = left;
        this.right = right;
        this.result = result;
        this.operation = operation;
        this.trials = new ArrayList<>();
    }

    public Task(int left, int right, int result, String operation, ArrayList<Trial> trials) {
        this(left, right, result, operation);
        this.trials = trials;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getResult() {
        return result;
    }

    public int getRight() {
        return right;
    }

    public int getLeft() {
        return left;
    }

    public String toString() {
        return new StringBuilder(left)
                .append(" ")
                .append(operation)
                .append(" ")
                .append(right)
                .append(" = ")
                .append(result)
                .toString();
    }

    public ArrayList<Trial> getTrials() {
        return trials;
    }

    public long getId() {
        return id;
    }
}
