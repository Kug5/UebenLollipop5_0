package com.example.greiser.uebenlollipop5_0.activities.math;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.activities.HowManyActivity;
import com.example.greiser.uebenlollipop5_0.helper.ExternalStorage;
import com.example.greiser.uebenlollipop5_0.helper.Ueben;

public class MathChoiceMultTableActivity extends AppCompatActivity {

  private Ueben ueben;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_math_choice_mult_table);

    ueben = (Ueben) getApplication();
    ExternalStorage es = new ExternalStorage();
    boolean[] choice = es.getChoiceMultTable(getApplicationContext(), ueben.getUsername());

    ((CheckBox) findViewById(R.id.checkBox0)).setChecked(choice[0]);
    ((CheckBox) findViewById(R.id.checkBox1)).setChecked(choice[1]);
    ((CheckBox) findViewById(R.id.checkBox2)).setChecked(choice[2]);
    ((CheckBox) findViewById(R.id.checkBox3)).setChecked(choice[3]);
    ((CheckBox) findViewById(R.id.checkBox4)).setChecked(choice[4]);
    ((CheckBox) findViewById(R.id.checkBox5)).setChecked(choice[5]);
    ((CheckBox) findViewById(R.id.checkBox6)).setChecked(choice[6]);
    ((CheckBox) findViewById(R.id.checkBox7)).setChecked(choice[7]);
    ((CheckBox) findViewById(R.id.checkBox8)).setChecked(choice[8]);
    ((CheckBox) findViewById(R.id.checkBox9)).setChecked(choice[9]);
    ((CheckBox) findViewById(R.id.checkBox10)).setChecked(choice[10]);

    Button nextButton = findViewById(R.id.next);
    nextButton.setOnClickListener(
        new View.OnClickListener() {

          @Override
          public void onClick(View v) {
            ueben.setMultTable(
                ((CheckBox) findViewById(R.id.checkBox0)).isChecked(),
                ((CheckBox) findViewById(R.id.checkBox1)).isChecked(),
                ((CheckBox) findViewById(R.id.checkBox2)).isChecked(),
                ((CheckBox) findViewById(R.id.checkBox3)).isChecked(),
                ((CheckBox) findViewById(R.id.checkBox4)).isChecked(),
                ((CheckBox) findViewById(R.id.checkBox5)).isChecked(),
                ((CheckBox) findViewById(R.id.checkBox6)).isChecked(),
                ((CheckBox) findViewById(R.id.checkBox7)).isChecked(),
                ((CheckBox) findViewById(R.id.checkBox8)).isChecked(),
                ((CheckBox) findViewById(R.id.checkBox9)).isChecked(),
                ((CheckBox) findViewById(R.id.checkBox10)).isChecked());
            new ExternalStorage()
                .storeChoiceMultTable(
                    getApplicationContext(), ueben.getMultTableToTrain(), ueben.getUsername());
            startActivity(new Intent(MathChoiceMultTableActivity.this, HowManyActivity.class));
          }
        });
  }
}
