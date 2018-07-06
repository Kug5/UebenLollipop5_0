package com.example.greiser.uebenlollipop5_0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class SettingsActivity extends AppCompatActivity {

    Ueben application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        application = (Ueben) getApplication();

        TextView settingsFor = findViewById(R.id.settingsFor);
        settingsFor.setText("Einstellungen für " + application.getUsername());

        final TextView count = findViewById(R.id.count);
        count.setText("" + application.getUsersettings().getCountBoxes());

        Button plus = findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int i = Integer.parseInt(count.getText().toString());
            i++;
            if (i > 3) {
                i = 1;
            }
            count.setText("" + i);
            application.getUsersettings().setCountBoxes(i);

            }
        });

        Button minus = findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int i = Integer.parseInt(count.getText().toString());
            i--;
            if (i < 1) {
                i = 3;
            }
            count.setText("" + i);
            application.getUsersettings().setCountBoxes(i);
            }
        });

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            saveSettings();
            }
        });
    }

    @Override
    protected void onDestroy() {
        saveSettings();
        super.onDestroy();
    }

    private void saveSettings() {
        ExternalStorage es = new ExternalStorage();
        try {
            es.storeSettings(getApplicationContext(), application.getUsersettings(), application.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            startActivity(new Intent(SettingsActivity.this, MenuActivity.class));
        }
    }
}
