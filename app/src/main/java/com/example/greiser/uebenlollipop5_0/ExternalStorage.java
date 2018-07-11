package com.example.greiser.uebenlollipop5_0;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExternalStorage {

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public File getTasksFile(Context context, String operation, int max, String name) {
        // Get the directory for the app's private pictures directory.
        return new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), this.getFileName(operation,max, name));
    }


    public void storeTasks(Context context, StorageData storedData, String operation, int max, String name) throws IOException {

        if(!isExternalStorageWritable()) {
            return;
        }

        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), this.getFileName(operation,max, name));
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        storedData.increaseCounter();
        bw.write("" + new Date().getTime());
        bw.newLine();
        bw.write("" + storedData.getCounter());
        bw.newLine();

        List<BigTask> newList = new ArrayList<BigTask>(storedData.getStep1());
        addAll_noDuplicats(newList, storedData.getStep2());
        addAll_noDuplicats(newList, storedData.getStep3());
//        newList.addAll(storedData.getStep2());
//        newList.addAll(storedData.getStep3());

        if (newList.size() > 462) {
            Log.println(Log.WARN, "debug", "zu viele Aufgaben");
        }

        for (BigTask bt: newList) {
            bw.write(getStringBigTask(bt));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    private void addAll_noDuplicats(List<BigTask> into, List<BigTask> toAdd) {
        for (BigTask taskInToAdd: toAdd) {
            int index = into.indexOf(taskInToAdd);
            if (index > -1) {
                BigTask tmp = into.get(index);
                if (tmp.box < taskInToAdd.box) {
                    tmp.box = taskInToAdd.box;
                }
            } else {
                into.add(taskInToAdd);
            }
        }
    }

    private String getStringBigTask(BigTask bt) {
        return bt.displayTask+","+bt.i+","+bt.j+","+bt.result+","+bt.box;
    }

    private String getFileName(String operation, int max, String name) {
        return name + "_" + operation + "_" + max + ".txt";
    }

    public File getFileListOfNames(Context context) {
        return new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), getFileNameListOfNames());
    }

    public void storeNames(Context context, String[] names, String newName) throws IOException {

        if(!isExternalStorageWritable()) {
            return;
        }

        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), getFileNameListOfNames());
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        if (names != null) {
            for (int i = 0; i < names.length; i++) {
                if (i != 0) {
                    bw.write(",");
                }
                bw.write(names[i]);
            }
        }

        if (names != null && names.length > 0) {
            bw.write(",");
        }
        bw.write(newName);

        bw.flush();
        bw.close();
    }

    private String getFileNameListOfNames() {
        return "listOfNames.txt";
    }

    public File getFileSettings(String name, Context context) {
       return new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), getFileNameSettings(name));
    }

    private String getFileNameSettings(String name) {
        return "settings_"+name+".txt";
    }

    public void storeSettings(Context context, UserSetting usersettings, String name) throws IOException {

        if(!isExternalStorageWritable()) {
            return;
        }

        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), getFileNameSettings(name));
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        bw.write("countBoxes:" + usersettings.getCountBoxes());
        bw.flush();
        bw.close();

    }

    public StorageData getStoredTasks(Context context, String operation, int max, String name) throws Exception {
        File file = getTasksFile(context, operation, max, name);
        return getStoredTasksData(file);
    }

    private StorageData getStoredTasksData(File storage) throws Exception {
        BufferedReader bufferIn = new BufferedReader(new FileReader(storage));
        StorageData sd = new StorageData();
        String line  = bufferIn.readLine();
        if(line == null) {
            throw new Exception("empty file");
        }
        sd.setDate(Long.parseLong(line));
        line = bufferIn.readLine();
        sd.setCounter(Integer.parseInt(line));
        line = bufferIn.readLine();
        while (line != null) {
            String [] taskSplit = line.split(",");
            sd.setTask(taskSplit[0], Integer.parseInt(taskSplit[1]), Integer.parseInt(taskSplit[2]), Integer.parseInt(taskSplit[3]), Integer.parseInt(taskSplit[4]));
            line = bufferIn.readLine();
        }
        bufferIn.close();

        return sd;
    }
}
