package com.example.greiser.uebenlollipop5_0.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.activities.german.GermanRightWrongActivity;
import com.example.greiser.uebenlollipop5_0.activities.german.GermanSingularPluralActivity;
import com.example.greiser.uebenlollipop5_0.activities.german.GermanWriteActivity;
import com.example.greiser.uebenlollipop5_0.activities.math.MenuMathActivity;
import com.example.greiser.uebenlollipop5_0.helper.Ueben;

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

                startActivity(new Intent(MenuActivity.this, GermanSingularPluralActivity.class));
                startActivity(new Intent(MenuActivity.this, GermanWriteActivity.class));

                // A random integer value in the range [Min,Max]
                int min = 0;
                int max = 2;
                int x = min + (int) (Math.random() * ((max - min) + 1));
                if (x == 0)
                    startActivity(new Intent(MenuActivity.this, GermanRightWrongActivity.class));
                else if (x == 1)
                    startActivity(new Intent(MenuActivity.this, GermanWriteActivity.class));
                else if (x == 2)
                    startActivity(new Intent(MenuActivity.this, GermanSingularPluralActivity.class));

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
