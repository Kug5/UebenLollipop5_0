package com.example.greiser.uebenlollipop5_0;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MatheActivity extends AppCompatActivity {

    Map plus = new HashMap<String, Integer>();
    Map minus = new HashMap<String, Integer>();

    String currentAufgabe = null;
    String currentErgebnis = null;

    static int max = 20;
    static int countAufgaben = 1;

    EditText viewCurrentAufgabe = null;
    EditText viewErgebnis = null;
    ProgressBar progressBar;

    int plusCorrect = 0;
    int minusCorrect = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathe);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(max);
        progressBar.setProgress(0);
        viewCurrentAufgabe = findViewById(R.id.aufgabe);
        viewCurrentAufgabe.setKeyListener(null);
        viewErgebnis = findViewById(R.id.ergebnis);
        viewErgebnis.requestFocus();

        viewErgebnis.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    checkResult();
                    return true;
                }
                return false;
            }
        });

        for (int i =  0; i <= max; i++) {
            for (int k =  0; k <= max; k++) {
                if (i + k <= max) {
                    plus.put(i + " + " + k + " = ", new Integer(i+k));
                }

                if (i - k >= 0) {
                    minus.put(i + " - " + k + " = ", new Integer(i-k));
                }
            }
        }

        chooseAufgabe();

//        final Button buttonCheck = findViewById(R.id.buttonCheck);
//        buttonCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkResult();
//            }
//        });
    }

    private void chooseAufgabe() {

        if (plusCorrect < countAufgaben) {
            int indexRandom = -1;
            do {
                indexRandom = (int)(Math.random() * plus.size());
            } while (indexRandom > plus.size() - 1 );

            currentAufgabe = plus.keySet().toArray()[indexRandom].toString();
            currentErgebnis = plus.get(currentAufgabe).toString();
        } else {
            int indexRandom = -1;
            do {
                indexRandom = (int)(Math.random() * minus.size());
            } while (indexRandom > minus.size() - 1 );

            currentAufgabe = minus.keySet().toArray()[indexRandom].toString();
            currentErgebnis = minus.get(currentAufgabe).toString();
        }

        viewCurrentAufgabe.setText(currentAufgabe);
    }

    private void checkResult () {
        if (viewErgebnis.getText().toString().equals(currentErgebnis)) {
            if (plusCorrect < countAufgaben) {
                plusCorrect++;
            } else {
                minusCorrect++;
            }

            progressBar.setProgress(plusCorrect + minusCorrect);
            if (plusCorrect < countAufgaben || minusCorrect < countAufgaben) {
                chooseAufgabe();
            } else {
                startActivity(new Intent(MatheActivity.this, SuperActivity.class));
            }
        } else {
            final Drawable background = viewErgebnis.getBackground();
            viewErgebnis.setBackgroundColor(Color.RED);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    viewErgebnis.setBackground(background);
                }
            }, 500);
        }
        viewErgebnis.setText("");
    }
}
