package com.example.greiser.uebenlollipop5_0;

import android.content.Intent;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

public class DeutschActivity extends AppCompatActivity {

    private TextToSpeech textToSpeach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deutsch);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        textToSpeach = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeach.setLanguage(Locale.GERMAN);
                }
            }
        });

        final ImageView play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak =((EditText) findViewById(R.id.toSpeak)).getText().toString();
               // Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show(); //?
                HashMap<String, String> hash = new HashMap<String, String>();
                hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_NOTIFICATION));
                textToSpeach.speak(toSpeak, TextToSpeech.QUEUE_ADD, hash);
            }
        });

        final Button stimmt = findViewById(R.id.stimmt);
        stimmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeutschActivity.this, SuperActivity.class));
            }
        });

    }
}
