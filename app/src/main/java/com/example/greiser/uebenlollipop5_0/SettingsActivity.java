package com.example.greiser.uebenlollipop5_0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView settingsFor = findViewById(R.id.settingsFor);
        settingsFor.setText("Einstellungen für " + ((Ueben)getApplication()).getUsername());
    }
}
