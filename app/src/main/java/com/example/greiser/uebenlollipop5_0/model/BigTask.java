package com.example.greiser.uebenlollipop5_0.model;

public class BigTask {

    String displayTask;
    int i;
    int j;
    int result;
    public int box;

    public BigTask(String displayTask, int i, int j, int result, int box) {
        this.displayTask = displayTask;
        this.i = i;
        this.j = j;
        this.result = result;
        this.box = box;
    }

    public int getBox() {
        return box;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getResult() {
        return result;
    }

    public String getDisplayTask() {
        return displayTask;
    }

    public void increaseBox() {
        this.box++;
    }

    public void setToFirstBox() {
        this.box = 1;
    }
}
