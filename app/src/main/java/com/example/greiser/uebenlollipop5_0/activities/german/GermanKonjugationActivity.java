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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_german_konjugation);

        ExternalStorage es = new ExternalStorage();
        this.entities = es.getDeutschKonjugationEntities(getApplicationContext());

        this.textViewInfinitive = findViewById(R.id.textViewInfnitive);
        this.textViewInfinitive.setText(this.entities.get(2).getInfinitive());

        this.editTextI = findViewById(R.id.editTextI);
        this.editTextI.setText(this.entities.get(2).getI());
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

        this.editTextSYou = findViewById(R.id.editTextSYou);
        this.editTextSYou.setText(this.entities.get(2).getsYou());
        this.editTextSYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllInvisible();
                editTextSYou.setVisibility(View.VISIBLE);
            }
        });

        this.editTextHe = findViewById(R.id.editTextHe);
        this.editTextHe.setText(this.entities.get(2).getHe());
        this.editTextHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllInvisible();
                editTextHe.setVisibility(View.VISIBLE);
            }
        });

        this.editTextWe = findViewById(R.id.editTextWe);
        this.editTextWe.setText(this.entities.get(2).getWe());
        this.editTextWe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllInvisible();
                editTextWe.setVisibility(View.VISIBLE);
            }
        });

        this.editTextYour = findViewById(R.id.editTextYour);
        this.editTextYour.setText(this.entities.get(2).getYour());
        this.editTextYour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllInvisible();
                editTextYour.setVisibility(View.VISIBLE);
            }
        });

        this.editTextPYou = findViewById(R.id.editTextPYou);
        this.editTextPYou.setText(this.entities.get(2).getpYou());
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
