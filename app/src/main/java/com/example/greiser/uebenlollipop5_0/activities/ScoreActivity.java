package com.example.greiser.uebenlollipop5_0.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.helper.StorageData;
import com.example.greiser.uebenlollipop5_0.helper.StoragePlusMinusMultDivide;
import com.example.greiser.uebenlollipop5_0.helper.Ueben;
import com.example.greiser.uebenlollipop5_0.layout.Box;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    private Ueben application;

    @SuppressLint({"SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrore);

        application = ((Ueben) getApplication());

        LinearLayout mainLayout = findViewById(R.id.mainScore);
        LinearLayout.LayoutParams paramsRow =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        LinearLayout.LayoutParams paramsBox =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);

        StorageData storedData;
        try {

            int colorIndex = 0;
            for (Ueben.Operations op : Ueben.Operations.values()) {
                int[] max = new int[0];
                String sign = "";
                if (op.name().equals(Ueben.OPERATION_PLUS)) {
                    max = new int[]{10};
                    sign = "+";
                } else if (op.name().equals(Ueben.OPERATION_PLUSMINUS)) {
                    max = new int[]{20, 30, 100};
                    sign = "+-";
                } else if (op.name().equals(Ueben.OPERATION_DIVIDE)) {
                    max = new int[]{100};
                    sign = ":";
                } else if (op.name().equals(Ueben.OPERATION_MULT)) {
                    max = new int[]{10, 20};
                    sign = "*";
                }

                if (max.length > 0) {
                    for (int i = 0; i < max.length; i++) {

                        try {
                            storedData =
                                    (StorageData)
                                            new StoragePlusMinusMultDivide(
                                                    getApplicationContext(), op.name(), max[i], application.getUsername())
                                                    .load();
                        } catch (Exception e) {
                            mainLayout.addView(createEmptyRow(paramsRow));
                            continue;
                        }

                        LinearLayout row = new LinearLayout(getApplicationContext());
                        row.setOrientation(LinearLayout.HORIZONTAL);
                        row.setLayoutParams(paramsRow);

                        mainLayout.addView(row);

                        List<Integer> customColor = getColorBands(getCustomColor(colorIndex), 4);
                        TextView label = new TextView(getApplicationContext());
                        label.setText(sign + " " + max[i]);
                        label.setLayoutParams(
                                new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT,
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        0));
                        label.setEms(5);
                        label.setBackgroundColor(customColor.get(0));
                        label.setGravity(Gravity.CENTER);
                        label.setTextColor(Color.BLACK);
                        row.addView(label);

                        for (int boxNr = 1; boxNr < 4; boxNr++) {
                            Box box = new Box(getApplicationContext());
                            box.setLayoutParams(paramsBox);
                            box.setBoxnr("" + boxNr);
                            box.setBackgroundColor(customColor.get(boxNr));
                            switch (boxNr) {
                                case 1:
                                    box.setCounter("" + storedData.getStep1().size());
                                    break;
                                case 2:
                                    box.setCounter("" + storedData.getStep2().size());
                                    break;
                                case 3:
                                    box.setCounter("" + storedData.getStep3().size());
                                    break;
                            }
                            row.addView(box);
                        }
                        colorIndex++;
                    }
                }
            }

            mainLayout.postInvalidate();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error", e.getMessage());
        }
    }

    private List<Integer> getColorBands(int color, int bands) {

        List<Integer> colorBands = new ArrayList<>(bands);
        for (int index = 0; index < bands; index++) {
            colorBands.add(darken(color, (double) index / (double) bands));
        }
        return colorBands;

    }

    private static int darken(int color, double fraction) {

        int red = (int) Math.round(Math.max(0, Color.red(color) - 100 * fraction));
        int green = (int) Math.round(Math.max(0, Color.green(color) - 100 * fraction));
        int blue = (int) Math.round(Math.max(0, Color.blue(color) - 100 * fraction));

        int alpha = (int) Color.alpha(color);

        return Color.argb(alpha, red, green, blue);

    }

    private int getCustomColor(int colorIndex) {
        switch (colorIndex) {
            case 0:
                return Color.GREEN;// - (boxNr ^ 5) - colorDiff;
            case 1:
                return Color.CYAN;// - (boxNr ^ 5) - colorDiff;
            case 2:
                return Color.RED;// - (boxNr ^ 5) - colorDiff;
            case 3:
                return Color.MAGENTA;// - (boxNr ^ 5) - colorDiff;
            case 4:
                return Color.YELLOW;// - (boxNr ^ 5) - colorDiff;
            case 5:
                return Color.BLUE;// - (boxNr ^ 5) - colorDiff;
        }
        return Color.WHITE;
    }

    private LinearLayout createEmptyRow(LinearLayout.LayoutParams paramsRow) {
        LinearLayout row = new LinearLayout(getApplicationContext());
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setLayoutParams(paramsRow);

        return row;
    }
}
