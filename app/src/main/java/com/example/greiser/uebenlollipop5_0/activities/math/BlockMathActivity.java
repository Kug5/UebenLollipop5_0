package com.example.greiser.uebenlollipop5_0.activities.math;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.activities.SuperActivity;
import com.example.greiser.uebenlollipop5_0.model.Task;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlockMathActivity extends AppCompatActivity {


    Map<String, Task> b1 = new LinkedHashMap<String, Task>();

    private EditText[] taskMap;
    private EditText[] resultMap;

    public EditText currentInputFocus;
    private Map[] blocks;

    private static int wrong = Color.RED;
    private static int right = Color.GREEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_math);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        blocks = new Map[4];

        createInputFields();
        createKeyboard();
        createTasks();

        currentInputFocus = resultMap[0];
        setCourser();
    }



    private void createInputFields() {
        taskMap = new EditText[16];
        taskMap[0] = findViewById(R.id.aufgabe01);
        taskMap[1] = findViewById(R.id.aufgabe02);
        taskMap[2] = findViewById(R.id.aufgabe03);
        taskMap[3] = findViewById(R.id.aufgabe04);
        taskMap[4] = findViewById(R.id.aufgabe05);
        taskMap[5] = findViewById(R.id.aufgabe06);
        taskMap[6] = findViewById(R.id.aufgabe07);
        taskMap[7] = findViewById(R.id.aufgabe08);
        taskMap[8] = findViewById(R.id.aufgabe09);
        taskMap[9] = findViewById(R.id.aufgabe10);
        taskMap[10] = findViewById(R.id.aufgabe11);
        taskMap[11] = findViewById(R.id.aufgabe12);
        taskMap[12] = findViewById(R.id.aufgabe13);
        taskMap[13] = findViewById(R.id.aufgabe14);
        taskMap[14] = findViewById(R.id.aufgabe15);
        taskMap[15] = findViewById(R.id.aufgabe16);

        resultMap = new EditText[16];
        resultMap[0] = findViewById(R.id.ergebnis01);
        resultMap[1] = findViewById(R.id.ergebnis02);
        resultMap[2] = findViewById(R.id.ergebnis03);
        resultMap[3] = findViewById(R.id.ergebnis04);
        resultMap[4] = findViewById(R.id.ergebnis05);
        resultMap[5] = findViewById(R.id.ergebnis06);
        resultMap[6] = findViewById(R.id.ergebnis07);
        resultMap[7] = findViewById(R.id.ergebnis08);
        resultMap[8] = findViewById(R.id.ergebnis09);
        resultMap[9] = findViewById(R.id.ergebnis10);
        resultMap[10] = findViewById(R.id.ergebnis11);
        resultMap[11] = findViewById(R.id.ergebnis12);
        resultMap[12] = findViewById(R.id.ergebnis13);
        resultMap[13] = findViewById(R.id.ergebnis14);
        resultMap[14] = findViewById(R.id.ergebnis15);
        resultMap[15] = findViewById(R.id.ergebnis16);

        for (EditText aResultMap : resultMap) {
            aResultMap.setShowSoftInputOnFocus(false);
        }
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
        String ts4 = "?" + " - " + summand2 + " = ";

        final int index_0 = block * 4;
        final int index_1 = index_0 + 1;
        final int index_2 = index_0 + 2;
        final int index_3 = index_0 + 3;

        blocks[block] = new LinkedHashMap<String, Task>();
        blocks[block].put(ts1, t1);
        blocks[block].put(ts2, t2);
        blocks[block].put(ts3, t3);
        blocks[block].put(ts4, t4);

        taskMap[index_0].setText(ts1);
        taskMap[index_1].setText(ts2);
        taskMap[index_2].setText(ts3);
        taskMap[index_3].setText(ts4);

        resultMap[index_0].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    currentInputFocus = resultMap[index_0];
                } else {
                    boolean right = checkResult (resultMap[index_0], t1);
                    if (right) {
                        replaceQuestionMark(taskMap[index_2], taskMap[index_3], (summand1 + summand2));
                    }
                }
            }
        });

        resultMap[index_1].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    currentInputFocus = resultMap[index_1];
                } else {
                    boolean right = checkResult (resultMap[index_1], t2);
                    if (right) {
                        replaceQuestionMark(taskMap[index_2], taskMap[index_3], (summand1 + summand2));
                    }
                }
            }
        });
        resultMap[index_2].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    currentInputFocus = resultMap[index_2];
                } else {
                    boolean right = checkResult (resultMap[index_2], t3);
                    if (right) {
                        replaceQuestionMark(taskMap[index_2], taskMap[index_3], (summand1 + summand2));
                    }
                }
            }
        });
        resultMap[index_3].setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    currentInputFocus = resultMap[index_3];
                } else {
                    boolean right = checkResult (resultMap[index_3], t4);
                    if (right) {
                        replaceQuestionMark(taskMap[index_2], taskMap[index_3], (summand1 + summand2));
                    }
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

        if (editText.getText().toString().equals("" + task.getSum())) {
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

                    boolean all = true;
                    for (int i = 0; i < 4; i++) {
                        Map<String, Task> whatever = blocks[i];
                        int counter = 0;
                        for (Task task : whatever.values()) {
                            boolean tmp = checkResult(resultMap[ i * 4 + counter], task);
                            counter++;
                            if (!tmp) {
                                all = false;
                            }
                        }
                    }
                    if (all) {
                        Intent finish = new Intent(BlockMathActivity.this, SuperActivity.class);
                        startActivity(finish);
                    }
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
