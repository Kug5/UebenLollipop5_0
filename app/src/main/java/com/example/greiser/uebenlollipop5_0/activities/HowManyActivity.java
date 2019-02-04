package com.example.greiser.uebenlollipop5_0.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.activities.math.MatheActivity;
import com.example.greiser.uebenlollipop5_0.helper.Ueben;

public class HowManyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_many);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final Button b6 = findViewById(R.id.b6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(6);
            }
        });
        final Button b10 = findViewById(R.id.b10);
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(10);
            }
        });
        final Button b16 = findViewById(R.id.b16);
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(16);
            }
        });
        final Button b20 = findViewById(R.id.b20);
        b20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(20);
            }
        });
        final Button b30 = findViewById(R.id.b30);
        b30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(30);
            }
        });
        final Button b40 = findViewById(R.id.b40);
        b40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(40);
            }
        });
        final Button b50 = findViewById(R.id.b50);
        b50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(50);
            }
        });
        final Button b60 = findViewById(R.id.b60);
        b60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(60);
            }
        });
    }

    private void goToMatheActivity(int many) {
        Intent math = new Intent(HowManyActivity.this, MatheActivity.class);
        ((Ueben)getApplication()).setMany(many);
        startActivity(math);
    }
}
