package com.example.greiser.uebenlollipop5_0.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.helper.ExternalStorage;
import com.example.greiser.uebenlollipop5_0.helper.Ueben;

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

    Button back = findViewById(R.id.back);
    back.setOnClickListener(
            new View.OnClickListener() {
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
      es.storeSettings(
              getApplicationContext(), application.getUsersettings(), application.getUsername());
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      startActivity(new Intent(SettingsActivity.this, MenuActivity.class));
    }
  }
}
