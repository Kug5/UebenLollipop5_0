package com.example.greiser.uebenlollipop5_0.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.helper.StorageToday;
import com.example.greiser.uebenlollipop5_0.helper.Ueben;

public class SuperActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        finish();
        backToMenu();
    }

    private void backToMenu() {
        startActivity(new Intent(SuperActivity.this, MenuActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super);

        Ueben ueben = (Ueben) getApplication();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        final ImageView backToMenu = findViewById(R.id.backToMenu);
        backToMenu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        backToMenu();
                    }
                });

        final TextView p020 = findViewById(R.id.p020);
        p020.setText("" + ueben.getHeightScores().getBestPlusMinus20() + "%");

        final TextView p030 = findViewById(R.id.p030);
        p030.setText("" + ueben.getHeightScores().getBestPlusMinus30() + "%");

        final TextView p100 = findViewById(R.id.p100);
        p100.setText("" + ueben.getHeightScores().getBestPlusMinus100() + "%");

        final TextView m100 = findViewById(R.id.m100);
        m100.setText("" + ueben.getHeightScores().getBestMult100() + "%");

        final TextView m400 = findViewById(R.id.m400);
        m400.setText("" + ueben.getHeightScores().getBestMult400() + "%");

        final TextView d100 = findViewById(R.id.d100);
        d100.setText("" + ueben.getHeightScores().getBestDivide100() + "%");

        final TextView points = findViewById(R.id.points);
        String op = ueben.getOperation();
        int max = ueben.getMax();
        int howMany = ueben.getHowMany();

        String output = "";

        switch (op) {
            case Ueben.GERMAN_KO:
                output += "Konjugation";
                break;
            case Ueben.GERMAN_READ:
                output += "Lesen";
                break;
            case Ueben.GERMAN_SP:
                output += "Einzahl/Mehrzahl";
                break;
            case Ueben.GERMAN_WRITE:
                output += "Schreiben";
                break;
            case Ueben.OPERATION_DIVIDE:
                output += "1:1";
                break;
            case Ueben.OPERATION_MULT:
                if (max == 10) output += "1x1";
                if (max == 20) output += "11x11";
                break;
            case Ueben.OPERATION_PLUSMINUS:
                output += "1+1 bis";
                if (max == 20) output += " 20";
                if (max == 30) output += " 30";
                if (max == 100) output += " 100";
                break;
        }

        if (op.equals(Ueben.OPERATION_DIVIDE) || (op.equals(Ueben.OPERATION_MULT) && max == 10)) {
            Boolean[] toTrain = ueben.getMultTableToTrain();
            output += System.lineSeparator() + "[";
            boolean first = true;
            for (int i = 0; i < toTrain.length; i++) {
                if (toTrain[i]) {
                    if (first) {
                        output += "" + i;
                        first = false;
                    } else {
                        output += "," + i;
                    }
                }
            }
            output += "]";
        }

        if (!op.equals(Ueben.OPERATION_BLOCK)) {
            int maxPoints = howMany;
            if (op.equals(Ueben.GERMAN_SP)) {
                maxPoints *= 3;
            } else {
                maxPoints *= 10;
            }

            output += System.lineSeparator() + ueben.lastPoints + " von " + maxPoints + " Punkten " +
                    "(" + (100*ueben.lastPoints)/maxPoints + "%)";
        } else {
            output = "Sehr gut";
        }

        points.setText(output);

        StorageToday storageToday = new StorageToday(getApplicationContext(), ueben.getUsername());
        storageToday.update(output);
    }
}
