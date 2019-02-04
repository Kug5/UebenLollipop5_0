package com.example.greiser.uebenlollipop5_0.helper;

import android.app.Application;

import com.example.greiser.uebenlollipop5_0.model.UserHeightScore;
import com.example.greiser.uebenlollipop5_0.model.UserSetting;

public class Ueben extends Application {

    public static final String OPERATION_PLUSMINUS = "plusminus";
    public static final String OPERATION_MULT = "mult";
    public static final String OPERATION_DIVIDE = "divide";

    private String username;
    private String operation;
    private int max;
    private int many;
    private UserSetting usersettings;
    private UserHeightScore userHeightScore;
    public int lastPoints = -1;

    public void setHeightScores(UserHeightScore heightScores) {
        this.userHeightScore = heightScores;
    }

    public UserHeightScore getHeightScores() {
        return this.userHeightScore;
    }

    public enum Operations { plusminus, mult, divide }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setMany(int many) {
        this.many = many;
    }

    public int getMany() {
        return many;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setUsersettings(UserSetting usersettings) {
        this.usersettings = usersettings;
    }

    public UserSetting getUsersettings() {
        return usersettings;
    }
}
