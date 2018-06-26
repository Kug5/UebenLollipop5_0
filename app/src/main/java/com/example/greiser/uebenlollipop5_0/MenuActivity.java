package com.example.greiser.uebenlollipop5_0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        final Button level_plusMinus20 = findViewById(R.id.level_plusMinus20);
        level_plusMinus20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            setOperationPlusMinus();
            setMax(20);
            startActivity(new Intent(MenuActivity.this, HowMany.class));
            }
        });

        final Button level_plusMinus30 = findViewById(R.id.level_plusMinus30);
        level_plusMinus30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            setOperationPlusMinus();
            setMax(30);
            startActivity(new Intent(MenuActivity.this, HowMany.class));
            }
        });

        final Button level_plusMinus100 = findViewById(R.id.level_plusMinus100);
        level_plusMinus100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            setOperationPlusMinus();
            setMax(100);
            startActivity(new Intent(MenuActivity.this, HowMany.class));
            }
        });

        final Button level_umkehr = findViewById(R.id.level_umkehr);
        level_umkehr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity( new Intent(MenuActivity.this, BlockMathActivity.class));
            }
        });

        final Button level_mult = findViewById(R.id.level_mult);
        level_mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            setOperationMult();
            setMax(10);
            startActivity(new Intent(MenuActivity.this, HowMany.class));
            }
        });
        final Button level_multBig = findViewById(R.id.level_multBig);
        level_multBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            setOperationMult();
            setMax(20);
            startActivity(new Intent(MenuActivity.this, MatheActivity.class));
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

    private void setOperationPlusMinus() {
        ((Ueben)getApplication()).setOperation(Ueben.OPEARATION_PLUSMINUS);
    }
    private void setOperationMult() {
        ((Ueben)getApplication()).setOperation(Ueben.OPEARATION_MULT);
    }

    private void setMax (int max) {
        ((Ueben)getApplication()).setMax(max);
    }

}
