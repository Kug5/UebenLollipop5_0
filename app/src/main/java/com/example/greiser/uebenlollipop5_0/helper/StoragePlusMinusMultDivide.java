package com.example.greiser.uebenlollipop5_0.helper;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.greiser.uebenlollipop5_0.model.BigTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StoragePlusMinusMultDivide extends UebenStorage implements Storage {

    private final String operation;
    private final int max;
    private final String username;
    private StorageData storedData;

    public StoragePlusMinusMultDivide(Context context, String operation, int max, String username) {
        super(context);
        this.operation = operation;
        this.max = max;
        this.username = username;
    }

    private void store() {

        if (!isExternalStorageWritable()) {
            return;
        }

        File file = getFile();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));

            storedData.increaseCounter();
            bw.write("" + new Date().getTime());
            bw.write(";");
            bw.write("" + storedData.getCounter());
            bw.write(";");

            List<BigTask> newList = new ArrayList<BigTask>(storedData.getStep1());
            addAll_noDuplicates(newList, storedData.getStep2());
            addAll_noDuplicates(newList, storedData.getStep3());

            for (BigTask bt : newList) {
                bw.write(getStringBigTask(bt));
                bw.write(";");
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object storageData) {
        this.storedData = (StorageData) storageData;
        store();
    }

    @Override
    public Object load() throws Exception {
        File file = getFile();
        if (!file.exists()) {
            return new StorageData();
        }
        this.storedData = getStoredTasksData(file);
        return this.storedData;
    }

    @Override
    public File getFile() {
        return new File(
                getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                username + "_" + operation + "_" + max + ".txt");
    }

    private void addAll_noDuplicates(List<BigTask> into, List<BigTask> toAdd) {
        for (BigTask taskInToAdd : toAdd) {

            int index = -1;
            int indexInto = 0;
            for (BigTask intoBigTask : into) {
                if (intoBigTask.getDisplayTask().equals(taskInToAdd.getDisplayTask())) {
                    index = indexInto;
                    break;
                }
                indexInto++;
            }

            if (index > -1) {
                BigTask tmp = into.get(index);
                if (tmp.getBox() < taskInToAdd.getBox()) {
                    tmp.box = taskInToAdd.getBox();
                }
            } else {
                into.add(taskInToAdd);
            }
        }
    }

    private StorageData getStoredTasksData(File storage) throws Exception {
        BufferedReader bufferIn = new BufferedReader(new FileReader(storage));
        StorageData sd = new StorageData();
        String line = bufferIn.readLine();
        if (line == null) {
            throw new Exception("empty file");
        }
        String[] splittedLine = line.split(";");
        sd.setDate(Long.parseLong(splittedLine[0]));
        sd.setCounter(Integer.parseInt(splittedLine[1]));

        for (int index = 2; index < splittedLine.length; index++) {
            String [] taskSplit = splittedLine[index].split(",");
            String taskComplete = taskSplit[0];
            int equalsSignIndex = taskComplete.indexOf("=");
            Matcher matcher = Pattern.compile("[\\+\\-*:]{1}").matcher(taskComplete);
            int opSignIndex = matcher.find() ? matcher.start() : -1;
            String taskWithoutResult = taskComplete.substring(0, equalsSignIndex + 1);
            int i = Integer.parseInt(taskComplete.substring(0, opSignIndex).trim());
            int j = Integer.parseInt(taskComplete.substring(opSignIndex + 1, equalsSignIndex).trim());
            int result = Integer.parseInt(taskComplete.substring(equalsSignIndex + 1).trim());
            sd.setTask(
                    taskWithoutResult,
                    i,j,result,
                    Integer.parseInt(taskSplit[1]));
        }
        bufferIn.close();
        return sd;
    }
}
