package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds;
    private boolean running;
    private int lapcount=0;
    Button button;
    TextView app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            lapcount=savedInstanceState.getInt("lapcount");
            app.setText(savedInstanceState.getString("laps"));
        }
        button = findViewById(R.id.button4);
        app = findViewById(R.id.textView2);
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putInt("lapcount",lapcount);
        outState.putString("laps",app.getText().toString());
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
        button.setEnabled(true);
    }

    public void onClickStop(View view)
    {
        running=false;
        button.setEnabled(false);
    }

    public void onClickReset(View view)
    {
        running=false;
        seconds=0;
        button.setEnabled(false);
        app.setText("");
        lapcount=0;
    }

    public void onClickLap(View view)       //Make lap inactive when stopwatch is stopped
    {
        lapcount++;
        TextView curr = findViewById(R.id.textView);
        StringBuilder current = new StringBuilder();
        current.append("\nLap ").append(lapcount).append(" ").append(curr.getText().toString());

        current.append(app.getText().toString());
        app.setText(current);
        app.setMovementMethod(new ScrollingMovementMethod());
    }
}
