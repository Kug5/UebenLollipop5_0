package com.example.greiser.uebenlollipop5_0;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private Ueben application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrore);

        application = ((Ueben) getApplication());

        LinearLayout mainLayout = findViewById(R.id.mainScore);
        LinearLayout.LayoutParams paramsRow = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        LinearLayout.LayoutParams paramsBox = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);

        StorageData storedData;
        try {
            ExternalStorage es = new ExternalStorage();

            for (Ueben.Operations op : Ueben.Operations.values()) {
                int [] max = new int[0];
                String sign = "";
                if (op.name().equals(Ueben.OPERATION_PLUSMINUS)) {
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
                            storedData = es.getStoredTasks(getApplicationContext(), op.name(), max[i], application.getUsername());
                        } catch (Exception e) {
                            continue;
                        }

                        LinearLayout row = new LinearLayout(getApplicationContext());
                        row.setOrientation(LinearLayout.HORIZONTAL);
                        row.setLayoutParams(paramsRow);
                        mainLayout.addView(row);

                        TextView label = new TextView(getApplicationContext());
                        label.setText("" + sign + " " + max[i]);
                        label.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,0));
                        label.setEms(5);
                        row.addView(label);

                        for (int boxNr = 1; boxNr < 4; boxNr++) {
                            Box box = new Box(getApplicationContext());
                            box.setLayoutParams(paramsBox);
                            box.setBoxnr("" + boxNr);
                            box.setBackgroundColor(Color.argb(255 - boxNr*boxNr*boxNr*boxNr, 51  - boxNr*boxNr*boxNr*boxNr, 181  - boxNr*boxNr*boxNr*boxNr, 229  - boxNr*boxNr*boxNr*boxNr));
                            switch (boxNr) {
                                case 1: box.setCounter("" + storedData.getStep1().size()); break;
                                case 2: box.setCounter("" + storedData.getStep2().size()); break;
                                case 3: box.setCounter("" + storedData.getStep3().size()); break;
                            }
                            row.addView(box);
                        }

                    }
                }

            }


            mainLayout.postInvalidate();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error", e.getMessage());
        }
    }
}
