package com.its.samplelearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class SplashscreenActivity extends AppCompatActivity {

    private static final int DELAY_MILLIS = 3000;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        sharedPreferences = getApplicationContext().getSharedPreferences(Utility.SHARED_PREF, MODE_PRIVATE);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (sharedPreferences.getBoolean(Utility.SHARED_PREF_ALREADY_LOGGED_IN, false)) {
                    intent = new Intent(SplashscreenActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(SplashscreenActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        };

        new Handler().postDelayed(runnable, DELAY_MILLIS);
    }

}