package com.example.greiser.uebenlollipop5_0.helper;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.greiser.uebenlollipop5_0.model.BigTask;
import com.example.greiser.uebenlollipop5_0.model.Konjugation;
import com.example.greiser.uebenlollipop5_0.model.SinglePlural;
import com.example.greiser.uebenlollipop5_0.model.UserHeightScore;
import com.example.greiser.uebenlollipop5_0.model.UserSetting;

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

    private static final String CURRENT_VERSION_DEUTSCH_VORLAGE = "v3";

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

    private String getStringBigTask(BigTask bt) {
        return bt.getDisplayTask() +","+ bt.getI() +","+ bt.getJ() +","+ bt.getResult() +","+bt.box;
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
                            "2 Computer der Komputer Compüter Computer Kompüter Kombüse\n" +
                            "2 Lehrerin die Lererin Lehrerien Lehrerinnen Lehrerinen Lererinnen\n" +
                            "2 Apfel der Appel Abfel Äpfel Epfel Äfel\n" +
                            "2 Schere die Schäre Schähre Scheren Schären Schähren \n" +
                            "2 Maus die Meis Mauhs Mäuse Meuse Meuhse\n" +
                            "2 Schule die Schuhle Schulle Schulen Schullen Schuhlen  \n" +
                            "2 Tasche die Tasce Tache Taschen Tachen Taschhen\n" +
                            "2 Schultasche die Chultasche Schuhltasche Schultaschen Schuhltaschen Schultachen\n" +
                            "2 Brot das Brott Prot Brote Prote Brotte\n" +
                            "2 Ball der Bal Baal Bälle Belle Bele\n" +
                            "2 Fenster das Fänster Venster Fenster Fänster Venster\n" +
                            "2 Bank die Bang Banck Bänke Benke Bäncke\n" +
                            "2 Birne die Bierne Pirne Birnen Biernen Pirnen\n" +
                            "2 Boden der Buden Boten Böden Bürden Bötten\n" +
                            "2 Gras das Grass Kras Gräser Kräser Greser\n" +
                            "2 Gemüse das Gemüze Gemüße Gemüse Kemüse Gemüsse\n" +
                            "2 Garten der Karten Garden Gärten Gerten Kerten\n" +
                            "2 Dach das Dack Tach Dächer Decher Techer\n" +
                            "2 Schwester die Schester Schwerter Schwestern Schestern Schwertern \n" +
                            "2 Vater der Fater Pfatter Väter Veter Feter\n" +
                            "2 Mutter die Muter Muttär Mütter Mitter Müttär\n" +
                            "2 Eltern die Elten Ältern Eltern Ältern Eltärn\n" +
                            "2 Bruder der Pruder Bruter Brüder Prüder Brüter\n" +
                            "2 Geschwister die Keschwister Geschfister Geschwister Kreschwister Geschwiter\n" +
                            "2 Opa der Oppa Oba Opas Obbas Oppas\n" +
                            "2 Oma die Omma Uma Omas Ommas Umas\n" +
                            "2 Wärme die Werme Wärmme Wärme Werme Wärmme\n" +
                            "2 Kälte die Kelte Gelte Kälte Kelte Gälte\n" +
                            "2 Großeltern die Grosältern Großältern Großeltern Grosältern Großältern\n" +
                            "2 Vogel der Fogel Vokel Vögel Fögel Vökel\n" +
                            "2 Verkehr der Ferkehr Verker Verkehre Ferkähre Verkähre\n" +
                            "2 Vase die Fase Wase Vasen Fasen Wasen\n" +
                            "2 Puppe die Pupe Bubben Puppen Buppen Pupen\n" +
                            "2 Papier das Pappier Papir Papiere Pappiere Pappire\n" +
                            "2 Tante die Tande Tantte Tanten Tantten Tanden\n" +
                            "2 Körper der Körber Körpper Körper Körpper Körpär\n" +
                            "2 Kopf der Kof Gopf Köpfe Göpfe Köfe\n" +
                            "2 Käfer der Kefer Käver Käfer Kefer Käpfer\n" +
                            "2 Kalender der Kalenter Galender Kalender Kälender Kalenter\n" +
                            "2 Dieb der Dib Diep Diebe Dibe Diepe\n" +
                            "2 Korb der Korp Gorb Körbe Körpe Köbe\n" +
                            "2 Tag der Tak Tack Tage Take Tagge\n" +
                            "2 Weg der Wek Weck Wege Wegge Wäge\n" +
                            "2 Kleid das Kleit Klied Kleider Kleiter Glitter\n" +
                            "2 Hand die Hant Hannd Hände Hende Hänte\n" +
                            "2 Sand der Sant Sannd Sande Sante Sandän\n" +
                            "2 Geld das Gelt Gäld Gelder Gälder Gelter\n" +
                            "2 Feld das Fält Felt Felder Fälder Felter\n" +
                            "2 Wind der Wint Wient Winde Wiende Winte\n" +
                            "2 Hund der Hunt Huntt Hunde Hunte Hundee\n" +
                            "2 Bauch der Beuch Bauhc Bäuche Beuche Bäuhce\n" +
                            "2 Nacht die Nachd Nahct Nächte Nechte nächte \n" +
                            "2 Tochter die Dochter tochter Töchter töchter Döchter\n" +
                            "2 Licht das licht Liecht Lichter Liechter Lihcter\n" +
                            "2 Schnee der Schne Schnä Schnee Schnä Schne\n" +
                            "2 Kugel die Gugel Kukel Kugeln Gugeln Kukeln\n" +
                            "2 Schneekugel die Schnekugel Schneegukel Scheekugeln Schnäkugeln Schnekukeln\n" +
                            "2 Schlitten der Schliten Schlieten Schlitten Schlieten Schliten\n" +
                            "2 Möhre die Mören Möhhre Möhren Mohrän Möhrän\n" +
                            "2 Regen der Rägen Reken Regen Reken Rägen\n" +
                            "2 Nebel der Näbel Nebbel Nebel Näbel Nebeln\n" +
                            "2 Hemd das Hämd Hemt Hemden Hämnden Hemnden\n" +
                            "2 Hecke die Häcke Heke Hecken Häcken Heken\n" +
                            "2 Ohr das Or Ohrr Ohren Oren Ohrän\n" +
                            "2 Rock der Rok Rog Röcke Röke Rökke\n" +
                            "2 Zucker der Zukker Sucker Zucker Sucker Zukker\n" +
                            "2 Rücken der Rükken Rükcen Rücken Rükken Rükcen\n" +
                            "2 Paket das Packet Pakket Pakete Packete Pakkete\n" +
                            "2 Ast der Aßt Asst Äste Äßte Ässte \n" +
                            "2 Blatt das Blad Blat Blätter Bletter Bläter\n" +
                            "2 Schuh der Schuh Schuhh Schuhe Schue Schuhee\n" +
                            "2 Busch der Bushc Buusch Büsche Biesche Büchse\n" +
                            "3 schlafen schlafe schläfst schläft schlafen schlaft schlafen\n" +
                            "3 scheinen scheine scheinst scheint scheinen scheint scheinen\n" +
                            "3 schauen schaue schaust schaut schauen schaut schauen\n" +
                            "3 schneiden schneide schneidest schneidet schneiden schneidet schneiden\n" +
                            "3 baden bade badest badet baden badet baden\n" +
                            "3 bauen baue baust baut bauen baut bauen\n" +
                            "3 dürfen darf darfst darf dürfen dürft dürfen\n" +
                            "3 lesen lese liest liest lesen lest lesen\n" +
                            "3 schreiben schreibe schreibst schreibt schreiben schreibt schreiben\n" +
                            "3 rechnen rechne rechnest rechnet rechnen rechnet rechnen\n" +
                            "3 malen male malst malt malen malt malen\n" +
                            "3 singen singe singst singt singen singt singen\n" +
                            "3 spielen spielst spielt spielen spielt spielen\n" +
                            "3 kochen koche kochst kocht kochen kocht kochen\n" +
                            "3 gießen gieße gießt gießt gießen gießt gießen\n" +
                            "3 decken decke deckst deckt decken deckt decken\n" +
                            "3 leeren leere leerst leert leeren leert leeren\n" +
                            "3 spülen spüle spülst spült spülen spült spülen\n" +
                            "3 putzen putze putzt putzt putzen putzt putzen\n" +
                            "3 wasche wasche wäschst wäscht waschen wascht waschen\n" +
                            "3 wünschen wünsche wünschst wünscht wünschen wünscht wünschen\n" +
                            "3 tanzen tanze tanzt tanzt tanzen tanzt tanzen\n" +
                            "3 lachen lache lachst lacht lachen lacht lachen\n" +
                            "3 versuchen versuche versuchst versucht versuchen versucht versuchen\n" +
                            "3 turnen turne turnst turnt turnen turnt turnen\n" +
                            "3 kaufen kaufe kaufst kauft kaufen kauft kaufen\n" +
                            "3 arbeiten arbeite arbeitest arbeitet arbeiten arbeitet arbeiten\n" +
                            "3 üben übe übst übt üben übt üben\n" +
                            "3 bleiben bleibe bleibst bleibt bleiben bliebt bleiben\n" +
                            "3 geben gebe gibst gibt geben gebt geben\n" +
                            "3 leben lebe lebst lebt leben lebt leben\n" +
                            "3 fragen frage fragst fragt fragen fragt fragen\n" +
                            "3 legen lege legst legt legen legt legen\n" +
                            "3 liegen liege liegst liegt liegen liegt liegen\n" +
                            "3 zeigen zeige zeigst zeigt zeigen zeigt zeigen\n" +
                            "3 schlagen schlage schlägst schlägt schlagen schlagt schlagen\n" +
                            "3 bewegen bewege bewegst bewegt bewegen bewegt bewegen\n" +
                            "3 pflegen pflege pflegst pflegt pflegen pflegt pflegen\n" +
                            "3 sein bin bist ist sind seid sind\n" +
                            "3 machen mache machst macht machen macht machen\n" +
                            "3 rollen rolle rollst rollt rollen rollt rollen\n" +
                            "3 holen hole holst holt holen holt holen\n" +
                            "3 schmelzen schmelze schmilzt schmilzt schmelzen schmelzt schmelzen\n" +
                            "3 fressen fresse frisst frisst fressen fresst fressen\n" +
                            "3 essen esse isst isst essen esst essen\n" +
                            "3 fallen falle fällst fällt fallen fallt falllen\n" +
                            "3 kommen komme kommst kommt kommen kommt kommen\n" +
                            "3 müssen muss musst muss müssen müsst müssen\n" +
                            "3 wollen will willst will wollen wollt wollen\n" +
                            "3 finden finde findest findet finden findet finden\n" +
                            "3 suchen suche suchst sucht suchen sucht suchen\n" +
                            "3 rufen rufe rufst ruft rufen ruft rufen\n" +
                            "3 backen backe backst bakct backen backt backen\n" +
                            "3 lecken lecke leckst leckt lecken leckt lecken\n" +
                            "3 drücken drücke drückst drückt drücken drückt drücken\n" +
                            "3 packen packe packst packt packen packt packen\n" +
                            "3 halten halte hälst hält halten haltet halten\n" +
                            "3 tragen trage trägst trägt tragen tragt tragen\n" +
                            "3 lügen lüge lügst lügt lügen lügt lügen\n" +
                            "3 petzen petze petzt petzt petzten petzt petzen\n" +
                            "3 helfen helfe hilfst hilft helfen helft helfen\n" +
                            "3 beschützen beschütze beschützt beschützt beschützen beschützt beschützen\n" +
                            "3 schreien schreie schreist schreit schreien schreit schreien"
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

                if (split.length == 8 && split[0].equals("2")) {
                    entities.add(new SinglePlural(split));
                }

                line = br.readLine();

            } while (line != null);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entities;

    }

    public String getImageFilePath(Context context, String singular) {
        return new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), singular + ".png").getAbsolutePath();
    }


    public List<Konjugation> getDeutschKonjugationEntities(Context context) {

        BufferedReader br = getBufferedReaderDeutschVorlagen(context);
        List<Konjugation> entities = new ArrayList<>();

        try {
            String line = br.readLine();

            do {
                String [] split = line.split(" ");

                if (split.length == 8 && split[0].equals("3")) {
                    entities.add(new Konjugation(split));
                }

                line = br.readLine();

            } while (line != null);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entities;


    }
}
