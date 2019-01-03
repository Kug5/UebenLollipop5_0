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
        addAll_noDuplicates(newList, storedData.getStep2());
        addAll_noDuplicates(newList, storedData.getStep3());
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

    private void addAll_noDuplicates(List<BigTask> into, List<BigTask> toAdd) {
        for (BigTask taskInToAdd: toAdd) {

            int index = -1;
            int indexInto = 0;
            for (BigTask intoBigTask: into) {
                if (intoBigTask.displayTask.equals(taskInToAdd.displayTask)) {
                    index = indexInto;
                    break;
                }
                indexInto++;
            }

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

    public UserHeightScore getHeightScores(Context context, String name) {
        File file = getHeightScoreFile(context, name);
        UserHeightScore uh = new UserHeightScore();

        try {
            BufferedReader iReader = new BufferedReader(new FileReader(file));
            String line = iReader.readLine();
            /**
             * p02001 p03001 p10001 m10001 m40001 d10001
             * p02002
             * p02003
             * p02004
             * p02005
             */
            while(line != null) {
                String substring = line.substring(0, 4);
                switch (line.substring(0, 4)){
                    case "p020": uh.addPlusMinus20(line.substring(6)); break;
                    case "p030": uh.addPlusMinus30(line.substring(6)); break;
                    case "p100": uh.addPlusMinus100(line.substring(6)); break;
                    case "m100": uh.addMult100(line.substring(6)); break;
                    case "m400": uh.addMult400(line.substring(6)); break;
                    case "d100": uh.addDivide100(line.substring(6)); break;
                }
                line = iReader.readLine();
            }

        } catch (Exception e) {
            return uh;
        }

        return uh;
    }

    public void storeHeightScores(Context context, String name, UserHeightScore userHeightScore) throws IOException {

        if(!isExternalStorageWritable()) {
            return;
        }

        File file = getHeightScoreFile(context, name);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        String[] shorts = {"p020", "p030", "p100", "m100", "m400", "d100"};

        for (int i = 0; i < shorts.length; i++) {
            for (int counter10 = 1; counter10 < 11; counter10++) {
                String stringCounter = counter10 < 10 ? ("0" + counter10) : ("" + counter10);

                switch (i) {
                    case 0: bw.write(shorts[i] + stringCounter + userHeightScore.bestPlusMinus20[counter10-1]);
                        bw.newLine(); break;
                    case 1: bw.write(shorts[i] + stringCounter + userHeightScore.bestPlusMinus30[counter10-1]);
                        bw.newLine(); break;
                    case 2:
                        bw.write(shorts[i] + stringCounter + userHeightScore.bestPlusMinus100[counter10-1]);
                        bw.newLine(); break;
                    case 3:
                        bw.write(shorts[i] + stringCounter + userHeightScore.bestMult100[counter10-1]);
                        bw.newLine(); break;
                    case 4:
                        bw.write(shorts[i] + stringCounter + userHeightScore.bestMult400[counter10-1]);
                        bw.newLine(); break;
                    case 5:
                        bw.write(shorts[i] + stringCounter + userHeightScore.bestDivide100[counter10-1]);
                        bw.newLine(); break;
                }
            }
        }


        bw.flush();
        bw.close();

    }

    public File getHeightScoreFile(Context context, String name) {
        // Get the directory for the app's private pictures directory.
        return new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), "hs" + name + ".txt");
    }
}
