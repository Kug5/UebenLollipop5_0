package com.example.greiser.uebenlollipop5_0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        final TextView halloName = findViewById(R.id.halloName);
        halloName.setText("Hallo " + ((Ueben)getApplication()).getUsername() + "! :)");


        final Button buttonDeutsch = findViewById(R.id.ButtonDeutsch);
        buttonDeutsch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(MenuActivity.this, DeutschActivity.class));
            }
        });

        final Button buttonMathe = findViewById(R.id.ButtonMathe);
        buttonMathe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, MenuMathActivity.class));
            }
        });

        final ImageButton settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, SettingsActivity.class));
            }
        });

        final ImageButton overviewScore = findViewById(R.id.overviewScore);
        overviewScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, ScoreActivity.class));
            }
        });
    }
}
