package com.example.greiser.uebenlollipop5_0.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.helper.StorageToday;
import com.example.greiser.uebenlollipop5_0.helper.Ueben;
import com.example.greiser.uebenlollipop5_0.model.Result;

import java.util.ArrayList;

public class ResultOverview extends AppCompatActivity {

    private int index = 0;
    private ArrayList<Result> data;
    private TextView dateView;
    private TextView resultView;
    private Button button_left;
    private Button button_right;

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(ResultOverview.this, MenuActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_overview);

        Button backButton = findViewById(R.id.back);
        backButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(ResultOverview.this, MenuActivity.class));
                    }
                });

        button_left = findViewById(R.id.button_left);
        button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (index - 1 < 0) {
                    return;
                }
                setData(--index);
                toggleLeftRightButtons();
            }
        });

        button_right = findViewById(R.id.button_right);
        button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index + 1 >= data.size()) {
                    return;
                }
                setData(++index);
                toggleLeftRightButtons();
            }
        });

        this.dateView = findViewById(R.id.dateView);
        this.resultView = findViewById(R.id.resultView);

        StorageToday st = new StorageToday(getApplicationContext(), ((Ueben) getApplication()).getUsername());
        try {
            this.data = (ArrayList<Result>) st.load();
            this.index = this.getIndexOfTheDay(StorageToday.getDateOfTheDay());
            setData(index);
            toggleLeftRightButtons();
        } catch (Exception e) {
            this.dateView.setText("Fehler");
            this.resultView.setText(e.getMessage());
        }
    }

    private void toggleLeftRightButtons() {

        if (data.size() == 0) {
            button_left.setEnabled(false);
            button_right.setEnabled(false);
            return;
        }

        if (index == 0) {
            button_left.setEnabled(false);
        } else {
            button_left.setEnabled(true);
        }
        if (index == this.data.size() - 1) {
            button_right.setEnabled(false);
        } else {
            button_right.setEnabled(true);
        }
    }

    private int getIndexOfTheDay(String dateOfTheDay) {
        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.get(i).getDate().equals(dateOfTheDay)) {
                return i;
            }
        }
        return 0;
    }

    private void setData(int index) {

        if (this.data.size() == 0) {
            this.dateView.setText("");
            this.resultView.setText(((Ueben) getApplication()).getUsername() + " hat noch keine Aufgabe beendet.");
            return;
        }

        Result result = this.data.get(index);

        String date = result.getDate();
        String dateOutput = "";
        if (date.length() == 8) {
            dateOutput = date.substring(6) + "." + date.substring(4, 6) + "." + date.substring(0, 4);
        } else {
            dateOutput = date;
        }

        this.dateView.setText(dateOutput);
        this.resultView.setText(result.getResult());
    }
}
