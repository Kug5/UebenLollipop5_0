package com.example.greiser.uebenlollipop5_0.helper;

import android.app.Application;

import com.example.greiser.uebenlollipop5_0.model.UserHeightScore;
import com.example.greiser.uebenlollipop5_0.model.UserSetting;

public class Ueben extends Application {

    public static final String OPERATION_PLUSMINUS = "plusminus";
    public static final String OPERATION_MULT = "mult";
    public static final String OPERATION_DIVIDE = "divide";
    public static final String GERMAN_READ = "read";
    public static final String GERMAN_WRITE = "write";
    public static final String GERMAN_SP = "singularplural";
    public static final String GERMAN_MIX = "mix";
    public static final String SUBJECT_MATH = "math";
    public static final String SUBJECT_GERMAN = "german";


    private String subject;
    private String username;
    private String operation;
    private String germanTarget;
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

    public int getHowMany() {
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

    public void setGermanTarget(String germanTarget) {
        this.germanTarget = germanTarget;
    }

    public String getGermanTarget() {
        return germanTarget;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
