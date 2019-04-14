package com.example.greiser.uebenlollipop5_0.activities.german;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.activities.SuperActivity;
import com.example.greiser.uebenlollipop5_0.helper.ExternalStorage;
import com.example.greiser.uebenlollipop5_0.helper.Ueben;
import com.example.greiser.uebenlollipop5_0.model.Konjugation;

import java.util.ArrayList;
import java.util.List;

public class GermanKonjugationActivity extends AppCompatActivity {

  public static final int BACKGROUND_COLOR_TO_TEST = Color.argb(255, 255, 181, 0);
  public static final int BACKGROUND_COLOR_INIT = Color.WHITE;
  private static final int BACKGROUND_COLOR_FAILED = Color.RED;
  private List<Konjugation> entities;
  private TextView textViewInfinitive;
  private EditText editTextI;
  private EditText editTextPYou;
  private EditText editTextSYou;
  private EditText editTextHe;
  private EditText editTextWe;
  private EditText editTextYour;
  private List<Integer> usedIndex = new ArrayList<>();
  private Konjugation entityToTest;
  private int toTestIndex;
  private int many;
  private boolean failed;
  private int points;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_german_konjugation);

    ExternalStorage es = new ExternalStorage();
    this.entities = es.getDeutschKonjugationEntities(getApplicationContext());
    this.many = 10;
    this.points = 0;

    initKonjugationFields();
    chooseEntry();
  }

  private void chooseEntry() {
    failed = false;
    int index = -1;

    do {
      index = (int) (Math.random() * entities.size());
    } while (index > entities.size() || usedIndex.contains(index));

    usedIndex.add(index);
    entityToTest = entities.get(index);

    this.toTestIndex = (int) (Math.random() * 6); // 6 -> ich, du, er, wir, ihr, sie
    setEntityToKonjugationFields(entityToTest);

    switch (this.toTestIndex) {
      case 0:
        createTimer(editTextI);
        break;
      case 1:
        createTimer(editTextSYou);
        break;
      case 2:
        createTimer(editTextHe);
        break;
      case 3:
        createTimer(editTextWe);
        break;
      case 4:
        createTimer(editTextYour);
        break;
      case 5:
        createTimer(editTextPYou);
        break;
    }
  }

  private void setEntityToKonjugationFields(Konjugation entityToTest) {

    this.textViewInfinitive.setText("");
    this.editTextSYou.setText("");
    this.editTextI.setText("");
    this.editTextHe.setText("");
    this.editTextWe.setText("");
    this.editTextYour.setText("");
    this.editTextPYou.setText("");

    this.editTextSYou.setBackgroundColor(BACKGROUND_COLOR_INIT);
    this.editTextI.setBackgroundColor(BACKGROUND_COLOR_INIT);
    this.editTextHe.setBackgroundColor(BACKGROUND_COLOR_INIT);
    this.editTextWe.setBackgroundColor(BACKGROUND_COLOR_INIT);
    this.editTextYour.setBackgroundColor(BACKGROUND_COLOR_INIT);
    this.editTextPYou.setBackgroundColor(BACKGROUND_COLOR_INIT);

    this.textViewInfinitive.setText(entityToTest.getInfinitive());
    if (this.toTestIndex != 0) this.editTextI.setText(entityToTest.getI());
    if (this.toTestIndex != 1) this.editTextSYou.setText(entityToTest.getsYou());
    if (this.toTestIndex != 2) this.editTextHe.setText(entityToTest.getHe());
    if (this.toTestIndex != 3) this.editTextWe.setText(entityToTest.getWe());
    if (this.toTestIndex != 4) this.editTextYour.setText(entityToTest.getYour());
    if (this.toTestIndex != 5) this.editTextPYou.setText(entityToTest.getpYou());

    switch (this.toTestIndex) {
      case 0:
        this.editTextI.setBackgroundColor(BACKGROUND_COLOR_TO_TEST);
        this.editTextI.setSelection(0);
        break;
      case 1:
        this.editTextSYou.setBackgroundColor(BACKGROUND_COLOR_TO_TEST);
        this.editTextSYou.setSelection(0);
        break;
      case 2:
        this.editTextHe.setBackgroundColor(BACKGROUND_COLOR_TO_TEST);
        this.editTextHe.setSelection(0);
        break;
      case 3:
        this.editTextWe.setBackgroundColor(BACKGROUND_COLOR_TO_TEST);
        this.editTextWe.setSelection(0);
        break;
      case 4:
        this.editTextYour.setBackgroundColor(BACKGROUND_COLOR_TO_TEST);
        this.editTextYour.setSelection(0);
        break;
      case 5:
        this.editTextPYou.setBackgroundColor(BACKGROUND_COLOR_TO_TEST);
        this.editTextPYou.setSelection(0);
        break;
    }
  }

  private void initKonjugationFields() {

    this.textViewInfinitive = findViewById(R.id.textViewInfnitive);
    this.editTextI = findViewById(R.id.editTextI);
    this.editTextSYou = findViewById(R.id.editTextSYou);
    this.editTextHe = findViewById(R.id.editTextHe);
    this.editTextWe = findViewById(R.id.editTextWe);
    this.editTextYour = findViewById(R.id.editTextYour);
    this.editTextPYou = findViewById(R.id.editTextPYou);

    this.editTextI.setOnEditorActionListener(
            new TextView.OnEditorActionListener() {
              public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return checkInput((EditText) v, actionId);
              }
            });

    this.editTextSYou.setOnEditorActionListener(
            new TextView.OnEditorActionListener() {
              public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return checkInput((EditText) v, actionId);
              }
            });
    this.editTextHe.setOnEditorActionListener(
            new TextView.OnEditorActionListener() {
              public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return checkInput((EditText) v, actionId);
              }
            });
    this.editTextWe.setOnEditorActionListener(
            new TextView.OnEditorActionListener() {
              public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return checkInput((EditText) v, actionId);
              }
            });
    this.editTextYour.setOnEditorActionListener(
            new TextView.OnEditorActionListener() {
              public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return checkInput((EditText) v, actionId);
              }
            });
    this.editTextPYou.setOnEditorActionListener(
            new TextView.OnEditorActionListener() {
              public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return checkInput((EditText) v, actionId);
              }
            });
  }

  private boolean checkInput(EditText v, int actionId) {
    if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
      if (checkSolution(v)) {
        if (!failed) {
          points++;
        }

        if (usedIndex.size() == many || usedIndex.size() == entities.size()) {
          ((Ueben) getApplication()).lastPoints = points * 10;
          startActivity(new Intent(GermanKonjugationActivity.this, SuperActivity.class));
        }

        InputMethodManager imm =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        setAllVisible();
        chooseEntry();
      } else {
        failed = true;
        v.setBackgroundColor(BACKGROUND_COLOR_FAILED);
        String userInput = v.getText().toString();
        v.setText(getMissing());
        createFailedTimer(v, userInput);
      }
    }
    return false;
  }

  private String getMissing() {

    switch (this.toTestIndex) {
      case 0:
        return entityToTest.getI();
      case 1:
        return entityToTest.getsYou();
      case 2:
        return entityToTest.getHe();
      case 3:
        return entityToTest.getWe();
      case 4:
        return entityToTest.getYour();
      case 5:
        return entityToTest.getpYou();
    }
    return "ERROR: 101";
  }

  private boolean checkSolution(TextView v) {
    switch (this.toTestIndex) {
      case 0:
        return v.getText().toString().trim().equals(entityToTest.getI());
      case 1:
        return v.getText().toString().trim().equals(entityToTest.getsYou());
      case 2:
        return v.getText().toString().trim().equals(entityToTest.getHe());
      case 3:
        return v.getText().toString().trim().equals(entityToTest.getWe());
      case 4:
        return v.getText().toString().trim().equals(entityToTest.getYour());
      case 5:
        return v.getText().toString().trim().equals(entityToTest.getpYou());
    }

    return false;
  }

  private void createTimer(final EditText toTest) {
    Handler handler = new Handler();
    handler.postDelayed(
            new Runnable() {
              @Override
              public void run() {
                setAllInvisible();
                toTest.setVisibility(View.VISIBLE);
                toTest.requestFocus();
                toTest.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                InputMethodManager imm =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(toTest, InputMethodManager.SHOW_FORCED);
              }
            },
            5000);
  }

  private void createFailedTimer(final EditText v, final String userInput) {
    Handler handler = new Handler();
    handler.postDelayed(
            new Runnable() {
              @Override
              public void run() {
                v.setBackgroundColor(BACKGROUND_COLOR_INIT);
                v.setText(userInput);
                v.setSelection(userInput.length() - 1);
                v.requestFocus();
                v.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                InputMethodManager imm =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
              }
            },
            2500);
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

  private void setAllVisible() {
    findViewById(R.id.editTextI).setVisibility(View.VISIBLE);
    findViewById(R.id.editTextSYou).setVisibility(View.VISIBLE);
    findViewById(R.id.editTextHe).setVisibility(View.VISIBLE);
    findViewById(R.id.editTextWe).setVisibility(View.VISIBLE);
    findViewById(R.id.editTextYour).setVisibility(View.VISIBLE);
    findViewById(R.id.editTextPYou).setVisibility(View.VISIBLE);

    findViewById(R.id.textViewInfnitive).setVisibility(View.VISIBLE);
    findViewById(R.id.textViewI).setVisibility(View.VISIBLE);
    findViewById(R.id.textViewSYou).setVisibility(View.VISIBLE);
    findViewById(R.id.textViewHe).setVisibility(View.VISIBLE);
    findViewById(R.id.textViewWe).setVisibility(View.VISIBLE);
    findViewById(R.id.textViewYour).setVisibility(View.VISIBLE);
    findViewById(R.id.textViewPYou).setVisibility(View.VISIBLE);
  }
}
