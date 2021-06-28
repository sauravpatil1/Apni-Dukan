package com.saurav.apnidukan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;

public class SuccessfulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.googlepay);
        mediaPlayer.start();
    }
}