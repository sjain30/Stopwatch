package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds;
    private boolean running;
    private int lapcount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
    }

    private void runTimer()
    {
        final TextView textView = findViewById(R.id.textView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes=(seconds%3600)/60;
                int sec = seconds%60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,sec);
                textView.setText(time);
                if (running)
                {
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

    public void onClickStart(View view)
    {
        running=true;
    }

    public void onClickStop(View view)
    {
        running=false;
    }

    public void onClickReset(View view)
    {
        running=false;
        seconds=0;
    }

    public void onClickLap(View view)       //Make lap inactive when stopwatch is stopped
    {
        lapcount++;
        TextView curr = findViewById(R.id.textView);
        StringBuilder current = new StringBuilder();
        current.append("\nLap ").append(lapcount).append(" ").append(curr.getText().toString());
        TextView app = findViewById(R.id.textView2);
        current.append(app.getText().toString());
        app.setText(current);
    }
}
