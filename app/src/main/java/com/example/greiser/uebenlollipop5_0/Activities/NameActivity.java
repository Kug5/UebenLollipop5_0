package com.example.greiser.uebenlollipop5_0.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.greiser.uebenlollipop5_0.Helper.ExternalStorage;
import com.example.greiser.uebenlollipop5_0.Helper.Ueben;
import com.example.greiser.uebenlollipop5_0.Helper.UserSetting;
import com.example.greiser.uebenlollipop5_0.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NameActivity extends AppCompatActivity {

    public static final int padding = 10;
    List<String> names;
    private ExternalStorage es;
    private Ueben application;
    private LinearLayout partForButtons;
    private TextView niemandText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        application = ((Ueben) getApplication());

        partForButtons = findViewById(R.id.partForButtons);
        niemandText = findViewById(R.id.niemandIstDa);
        niemandText.setVisibility(View.GONE);

        this.es = new ExternalStorage();
        File file = es.getFileListOfNames(getApplicationContext());

        try {
            this.names = getNames(file);

            if (names != null) {
                recreatePartForButtons();
            } else {
                setEmptyNamesLayout();
            }
        } catch (Exception e) {
            names = new ArrayList<>();
            setEmptyNamesLayout();
        }

        final EditText inputName = findViewById(R.id.inputName);
        inputName.setVisibility(View.INVISIBLE);

        final ImageView newName = findViewById(R.id.newName);
        newName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                inputName.setVisibility(View.VISIBLE);
                inputName.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(inputName, InputMethodManager.SHOW_FORCED);
            }
        });

        inputName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
                    names.add(inputName.getText().toString());
                    recreatePartForButtons();

                    niemandText.setVisibility(View.GONE);
                    findViewById(R.id.werIstDa).setVisibility(View.GONE);
                    partForButtons.setVisibility(View.VISIBLE);

                    try {
                        es.storeNames(getApplicationContext(), names);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    inputName.setText("");
                    inputName.setVisibility(View.INVISIBLE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return false;
            }
        });


    }

    private void setEmptyNamesLayout() {
        findViewById(R.id.werIstDa).setVisibility(View.GONE);
        niemandText.setVisibility(View.VISIBLE);
        partForButtons.setVisibility(View.GONE);
    }

    private void recreatePartForButtons() {

        partForButtons.removeAllViews();
        int counter = 0;
        LinearLayout currentLine = createNewLine();
        partForButtons.addView(currentLine);
        for (String name: names) {
            if (counter%5 == 0) {
                currentLine = createNewLine();
                partForButtons.addView(currentLine);
            }
            currentLine.addView(createButton(name));
            counter++;
        }
    }

    private LinearLayout createNewLine() {
        LinearLayout newLine = new LinearLayout(this);
        newLine.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams newLineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        newLine.setLayoutParams(newLineParams);

        return newLine;
    }

    @NonNull
    private Button createButton(final String name) {

        Button buttonName = new Button(this);
        buttonName.setText(name);
        buttonName.setBackgroundColor(Color.argb(255, 51, 181, 229));
        buttonName.setTextColor(Color.BLACK);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(padding, padding, padding, padding);
        buttonName.setLayoutParams(params);
        buttonName.setPadding(padding, padding, padding, padding);

        buttonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.setUsername(name);
                application.setUsersettings(loadUserSettings(name));
                application.setHeightScores(es.getHeightScores(getApplicationContext(), name));
            Intent menu = new Intent(NameActivity.this, MenuActivity.class);
            startActivity(menu);
            }

        });
        return buttonName;
    }

    private UserSetting loadUserSettings(String name) {
        File file = es.getFileSettings(name, getApplicationContext());
        UserSetting settings;
        try {
            settings = getSettings(file);
        } catch (Exception e) {
            settings = new UserSetting();
            e.printStackTrace();
        }

        return settings;
    }

    private UserSetting getSettings(File file) throws Exception {

        UserSetting returnValue = new UserSetting();
      //  BufferedReader bufferIn = new BufferedReader(new FileReader(file));

        return returnValue;
    }

    private List<String> getNames(File file) throws Exception {

        BufferedReader bufferIn = new BufferedReader(new FileReader(file));
        List<String> names = new ArrayList<String>();

        String line  = bufferIn.readLine();
        if(line != null) {
            String [] tmpNames = line.split(",");
            bufferIn.close();
            return new ArrayList<String>(Arrays.asList(tmpNames));
        }

        return names;
    }
}
