package com.example.greiser.uebenlollipop5_0.model;

import com.example.greiser.uebenlollipop5_0.helper.Ueben;

public class UserHeightScore {

    public int[] bestPlusMinus20 = new int[10];
    public int[] bestPlusMinus30 = new int[10];
    public int[] bestPlusMinus100 = new int[10];
    public int[] bestMult100 = new int[10];
    public int[] bestMult400 = new int[10];
    public int[] bestDivide100 = new int[10];

    public int getBestPlusMinus20() {
        int max = bestPlusMinus20[0];
        for (int i=1; i < bestPlusMinus20.length; i++) {
            max = Math.max(max, bestPlusMinus20[i]);
        }
        return max;
    }

    public int getBestPlusMinus30() {
        int max = bestPlusMinus30[0];
        for (int i=1; i < bestPlusMinus30.length; i++) {
            max = Math.max(max, bestPlusMinus30[i]);
        }
        return max;
    }

    public int getBestPlusMinus100() {
        int max = bestPlusMinus100[0];
        for (int i=1; i < bestPlusMinus100.length; i++) {
            max = Math.max(max, bestPlusMinus100[i]);
        }
        return max;
    }

    public int getBestMult100() {
        int max = bestMult100[0];
        for (int i=1; i < bestMult100.length; i++) {
            max = Math.max(max, bestMult100[i]);
        }
        return max;
    }

    public int getBestMult400() {
        int max = bestMult400[0];
        for (int i=1; i < bestMult400.length; i++) {
            max = Math.max(max, bestMult400[i]);
        }
        return max;
    }

    public int getBestDivide100() {
        int max = bestDivide100[0];
        for (int i = 1; i < bestDivide100.length; i++) {
            max = Math.max(max, bestDivide100[i]);
        }
        return max;
    }

    public void addPlusMinus20(String score) {
        int scoreInt = Integer.parseInt(score);
        for (int i=0; i < bestPlusMinus20.length; i++) {
            if (bestPlusMinus20[i] == 0) {
                bestPlusMinus20[i] = scoreInt;
                break;
            }
        }
    }
    public void addPlusMinus30(String score) {
        int scoreInt = Integer.parseInt(score);
        for (int i=0; i < bestPlusMinus30.length; i++) {
            if (bestPlusMinus30[i] == 0) {
                bestPlusMinus30[i] = scoreInt;
                break;
            }
        }
    }

    public void addPlusMinus100(String score) {
        int scoreInt = Integer.parseInt(score);
        for (int i=0; i < bestPlusMinus100.length; i++) {
            if (bestPlusMinus100[i] == 0) {
                bestPlusMinus100[i] = scoreInt;
                break;
            }
        }
    }

    public void addMult100(String score) {
        int scoreInt = Integer.parseInt(score);
        for (int i=0; i < bestMult100.length; i++) {
            if (bestMult100[i] == 0) {
                bestMult100[i] = scoreInt;
                break;
            }
        }
    }

    public void addMult400(String score) {
        int scoreInt = Integer.parseInt(score);
        for (int i=0; i < bestMult400.length; i++) {
            if (bestMult400[i] == 0) {
                bestMult400[i] = scoreInt;
                break;
            }
        }
    }

    public void addDivide100(String score) {
        int scoreInt = Integer.parseInt(score);
        for (int i=0; i < bestDivide100.length; i++) {
            if (bestDivide100[i] == 0) {
                bestDivide100[i] = scoreInt;
                break;
            }
        }
    }

    public void setNewScore(String operation, int max, int points) {
        switch (operation) {
            case Ueben.OPERATION_DIVIDE: insertIntoDivide(max, points); break;
            case Ueben.OPERATION_MULT: insertIntoMult(max, points); break;
            case Ueben.OPERATION_PLUSMINUS: insertPlusMinus(max, points); break;
        }
    }

    private void insertIntoDivide(int max, int points) {
        this.insertInto(this.bestDivide100, points);
    }

    private void insertIntoMult(int max, int points) {
        switch (max) {
            case 100: this.insertInto(this.bestMult100, points); break;
            case 400: this.insertInto(this.bestMult400, points); break;
        }
    }

    private void insertPlusMinus(int max, int points) {
        switch (max) {
            case 20: this.insertInto(this.bestPlusMinus20, points); break;
            case 30: this.insertInto(this.bestPlusMinus30, points); break;
            case 40: this.insertInto(this.bestPlusMinus100, points); break;
        }
    }

    private void insertInto(int[]array, int points) {
        if (array == null) {
            return;
        }

        int minIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[minIndex] > array[i]) {
                minIndex = i;
            }
        }
        array[minIndex] = points;
    }
}
