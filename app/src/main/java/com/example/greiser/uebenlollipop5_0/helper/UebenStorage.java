package com.example.greiser.uebenlollipop5_0.helper;

import android.content.Context;
import android.os.Environment;
import com.example.greiser.uebenlollipop5_0.model.BigTask;

public abstract class UebenStorage {

  public Context getContext() {
    return context;
  }

  private final Context context;

  public UebenStorage(Context context) {
    this.context = context;
  }

  /* Checks if external storage is available for read and write */
  public static boolean isExternalStorageWritable() {
    String state = Environment.getExternalStorageState();
    return Environment.MEDIA_MOUNTED.equals(state);
  }

  /* Checks if external storage is available to at least read */
  public static boolean isExternalStorageReadable() {
    String state = Environment.getExternalStorageState();
    return Environment.MEDIA_MOUNTED.equals(state)
        || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
  }

  public static String getStringBigTask(BigTask bt) {
    return bt.getDisplayTask()
        + ","
        + bt.getI()
        + ","
        + bt.getJ()
        + ","
        + bt.getResult()
        + ","
        + bt.box;
  }

  private static void store() {}
}
