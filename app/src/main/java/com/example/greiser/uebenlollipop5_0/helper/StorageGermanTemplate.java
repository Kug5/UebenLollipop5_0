package com.example.greiser.uebenlollipop5_0.helper;

import android.content.Context;
import android.os.Environment;

import com.example.greiser.uebenlollipop5_0.model.Konjugation;
import com.example.greiser.uebenlollipop5_0.model.SinglePlural;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StorageGermanTemplate extends UebenStorage implements Storage {

    private static final String CURRENT_VERSION_DEUTSCH_VORLAGE = "v4";

    public StorageGermanTemplate(Context context) {
        super(context);
    }

    private void store() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(getFile()));
            bw.write(
                    CURRENT_VERSION_DEUTSCH_VORLAGE
                            + "\n"
                            + "1 bunt gelb braun rot blau grün süß saftig groß rund klein schwer grau klar stark kalt heftig schwarz schnell schwer schön schon November Dezember Januar Februar März April Mai Juni Juli August September Oktober voll viel vom von vor vier eins zwei drei vier fünf sechs sieben acht neuen zehn elf zwölf dreihzehn vierzehn einhundert tausend klein kalt krank morgen gesund fremd uns und noch auch doch weiß zuerst dann danach nun zuletzt warm stark freundlich klug nett hilfsbereit fleißig schüchtern witzig mutig frech ehrlich\n"
                            + "2 Bild das Bield Bilt Bilder Bielder Bilter \n"
                            + "2 Baum der Paum Bum Bäume Beume Päume\n"
                            + "2 Buch das Boch Bug Bücher Büscher Bicher\n"
                            + "2 Fisch der Füsch Fühsch Fische Füsche Fiche\n"
                            + "2 Blume die Plume Blome Blumen Blümen Plumen\n"
                            + "2 Katze die Kaze Gazte Katzen Gatzen Kazten\n"
                            + "2 Kind das Gind Kint Kinder Ginder Kinter\n"
                            + "2 Stift der Stiefte Stivte Stifte Stiefte Stivte\n"
                            + "2 Mädchen das Medchen Mätchen Mädchen Medchen Mätchen\n"
                            + "2 Junge der June Jonge Jungen Jugen Jüngen\n"
                            + "2 Computer der Komputer Compüter Computer Kompüter Kombüse\n"
                            + "2 Lehrerin die Lererin Lehrerien Lehrerinnen Lehrerinen Lererinnen\n"
                            + "2 Apfel der Appel Abfel Äpfel Epfel Äfel\n"
                            + "2 Schere die Schäre Schähre Scheren Schären Schähren \n"
                            + "2 Maus die Meis Mauhs Mäuse Meuse Meuhse\n"
                            + "2 Schule die Schuhle Schulle Schulen Schullen Schuhlen  \n"
                            + "2 Tasche die Tasce Tache Taschen Tachen Taschhen\n"
                            + "2 Schultasche die Chultasche Schuhltasche Schultaschen Schuhltaschen Schultachen\n"
                            + "2 Brot das Brott Prot Brote Prote Brotte\n"
                            + "2 Ball der Bal Baal Bälle Belle Bele\n"
                            + "2 Fenster das Fänster Venster Fenster Fänster Venster\n"
                            + "2 Bank die Bang Banck Bänke Benke Bäncke\n"
                            + "2 Birne die Bierne Pirne Birnen Biernen Pirnen\n"
                            + "2 Boden der Buden Boten Böden Bürden Bötten\n"
                            + "2 Gras das Grass Kras Gräser Kräser Greser\n"
                            + "2 Gemüse das Gemüze Gemüße Gemüse Kemüse Gemüsse\n"
                            + "2 Garten der Karten Garden Gärten Gerten Kerten\n"
                            + "2 Dach das Dack Tach Dächer Decher Techer\n"
                            + "2 Schwester die Schester Schwerter Schwestern Schestern Schwertern \n"
                            + "2 Vater der Fater Pfatter Väter Veter Feter\n"
                            + "2 Mutter die Muter Muttär Mütter Mitter Müttär\n"
                            + "2 Eltern die Elten Ältern Eltern Ältern Eltärn\n"
                            + "2 Bruder der Pruder Bruter Brüder Prüder Brüter\n"
                            + "2 Geschwister die Keschwister Geschfister Geschwister Kreschwister Geschwiter\n"
                            + "2 Opa der Oppa Oba Opas Obbas Oppas\n"
                            + "2 Oma die Omma Uma Omas Ommas Umas\n"
                            + "2 Wärme die Werme Wärmme Wärme Werme Wärmme\n"
                            + "2 Kälte die Kelte Gelte Kälte Kelte Gälte\n"
                            + "2 Großeltern die Grosältern Großältern Großeltern Grosältern Großältern\n"
                            + "2 Vogel der Fogel Vokel Vögel Fögel Vökel\n"
                            + "2 Verkehr der Ferkehr Verker Verkehre Ferkähre Verkähre\n"
                            + "2 Vase die Fase Wase Vasen Fasen Wasen\n"
                            + "2 Puppe die Pupe Bubben Puppen Buppen Pupen\n"
                            + "2 Papier das Pappier Papir Papiere Pappiere Pappire\n"
                            + "2 Tante die Tande Tantte Tanten Tantten Tanden\n"
                            + "2 Körper der Körber Körpper Körper Körpper Körpär\n"
                            + "2 Kopf der Kof Gopf Köpfe Göpfe Köfe\n"
                            + "2 Käfer der Kefer Käver Käfer Kefer Käpfer\n"
                            + "2 Kalender der Kalenter Galender Kalender Kälender Kalenter\n"
                            + "2 Dieb der Dib Diep Diebe Dibe Diepe\n"
                            + "2 Korb der Korp Gorb Körbe Körpe Köbe\n"
                            + "2 Tag der Tak Tack Tage Take Tagge\n"
                            + "2 Weg der Wek Weck Wege Wegge Wäge\n"
                            + "2 Kleid das Kleit Klied Kleider Kleiter Glitter\n"
                            + "2 Hand die Hant Hannd Hände Hende Hänte\n"
                            + "2 Sand der Sant Sannd Sande Sante Sandän\n"
                            + "2 Geld das Gelt Gäld Gelder Gälder Gelter\n"
                            + "2 Feld das Fält Felt Felder Fälder Felter\n"
                            + "2 Wind der Wint Wient Winde Wiende Winte\n"
                            + "2 Hund der Hunt Huntt Hunde Hunte Hundee\n"
                            + "2 Bauch der Beuch Bauhc Bäuche Beuche Bäuhce\n"
                            + "2 Nacht die Nachd Nahct Nächte Nechte nächte \n"
                            + "2 Tochter die Dochter tochter Töchter töchter Döchter\n"
                            + "2 Licht das licht Liecht Lichter Liechter Lihcter\n"
                            + "2 Schnee der Schne Schnä Schnee Schnä Schne\n"
                            + "2 Kugel die Gugel Kukel Kugeln Gugeln Kukeln\n"
                            + "2 Schneekugel die Schnekugel Schneegukel Scheekugeln Schnäkugeln Schnekukeln\n"
                            + "2 Schlitten der Schliten Schlieten Schlitten Schlieten Schliten\n"
                            + "2 Möhre die Mören Möhhre Möhren Mohrän Möhrän\n"
                            + "2 Regen der Rägen Reken Regen Reken Rägen\n"
                            + "2 Nebel der Näbel Nebbel Nebel Näbel Nebeln\n"
                            + "2 Hemd das Hämd Hemt Hemden Hämnden Hemnden\n"
                            + "2 Hecke die Häcke Heke Hecken Häcken Heken\n"
                            + "2 Ohr das Or Ohrr Ohren Oren Ohrän\n"
                            + "2 Rock der Rok Rog Röcke Röke Rökke\n"
                            + "2 Zucker der Zukker Sucker Zucker Sucker Zukker\n"
                            + "2 Rücken der Rükken Rükcen Rücken Rükken Rükcen\n"
                            + "2 Paket das Packet Pakket Pakete Packete Pakkete\n"
                            + "2 Ast der Aßt Asst Äste Äßte Ässte \n"
                            + "2 Blatt das Blad Blat Blätter Bletter Bläter\n"
                            + "2 Schuh der Schuuh Schuhh Schuhe Schue Schuhee\n"
                            + "2 Busch der Bushc Buusch Büsche Biesche Büchse\n"
                            + "3 schlafen schlafe schläfst schläft schlafen schlaft schlafen\n"
                            + "3 scheinen scheine scheinst scheint scheinen scheint scheinen\n"
                            + "3 schauen schaue schaust schaut schauen schaut schauen\n"
                            + "3 schneiden schneide schneidest schneidet schneiden schneidet schneiden\n"
                            + "3 baden bade badest badet baden badet baden\n"
                            + "3 bauen baue baust baut bauen baut bauen\n"
                            + "3 dürfen darf darfst darf dürfen dürft dürfen\n"
                            + "3 lesen lese liest liest lesen lest lesen\n"
                            + "3 schreiben schreibe schreibst schreibt schreiben schreibt schreiben\n"
                            + "3 rechnen rechne rechnest rechnet rechnen rechnet rechnen\n"
                            + "3 malen male malst malt malen malt malen\n"
                            + "3 singen singe singst singt singen singt singen\n"
                            + "3 spielen spielst spielt spielen spielt spielen\n"
                            + "3 kochen koche kochst kocht kochen kocht kochen\n"
                            + "3 gießen gieße gießt gießt gießen gießt gießen\n"
                            + "3 decken decke deckst deckt decken deckt decken\n"
                            + "3 leeren leere leerst leert leeren leert leeren\n"
                            + "3 spülen spüle spülst spült spülen spült spülen\n"
                            + "3 putzen putze putzt putzt putzen putzt putzen\n"
                            + "3 waschen wasche wäschst wäscht waschen wascht waschen\n"
                            + "3 wünschen wünsche wünschst wünscht wünschen wünscht wünschen\n"
                            + "3 tanzen tanze tanzt tanzt tanzen tanzt tanzen\n"
                            + "3 lachen lache lachst lacht lachen lacht lachen\n"
                            + "3 versuchen versuche versuchst versucht versuchen versucht versuchen\n"
                            + "3 turnen turne turnst turnt turnen turnt turnen\n"
                            + "3 kaufen kaufe kaufst kauft kaufen kauft kaufen\n"
                            + "3 arbeiten arbeite arbeitest arbeitet arbeiten arbeitet arbeiten\n"
                            + "3 üben übe übst übt üben übt üben\n"
                            + "3 bleiben bleibe bleibst bleibt bleiben bliebt bleiben\n"
                            + "3 geben gebe gibst gibt geben gebt geben\n"
                            + "3 leben lebe lebst lebt leben lebt leben\n"
                            + "3 fragen frage fragst fragt fragen fragt fragen\n"
                            + "3 legen lege legst legt legen legt legen\n"
                            + "3 liegen liege liegst liegt liegen liegt liegen\n"
                            + "3 zeigen zeige zeigst zeigt zeigen zeigt zeigen\n"
                            + "3 schlagen schlage schlägst schlägt schlagen schlagt schlagen\n"
                            + "3 bewegen bewege bewegst bewegt bewegen bewegt bewegen\n"
                            + "3 pflegen pflege pflegst pflegt pflegen pflegt pflegen\n"
                            + "3 sein bin bist ist sind seid sind\n"
                            + "3 machen mache machst macht machen macht machen\n"
                            + "3 rollen rolle rollst rollt rollen rollt rollen\n"
                            + "3 holen hole holst holt holen holt holen\n"
                            + "3 schmelzen schmelze schmilzt schmilzt schmelzen schmelzt schmelzen\n"
                            + "3 fressen fresse frisst frisst fressen fresst fressen\n"
                            + "3 essen esse isst isst essen esst essen\n"
                            + "3 fallen falle fällst fällt fallen fallt fallen\n"
                            + "3 kommen komme kommst kommt kommen kommt kommen\n"
                            + "3 müssen muss musst muss müssen müsst müssen\n"
                            + "3 wollen will willst will wollen wollt wollen\n"
                            + "3 finden finde findest findet finden findet finden\n"
                            + "3 suchen suche suchst sucht suchen sucht suchen\n"
                            + "3 rufen rufe rufst ruft rufen ruft rufen\n"
                            + "3 backen backe backst backt backen backt backen\n"
                            + "3 lecken lecke leckst leckt lecken leckt lecken\n"
                            + "3 drücken drücke drückst drückt drücken drückt drücken\n"
                            + "3 packen packe packst packt packen packt packen\n"
                            + "3 halten halte hältst hält halten haltet halten\n"
                            + "3 tragen trage trägst trägt tragen tragt tragen\n"
                            + "3 lügen lüge lügst lügt lügen lügt lügen\n"
                            + "3 petzen petze petzt petzt petzten petzt petzen\n"
                            + "3 helfen helfe hilfst hilft helfen helft helfen\n"
                            + "3 beschützen beschütze beschützt beschützt beschützen beschützt beschützen\n"
                            + "3 schreien schreie schreist schreit schreien schreit schreien");

            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object load() {
        return null;
    }

    @Override
    public File getFile() {
        return new File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "deutschVorlagen.txt");
    }

    @Override
    public void update(Object newValue) {
    }

    public List<String> getDeutschWriteEntities() {

        List<String> entities = new ArrayList<>();

        try {
            BufferedReader br = getBufferedReaderDeutschVorlagen();
            String line = br.readLine();
            do {
                String[] split = line.split(" ");
                if (split.length < 2) {
                    continue;
                }
                switch (split[0]) {
                    case "1":
                        entities.addAll(getEntriesType1(split));
                        break;
                    case "2":
                        entities.add(split[1]);
                        break;
                    case "3":
                        entities.add(split[1]);
                        break;
                }
                line = br.readLine();
            } while (line != null);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return entities;
    }

    private BufferedReader getBufferedReaderDeutschVorlagen() {

        File template = getFile();
        if (!template.exists()) {
            store();
            return getBufferedReaderDeutschVorlagen();
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(template));

            String line = br.readLine();
            if (line == null || !line.equals(CURRENT_VERSION_DEUTSCH_VORLAGE)) {
                store();
                return getBufferedReaderDeutschVorlagen();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return br;
        }
    }

    public List<SinglePlural> getDeutschSinglePluralEntities() {

        BufferedReader br = getBufferedReaderDeutschVorlagen();
        List<SinglePlural> entities = new ArrayList<>();

        try {
            String line = br.readLine();

            do {
                String[] split = line.split(" ");

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

    public List<Konjugation> getDeutschKonjugationEntities() {

        BufferedReader br = getBufferedReaderDeutschVorlagen();
        List<Konjugation> entities = new ArrayList<>();

        try {
            String line = br.readLine();

            do {
                String[] split = line.split(" ");

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

    private List<String> getEntriesType1(String[] split) {

        List<String> entries = new ArrayList<>();
        for (String entry : split) {
            try {
                Integer.parseInt(entry);
            } catch (NumberFormatException e) {
                entries.add(entry);
            }
        }
        return entries;
    }
}
