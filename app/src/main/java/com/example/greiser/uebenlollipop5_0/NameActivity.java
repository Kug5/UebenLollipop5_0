package com.example.greiser.uebenlollipop5_0;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NameActivity extends AppCompatActivity {

    public static final int padding = 10;
    String[] _names;
    private ExternalStorage es;
    private Ueben application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        application = ((Ueben) getApplication());

        final LinearLayout partForButtons = findViewById(R.id.partForButtons);
        this.es = new ExternalStorage();
        File file = es.getFileListOfNames(getApplicationContext());

        try {
            final String[] names = getNames(file);
            this._names = names;

            if (names != null) {

                for (int i = 0; i < names.length; i++) {
                    final String name = names[i];
                    Button buttonName = createButton(name);
                    partForButtons.addView(buttonName);
                }
            } else {
                findViewById(R.id.werIstDa).setVisibility(View.GONE);
                partForButtons.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            findViewById(R.id.werIstDa).setVisibility(View.GONE);
            partForButtons.setVisibility(View.GONE);
        }

        final EditText inputName = findViewById(R.id.inputName);
        inputName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
                    partForButtons.addView(createButton(inputName.getText().toString()));
                    partForButtons.setVisibility(View.VISIBLE);
                    try {
                        es.storeNames(getApplicationContext(), _names, inputName.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    inputName.setText("");
                }
                return false;
            }
        });


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

    private String[] getNames(File file) throws Exception {

        BufferedReader bufferIn = new BufferedReader(new FileReader(file));
        List<String> names = new ArrayList<String>();

        String line  = bufferIn.readLine();
        if(line == null) {
            throw new Exception("empty file");
        }
        bufferIn.close();

        return line.split(",");

    }

}
