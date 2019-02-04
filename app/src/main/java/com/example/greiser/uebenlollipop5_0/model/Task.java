package com.example.greiser.uebenlollipop5_0.model;

public class Task {

    int summand1 = -1;
    int summand2 = -1;
    int sum = -1;

    public Task(int summand1, int summand2, int sum) {
        this.sum = sum;
        this.summand1 = summand1;
        this.summand2 = summand2;
    }

    public int getSum() {
        return this.sum;
    }

}
