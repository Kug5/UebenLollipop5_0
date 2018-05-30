package com.example.greiser.uebenlollipop5_0;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mTextMessage = (TextView) findViewById(R.id.message);

        final Button level_plusMinus20 = findViewById(R.id.level_plusMinus20);
        level_plusMinus20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent math = new Intent(MenuActivity.this, HowMany.class);
            math.putExtra("operation", "plusminus");
            math.putExtra("max", 20);
            startActivity(math);
            }
        });

        final Button level_plusMinus30 = findViewById(R.id.level_plusMinus30);
        level_plusMinus30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent math = new Intent(MenuActivity.this, HowMany.class);
            math.putExtra("operation", "plusminus");
            math.putExtra("max", 30);
            startActivity(math);
            }
        });

        final Button level_plusMinus100 = findViewById(R.id.level_plusMinus100);
        level_plusMinus100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent math = new Intent(MenuActivity.this, HowMany.class);
            math.putExtra("operation", "plusminus");
            math.putExtra("max", 100);
            startActivity(math);
            }
        });

        final Button level_umkehr = findViewById(R.id.level_umkehr);
        level_umkehr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent math = new Intent(MenuActivity.this, BlockMathActivity.class);
            startActivity(math);
            }
        });

        final Button level_mult = findViewById(R.id.level_mult);
        level_mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent math = new Intent(MenuActivity.this, HowMany.class);
                math.putExtra("operation", "mult");
                math.putExtra("max", 10);
                startActivity(math);
            }
        });
        final Button level_multBig = findViewById(R.id.level_multBig);
        level_multBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent math = new Intent(MenuActivity.this, MatheActivity.class);
                math.putExtra("operation", "mult");
                math.putExtra("max", 20);
                startActivity(math);
            }
        });

        final Button buttonDeutsch = findViewById(R.id.losDeutsch);
        buttonDeutsch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, DeutschActivity.class));
            }
        });
    }

}
