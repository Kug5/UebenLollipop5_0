package com.example.greiser.uebenlollipop5_0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class HowMany extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_many);

        Intent myIntent = getIntent(); // gets the previously created intent
        final String operation = myIntent.getStringExtra("operation");
        final int max = myIntent.getIntExtra("max", 0);
        final String name = myIntent.getStringExtra("name");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final Button b6 = findViewById(R.id.b6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(6, operation, max, name);
            }
        });
        final Button b10 = findViewById(R.id.b10);
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(10, operation, max, name);
            }
        });
        final Button b16 = findViewById(R.id.b16);
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(16, operation, max, name);
            }
        });
        final Button b20 = findViewById(R.id.b20);
        b20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(20, operation, max, name);
            }
        });
        final Button b30 = findViewById(R.id.b30);
        b30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(30, operation, max, name);
            }
        });
        final Button b40 = findViewById(R.id.b40);
        b40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(40, operation, max, name);
            }
        });
        final Button b50 = findViewById(R.id.b50);
        b50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(50, operation, max, name);
            }
        });
        final Button b60 = findViewById(R.id.b60);
        b60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(60, operation, max, name);
            }
        });
    }

    private void goToMatheActivity(int i, String operation, int max, String name) {
        Intent math = new Intent(HowMany.this, MatheActivity.class);
        math.putExtra("many", i);
        math.putExtra("max", max);
        math.putExtra("operation", operation);
        math.putExtra("name", name);
        startActivity(math);
    }
}
