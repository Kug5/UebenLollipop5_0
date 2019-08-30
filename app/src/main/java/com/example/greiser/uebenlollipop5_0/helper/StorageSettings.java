package com.example.greiser.uebenlollipop5_0.helper;

import android.content.Context;
import android.os.Environment;

import com.example.greiser.uebenlollipop5_0.model.UserSetting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StorageSettings extends UebenStorage implements Storage {

    private final String name;

    public StorageSettings(Context context, String name) {
        super(context);
        this.name = name;
    }

    private void store() {
        if (!isExternalStorageWritable()) {
            return;
        }

        File file = getFile();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));

            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object load() {
        return new UserSetting();
    }

    @Override
    public File getFile() {
        return new File(
                getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                "settings_" + name + ".txt");
    }

    @Override
    public void update(Object newValue) {
    }
}
