package com.example.greiser.uebenlollipop5_0.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.helper.Ueben;

public class SuperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super);

        Ueben ueben = (Ueben)getApplication();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        final ImageView backToMenu = findViewById(R.id.backToMenu);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SuperActivity.this, MenuActivity.class));
            }
        });


        final TextView p020 = findViewById(R.id.p020);
        p020.setText("" + ueben.getHeightScores().getBestPlusMinus20());

        final TextView p030 = findViewById(R.id.p030);
        p030.setText("" + ueben.getHeightScores().getBestPlusMinus30());

        final TextView p100 = findViewById(R.id.p100);
        p100.setText("" + ueben.getHeightScores().getBestPlusMinus100());

        final TextView m100 = findViewById(R.id.m100);
        m100.setText("" + ueben.getHeightScores().getBestMult100());

        final TextView m400 = findViewById(R.id.m400);
        m400.setText("" + ueben.getHeightScores().getBestMult400());

        final TextView d100 = findViewById(R.id.d100);
        d100.setText("" + ueben.getHeightScores().getBestDivide100());

        final TextView points = findViewById(R.id.points);
        points.setText("" + (ueben.lastPoints > -1 ? ueben.lastPoints : 0));

    }
}
