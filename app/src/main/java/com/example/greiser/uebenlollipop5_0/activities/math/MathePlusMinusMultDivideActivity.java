package com.example.greiser.uebenlollipop5_0.activities.math;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.greiser.uebenlollipop5_0.R;
import com.example.greiser.uebenlollipop5_0.activities.MenuActivity;
import com.example.greiser.uebenlollipop5_0.activities.SuperActivity;
import com.example.greiser.uebenlollipop5_0.helper.StorageData;
import com.example.greiser.uebenlollipop5_0.helper.StorageHeightScore;
import com.example.greiser.uebenlollipop5_0.helper.StoragePlusMinusMultDivide;
import com.example.greiser.uebenlollipop5_0.helper.Ueben;
import com.example.greiser.uebenlollipop5_0.model.BigTask;
import com.example.greiser.uebenlollipop5_0.model.UserHeightScore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MathePlusMinusMultDivideActivity extends AppCompatActivity {

    public static final String BR = "<br/>";
    static final String kugel = "&#9679;";
    static final String kugel_minus = "&#9152;";
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
    int points = 0;
    private TextView abakus;

    private List<Integer> usedIndex;
    private StorageData storedData;
    private long startTaskDate;
    private List<BigTask> taskList;
    private Ueben application;
    private UserHeightScore heightScores;
    private TextView timeAndPoints;
    private Handler timerHandler;
    private Runnable timerRunnable;
    private int seconds;
    private int nextPoint;
    private StoragePlusMinusMultDivide storagePlusMinusMultDivide;


    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(MathePlusMinusMultDivideActivity.this, MenuActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathe);

        application = ((Ueben) getApplication());
        timerHandler = new Handler();

        this.name = application.getUsername();
        this.operation = application.getOperation();
        this.max = application.getMax();
        this.heightScores = application.getHeightScores();

        int many = application.getHowMany();
        if (many == 0) {
            application.setMany(10);
            many = application.getHowMany();
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
        help.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        abakus.setVisibility(View.VISIBLE);
                    }
                });

        timeAndPoints = findViewById(R.id.timeAndPoints);

        if (operation.contains(Ueben.OPERATION_PLUS) || operation.contains(Ueben.OPERATION_MINUS)) {
            help.setVisibility(View.VISIBLE);
        } else {
            help.setVisibility(View.INVISIBLE);
        }

        usedIndex = new ArrayList<>();

        createKeybord();

        try {
            storagePlusMinusMultDivide =
                    new StoragePlusMinusMultDivide(getApplicationContext(), operation, max, name);
            storedData = (StorageData) storagePlusMinusMultDivide.load();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (storedData.isEmpty()) {
                createTasks();
            }
            fillTaskList();
        }
        chooseTask();
    }

    private void fillTaskList() {
        int counter = storedData.getCounter();

        if (counter != 3 && counter != 6 && counter != 8) { // 0 && 1 && 2 && 4 && 5 && 7
            taskList = storedData.getStep1();
            if (taskList.size() < this.many) {
                taskList.addAll(getXFromBox(many - taskList.size(), 2));
            }
            if (taskList.size() < this.many) {
                taskList.addAll(getXFromBox(many - taskList.size(),3));
            }
        } else if (counter == 3 || counter == 6) {
            taskList = storedData.getStep2();
            if (taskList.size() < this.many) {
                taskList.addAll(getXFromBox(many - taskList.size(),3));
            }
            if (taskList.size() < this.many) {
                taskList.addAll(getXFromBox(many - taskList.size(),1));
            }
        } else { // 8
            taskList = storedData.getStep3();
            if (taskList.size() < this.many) {
                taskList.addAll(getXFromBox(many - taskList.size(),2));
            }
            if (taskList.size() < this.many) {
                taskList.addAll(getXFromBox(many - taskList.size(),1));
            }
        }

        if (application.getOperation().equals(Ueben.OPERATION_MULT) || application.getOperation().equals(Ueben.OPERATION_DIVIDE)) {
            List<BigTask> tmp = new ArrayList<>();
            for (BigTask task : taskList) {
                if ((application.getOperation().equals(Ueben.OPERATION_MULT) && application.canCheck(task))
                        || (application.getOperation().equals(Ueben.OPERATION_DIVIDE) && application.canCheckDivide(task))) {
                    tmp.add(task);
                }
            }
            taskList = tmp;
        }
        checkMany();
    }

    private void checkMany() {
        if (taskList.size() < many) {
            this.many = taskList.size();
            application.setMany(this.many);
        }
    }

    private void initTimer() {
        timerRunnable =
                new Runnable() {
                    @Override
                    public void run() {
                        long millis = System.currentTimeMillis() - startTaskDate;
                        seconds = ((int) (millis / 1000)) % 60;

                        int diff = seconds - 5;
                        nextPoint = diff < 0 ? 10 : (10 - diff);
                        nextPoint = Math.max(nextPoint, 0);
                        updateTimeAndPoints();

                        timerHandler.postDelayed(timerRunnable, 1000);
                    }
                };
    }

    private void updateTimeAndPoints() {
        timeAndPoints.setText("Your points: " + points + " (+ " + nextPoint + ") Time: " + seconds);
    }

    private List<BigTask> getXFromBox(int count, int boxNumber) {
        List<BigTask> box;
        switch (boxNumber) {
            case 1: box = storedData.getStep1(); break;
            case 2: box = storedData.getStep2(); break;
            case 3: box = storedData.getStep3(); break;
            default:
                throw new IllegalStateException("Unexpected value: " + boxNumber);
        }
        ArrayList<BigTask> returnValue = new ArrayList<>();
        if (count <= 0 || box == null || box.size() == 0) {
            return returnValue;
        }

        if (box.size() <= count) {
            returnValue.addAll(box);
            return returnValue;
        }

        addCountBigTaskFromThirdToSecondParamter(count, returnValue, box);
        return returnValue;
    }

    private void addCountBigTaskFromThirdToSecondParamter(int count, ArrayList<BigTask> to, List<BigTask> from) {
        int indexRandom;
        List<Integer> tmpIndexes = new ArrayList<>();
        do {
            indexRandom = (int) (Math.random() * from.size());
            if (!tmpIndexes.contains(indexRandom) && indexRandom < from.size()) {
                to.add(from.get(indexRandom));
                tmpIndexes.add(indexRandom);
            }
        } while (to.size() < count);
    }

    private void createTasks() {

        switch (operation) {
            case Ueben.OPERATION_MINUS:
                createMinusTasks();
                break;
            case Ueben.OPERATION_PLUS:
                createPlusTasks();
                break;
            case Ueben.OPERATION_PLUSMINUS:
                createPlusMinusTasks();
                break;
            case Ueben.OPERATION_MULT:
                createMultTasks();
                break;
            case Ueben.OPERATION_DIVIDE:
                createDivideTasks();
                break;
        }
    }

    private void createDivideTasks() {
        if (this.max == 100) {
            for (int i = 100; i >= 0; i--) {
                for (int k = 10; k > 0; k--) {
                    if (i % k == 0 && (i / k) <= 10) {
                        storedData.setTask(i + " : " + k + " = ", i, k, (i / k), 1);
                    }
                }
            }
        }
    }

    private void createMultTasks() {

        if (this.max == 10) {
            for (int i = 0; i <= 10; i++) {
                for (int k = 1; k <= 10; k++) {
                    storedData.setTask(i + " * " + k + " = ", i, k, i * k, 1);
                }
            }
        } else {
            for (int i = 10; i <= 20; i++) {
                storedData.setTask(i + " * " + i + " = ", i, i, i * i, 1);
            }
        }
    }

    private void createPlusMinusTasks() {

        for (int i = 0; i <= max; i++) {
            for (int k = 0; k <= max; k++) {
                if (i + k <= max) {
                    storedData.setTask(i + " + " + k + " = ", i, k, i + k, 1);
                }

                if (i - k >= 0) {
                    storedData.setTask(i + " - " + k + " = ", i, k, i - k, 1);
                }
            }
        }
    }
    private void createPlusTasks() {

        for (int i = 0; i <= max; i++) {
            for (int k = 0; k <= max; k++) {
                if (i + k <= max) {
                    storedData.setTask(i + " + " + k + " = ", i, k, i + k, 1);
                }
            }
        }
    }

    private void createMinusTasks() {

        for (int i = 0; i <= max; i++) {
            for (int k = 0; k <= max; k++) {
                if (i-k>=0) {
                    storedData.setTask(i + " - " + k + " = ", i, k, i - k, 1);
                }
            }
        }
    }

    private void createKeybord() {
        final Button button_0 = findViewById(R.id.button_0);
        button_0.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNumber(0);
                    }
                });

        final Button button_1 = findViewById(R.id.button_1);
        button_1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNumber(1);
                    }
                });

        final Button button_2 = findViewById(R.id.button_2);
        button_2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNumber(2);
                    }
                });

        final Button button_3 = findViewById(R.id.button_3);
        button_3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNumber(3);
                    }
                });

        final Button button_4 = findViewById(R.id.button_4);
        button_4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNumber(4);
                    }
                });

        final Button button_5 = findViewById(R.id.button_5);
        button_5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNumber(5);
                    }
                });

        final Button button_6 = findViewById(R.id.button_6);
        button_6.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNumber(6);
                    }
                });

        final Button button_7 = findViewById(R.id.button_7);
        button_7.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNumber(7);
                    }
                });

        final Button button_8 = findViewById(R.id.button_8);
        button_8.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNumber(8);
                    }
                });

        final Button button_9 = findViewById(R.id.button_9);
        button_9.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNumber(9);
                    }
                });

        final Button button_BACK = findViewById(R.id.button_back);
        button_BACK.setOnClickListener(
                new View.OnClickListener() {
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
        button_OK.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkResult();
                    }
                });
    }

    private void addNumber(int number) {
        viewResult.setText(viewResult.getText().toString() + number);
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

        currentAufgabe = taskList.get(indexRandom).getDisplayTask();
        currentErgebnis = "" + taskList.get(indexRandom).getResult();

        if (operation.contains(Ueben.OPERATION_PLUS)) {
            createAbakusPlus(taskList.get(indexRandom));
        } else
        if (operation.contains(Ueben.OPERATION_MINUS)) {
            createAbakusMinus(taskList.get(indexRandom));
        }

        viewCurrentTask.setText(currentAufgabe);
        startTaskDate = new Date().getTime();
        if (timerRunnable != null) timerHandler.removeCallbacks(timerRunnable);
        initTimer();
        timerHandler.postDelayed(timerRunnable, 0);
    }

    private void createAbakusPlus(BigTask task) {

        StringBuilder forI = new StringBuilder();
        StringBuilder fork = new StringBuilder();

        int countKugelI = 0;
        int countKugelK = 0;

        for (int i = 0; i < task.getI(); i++) {
            forI.append(kugel);
            countKugelI++;
            if (countKugelI % 10 == 0) {
                forI.append(BR);
            }
        }

        for (int k = 0; k < task.getJ(); k++) {
            fork.append(kugel);
            countKugelK++;
            if ((countKugelI + countKugelK) % 10 == 0) {
                fork.append(BR);
            }
        }

        abakus.setText(
                Html.fromHtml(
                        "<font color='#FF0000'>"
                                + forI
                                + " </font> <font color='#0000FF' >"
                                + fork
                                + "</font>"));
    }

    private void createAbakusMinus(BigTask task) {
        StringBuilder forI = new StringBuilder();
        StringBuilder fork = new StringBuilder();

        int countKugelI = 0;
        int countKugelK = 0;

        for (int i = 0; i < task.getResult(); i++) {
            forI.append(kugel);
            countKugelI++;
            if (countKugelI % 10 == 0) {
                forI.append(BR);
            }
        }

        for (int k = 0; k < task.getJ(); k++) {
            fork.append(kugel_minus);
            countKugelK++;
            if ((countKugelI + countKugelK) % 10 == 0) {
                fork.append(BR);
            }
        }

        abakus.setText(
                Html.fromHtml(
                        "<font color='#FF0000'>"
                                + forI
                                + " </font> <font color='#FF0000' >"
                                + fork
                                + "</font>"));
    }

    private void checkResult() {

        int tmpIndex = usedIndex.get(usedIndex.size() - 1);

        if (viewResult.getText().toString().equals(currentErgebnis)) {

            counterCorrect++;

            // Wenn die Frage erst falsch beantwortet wurde, gehe ich davon aus, dass seconds zu groß ist,
            // andern Falls hat der User Glück gehabt und die Antwort wird in die nächste Box verschoben
            // :)
            if (nextPoint == 10) {
                int maxBoxes = 3;
                if (taskList.get(tmpIndex).getBox() < maxBoxes) {
                    taskList.get(tmpIndex).increaseBox();
                }
            }

            points += nextPoint;

            progressBar.setProgress(counterCorrect);
            if (counterCorrect < many) {
                chooseTask();
            } else {
                application.lastPoints = points;
                this.heightScores.setNewScore(operation, max, points, many * 10);
                StorageHeightScore storageHeightScore = new StorageHeightScore(getApplicationContext(), name);
                storageHeightScore.update(this.heightScores);
                storagePlusMinusMultDivide.update(storedData);
                startActivity(new Intent(MathePlusMinusMultDivideActivity.this, SuperActivity.class));
            }
        } else {
            taskList.get(tmpIndex).setToFirstBox();
            final Drawable background = viewResult.getBackground();
            viewResult.setBackgroundColor(Color.RED);
            Handler handler = new Handler();
            handler.postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            viewResult.setBackground(background);
                        }
                    },
                    500);
        }
        viewResult.setText("");
    }
}
