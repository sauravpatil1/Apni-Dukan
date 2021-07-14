package com.saurav.apnidukan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;

public class SuccessfulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.googlepay);
        mediaPlayer.start();
        new CountDownTimer(3000, 1000){
            public void onFinish(){
                Intent intent = new Intent(SuccessfulActivity.this, MainActivity.class);
                startActivity(intent);
            }
            public void onTick(long mi){}
        }.start();
    }
}