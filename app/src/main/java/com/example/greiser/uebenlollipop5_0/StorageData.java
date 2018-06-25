package com.example.greiser.uebenlollipop5_0;

import java.util.ArrayList;
import java.util.List;

class StorageData {

    private long date;
    private int counter;

    private List<BigTask> step1;
    private List<BigTask> step2;

    public void setDate(long date) {
        this.date = date;
    }

    public void increaseCounter() {
        this.counter++;
    }

    public int getCounter() {
        return counter;
    }

    public void setTask (String displayTask, int i, int j, int result, boolean ready) {

        if(step1 == null) {
            this.step1 = new ArrayList<>();
        }
        if(step2 == null) {
            this.step2 = new ArrayList<>();
        }

        if (ready) {
            step2.add(new BigTask(displayTask, i, j, result, ready));
        } else {
            step1.add(new BigTask(displayTask, i, j, result, ready));
        }
    }

    public List<BigTask> getStep1() {
        return step1 != null ? step1 : new ArrayList<BigTask>();
    }

    public List<BigTask> getStep2() {
        return step2 != null ? step2 : new ArrayList<BigTask>();
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
