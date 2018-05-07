package com.example.greiser.uebenlollipop5_0;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlockMathActivity extends AppCompatActivity {


    Map<String, Task> b1 = new LinkedHashMap<String, Task>();
    Map<String, Task> b2 = new LinkedHashMap<String, Task>();
    Map<String, Task> b3 = new LinkedHashMap<String, Task>();
    Map<String, Task> b4 = new LinkedHashMap<String, Task>();
    private EditText[] aufgabenMap;
    private EditText[] ergebnisMap;

    public EditText currentInputFocus;
    private Map[] b;

    private int right = Color.GREEN;
    private int wrong = Color.RED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_math);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        b = new Map[4];

        createInputFields();
        createKeyboard();
        createTasks();

        currentInputFocus = ergebnisMap[0];
        setCourser();
    }

    private void createInputFields() {
        aufgabenMap = new EditText[16];
        aufgabenMap[0] = findViewById(R.id.aufgabe01);
        aufgabenMap[1] = findViewById(R.id.aufgabe02);
        aufgabenMap[2] = findViewById(R.id.aufgabe03);
        aufgabenMap[3] = findViewById(R.id.aufgabe04);
        aufgabenMap[4] = findViewById(R.id.aufgabe05);
        aufgabenMap[5] = findViewById(R.id.aufgabe06);
        aufgabenMap[6] = findViewById(R.id.aufgabe07);
        aufgabenMap[7] = findViewById(R.id.aufgabe08);
        aufgabenMap[8] = findViewById(R.id.aufgabe09);
        aufgabenMap[9] = findViewById(R.id.aufgabe10);
        aufgabenMap[10] = findViewById(R.id.aufgabe11);
        aufgabenMap[11] = findViewById(R.id.aufgabe12);
        aufgabenMap[12] = findViewById(R.id.aufgabe13);
        aufgabenMap[13] = findViewById(R.id.aufgabe14);
        aufgabenMap[14] = findViewById(R.id.aufgabe15);
        aufgabenMap[15] = findViewById(R.id.aufgabe16);

        ergebnisMap = new EditText[16];
        ergebnisMap[0] = findViewById(R.id.ergebnis01);
        ergebnisMap[1] = findViewById(R.id.ergebnis02);
        ergebnisMap[2] = findViewById(R.id.ergebnis03);
        ergebnisMap[3] = findViewById(R.id.ergebnis04);
        ergebnisMap[4] = findViewById(R.id.ergebnis05);
        ergebnisMap[5] = findViewById(R.id.ergebnis06);
        ergebnisMap[6] = findViewById(R.id.ergebnis07);
        ergebnisMap[7] = findViewById(R.id.ergebnis08);
        ergebnisMap[8] = findViewById(R.id.ergebnis09);
        ergebnisMap[9] = findViewById(R.id.ergebnis10);
        ergebnisMap[10] = findViewById(R.id.ergebnis11);
        ergebnisMap[11] = findViewById(R.id.ergebnis12);
        ergebnisMap[12] = findViewById(R.id.ergebnis13);
        ergebnisMap[13] = findViewById(R.id.ergebnis14);
        ergebnisMap[14] = findViewById(R.id.ergebnis15);
        ergebnisMap[15] = findViewById(R.id.ergebnis16);

    }

    private void createTasks() {
        for (int i = 0; i < 4; i++) {

            int summand1 = -1;
            int summand2 = -1;
            //do {
            summand1 = (int) (Math.random() * 10);
            summand2 = (int) (Math.random() * 10);
            //} while (summand2 > summand1 );

            fillLayout(i, summand1, summand2);
        }
    }

    private void fillLayout(final int block, final int summand1, final int summand2) {

        final Task t1 = new Task(summand1, summand2, summand1 + summand2);
        final Task t2 = new Task(summand2, summand1, summand1 + summand2);
        final Task t3 = new Task(summand1 + summand2, summand1, summand2);
        final Task t4 = new Task(summand1 + summand2, summand2, summand1);

        String ts1 = summand1 + " + " + summand2 + " = ";
        String ts2 = summand2 + " + " + summand1 + " = ";
        String ts3 = "?" + " - " + summand1 + " = ";
        final String ts4 = "?" + " - " + summand2 + " = ";

        final int index = block * 4;
        final int index_1 = index + 1;
        final int index_2 = index + 2;
        final int index_3 = index + 3;

        b[block] = new LinkedHashMap<String, Task>();
        b[block].put(ts1, t1);
        b[block].put(ts2, t2);
        b[block].put(ts3, t3);
        b[block].put(ts4, t4);

        aufgabenMap[index].setText(ts1);
        aufgabenMap[index_1].setText(ts2);
        aufgabenMap[index_2].setText(ts3);
        aufgabenMap[index_3].setText(ts4);

        ergebnisMap[index].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentInputFocus = ergebnisMap[index];
                } else {
                    boolean right = checkResult (ergebnisMap[index], t1);
                    if (right) {
                        replaceQuestionMark(aufgabenMap[index_2], aufgabenMap[index_3], (summand1 + summand2));
                    }
                }
            }
        });

        ergebnisMap[index_1].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentInputFocus = ergebnisMap[index_1];
                } else {
                    boolean right = checkResult (ergebnisMap[index_1], t2);
                    if (right) {
                        replaceQuestionMark(aufgabenMap[index_2], aufgabenMap[index_3], (summand1 + summand2));
                    }
                }
            }
        });
        ergebnisMap[index_2].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentInputFocus = ergebnisMap[index_2];
                } else {
                    checkResult (ergebnisMap[index_2], t3);
                }
            }
        });
        ergebnisMap[index_3].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    currentInputFocus = ergebnisMap[index_3];
                } else {
                    checkResult (ergebnisMap[index_3], t4);
                }
            }
        });
    }

    private void replaceQuestionMark(EditText editText1, EditText editText2, int with) {
        editText1.setText(editText1.getText().toString().replace("?", "" + with));
        editText2.setText(editText2.getText().toString().replace("?", "" + with));
    }

    private boolean checkResult(EditText editText, Task task) {

        if (editText.getText().toString().trim().length() == 0) {
            return false;
        }

        if (editText.getText().toString().equals("" + task.sum)) {
            editText.setBackgroundColor(right);
            return true;
        } else {
            editText.setBackgroundColor(wrong);
            return false;
        }
    }

    private void createKeyboard() {
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
                    String tmp = currentInputFocus.getText().toString();
                    if (tmp.length() != 0) {
                        currentInputFocus.setText(tmp.subSequence(0, tmp.length() - 1));
                        setCourser();

                    }
                }
            });

            final Button button_OK = findViewById(R.id.button_OK);
            button_OK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (int i = 0; i < 4; i++) {
                        Map<String, Task> whatever = b[i];
                        int counter = 0;
                        for (Task task : whatever.values()) {
                            checkResult(ergebnisMap[ i * 4 + counter], task);
                            counter++;
                        }


                    }
                    // checkResult();
                }
            });

    }

    private void addNumber(int number) {
        currentInputFocus.setText(currentInputFocus.getText().toString()+ number);
        setCourser();
    }

    private void setCourser() {
        currentInputFocus.setSelection(currentInputFocus.getText().toString().length());
    }

}
