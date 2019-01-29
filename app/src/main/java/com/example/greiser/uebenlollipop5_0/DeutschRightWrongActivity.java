package com.example.greiser.uebenlollipop5_0;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class DeutschRightWrongActivity extends AppCompatActivity {

    private TextToSpeech textToSpeach;
    private List<Question> questions = new ArrayList<Question>();
    private Question currentQuestion;
    private TextView questionText;
    static int counter = 0;
    static final int howMany = 10;
    private Ueben application;
    private List<Integer> usedIndex = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deutsch);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        application = ((Ueben) getApplication());

        questionText = findViewById(R.id.toSpeak);
        createQuestions();
        chooseTask();

        textToSpeach = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeach.setLanguage(Locale.GERMAN);
                }
            }
        });

        final ImageView play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show(); //?
                HashMap<String, String> hash = new HashMap<String, String>();
                hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_NOTIFICATION));
                textToSpeach.speak(questionText.getText().toString(), TextToSpeech.QUEUE_ADD, hash);
            }
        });

        final Button stimmt = findViewById(R.id.stimmt);
        stimmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestion.answer.rightIndex == 0) {
                    counter++;
                    chooseTask();
                }
            }
        });

        final Button falsch = findViewById(R.id.falsch);
        falsch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestion.answer.rightIndex == 1) {
                    counter++;
                    chooseTask();
                }
            }
        });


    }

    private void chooseTask() {

        if (howMany == counter) {
            application.lastPoints = counter;
            startActivity(new Intent(DeutschRightWrongActivity.this, SuperActivity.class));
        }

        int index = -1;

        do {
            index = (int)(Math.random()* questions.size());
        } while (index > questions.size() || usedIndex.contains(index));

        usedIndex.add(index);
        currentQuestion = questions.get(index);
        questionText.setText(currentQuestion.text);
    }

    private void createQuestions() {
        String[] tmp = new String[2];
        tmp[0] = "richtig";
        tmp[1] = "falsch";
        questions.add(new Question("Opa ist der Papa von Papa", new Answer(tmp, 0)));
        questions.add(new Question("Raps ist gelb", new Answer(tmp, 0)));
        questions.add(new Question("Alle Autos sind lila", new Answer(tmp, 1)));
        questions.add(new Question("Wale schwimmen im Meer", new Answer(tmp, 0)));
        questions.add(new Question("Baden kann ich nur im See", new Answer(tmp, 1)));
        questions.add(new Question("Die Sonne ist gelb", new Answer(tmp, 0)));
        questions.add(new Question("Die Vögel pfeifen ein Lied", new Answer(tmp, 0)));
        questions.add(new Question("Die Wolken sind rosa", new Answer(tmp, 1)));
        questions.add(new Question("Der Tiger ist gefährlich", new Answer(tmp, 0)));
        questions.add(new Question("Das Eichhörnchen lebt im Wasser", new Answer(tmp, 1)));
        questions.add(new Question("Die Bienen summen in der Luft", new Answer(tmp, 0)));
        questions.add(new Question("Kinder sind groß", new Answer(tmp, 1)));
        questions.add(new Question("Erdbeereis schmeckt salzig", new Answer(tmp, 1)));
        questions.add(new Question("Das Auto parkt in der Garage", new Answer(tmp, 0)));
        questions.add(new Question("Der Lehrer kann nicht lesen", new Answer(tmp, 1)));
        questions.add(new Question("Das Auto kann fliegen", new Answer(tmp, 1)));
        questions.add(new Question("Gemüse ist ungesund", new Answer(tmp, 1)));
        questions.add(new Question("Sport ist gefährlich", new Answer(tmp, 1)));
        questions.add(new Question("Pferde können wiehern", new Answer(tmp, 0)));
        questions.add(new Question("Katzen können sprechen", new Answer(tmp, 1)));
        questions.add(new Question("Ein Viereck ist rund", new Answer(tmp, 1)));
        questions.add(new Question("Am Sonntag haben die Geschäfte geschlossen", new Answer(tmp, 0)));
        questions.add(new Question("Eine Woche hat 7 Tage", new Answer(tmp, 0)));
        questions.add(new Question("Zähne putzen ist wichtig", new Answer(tmp, 0)));
        questions.add(new Question("Im Sommer gehen wir ins Freibad (Schwimmbad)", new Answer(tmp, 0)));
    }

    private class Answer {

        String [] possibilities = null;
        int rightIndex=-1;

        public Answer (String[] possibilities, int rightIndex) {
            this.possibilities = possibilities;
            this.rightIndex = rightIndex;
        }

    }

    private class Question {
        String text;
        Answer answer;

        public Question (String text, Answer answer) {
            this.text = text;
            this.answer = answer;
        }
    }
}
