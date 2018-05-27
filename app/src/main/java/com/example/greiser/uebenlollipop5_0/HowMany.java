package com.example.greiser.uebenlollipop5_0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HowMany extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_many);

        final Button b5 = findViewById(R.id.b5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(5);
            }
        });
        final Button b10 = findViewById(R.id.b10);
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(10);
            }
        });
        final Button b15 = findViewById(R.id.b15);
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMatheActivity(15);
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

    private void goToMatheActivity(int i) {
        Intent math = new Intent(HowMany.this, MatheActivity.class);
        math.putExtra("many", i);
        startActivity(math);
    }
}
