package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.button1)).setOnClickListener(this);

        ((Button)findViewById(R.id.button2)).setOnClickListener(this);

        ((Button)findViewById(R.id.button3)).setOnClickListener(this);

        ConstraintLayout constraintLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button1:
                Intent intent = new Intent(getApplicationContext(), WorkoutTimer.class);
                intent.putExtra("message", "Crunches (1 minute)");
                intent.putExtra("time", 60);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(getApplicationContext(), WorkoutTimer.class);
                intent2.putExtra("message", "Turkish Get Ups (10 minutes)" );
                intent2.putExtra("time", 600);
                startActivity(intent2);
                break;
            case R.id.button3:
                Intent intent3 = new Intent(getApplicationContext(), WorkoutTimer.class);
                intent3.putExtra("message", "Treadmill (1 hour)");
                intent3.putExtra("time", 3600);
                startActivity(intent3);
                break;
        }

    }


}






