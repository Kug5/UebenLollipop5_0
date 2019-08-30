package com.example.greiser.uebenlollipop5_0.helper;

import android.content.Context;
import android.os.Environment;
import com.example.greiser.uebenlollipop5_0.model.UserHeightScore;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StorageHeightScore extends UebenStorage implements Storage {

  private final String username;
  private UserHeightScore userHeightScore;

  public StorageHeightScore(Context context, String username) {
    super(context);
    this.username = username;
  }

  @Override
  public void update(Object userHeightScore) {
    this.userHeightScore = (UserHeightScore) userHeightScore;
    store();
  }

  private void store() {
    if (!isExternalStorageWritable()) {
      return;
    }

    File file = getFile();
    BufferedWriter bw = null;
    try {
      bw = new BufferedWriter(new FileWriter(file));

      String[] shorts = {"p020", "p030", "p100", "m100", "m400", "d100"};

      for (int i = 0; i < shorts.length; i++) {
        for (int counter10 = 1; counter10 < 11; counter10++) {
          String stringCounter = counter10 < 10 ? ("0" + counter10) : ("" + counter10);

          switch (i) {
            case 0:
              bw.write(shorts[i] + stringCounter + userHeightScore.bestPlusMinus20[counter10 - 1]);
              bw.newLine();
              break;
            case 1:
              bw.write(shorts[i] + stringCounter + userHeightScore.bestPlusMinus30[counter10 - 1]);
              bw.newLine();
              break;
            case 2:
              bw.write(shorts[i] + stringCounter + userHeightScore.bestPlusMinus100[counter10 - 1]);
              bw.newLine();
              break;
            case 3:
              bw.write(shorts[i] + stringCounter + userHeightScore.bestMult100[counter10 - 1]);
              bw.newLine();
              break;
            case 4:
              bw.write(shorts[i] + stringCounter + userHeightScore.bestMult400[counter10 - 1]);
              bw.newLine();
              break;
            case 5:
              bw.write(shorts[i] + stringCounter + userHeightScore.bestDivide100[counter10 - 1]);
              bw.newLine();
              break;
          }
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
    File file = getFile();
    UserHeightScore uh = new UserHeightScore();

    try {
      BufferedReader iReader = new BufferedReader(new FileReader(file));
      String line = iReader.readLine();
      /** p02001 p03001 p10001 m10001 m40001 d10001 p02002 p02003 p02004 p02005 */
      while (line != null) {
        String substring = line.substring(0, 4);
        switch (line.substring(0, 4)) {
          case "p020":
            uh.addPlusMinus20(line.substring(6));
            break;
          case "p030":
            uh.addPlusMinus30(line.substring(6));
            break;
          case "p100":
            uh.addPlusMinus100(line.substring(6));
            break;
          case "m100":
            uh.addMult100(line.substring(6));
            break;
          case "m400":
            uh.addMult400(line.substring(6));
            break;
          case "d100":
            uh.addDivide100(line.substring(6));
            break;
        }
        line = iReader.readLine();
      }

    } catch (Exception e) {
      return uh;
    }

    return uh;
  }

  @Override
  public File getFile() {
    return new File(
        getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
        "hs" + username + ".txt");
  }
}
