package com.example.channelprotest_mvc.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.channelprotest_mvc.R;

public class SplashScreenActivity extends AppCompatActivity {

    Boolean first_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sharedPreferences = getSharedPreferences("StatusPref",MODE_PRIVATE);

        first_time=sharedPreferences.getBoolean("first_time",true);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //This method will be executed once the timer is over
                // Start your app main activity
                if (first_time){
                    /*myEdit.putBoolean("first_time", false);
                    myEdit.apply();*/

                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
                else
                {

                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();

                }
            }
        }, 3000);
    }
}