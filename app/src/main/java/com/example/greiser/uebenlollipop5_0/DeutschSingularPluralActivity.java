package com.example.greiser.uebenlollipop5_0;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class DeutschSingularPluralActivity extends AppCompatActivity {

    private List<SinglePlural> entities;
    private LinearLayout layoutArticle;
    private LinearLayout layoutSingular;
    private LinearLayout layoutPlural;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deutsch_singular_plural);

        ExternalStorage es = new ExternalStorage();
        entities = es.getDeutschSinglePluralEntities(getApplicationContext());

        layoutArticle = findViewById(R.id.layoutArticle);
        layoutSingular = findViewById(R.id.layoutSingular);
        layoutPlural = findViewById(R.id.layoutPlural);

        Button a1 = new Button(getApplicationContext());
        a1.setText("a1");
        Button a2 = new Button(getApplicationContext());
        a2.setText("a2");
        Button a3 = new Button(getApplicationContext());
        a3.setText("a3");

        layoutArticle.addView(a1);
        layoutArticle.addView(a2);
        layoutArticle.addView(a3);

        Button s1 = new Button(getApplicationContext());
        s1.setText("s1");
        Button s2 = new Button(getApplicationContext());
        s2.setText("s2");
        Button s3 = new Button(getApplicationContext());
        s3.setText("s3");

        layoutSingular.addView(s1);
        layoutSingular.addView(s2);
        layoutSingular.addView(s3);

        Button p1 = new Button(getApplicationContext());
        p1.setText("p1");
        Button p2 = new Button(getApplicationContext());
        p2.setText("p2");
        Button p3 = new Button(getApplicationContext());
        p3.setText("p3");

        layoutPlural.addView(p1);
        layoutPlural.addView(p2);
        layoutPlural.addView(p3);
        
        image = (ImageView) findViewById(R.id.imageSingular);
        Bitmap bMap = BitmapFactory.decodeFile(es.getImageFilePath(getApplicationContext(), "Baum"));
        image.setImageBitmap(bMap);
    }
}
