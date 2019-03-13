package com.example.greiser.uebenlollipop5_0.activities.german;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.helper.ExternalStorage;
import com.example.greiser.uebenlollipop5_0.model.Konjugation;

import java.util.ArrayList;
import java.util.List;

public class GermanKonjugationActivity extends AppCompatActivity {

    private List<Konjugation> entities;
    private LinearLayout mainLayout;
    private TextView textViewInfinitive;
    private EditText editTextI;
    private EditText editTextPYou;
    private EditText editTextSYou;
    private EditText editTextHe;
    private EditText editTextWe;
    private EditText editTextYour;
    private List<Integer> usedIndex = new ArrayList<>();
    private Konjugation entityToTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_german_konjugation);

        ExternalStorage es = new ExternalStorage();
        this.entities = es.getDeutschKonjugationEntities(getApplicationContext());

        initKonjugationFields();
        chooseEntry();
    }

    private void chooseEntry() {
        int index = -1;

        do {
            index = (int) (Math.random() * entities.size());
        } while (index > entities.size() || usedIndex.contains(index));

        usedIndex.add(index);

        entityToTest = entities.get(index);
        setEntityToKonjugationFields(entityToTest);
    }

    private void setEntityToKonjugationFields(Konjugation entityToTest) {
        this.textViewInfinitive.setText(entityToTest.getInfinitive());
        this.editTextI.setText(entityToTest.getI());
        this.editTextSYou.setText(entityToTest.getsYou());
        this.editTextHe.setText(entityToTest.getHe());
        this.editTextWe.setText(entityToTest.getWe());
        this.editTextYour.setText(entityToTest.getYour());
        this.editTextPYou.setText(entityToTest.getpYou());
    }

    private void initKonjugationFields() {

        this.textViewInfinitive = findViewById(R.id.textViewInfnitive);
        this.editTextI = findViewById(R.id.editTextI);
        this.editTextSYou = findViewById(R.id.editTextSYou);
        this.editTextHe = findViewById(R.id.editTextHe);
        this.editTextWe = findViewById(R.id.editTextWe);
        this.editTextYour = findViewById(R.id.editTextYour);
        this.editTextPYou = findViewById(R.id.editTextPYou);

        this.editTextI.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
                    checkSolution();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                } else {
                    setAllInvisible();
                    editTextI.setVisibility(View.VISIBLE);

                    editTextI.requestFocus();
                    editTextI.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editTextI, InputMethodManager.SHOW_FORCED);
                }
                return false;
            }
        });


        this.editTextSYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllInvisible();
                editTextSYou.setVisibility(View.VISIBLE);
            }
        });

        this.editTextHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllInvisible();
                editTextHe.setVisibility(View.VISIBLE);
            }
        });

        this.editTextWe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllInvisible();
                editTextWe.setVisibility(View.VISIBLE);
            }
        });

        this.editTextYour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllInvisible();
                editTextYour.setVisibility(View.VISIBLE);
            }
        });

        this.editTextPYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllInvisible();
                editTextPYou.setVisibility(View.VISIBLE);
            }
        });
    }

    private void checkSolution() {
    }

    private void setAllInvisible() {
        findViewById(R.id.editTextI).setVisibility(View.GONE);
        findViewById(R.id.editTextSYou).setVisibility(View.GONE);
        findViewById(R.id.editTextHe).setVisibility(View.GONE);
        findViewById(R.id.editTextWe).setVisibility(View.GONE);
        findViewById(R.id.editTextYour).setVisibility(View.GONE);
        findViewById(R.id.editTextPYou).setVisibility(View.GONE);

        findViewById(R.id.textViewInfnitive).setVisibility(View.GONE);
        findViewById(R.id.textViewI).setVisibility(View.GONE);
        findViewById(R.id.textViewSYou).setVisibility(View.GONE);
        findViewById(R.id.textViewHe).setVisibility(View.GONE);
        findViewById(R.id.textViewWe).setVisibility(View.GONE);
        findViewById(R.id.textViewYour).setVisibility(View.GONE);
        findViewById(R.id.textViewPYou).setVisibility(View.GONE);
    }
}
