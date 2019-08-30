package com.example.greiser.uebenlollipop5_0.activities.german;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.activities.SuperActivity;
import com.example.greiser.uebenlollipop5_0.helper.StorageGermanTemplate;
import com.example.greiser.uebenlollipop5_0.helper.Ueben;

import java.util.ArrayList;
import java.util.List;

public class GermanWriteActivity extends AppCompatActivity {

    private TextView preset;
    private LinearLayout layoutInput;
    private int howMany = 10;
    private int counter = 0;
    private List<String> entries;
    private List<Integer> usedIndex = new ArrayList<>();
    private Ueben application;
    private EditText userInput;
    private boolean failed = false;
    private int points = 0;
    private Button startButton;
    private TextView merke;


    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(GermanWriteActivity.this, MenuGermaActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deutsch_write);

        reset();
        application = ((Ueben) getApplication());
        howMany = application.getHowMany();

        entries = new StorageGermanTemplate(getApplicationContext()).getDeutschWriteEntities();

        merke = findViewById(R.id.merke);
        preset = findViewById(R.id.preset);
        layoutInput = findViewById(R.id.layoutInput);
        userInput = findViewById(R.id.userInput);
        userInput.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
                            checkSolution();
                            InputMethodManager imm =
                                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                        return false;
                    }
                });

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseEntry();
                    }
                });

        setPreStartConfigs();
    }

    private void reset() {
        this.counter = 0;
        this.failed = false;
        this.points = 0;
    }

    private void setPreStartConfigs() {
        merke.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.VISIBLE);
        preset.setVisibility(View.INVISIBLE);
        layoutInput.setVisibility(View.INVISIBLE);
    }

    private void setStartConfigs() {
        merke.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.INVISIBLE);

        failed = false;
        preset.setVisibility(View.VISIBLE);
        preset.setBackgroundColor(Color.WHITE);
        layoutInput.setVisibility(View.INVISIBLE);
        userInput.setText("");
    }

    private void checkSolution() {
        if (preset.getText().equals(userInput.getText().toString().trim())) {
            if (!failed) {
                points++;
            }
            counter++;
            chooseEntry();
        } else {
            failed = true;
            preset.setVisibility(View.VISIBLE);
            preset.setBackgroundColor(Color.RED);
            layoutInput.setVisibility(View.INVISIBLE);
            createTimer();
        }
    }

    private void chooseEntry() {

        setStartConfigs();
        if (howMany == counter) {
            application.lastPoints = points;
            startActivity(new Intent(GermanWriteActivity.this, SuperActivity.class));
        }

        int index = -1;

        do {
            index = (int) (Math.random() * entries.size());
        } while (index > entries.size() || usedIndex.contains(index));

        usedIndex.add(index);

        String entityToTest = entries.get(index);
        preset.setText(entityToTest);

        createTimer();

        // test
        //        Handler handler = new Handler();
        //        handler.postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                counter++;
        //                chooseEntry();
        //            }
        //        }, 2000);

    }

    private void createTimer() {
        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        preset.setVisibility(View.INVISIBLE);
                        layoutInput.setVisibility(View.VISIBLE);
                        userInput.requestFocus();
                        userInput.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                        InputMethodManager imm =
                                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(userInput, InputMethodManager.SHOW_FORCED);
                    }
                },
                5000);
    }
}
