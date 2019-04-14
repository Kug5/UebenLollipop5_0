package com.example.greiser.uebenlollipop5_0.activities.german;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.activities.HowManyActivity;
import com.example.greiser.uebenlollipop5_0.helper.Ueben;

public class MenuGermaActivity extends AppCompatActivity {

    private Ueben ueben;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_germa);

        ueben = (Ueben) getApplication();

        Button read = findViewById(R.id.read);
        read.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ueben.setGermanTarget(Ueben.GERMAN_READ);
                        startActivity(new Intent(MenuGermaActivity.this, HowManyActivity.class));
                    }
        });

        Button write = findViewById(R.id.write);
        write.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ueben.setGermanTarget(Ueben.GERMAN_WRITE);
                        startActivity(new Intent(MenuGermaActivity.this, HowManyActivity.class));
                    }
        });

        Button singularplural = findViewById(R.id.singularplural);
        singularplural.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ueben.setGermanTarget(Ueben.GERMAN_SP);
                        startActivity(new Intent(MenuGermaActivity.this, HowManyActivity.class));
                    }
        });

        Button random = findViewById(R.id.random);
        random.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // A random integer value in the range [Min,Max]
                        int min = 0;
                        int max = 2;
                        int x = min + (int) (Math.random() * ((max - min) + 1));
                        if (x == 0) {
                            ueben.setGermanTarget(Ueben.GERMAN_READ);
                        } else if (x == 1) {
                            ueben.setGermanTarget(Ueben.GERMAN_WRITE);
                        } else if (x == 2) {
                            ueben.setGermanTarget(Ueben.GERMAN_SP);
                        }

                        startActivity(new Intent(MenuGermaActivity.this, HowManyActivity.class));
                    }
        });
        Button konjugation = findViewById(R.id.konjugation);
        konjugation.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MenuGermaActivity.this, GermanKonjugationActivity.class));
                    }
        });
    }
}
