package com.example.greiser.uebenlollipop5_0.activities.math;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.activities.HowManyActivity;
import com.example.greiser.uebenlollipop5_0.activities.NameActivity;
import com.example.greiser.uebenlollipop5_0.helper.Ueben;

public class MenuMathActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(MenuMathActivity.this, NameActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_math);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        final TextView halloName = findViewById(R.id.halloName);
        halloName.setText("Hallo " + ((Ueben) getApplication()).getUsername() + "! :)");

        final Button level_plus10 = findViewById(R.id.level_plus10);
        level_plus10.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationPlus();
                        setMax(10);
                        startActivity(new Intent(MenuMathActivity.this, HowManyActivity.class));
                    }
                });
final Button level_plus20 = findViewById(R.id.level_plus20);
        level_plus20.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationPlus();
                        setMax(20);
                        startActivity(new Intent(MenuMathActivity.this, HowManyActivity.class));
                    }
                });
final Button level_plus30 = findViewById(R.id.level_plus30);
        level_plus30.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationPlus();
                        setMax(30);
                        startActivity(new Intent(MenuMathActivity.this, HowManyActivity.class));
                    }
                });
final Button level_plus100 = findViewById(R.id.level_plus100);
        level_plus100.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationPlus();
                        setMax(100);
                        startActivity(new Intent(MenuMathActivity.this, HowManyActivity.class));
                    }
                });
        final Button level_minus10 = findViewById(R.id.level_minus10);
        level_minus10.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationMinus();
                        setMax(10);
                        startActivity(new Intent(MenuMathActivity.this, HowManyActivity.class));
                    }
                });
final Button level_minus20 = findViewById(R.id.level_minus20);
        level_minus20.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationMinus();
                        setMax(20);
                        startActivity(new Intent(MenuMathActivity.this, HowManyActivity.class));
                    }
                });
final Button level_minus30 = findViewById(R.id.level_minus30);
        level_minus30.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationMinus();
                        setMax(30);
                        startActivity(new Intent(MenuMathActivity.this, HowManyActivity.class));
                    }
                });
final Button level_minus100 = findViewById(R.id.level_minus100);
        level_minus100.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationMinus();
                        setMax(100);
                        startActivity(new Intent(MenuMathActivity.this, HowManyActivity.class));
                    }
                });

        final Button level_plusMinus10 = findViewById(R.id.level_plusMinus10);
        level_plusMinus10.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationPlusMinus();
                        setMax(10);
                        startActivity(new Intent(MenuMathActivity.this, HowManyActivity.class));
                    }
                });

        final Button level_plusMinus20 = findViewById(R.id.level_plusMinus20);
        level_plusMinus20.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationPlusMinus();
                        setMax(20);
                        startActivity(new Intent(MenuMathActivity.this, HowManyActivity.class));
                    }
                });

        final Button level_plusMinus30 = findViewById(R.id.level_plusMinus30);
        level_plusMinus30.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationPlusMinus();
                        setMax(30);
                        startActivity(new Intent(MenuMathActivity.this, HowManyActivity.class));
                    }
                });

        final Button level_plusMinus100 = findViewById(R.id.level_plusMinus100);
        level_plusMinus100.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationPlusMinus();
                        setMax(100);
                        startActivity(new Intent(MenuMathActivity.this, HowManyActivity.class));
                    }
                });

        final Button level_umkehr = findViewById(R.id.level_umkehr);
        level_umkehr.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationBlock();
                        setMax(0);
                        startActivity(new Intent(MenuMathActivity.this, MathBlockActivity.class));
                    }
                });

        final Button level_mult = findViewById(R.id.level_mult);
        level_mult.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationMult();
                        setMax(10);
                        startActivity(new Intent(MenuMathActivity.this, MathChoiceMultTableActivity.class));
                    }
                });

        final Button level_divide = findViewById(R.id.level_divide);
        level_divide.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationDivide();
                        setMax(100);
                        startActivity(new Intent(MenuMathActivity.this, MathChoiceMultTableActivity.class));
                    }
                });

        final Button level_multBig = findViewById(R.id.level_multBig);
        level_multBig.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOperationMult();
                        setMax(20);
                        startActivity(
                                new Intent(MenuMathActivity.this, MathePlusMinusMultDivideActivity.class));
                    }
                });
    }

    private void setOperationMinus() {
        ((Ueben) getApplication()).setOperation(Ueben.OPERATION_MINUS);
    }

    private void setOperationPlusMinus() {
        ((Ueben) getApplication()).setOperation(Ueben.OPERATION_PLUSMINUS);
    }
    private void setOperationPlus() {
        ((Ueben) getApplication()).setOperation(Ueben.OPERATION_PLUS);
    }

    private void setOperationMult() {
        ((Ueben) getApplication()).setOperation(Ueben.OPERATION_MULT);
    }

    private void setOperationBlock() {
        ((Ueben) getApplication()).setOperation(Ueben.OPERATION_BLOCK);
    }

    private void setOperationDivide() {
        ((Ueben) getApplication()).setOperation(Ueben.OPERATION_DIVIDE);
    }

    private void setMax(int max) {
        ((Ueben) getApplication()).setMax(max);
    }
}
