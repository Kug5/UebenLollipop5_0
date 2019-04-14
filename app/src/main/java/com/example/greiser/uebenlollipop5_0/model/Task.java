package com.example.greiser.uebenlollipop5_0.model;

public class Task {

  private int summand1;
  private int summand2;
  private int sum;

  public Task(int summand1, int summand2, int sum) {
    this.sum = sum;
    this.summand1 = summand1;
    this.summand2 = summand2;
  }

  public int getSum() {
    return this.sum;
  }
}
