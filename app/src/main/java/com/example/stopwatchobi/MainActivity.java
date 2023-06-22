package com.example.stopwatchobi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int sec;
    private boolean run;
    private boolean wasrun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null)
        {
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }
    public void onStart(View view)
    {
        run = true;
    }
    public void onStop(View view)
    {
        run = false;

    }
    public void onReset(View view){
    run = false;
    sec = 0;
    }
    @Override
    protected  void onPause()
    {
        super.onPause();
        wasrun = run;
        run = false;

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(wasrun){
            run = true;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("second",sec);
        outState.putBoolean("running",run);
        outState.putBoolean("wasRunning",wasrun);

    }

    private void runTimer() {
        TextView timeView = findViewById(R.id.txt);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hour = sec / 3600;
                int min =( sec % 3600)/60;
                int second = sec % 60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hour,min,second);
                timeView.setText(time);
                if(run){
                    sec++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

}