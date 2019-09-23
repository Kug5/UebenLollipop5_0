package com.example.greiser.uebenlollipop5_0.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;

import com.example.greiser.uebenlollipop5_0.model.Result;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StorageToday extends UebenStorage implements Storage {

    public static final String ROTD_ = "rotd_";
    private final String username;

    public StorageToday(Context context, String username) {
        super(context);
        this.username = username;
    }


    @Override
    public Object load() throws Exception { // returns Map<String, String>

        ArrayList<Result> resultsPerDay = new ArrayList<>();

        for (File result:getAllFiles(this.username)) {
            String date = result.getName().substring(ROTD_.length(), ROTD_.length() + 8);
            BufferedReader reader = new BufferedReader(new FileReader(result));
            String line = reader.readLine();
            StringBuffer results = new StringBuffer();
             while (line != null) {
                 results.append(line).append(System.lineSeparator());
                 line = reader.readLine();
             }
             resultsPerDay.add(new Result(date, results.toString()));

        }

        return resultsPerDay;
    }

    @Override
    public File getFile() {
        return new File(
                getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                ROTD_ + getDateOfTheDay() + "_" + username + ".txt");
    }

    private List<File> getAllFiles (String username) {
        List returnList = new ArrayList<File>();
        File folder = getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File[] listOfFiles = folder.listFiles();
        for (File one: listOfFiles) {
            if (one.getName().startsWith(ROTD_) && one.getName().contains(username)) {
                returnList.add(one);
            }
        }
        return returnList;
    }

    /*private List<File> getAllFilesOfTheDay() {
        List<File> today = new ArrayList<>();
        String dateToday = getDateOfTheDay().toString();
        for (File one: getAllFiles()) {
            if (one.getName().contains(dateToday)) {
                today.add(one);
            }
        }
        return today;
    }*/

    public static String getDateOfTheDay() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    @Override
    public void update(Object newValue) { // newValue:String

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(getFile(), true));
            br.newLine();
            br.write(((String)newValue).replace(System.lineSeparator(), " "));
            br.flush();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
