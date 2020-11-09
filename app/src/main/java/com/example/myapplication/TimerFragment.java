package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class TimerFragment extends Fragment  implements View.OnClickListener {

    private int seconds = 0;
    private String message = "this is text";
    private TextView tv;
    private boolean paused = true;
    private FloatingActionButton fab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        runTimer();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ConstraintLayout constraintLayout = view.findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        view.findViewById(R.id.play_pause_fab).setOnClickListener(this);
        TextView tt = view.findViewById(R.id.fragment_text);
        seconds = getArguments().getInt("time");
        message = getArguments().getString("message");
        tt.setText(message);
        fab = view.findViewById(R.id.play_pause_fab);
        tv = view.findViewById(R.id.time_text);
        if(savedInstanceState != null) {
            paused = savedInstanceState.getBoolean("paused");
            seconds = savedInstanceState.getInt("seconds");
        }
    }
    public static TimerFragment newInstance(int time, String message) {
        TimerFragment tf = new TimerFragment();
        Bundle args = new Bundle();
        args.putInt("time", time);
        args.putString("message", message);
        tf.setArguments(args);
        return tf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("paused", paused);
    }



    public void StarTimer(View view){
        paused = !paused;
        Drawable PLAY = getResources().getDrawable(android.R.drawable.ic_media_play);
        Drawable PAUSE = getResources().getDrawable(android.R.drawable.ic_media_pause);
        Drawable icon = paused ? PLAY : PAUSE;
        fab.setImageDrawable(icon);

    }

    public void runTimer() {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                int sec = seconds % 60;
                int min = (seconds % 3600) / 60;
                int hour = seconds / 3600;
                tv.setText(String.format("%02d : %02d : %02d", hour, min, sec));
                if(!paused)
                    seconds--;
                if(seconds <= 0) {
                    paused = true;
                    if(seconds < 0)
                        seconds = 0;
                }

                handler.postDelayed(this, 1000);
            }
        });
}

    @Override
    public void onClick(View view) {
        StarTimer(view);
    }


}