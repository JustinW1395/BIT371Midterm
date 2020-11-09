package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class WorkoutTimer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_timer);


        int time = getIntent().getExtras().getInt("time");
        String message = getIntent().getExtras().getString("message");

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, TimerFragment.newInstance(time, message))
                .commit();
        //Log.i("INFO", "time is " + time);
        //Log.i("INFO", "message is " + message);


    }

}