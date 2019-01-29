package com.example.greiser.uebenlollipop5_0;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExternalStorage {

    private static final String CURRENT_VERSION_DEUTSCH_VORLAGE = "v1";

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

    public void storeNames(Context context, List<String> names) throws IOException {

        if(!isExternalStorageWritable()) {
            return;
        }

        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), getFileNameListOfNames());
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

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

    private BufferedReader getBufferedReaderDeutschVorlagen(Context context) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(getDeutschFileName(context)));

            String line = br.readLine();
            if (line == null || !line.equals(CURRENT_VERSION_DEUTSCH_VORLAGE)) {
                createDeutschVorlagenFile(context);
                return getBufferedReaderDeutschVorlagen(context);
            }

        } catch (FileNotFoundException e) {
            createDeutschVorlagenFile(context);
            return getBufferedReaderDeutschVorlagen(context);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return br;
    }

    public List<String> getDeutschWriteEntities(Context context) {

        List<String> entities = new ArrayList<>();

        try {
            BufferedReader br = getBufferedReaderDeutschVorlagen(context);
            String line = br.readLine();
            do {
                String [] split = line.split(" ");
                if (split.length < 2) {
                    continue;
                }
                switch (split[0]) {
                    case "1": entities.addAll(getEntriesType1(split)); break;
                    case "2": entities.add(split[1]); break;
                    case "3": entities.add(split[1]); break;
                }
                line = br.readLine();
            } while (line != null);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return entities;
    }

    private List<String> getEntriesType1(String[] split) {

        List<String> entries = new ArrayList<>();
        for (String entry: split) {
            try {
                Integer.parseInt(entry);
            } catch (NumberFormatException e) {
                entries.add(entry);
            }
        }
        return entries;
    }

    private void createDeutschVorlagenFile(Context context) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(getDeutschFileName(context)));
            bw.write(
                    CURRENT_VERSION_DEUTSCH_VORLAGE + "\n" +
                            "1 bunt gelb braun rot blau grün süß saftig groß rund klein schwer grau klar stark kalt heftig schwarz schnell schwer schön schon November Dezember Januar Februar März April Mai Juni Juli August September Oktober voll viel vom von vor vier eins zwei drei vier fünf sechs sieben acht neuen zehn elf zwölf dreihzehn vierzehn einhundert tausend klein kalt krank morgen gesund fremd uns und noch auch doch weiß zuerst dann danach nun zuletzt warm stark freundlich klug nett hilfsbereit fleißig schüchtern witzig mutig frech ehrlich\n" +
                            "2 Bild das Bield Bilt Bilder Bielder Bilter \n" +
                            "2 Baum der Paum Bum Bäume Beume Päume\n" +
                            "2 Buch das Boch Bug Bücher Büscher Bicher\n" +
                            "2 Fisch der Füsch Fühsch Fische Füsche Fiche\n" +
                            "2 Blume die Plume Blome Blumen Blümen Plumen\n" +
                            "2 Katze die Kaze Gazte Katzen Gatzen Kazten\n" +
                            "2 Kind das Gind Kint Kinder Ginder Kinter\n" +
                            "2 Stift der Stiefte Stivte Stifte Stiefte Stivte\n" +
                            "2 Mädchen das Medchen Mätchen Mädchen Medchen Mätchen\n" +
                            "2 Junge der June Jonge Jungen Jugen Jüngen\n" +
                            "2 Computer Komputer Compüter Computer Kompüter Kombüse\n" +
                            "2 Lehrerin die Lererin Lehrerien Lehrerinnen Lehrerinen Lererinnen\n" +
                            "2 Apfel der Appel Abfel Äpfel Epfel Äfel\n" +
                            "2 Schere die Schäre Schähre Scheren Schären Schähren \n" +
                            "2 Maus die Meis Mauhs Mäuse Meuse Meuhse\n" +
                            "2 Schule die Schuhle Schulle Schulen Schullen Schuhlen  \n" +
                            "2 Tasche die Tasce Tache Taschen Tachen Taschhen\n" +
                            "2 Schultasche die\n" +
                            "2 Brot das\n" +
                            "2 Ball der\n" +
                            "2 Fenster das\n" +
                            "2 Bank die\n" +
                            "2 Birne die\n" +
                            "2 Boden der\n" +
                            "2 Gras das\n" +
                            "2 Gemüse das\n" +
                            "2 Garten der\n" +
                            "2 Dach das\n" +
                            "2 Schwester die\n" +
                            "2 Vater der\n" +
                            "2 Mutter die\n" +
                            "2 Eltern die\n" +
                            "2 Bruder der\n" +
                            "2 Schwester die\n" +
                            "2 Geschwister die\n" +
                            "2 Opa der\n" +
                            "2 Oma die\n" +
                            "2 Wärme die\n" +
                            "2 Kälte die\n" +
                            "2 Großeltern die\n" +
                            "2 Vogel der\n" +
                            "2 Verkehr der\n" +
                            "2 Vase die\n" +
                            "2 Puppe die\n" +
                            "2 Papier das\n" +
                            "2 Tante die\n" +
                            "2 Körper der\n" +
                            "2 Kopf der\n" +
                            "2 Käfer der\n" +
                            "2 Kalender der\n" +
                            "2 Dieb der\n" +
                            "2 Korb der\n" +
                            "2 Tag der\n" +
                            "2 Weg der\n" +
                            "2 Kleid das\n" +
                            "2 Hand die\n" +
                            "2 Sand der\n" +
                            "2 Geld das\n" +
                            "2 Feld das\n" +
                            "2 Wind der\n" +
                            "2 Hund der\n" +
                            "2 Bauch der\n" +
                            "2 Nacht die\n" +
                            "2 Tochter die\n" +
                            "2 Licht das\n" +
                            "2 Schnee der\n" +
                            "2 Kugel die\n" +
                            "2 Schneekugel die\n" +
                            "2 Schlitten der\n" +
                            "2 Möhre die\n" +
                            "2 Regen der\n" +
                            "2 Nebel der\n" +
                            "2 Hemd das\n" +
                            "2 Hecke die\n" +
                            "2 Ohr das\n" +
                            "2 Rock der\n" +
                            "2 Zucker der\n" +
                            "2 Rücken der\n" +
                            "2 Paket das\n" +
                            "2 Ast der\n" +
                            "2 Blatt das\n" +
                            "2 Schuh der\n" +
                            "2 Busch der\n" +
                            "3 schlafen\n" +
                            "3 scheinen\n" +
                            "3 schauen\n" +
                            "3 schneiden\n" +
                            "3 baden\n" +
                            "3 bauen\n" +
                            "3 dürfen\n" +
                            "3 lesen lese liest liest lesen lest lesen\n" +
                            "3 schreiben schreibe schreibst schreibt schreiben schreibt schreiben\n" +
                            "3 rechnen rechne rechnest rechnet rechnen rechnet rechnen\n" +
                            "3 malen male malst malt malen malt malen\n" +
                            "3 singen singe singst singt singen singt singen\n" +
                            "3 spielen\n" +
                            "3 kochen\n" +
                            "3 gießen\n" +
                            "3 decken\n" +
                            "3 leeren\n" +
                            "3 spülen\n" +
                            "3 putzen\n" +
                            "3 waschen\n" +
                            "3 wünschen\n" +
                            "3 tanzen\n" +
                            "3 lachen\n" +
                            "3 versuchen\n" +
                            "3 turnen\n" +
                            "3 kaufen\n" +
                            "3 arbeiten\n" +
                            "3 üben\n" +
                            "3 bleiben\n" +
                            "3 geben\n" +
                            "3 leben\n" +
                            "3 fragen\n" +
                            "3 legen\n" +
                            "3 liegen\n" +
                            "3 zeigen\n" +
                            "3 schlagen\n" +
                            "3 bewegen\n" +
                            "3 pflegen\n" +
                            "3 sein\n" +
                            "3 machen\n" +
                            "3 rollen\n" +
                            "3 holen\n" +
                            "3 schmelzen\n" +
                            "3 fressen\n" +
                            "3 essen\n" +
                            "3 fallen\n" +
                            "3 kommen\n" +
                            "3 müssen\n" +
                            "3 wollen\n" +
                            "3 finden\n" +
                            "3 suchen\n" +
                            "3 rufen\n" +
                            "3 backen\n" +
                            "3 lecken\n" +
                            "3 drücken\n" +
                            "3 packen\n" +
                            "3 halten\n" +
                            "3 tragen\n" +
                            "3 lügen\n" +
                            "3 petzen\n" +
                            "3 helfen\n" +
                            "3 beschützen\n" +
                            "3 schreien"
            );

            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getDeutschFileName(Context context) {

       return new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), "deutschVorlagen.txt");

    }

    public List<SinglePlural> getDeutschSinglePluralEntities(Context context) {

        BufferedReader br = getBufferedReaderDeutschVorlagen(context);
        List<SinglePlural> entities = new ArrayList<>();

        try {
          String line = br.readLine();

            do {
                String [] split = line.split(" ");
                if (split.length < 8) {
                    continue;
                }

                if (split[0].equals("2")) {
                    entities.add(new SinglePlural(split)); break;
                }

                line = br.readLine();

            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return entities;

    }

    public String getImageFilePath(Context context, String singular) {
        return new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), singular + ".png").getAbsolutePath();

    }


}
