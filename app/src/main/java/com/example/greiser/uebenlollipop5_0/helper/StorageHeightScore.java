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

            String[] shorts = {"p010","p020","p030","p100","mi010","mi020","mi030","mi100","pm010","pm020", "pm030", "pm100", "m100", "m400", "d100"};

            for (int i = 0; i < shorts.length; i++) {
                for (int counter10 = 1; counter10 < 11; counter10++) {
                    String stringCounter = counter10 < 10 ? ("0" + counter10) : ("" + counter10);
                    String shorty = shorts[i];
                    switch (shorty) {
                        case "p010":
                            bw.write(shorty + stringCounter + userHeightScore.bestPlus10[counter10 - 1]);
                            break;
                            case "p020":
                            bw.write(shorty + stringCounter + userHeightScore.bestPlus20[counter10 - 1]);
                            break;
                            case "p030":
                            bw.write(shorty + stringCounter + userHeightScore.bestPlus30[counter10 - 1]);
                            break;
                        case "p100":
                            bw.write(shorty + stringCounter + userHeightScore.bestPlus100[counter10 - 1]);
                            break;
                        case "mi010":
                            bw.write(shorty + stringCounter + userHeightScore.bestMinus10[counter10 - 1]);
                            break;
                        case "mi020":
                            bw.write(shorty + stringCounter + userHeightScore.bestMinus20[counter10 - 1]);
                            break;
                        case "mi030":
                            bw.write(shorty + stringCounter + userHeightScore.bestMinus30[counter10 - 1]);
                            break;
                        case "mi100":
                            bw.write(shorty + stringCounter + userHeightScore.bestMinus100[counter10 - 1]);
                            break;
                        case "pm010":
                            bw.write(shorty + stringCounter + userHeightScore.bestPlusMinus10[counter10 - 1]);
                            break;
                        case "pm020":
                            bw.write(shorts[i] + stringCounter + userHeightScore.bestPlusMinus20[counter10 - 1]);
                            break;
                        case "pm030":
                            bw.write(shorts[i] + stringCounter + userHeightScore.bestPlusMinus30[counter10 - 1]);
                            break;
                        case "pm100":
                            bw.write(shorts[i] + stringCounter + userHeightScore.bestPlusMinus100[counter10 - 1]);
                            break;
                        case "m100":
                            bw.write(shorts[i] + stringCounter + userHeightScore.bestMult100[counter10 - 1]);
                            break;
                        case "m400":
                            bw.write(shorts[i] + stringCounter + userHeightScore.bestMult400[counter10 - 1]);
                            break;
                        case "d100":
                            bw.write(shorts[i] + stringCounter + userHeightScore.bestDivide100[counter10 - 1]);
                            break;
                    }
                    bw.newLine();
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
            /** pm02001 pm03001 pm10001 m10001 m40001 d10001 pm02002 pm02003 pm02004 pm02005 */
            while (line != null) {
                String substring = line.substring(0, 4);
                switch (line.substring(0, 4)) {
                    case "p010":
                        uh.addPlus(10,line.substring(6));
                        break;
                    case "p020":
                        uh.addPlus(20,line.substring(6));
                        break;
                    case "p030":
                        uh.addPlus(30,line.substring(6));
                        break;
                    case "p100":
                        uh.addPlus(100,line.substring(6));
                        break;
                    case "mi010":
                        uh.addMinus(10,line.substring(6));
                        break;
                    case "mi020":
                        uh.addMinus(20,line.substring(6));
                        break;
                    case "mi030":
                        uh.addMinus(30,line.substring(6));
                        break;
                    case "mi100":
                        uh.addMinus(100,line.substring(6));
                        break;
                    case "pm010":
                        uh.addPlusMinus(10,line.substring(6));
                    case "pm020":
                        uh.addPlusMinus(20,line.substring(6));
                        break;
                    case "pm030":
                        uh.addPlusMinus(30,line.substring(6));
                        break;
                    case "pm100":
                        uh.addPlusMinus(100,line.substring(6));
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
