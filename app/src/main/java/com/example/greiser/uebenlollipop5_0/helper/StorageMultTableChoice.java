package com.example.greiser.uebenlollipop5_0.helper;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StorageMultTableChoice extends UebenStorage implements Storage {
    private final String username;
    private boolean[] multTable;

    public StorageMultTableChoice(Context context, String username) {
        super(context);
        this.username = username;
    }

    private void store() {
        if (!isExternalStorageWritable()) {
            return;
        }
        File file = getFile();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (boolean mult : this.multTable) {
                bw.write(mult ? "1" : "0");
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object newValue) {
        for (int i = 0; i < ((Boolean[]) newValue).length; i++) {
            this.multTable[i] = ((Boolean[]) newValue)[i].booleanValue();
        }
        store();
    }

    @Override
    public Object load() {
        this.multTable = new boolean[11];
        File file = getFile();
        if (!file.exists()) {
            return this.multTable;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            int counter = 0;
            do {
                if (counter < multTable.length) {
                    multTable[counter] = line.equals("1");
                }
                counter++;
                line = br.readLine();
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return multTable;
    }

    @Override
    public File getFile() {
        return new File(
                getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                "multtable_" + username + ".txt");
    }
}
