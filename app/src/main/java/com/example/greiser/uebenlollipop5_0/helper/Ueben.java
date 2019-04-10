package com.example.greiser.uebenlollipop5_0.helper;

import android.app.Application;

import com.example.greiser.uebenlollipop5_0.model.BigTask;
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
    private Boolean[] multTableToTrain;

    public void setHeightScores(UserHeightScore heightScores) {
        this.userHeightScore = heightScores;
    }

    public UserHeightScore getHeightScores() {
        return this.userHeightScore;
    }

    public void setMultTable(boolean checked0, boolean checked1,
                             boolean checked2, boolean checked3,
                             boolean checked4, boolean checked5,
                             boolean checked6, boolean checked7,
                             boolean checked8, boolean checked9, boolean checked10) {
        this.multTableToTrain = new Boolean[]{checked0, checked1, checked2, checked3, checked4,
                checked5, checked6, checked7, checked8, checked9, checked10};
    }

    public boolean canCheck(BigTask task) {
        if (task.getI() < 0 || task.getI() > 10 || task.getJ() < 0 || task.getJ() > 10) {
            return false;
        }
        return multTableToTrain[task.getI()] || multTableToTrain[task.getJ()];
    }

    public boolean canCheckDivide(BigTask task) {
        return multTableToTrain[task.getResult()];
    }

    public Boolean[] getMultTableToTrain() {
        return multTableToTrain;
    }

    public enum Operations {plusminus, mult, divide}


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
