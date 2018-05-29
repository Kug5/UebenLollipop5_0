package com.example.greiser.uebenlollipop5_0;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

public class MatheActivity extends AppCompatActivity {

    static final String kugel = "&#9679;";
    static final String kugel_minus = "&#9152;";

    Map plus = new HashMap<String, Task>();
    Map minus = new HashMap<String, Task>();

    String currentAufgabe = null;
    String currentErgebnis = null;

    static int max = 20;
    int countAufgaben;

    EditText viewCurrentAufgabe = null;
    EditText viewErgebnis = null;
    ProgressBar progressBar;

    int plusCorrect = 0;
    int minusCorrect = 0;
    private EditText abakus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathe);

        Intent myIntent = getIntent(); // gets the previously created intent
        int many = myIntent.getIntExtra("many", 0);
        String operation = myIntent.getStringExtra("operation");

        if (operation.equals("plusminus")) {
            this.countAufgaben = many/2;
        } else {
            this.countAufgaben = many;
        }

        progressBar = findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        progressBar.setMax(countAufgaben * 2 );
        progressBar.setProgress(0);

        viewCurrentAufgabe = findViewById(R.id.aufgabe);
        viewCurrentAufgabe.setKeyListener(null);
        viewErgebnis = findViewById(R.id.ergebnis);
        viewErgebnis.setShowSoftInputOnFocus(false);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        abakus = findViewById(R.id.abakus);
        abakus.setShowSoftInputOnFocus(false);
        final ImageView help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abakus.setVisibility(View.VISIBLE);
            }
        });

        createKeybord(operation);
        createAufgaben(operation);
        chooseAufgabe(operation);
    }

    private void createAufgaben(String operation) {
        if (operation.equals("plusminus")) {
            createPlusMinusAufgaben();
        } else if (operation.equals("mult")) {
            createMultAufgaben();
        }
    }

    private void createMultAufgaben () {
        for (int i =  0; i <= 10; i++) {
            for (int k =  1; k <= 10; k++) {
                plus.put(i + " * " + k + " = ", new Task(i,k, i*k) );
            }
        }
    }

    private void createPlusMinusAufgaben () {
        for (int i =  0; i <= max; i++) {
            for (int k =  0; k <= max; k++) {
                if (i + k <= max) {
                    plus.put(i + " + " + k + " = ", new Task (i, k, i+k));
                }

                if (i - k >= 0) {
                    minus.put(i + " - " + k + " = ", new Task(i, k, i-k));
                }
            }
        }
    }

    private void createKeybord(final String operation) {
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
               checkResult(operation);
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
    private void chooseAufgabe(String operation) {

        abakus.setVisibility(View.INVISIBLE);
        if (operation.equals("plusminus")) {
            if (plusCorrect < countAufgaben) {
                int indexRandom = -1;
                do {
                    indexRandom = (int) (Math.random() * plus.size());
                } while (indexRandom > plus.size() - 1);

                currentAufgabe = plus.keySet().toArray()[indexRandom].toString();
                currentErgebnis = "" + ((Task) plus.get(currentAufgabe)).sum;
                createAbakusPlus((Task) plus.get(currentAufgabe));
                // abakusFive((Task)plus.get(currentAufgabe));
            } else {
                int indexRandom = -1;
                do {
                    indexRandom = (int) (Math.random() * minus.size());
                } while (indexRandom > minus.size() - 1);

                currentAufgabe = minus.keySet().toArray()[indexRandom].toString();
                currentErgebnis = "" + ((Task) minus.get(currentAufgabe)).sum;
                createAbakusMinus((Task) minus.get(currentAufgabe));
            }
        } else {
            int indexRandom = -1;
            do {
                indexRandom = (int) (Math.random() * plus.size());
            } while (indexRandom > plus.size() - 1);

            currentAufgabe = plus.keySet().toArray()[indexRandom].toString();
            currentErgebnis = "" + ((Task) plus.get(currentAufgabe)).sum;
            createAbakusPlus((Task) plus.get(currentAufgabe));
        }

        viewCurrentAufgabe.setText(currentAufgabe);
    }

    private void abakusFive(Task task) {

        String result = "";

        for(int i=1; i<= task.sum; i++) {
            if (i <=5 || (i > 10 && i<=15)) {
                result+="<font color='#FF0000'>" + kugel + "</font>";
            } else if (i>5 && i<=10 || (i > 15)) {
                result+="<font color='#0000FF'>" + kugel + "</font>";
            }

            if (i%10 == 0) {
                result+="<br/>";
            }
        }

        abakus.setText(Html.fromHtml(result));
    }

    private void createAbakusPlus(Task task) {

        String forI = "";
        String fork = "";

        for (int i = 0; i< task.summand1; i++) {
            forI+=kugel;
            if (i == 9) {
                forI+="<br/>";
            }
        }
        int lineDiff = task.summand1 - 10;
        if (lineDiff > 0) {
            lineDiff-= 10;
        }

        for (int k = 0; k< task.summand2; k++) {
            fork+=kugel;
            if ( Math.abs(lineDiff) == k + 1) {
                fork+="<br/>";
            }
        }

        abakus.setText(Html.fromHtml("<font color='#FF0000'>" + forI + " </font> <font color='#0000FF' >"+ fork+ "</font>"));
    }

    private void createAbakusMinus (Task task) {
        String forI = "";
        String fork = "";

        for (int i = 0; i< task.sum; i++) {
            forI+=kugel;
            if (i == 9) {
                forI+="<br/>";
            }
        }

        int lineDiff = task.sum - 10;
        if (lineDiff > 0) {
            lineDiff-= 10;
        }

        for (int k = 0; k< task.summand2; k++) {
            fork+=kugel_minus;
            if ( Math.abs(lineDiff) == k + 1) {
                fork+="<br/>";
            }

        }

        abakus.setText(Html.fromHtml("<font color='#FF0000'>" + forI + " </font> <font color='#FF0000' >"+ fork+ "</font>"));
    }

    private void checkResult (String operation) {
        if (viewErgebnis.getText().toString().equals(currentErgebnis)) {
            if (plusCorrect < countAufgaben) {
                plusCorrect++;
            } else {
                minusCorrect++;
            }

            progressBar.setProgress(plusCorrect + minusCorrect);
            if (plusCorrect < countAufgaben || minusCorrect < countAufgaben) {
                chooseAufgabe(operation);
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
