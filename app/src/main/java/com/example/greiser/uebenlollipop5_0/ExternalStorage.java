package com.example.greiser.uebenlollipop5_0;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

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

    public File getPrivateDocumentsStorageFile(Context context, String operation, int max, String name) {
        // Get the directory for the app's private pictures directory.
        return new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), this.getFileName(operation,max, name));
    }


    public void store(Context context, StorageData storedData, String operation, int max, String name) throws IOException {

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
        for (BigTask bt: storedData.getStep1()) {
            bw.write(getStringBigTask(bt));
            bw.newLine();
        }
        for (BigTask bt: storedData.getStep2()) {
            bw.write(getStringBigTask(bt));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    private String getStringBigTask(BigTask bt) {
        return bt.displayTask+","+bt.i+","+bt.j+","+bt.result+","+bt.ready;
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
}
