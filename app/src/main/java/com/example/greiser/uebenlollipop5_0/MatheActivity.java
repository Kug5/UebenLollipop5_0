package com.example.greiser.uebenlollipop5_0;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatheActivity extends AppCompatActivity {

    static final String kugel = "&#9679;";
    static final String kugel_minus = "&#9152;";
    public static final String BR = "<br/>";

    String currentAufgabe = null;
    String currentErgebnis = null;

    int max;
    int many;
    String operation;
    String name;

    EditText viewCurrentTask = null;
    EditText viewResult = null;
    ProgressBar progressBar;

    int counterCorrect = 0;
    private EditText abakus;

    private List<Integer> usedIndex;
    private StorageData storedData;
    private long startTaskDate;
    private List<BigTask> taskList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathe);

        this.name =  ((Ueben) getApplication()).getUsername();
        this.operation = ((Ueben) getApplication()).getOperation();
        this.max = ((Ueben) getApplication()).getMax();

        int many = ((Ueben) getApplication()).getMany();
        if (many == 0) {
            many = 10;
        }
        this.many = many;

        progressBar = findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        progressBar.setMax(many);
        progressBar.setProgress(0);

        viewCurrentTask = findViewById(R.id.aufgabe);
        viewCurrentTask.setKeyListener(null);
        viewResult = findViewById(R.id.ergebnis);
        viewResult.setShowSoftInputOnFocus(false);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        abakus = findViewById(R.id.abakus);
        abakus.setShowSoftInputOnFocus(false);
        final ImageView help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abakus.setVisibility(View.VISIBLE);
            }
        });

        if (operation.equals(Ueben.OPEARATION_PLUSMINUS)) {
            help.setVisibility(View.VISIBLE);
        }else {
            help.setVisibility(View.INVISIBLE);
        }

        usedIndex = new ArrayList<Integer>();

        createKeybord();

        File storageFile = getStorageFile(this.operation, this.max);
        try {
            storedData = getStoredData(storageFile);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error", e.getMessage());
            storedData = new StorageData();
            createTasks();
        } finally {
            if (storedData.getCounter()%4 == 0 && storedData.getStep2().size() >= this.many) {
                taskList = storedData.getStep2();
            } else if (storedData.getStep1().size() >= this.many){
                taskList = storedData.getStep1();
            } else if (storedData.getStep2().size() >= this.many){
                taskList = storedData.getStep2();
            } else { // worst case :(
                taskList.addAll(storedData.getStep1());
                taskList.addAll(storedData.getStep2());
            }
        }

        chooseTask();
    }

    private StorageData getStoredData(File storage) throws Exception {
        BufferedReader bufferIn = new BufferedReader(new FileReader(storage));
        StorageData sd = new StorageData();
        String line  = bufferIn.readLine();
        if(line == null) {
            throw new Exception("empty file");
        }
        sd.setDate(Long.parseLong(line));
        line = bufferIn.readLine();
        sd.setCounter(Integer.parseInt(line));
        line = bufferIn.readLine();
        while (line != null) {
            String [] taskSplit = line.split(",");
            sd.setTask(taskSplit[0], Integer.parseInt(taskSplit[1]), Integer.parseInt(taskSplit[2]), Integer.parseInt(taskSplit[3]), Boolean.parseBoolean(taskSplit[4]));
            line = bufferIn.readLine();
        }
        bufferIn.close();

        return sd;
    }

    private File getStorageFile(String operation, int max) {
        ExternalStorage es = new ExternalStorage();
        return es.getPrivateDocumentsStorageFile(getApplicationContext(), operation,max, name);
    }

    private void createTasks() {

        if (operation.equals(Ueben.OPEARATION_PLUSMINUS)) {
            createPlusMinusTasks();
        } else if (operation.equals(Ueben.OPEARATION_MULT)) {
            createMultTasks();
        }
    }

    private void createMultTasks() {

        if (this.max == 10) {
            for (int i = 0; i <= 10; i++) {
                for (int k = 1; k <= 10; k++) {
                    storedData.setTask(i + " * " + k + " = ", i, k, i*k, false);
                }
            }
        } else {
            for (int i = 10; i <= 20; i++) {
                storedData.setTask(i + " * " + i + " = ", i, i, i*i, false);
            }
        }
    }

    private void createPlusMinusTasks() {

        for (int i =  0; i <= max; i++) {
            for (int k =  0; k <= max; k++) {
                if (i + k <= max) {
                    storedData.setTask(i + " + " + k + " = ", i, k, i+k, false);
                }

                if (i - k >= 0) {
                    storedData.setTask(i + " - " + k + " = ", i, k, i-k, false);
                }
            }
        }
    }

    private void createKeybord() {
        final Button button_0 = findViewById(R.id.button_0);
        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(0);
            }
        });

        final Button button_1 = findViewById(R.id.button_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(1);
            }
        });

        final Button button_2 = findViewById(R.id.button_2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(2);
            }
        });

        final Button button_3 = findViewById(R.id.button_3);
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(3);
            }
        });

        final Button button_4 = findViewById(R.id.button_4);
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(4);
            }
        });

        final Button button_5 = findViewById(R.id.button_5);
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(5);
            }
        });

        final Button button_6 = findViewById(R.id.button_6);
        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(6);
            }
        });

        final Button button_7 = findViewById(R.id.button_7);
        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(7);
            }
        });

        final Button button_8 = findViewById(R.id.button_8);
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(8);
            }
        });

        final Button button_9 = findViewById(R.id.button_9);
        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber(9);
            }
        });

        final Button button_BACK = findViewById(R.id.button_back);
        button_BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = viewResult.getText().toString();
                if (tmp.length() != 0) {
                    viewResult.setText(tmp.subSequence(0, tmp.length() - 1));
                    setCourser();

                }
            }
        });

        final Button button_OK = findViewById(R.id.button_OK);
        button_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkResult();
            }
        });
    }

    private void addNumber(int number) {
        viewResult.setText(new StringBuilder().append(viewResult.getText().toString()).append(number).toString());
        setCourser();
    }
    private void setCourser() {
        viewResult.setSelection(viewResult.getText().toString().length());
    }
    private void chooseTask() {

        abakus.setVisibility(View.INVISIBLE);

        int indexRandom;
        do {
            indexRandom = (int) (Math.random() * taskList.size());
        } while (indexRandom > taskList.size() - 1 || usedIndex.contains(indexRandom));

        usedIndex.add(indexRandom);

        currentAufgabe = taskList.get(indexRandom).displayTask;
        currentErgebnis = "" + taskList.get(indexRandom).result;

        if (operation.equals(Ueben.OPEARATION_PLUSMINUS) && taskList.get(indexRandom).displayTask.contains(" + ")) {
            createAbakusPlus(taskList.get(indexRandom));
        } else if (operation.equals(Ueben.OPEARATION_PLUSMINUS) && taskList.get(indexRandom).displayTask.contains(" - ")) {
            createAbakusMinus(taskList.get(indexRandom));
        }

        viewCurrentTask.setText(currentAufgabe);
        startTaskDate = new Date().getTime();
    }

    private void createAbakusPlus(BigTask task) {

        StringBuilder forI = new StringBuilder();
        StringBuilder fork = new StringBuilder();

        int countKugelI = 0;
        int countKugelK = 0;

        for (int i = 0; i< task.i; i++) {
            forI.append(kugel);
            countKugelI++;
            if (countKugelI % 10 == 0) {
                forI.append(BR);
            }
        }

        for (int k = 0; k< task.j; k++) {
            fork.append(kugel);
            countKugelK++;
            if ( (countKugelI + countKugelK) % 10 == 0 ) {
                fork.append(BR);
            }
        }

        abakus.setText(Html.fromHtml("<font color='#FF0000'>" + forI + " </font> <font color='#0000FF' >"+ fork+ "</font>"));
    }

    private void createAbakusMinus (BigTask task) {
        StringBuilder forI = new StringBuilder();
        StringBuilder fork = new StringBuilder();

        int countKugelI = 0;
        int countKugelK = 0;

        for (int i = 0; i< task.result; i++) {
            forI.append(kugel);
            countKugelI++;
            if (countKugelI % 10 == 0) {
                forI.append(BR);
            }
        }

        for (int k = 0; k< task.j; k++) {
            fork.append(kugel_minus);
            countKugelK++;
            if ( (countKugelI + countKugelK) % 10 == 0 ) {
                fork.append(BR);
            }

        }

        abakus.setText(Html.fromHtml("<font color='#FF0000'>" + forI + " </font> <font color='#FF0000' >"+ fork+ "</font>"));
    }

    private void checkResult () {

        int tmpIndex = usedIndex.get(usedIndex.size() - 1 );

        if (viewResult.getText().toString().equals(currentErgebnis)) {

            long duration = new Date().getTime() - startTaskDate;
            counterCorrect++;

            // Wenn die Frage erst falsch beantwortet wurde, gehe ich davon aus, dass die duration zu groß ist, andern Falls hat der User Glück gehabt und die Antwort wird in Step 2 verschoben :)
            if (readyForStep2(duration)) {
                taskList.get(tmpIndex).ready = true;
            }

            progressBar.setProgress(counterCorrect);
            if (counterCorrect < many) {
                chooseTask();
            } else {
                ExternalStorage es = new ExternalStorage();
                try {
                    es.store(getApplicationContext(), storedData, operation, max, name);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(MatheActivity.this, SuperActivity.class));
                }
            }
        } else {
            taskList.get(tmpIndex).ready = false;
            final Drawable background = viewResult.getBackground();
            viewResult.setBackgroundColor(Color.RED);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    viewResult.setBackground(background);
                }
            }, 500);
        }
        viewResult.setText("");
    }

    private boolean readyForStep2(long duration) {
         return duration < 5000;
    }
}
