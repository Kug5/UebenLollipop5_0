package com.example.greiser.uebenlollipop5_0.activities;

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

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.helper.StorageHeightScore;
import com.example.greiser.uebenlollipop5_0.helper.StorageListOfUsers;
import com.example.greiser.uebenlollipop5_0.helper.StorageSettings;
import com.example.greiser.uebenlollipop5_0.helper.Ueben;
import com.example.greiser.uebenlollipop5_0.model.UserHeightScore;
import com.example.greiser.uebenlollipop5_0.model.UserSetting;

import java.util.List;

public class NameActivity extends AppCompatActivity {

    public static final int padding = 10;
    private StorageListOfUsers storageListOfUsers;
    private Ueben application;
    private LinearLayout partForButtons;
    private TextView niemandText;


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        application = ((Ueben) getApplication());

        partForButtons = findViewById(R.id.partForButtons);
        niemandText = findViewById(R.id.niemandIstDa);
        niemandText.setVisibility(View.GONE);

        this.storageListOfUsers = new StorageListOfUsers(getApplicationContext());
        List<String> names = storageListOfUsers.getNames();
        try {
            if (names != null && names.size() > 0) {
                recreatePartForButtons(names);
            } else {
                setEmptyNamesLayout();
            }
        } catch (Exception e) {
            setEmptyNamesLayout();
        }

        final EditText inputName = findViewById(R.id.inputName);
        inputName.setVisibility(View.INVISIBLE);

        final ImageView newName = findViewById(R.id.newName);
        newName.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        inputName.setVisibility(View.VISIBLE);
                        inputName.requestFocus();
                        InputMethodManager imm =
                                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(inputName, InputMethodManager.SHOW_FORCED);
                    }
                });

        inputName.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
                            storageListOfUsers.update(inputName.getText().toString());
                            recreatePartForButtons(storageListOfUsers.getNames());

                            niemandText.setVisibility(View.GONE);
                            findViewById(R.id.werIstDa).setVisibility(View.GONE);
                            partForButtons.setVisibility(View.VISIBLE);

                            inputName.setText("");
                            inputName.setVisibility(View.INVISIBLE);
                            InputMethodManager imm =
                                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
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

    private void recreatePartForButtons(List<String> names) {

        partForButtons.removeAllViews();
        int counter = 0;
        LinearLayout currentLine = createNewLine();
        partForButtons.addView(currentLine);
        for (String name : names) {
            if (counter % 5 == 0) {
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

        LinearLayout.LayoutParams newLineParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        newLine.setLayoutParams(newLineParams);

        return newLine;
    }

    @NonNull
    private Button createButton(final String name) {

        Button buttonName = new Button(this);
        buttonName.setText(name);
        buttonName.setBackgroundColor(Color.argb(255, 51, 181, 229));
        buttonName.setTextColor(Color.BLACK);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(padding, padding, padding, padding);
        buttonName.setLayoutParams(params);
        buttonName.setPadding(padding, padding, padding, padding);

        buttonName.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        application.setUsername(name);
                        application.setUsersettings(
                                (UserSetting) new StorageSettings(getApplicationContext(), name).load());
                        application.setHeightScores(
                                (UserHeightScore) new StorageHeightScore(getApplicationContext(), name).load());
                        Intent menu = new Intent(NameActivity.this, MenuActivity.class);
                        startActivity(menu);
                    }
                });
        return buttonName;
    }
}
