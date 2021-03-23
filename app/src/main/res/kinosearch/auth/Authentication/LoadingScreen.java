package com.example.kinosearch.auth.Authentication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kinosearch.LoginActivity;
import com.example.kinosearch.R;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(com.example.kinosearch.LoadingScreen.this, LoginActivity.class));
            }
        };
        timer.schedule(tt, 3000L);
    }
    }
