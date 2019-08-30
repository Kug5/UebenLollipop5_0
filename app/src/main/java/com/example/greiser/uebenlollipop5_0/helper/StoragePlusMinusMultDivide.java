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
      bw.newLine();
      bw.write("" + storedData.getCounter());
      bw.newLine();

      List<BigTask> newList = new ArrayList<BigTask>(storedData.getStep1());
      addAll_noDuplicates(newList, storedData.getStep2());
      addAll_noDuplicates(newList, storedData.getStep3());

      if (newList.size() > 462) {
        Log.println(Log.WARN, "debug", "zu viele Aufgaben");
      }

      for (BigTask bt : newList) {
        bw.write(getStringBigTask(bt));
        bw.newLine();
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
    sd.setDate(Long.parseLong(line));
    line = bufferIn.readLine();
    sd.setCounter(Integer.parseInt(line));
    line = bufferIn.readLine();
    while (line != null) {
      String[] taskSplit = line.split(",");
      sd.setTask(
          taskSplit[0],
          Integer.parseInt(taskSplit[1]),
          Integer.parseInt(taskSplit[2]),
          Integer.parseInt(taskSplit[3]),
          Integer.parseInt(taskSplit[4]));
      line = bufferIn.readLine();
    }
    bufferIn.close();

    return sd;
  }
}
