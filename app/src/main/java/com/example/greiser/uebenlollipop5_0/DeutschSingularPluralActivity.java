package com.example.greiser.uebenlollipop5_0;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeutschSingularPluralActivity extends AppCompatActivity {

    private static final int SELECTED_COLOR = Color.CYAN;
    private static final int RIGHT_COLOR = Color.GREEN;
    private static final int WRONG_COLOR = Color.RED;
    private static final int DEFAULT_COLOR = Color.LTGRAY;

    private List<SinglePlural> entities;
    private LinearLayout layoutArticle;
    private LinearLayout layoutSingular;
    private LinearLayout layoutPlural;
    private ImageView image;
    private List<Integer> usedIndex = new ArrayList<>();
    private int points = 0;
    private String selectedPlural;
    private Button selectedPluralButton;
    private String selectedSingular;
    private Button selectedSingularButton;
    private String selectedArticle;
    private Button selectedArticleButton;
    private int somethingWrong;
    private int howMany = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deutsch_singular_plural);

        ExternalStorage es = new ExternalStorage();
        entities = es.getDeutschSinglePluralEntities(getApplicationContext());

        layoutArticle = findViewById(R.id.layoutArticle);
        layoutSingular = findViewById(R.id.layoutSingular);
        layoutPlural = findViewById(R.id.layoutPlural);

        image = (ImageView) findViewById(R.id.imageSingular);

        createArticleLayout();
        chooseTask();
    }

    private void chooseTask() {

        if (layoutSingular.getChildCount() > 1) {
            layoutSingular.removeViews(1, 3);
            layoutPlural.removeViews(1, 3);
        }

        int index;

        do {
            index = (int) (Math.random() * entities.size());
        } while (index > entities.size() || usedIndex.contains(index));

        usedIndex.add(index);

        SinglePlural tmp = entities.get(index);

        createSingularLayout(tmp);
        createPluralLayout(tmp);

        image.setImageResource(getResource(tmp.getsRight()));
    }

    private void createPluralLayout(final SinglePlural tmp) {

        final Button p1 = new Button(getApplicationContext());
        final Button p2 = new Button(getApplicationContext());
        final Button p3 = new Button(getApplicationContext());

        p1.setText(tmp.getpRight());
        p2.setText(tmp.getpWrong1());
        p3.setText(tmp.getpWrong2());

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPlural = tmp.getpRight();
                selectedPluralButton = p1;
                p1.getBackground().setColorFilter(SELECTED_COLOR, PorterDuff.Mode.MULTIPLY);
                p2.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                p3.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                check();
            }
        });
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPlural = tmp.getpWrong1();
                selectedPluralButton = p2;
                p2.getBackground().setColorFilter(SELECTED_COLOR, PorterDuff.Mode.MULTIPLY);
                p1.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                p3.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                check();
            }
        });
        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPlural = tmp.getpWrong2();
                selectedPluralButton = p3;
                p3.getBackground().setColorFilter(SELECTED_COLOR, PorterDuff.Mode.MULTIPLY);
                p2.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                p1.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                check();
            }
        });

        shuffle(layoutPlural, p1, p2, p3);
    }

    private void shuffle(LinearLayout layout, Button b1, Button b2, Button b3) {

        List<Button> buttonList = new ArrayList<>();
        buttonList.add(b1);
        buttonList.add(b2);
        buttonList.add(b3);
        Collections.shuffle(buttonList);
        for (Button b: buttonList) {
            layout.addView(b);
        }
    }

    private void createSingularLayout(final SinglePlural tmp) {

        final Button s1 = new Button(getApplicationContext());
        final Button s2 = new Button(getApplicationContext());
        final Button s3 = new Button(getApplicationContext());

        s1.setText(tmp.getsRight());
        s2.setText(tmp.getsWrong1());
        s3.setText(tmp.getsWrong2());

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedSingular = tmp.getsRight();
                selectedSingularButton = s1;
                s1.getBackground().setColorFilter(SELECTED_COLOR, PorterDuff.Mode.MULTIPLY);
                s2.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                s3.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                check();
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedSingular = tmp.getsWrong1();
                selectedSingularButton = s2;
                s2.getBackground().setColorFilter(SELECTED_COLOR, PorterDuff.Mode.MULTIPLY);
                s1.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                s3.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                check();
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedSingular = tmp.getsWrong2();
                selectedSingularButton = s3;
                s3.getBackground().setColorFilter(SELECTED_COLOR, PorterDuff.Mode.MULTIPLY);
                s2.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                s1.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                check();
            }
        });

        shuffle(layoutSingular, s1, s2, s3);

    }

    private void createArticleLayout() {
        final Button a1 = new Button(getApplicationContext());
        final Button a2 = new Button(getApplicationContext());
        final Button a3 = new Button(getApplicationContext());

        a1.setText("der");
        a2.setText("die");
        a3.setText("das");

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedArticle = "der";
                selectedArticleButton = a1;
                a1.getBackground().setColorFilter(SELECTED_COLOR, PorterDuff.Mode.MULTIPLY);
                a2.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                a3.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                check();
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedArticle = "die";
                selectedArticleButton = a2;
                a2.getBackground().setColorFilter(SELECTED_COLOR, PorterDuff.Mode.MULTIPLY);
                a3.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                a1.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                check();
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedArticle = "das";
                selectedArticleButton = a3;
                a3.getBackground().setColorFilter(SELECTED_COLOR, PorterDuff.Mode.MULTIPLY);
                a2.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                a1.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
                check();
            }
        });

        layoutArticle.addView(a1);
        layoutArticle.addView(a2);
        layoutArticle.addView(a3);
    }

    private void check() {
        if (selectedArticle == null || selectedSingular == null || selectedPlural == null) {
            return;
        }
        if (selectedArticle.equals("") || selectedPlural.equals("") || selectedSingular.equals("")) {
            return;
        }
        SinglePlural tmp = entities.get(usedIndex.get(usedIndex.size() - 1));
        boolean stillWrong = false;
        if (selectedSingular.equals(tmp.getsRight())) {
            selectedSingularButton.getBackground().setColorFilter(RIGHT_COLOR, PorterDuff.Mode.MULTIPLY);
        } else {
            selectedSingularButton.getBackground().setColorFilter(WRONG_COLOR, PorterDuff.Mode.MULTIPLY);
            somethingWrong++;
            stillWrong = true;
        }

        if (selectedPlural.equals(tmp.getpRight())) {
            selectedPluralButton.getBackground().setColorFilter(RIGHT_COLOR, PorterDuff.Mode.MULTIPLY);
        } else {
            selectedPluralButton.getBackground().setColorFilter(WRONG_COLOR, PorterDuff.Mode.MULTIPLY);
            somethingWrong++;
            stillWrong = true;
        }

        if (selectedArticle.equals(tmp.getArticle())) {
            selectedArticleButton.getBackground().setColorFilter(RIGHT_COLOR, PorterDuff.Mode.MULTIPLY);
        } else {
            selectedArticleButton.getBackground().setColorFilter(WRONG_COLOR, PorterDuff.Mode.MULTIPLY);
            somethingWrong++;
            stillWrong = true;
        }

        if (!stillWrong) {
            points += (3 - somethingWrong);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (usedIndex.size() == howMany) {
                        ((Ueben) getApplication()).lastPoints = points;
                        startActivity(new Intent(DeutschSingularPluralActivity.this, SuperActivity.class));
                    } else {
                        reset();
                        chooseTask();
                    }
                }
            }, 800);
        }
    }

    private void reset() {
        selectedArticle = "";
        selectedPlural = "";
        selectedPluralButton = null;
        selectedSingular = "";
        selectedSingularButton = null;
        somethingWrong = 0;
        selectedArticleButton.getBackground().setColorFilter(DEFAULT_COLOR, PorterDuff.Mode.MULTIPLY);
    }

    private int getResource(String singular) {
        switch (singular) {
            case "Baum":
                return R.drawable.baum;
            case "Bild":
                return R.drawable.bild;
            case "Buch":
                return R.drawable.buch;
            case "Fisch":
                return R.drawable.fisch;
            case "Blume":
                return R.drawable.blume;
            case "Katze":
                return R.drawable.katze;
            case "Kind":
                return R.drawable.kind;
            case "Stift":
                return R.drawable.stift;
            case "Vogel":
                return R.drawable.vogel;
            case "Mädchen":
                return R.drawable.maedchen;
            case "Junge":
                return R.drawable.junge;
            case "Computer":
                return R.drawable.computer;
        }

        return 0;
    }
}
