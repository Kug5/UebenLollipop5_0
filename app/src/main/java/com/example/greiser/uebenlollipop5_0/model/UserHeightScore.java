package com.example.greiser.uebenlollipop5_0.model;

import com.example.greiser.uebenlollipop5_0.helper.Ueben;

public class UserHeightScore {
    public int[] bestPlus10 = new int[10];
    public int[] bestPlus20 = new int[10];
    public int[] bestPlus30 = new int[10];
    public int[] bestPlus100 = new int[10];

    public int[] bestMinus10 = new int[10];
    public int[] bestMinus20 = new int[10];
    public int[] bestMinus30 = new int[10];
    public int[] bestMinus100 = new int[10];

    public int[] bestPlusMinus10 = new int[10];
    public int[] bestPlusMinus20 = new int[10];
    public int[] bestPlusMinus30 = new int[10];
    public int[] bestPlusMinus100 = new int[10];

    public int[] bestMult100 = new int[10];
    public int[] bestMult400 = new int[10];

    public int[] bestDivide100 = new int[10];

    private int[] getListMinus(int max) {
        switch (max) {
            case 10: return bestMinus10;
            case 20: return bestMinus20;
            case 30: return bestMinus30;
            case 100: return bestMinus100;
        }
        return new int[0];
    }
    private int[] getListPlus(int max) {
        switch (max) {
            case 10: return bestPlus10;
            case 20: return bestPlus20;
            case 30: return bestPlus30;
            case 100: return bestPlus100;
        }
        return new int[0];
    }
    private int[] getListPlusMinus(int max) {
        switch (max) {
            case 10: return bestPlusMinus10;
            case 20: return bestPlusMinus20;
            case 30: return bestPlusMinus30;
            case 100: return bestPlusMinus100;
        }
        return new int[0];
    }

    public int getBestPlusMinus(int max) {
        int [] bestList = getListPlusMinus(max);
        return getMaxfromList(bestList);
    }
    public int getBestMinus(int max) {
        int [] bestList = getListMinus(max);
        return getMaxfromList(bestList);
    }
    public int getBestPlus(int max) {
        int [] bestList = getListPlus(max);
        return getMaxfromList(bestList);
    }

    private int getMaxfromList(int[] bestList) {
        if (bestList.length == 0) {
            return 0;
        }
        int maxHS = bestList[0];
        for (int i = 1; i < bestList.length; i++) {
            maxHS = Math.max(maxHS, bestList[i]);
        }
        return maxHS;
    }

    public int getBestMult100() {
        return getMaxfromList(bestMult100);
    }

    public int getBestMult400() {
        return getMaxfromList(bestMult400);
    }

    public int getBestDivide100() {
        return getMaxfromList(bestDivide100);
    }


    public void addPlusMinus(int max, String score) {
        int scoreInt = Integer.parseInt(score);
        int []bestList = getListPlusMinus(max);
        setNewScore(scoreInt, bestList);
    }
    public void addMinus(int max, String score) {
        int scoreInt = Integer.parseInt(score);
        int []bestList = getListMinus(max);
        setNewScore(scoreInt, bestList);
    }
    public void addPlus(int max, String score) {
        int scoreInt = Integer.parseInt(score);
        int []bestList = getListPlus(max);
        setNewScore(scoreInt, bestList);
    }


    private void setNewScore(int scoreInt, int[] bestList) {
        for (int i = 0; i < bestList.length; i++) {
            if (bestList[i] == 0) {
                bestList[i] = scoreInt;
                break;
            }
        }
    }
    public void addMult100(String score) {
        int scoreInt = Integer.parseInt(score);
        setNewScore(scoreInt, bestMult100);
    }

    public void addMult400(String score) {
        int scoreInt = Integer.parseInt(score);
        setNewScore(scoreInt, bestMult400);
    }

    public void addDivide100(String score) {
        int scoreInt = Integer.parseInt(score);
        setNewScore(scoreInt, bestDivide100);
    }

    public void setNewScore(String operation, int max, int points, int maxPoints) {

        int percent = percent(points, maxPoints);

        switch (operation) {
            case Ueben.OPERATION_DIVIDE:
                insertIntoDivide(max, percent);
                break;
            case Ueben.OPERATION_MULT:
                insertIntoMult(max, percent);
                break;
            case Ueben.OPERATION_PLUSMINUS:
                insertPlusMinus(max, percent);
                break;
            case Ueben.OPERATION_PLUS:
                insertPlus(max, percent);
                break;
            case Ueben.OPERATION_MINUS:
                insertMinus(max, percent);
                break;
        }
    }

    private int percent(int points, int maxPoints) {
        return 100 * points / maxPoints;
    }

    private void insertIntoDivide(int max, int points) {
        this.insertInto(this.bestDivide100, points);
    }

    private void insertIntoMult(int max, int points) {
        switch (max) {
            case 10:
                this.insertInto(this.bestMult100, points);
                break;
            case 20:
                this.insertInto(this.bestMult400, points);
                break;
        }
    }

    private void insertPlusMinus(int max, int points) {
        switch (max) {
            case 10:
                this.insertInto(this.bestPlusMinus10, points);
                break;
            case 20:
                this.insertInto(this.bestPlusMinus20, points);
                break;
            case 30:
                this.insertInto(this.bestPlusMinus30, points);
                break;
            case 100:
                this.insertInto(this.bestPlusMinus100, points);
                break;
        }
    }

    private void insertPlus(int max, int points) {
        switch (max) {
            case 10:
                this.insertInto(this.bestPlus10, points);
                break;
            case 20:
                this.insertInto(this.bestPlus20, points);
                break;
            case 30:
                this.insertInto(this.bestPlus30, points);
                break;
            case 100:
                this.insertInto(this.bestPlus100, points);
                break;
        }
    }

    private void insertMinus(int max, int points) {
        switch (max) {
            case 10:
                this.insertInto(this.bestMinus10, points);
                break;
            case 20:
                this.insertInto(this.bestMinus20, points);
                break;
            case 30:
                this.insertInto(this.bestMinus30, points);
                break;
            case 100:
                this.insertInto(this.bestMinus100, points);
                break;
        }
    }

    private void insertInto(int[] array, int points) {
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
