package com.example.greiser.uebenlollipop5_0;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
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

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        progressBar = findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        progressBar.setMax(countAufgaben * 2 );
        progressBar.setProgress(0);

        viewCurrentAufgabe = findViewById(R.id.aufgabe);
        viewCurrentAufgabe.setKeyListener(null);
        viewErgebnis = findViewById(R.id.ergebnis);
        viewErgebnis.requestFocus();

        createKeybord();

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

    private void createKeybord() {
        final Button button_0 = findViewById(R.id.button_0);
        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(0);
            }
        });

        final Button button_1 = findViewById(R.id.button_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(1);
            }
        });

        final Button button_2 = findViewById(R.id.button_2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(2);
            }
        });

        final Button button_3 = findViewById(R.id.button_3);
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(3);
            }
        });

        final Button button_4 = findViewById(R.id.button_4);
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(4);
            }
        });

        final Button button_5 = findViewById(R.id.button_5);
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(5);
            }
        });

        final Button button_6 = findViewById(R.id.button_6);
        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(6);
            }
        });

        final Button button_7 = findViewById(R.id.button_7);
        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(7);
            }
        });

        final Button button_8 = findViewById(R.id.button_8);
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(8);
            }
        });

        final Button button_9 = findViewById(R.id.button_9);
        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(9);
            }
        });

        final Button button_BACK = findViewById(R.id.button_back);
        button_BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = viewErgebnis.getText().toString();
                if (tmp.length() != 0) {
                    viewErgebnis.setText(tmp.subSequence(0, tmp.length() - 1));
                    setCourser();

                }
            }
        });

        final Button button_OK = findViewById(R.id.button_OK);
        button_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkResult();
            }
        });
    }

    private void addNumber(int number) {
        viewErgebnis.setText(viewErgebnis.getText().toString()+ number);
        setCourser();
    }
    private void setCourser() {
        viewErgebnis.setSelection(viewErgebnis.getText().toString().length());
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
