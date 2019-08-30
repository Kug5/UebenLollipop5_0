package com.example.greiser.uebenlollipop5_0.helper;

import android.content.Context;
import android.os.Environment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StorageListOfUsers extends UebenStorage implements Storage {

  private List<String> names = new ArrayList<>();

  public StorageListOfUsers(Context context) {
    super(context);
  }

  private void store() {

    if (!isExternalStorageWritable()) {
      return;
    }

    File file = getFile();
    BufferedWriter bw = null;
    try {
      bw = new BufferedWriter(new FileWriter(file));

      if (names != null) {
        for (int i = 0; i < names.size(); i++) {
          if (i != 0) {
            bw.write(",");
          }
          bw.write(names.get(i));
        }
      }

      bw.flush();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Object load() {
    this.names = new ArrayList<String>();
    BufferedReader bufferIn = null;
    try {
      bufferIn = new BufferedReader(new FileReader(getFile()));

      String line = bufferIn.readLine();
      if (line != null) {
        String[] tmpNames = line.split(",");
        bufferIn.close();
        this.names = new ArrayList<String>(Arrays.asList(tmpNames));
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      return names;
    }
  }

  @Override
  public File getFile() {
    return new File(
        getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "listOfNames.txt");
  }


  @Override
  public void update(Object newName) {
    this.names.add((String) newName);
    this.store();
  }

  public List<String> getNames() {
    if (this.names.size() == 0) {
      return (List<String>) this.load();
    }
    return this.names;
  }
}
