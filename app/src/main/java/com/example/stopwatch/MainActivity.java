package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds;
    private boolean running;
    private int lapcount;
    private Button button;
    private Button b1;
    private TextView app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button3);
        b1 = findViewById(R.id.button);
        app = findViewById(R.id.textView2);
        if(savedInstanceState!=null)
        {
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            lapcount=savedInstanceState.getInt("lapcount");
            app.setText(savedInstanceState.getString("laps"));
            button.setText(savedInstanceState.getString("b2text"));
            b1.setText(savedInstanceState.getString("b1text"));
            if (savedInstanceState.getString("b1text").equals("Start"))
                b1.setBackground(getDrawable(R.drawable.roundbutton));
            else
                b1.setBackground(getDrawable(R.drawable.roundbutton3));
            button.setEnabled(savedInstanceState.getBoolean("b2state"));
        }
        else {
            lapcount = 0;
        }
        app.setMovementMethod(new ScrollingMovementMethod());
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("b1text",b1.getText().toString());
        outState.putString("b2text",button.getText().toString());
        if (seconds!=0)
            outState.putBoolean("b2state",true);
//        else if (running==false && seconds!=0)
//            outState.putBoolean("b2state",true);
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

    public void onClickStartStop(View view)
    {
        if (b1.getText().toString().equals("Start")) {
            running = true;
            button.setEnabled(true);
            button.setText(R.string.lap);
            b1.setText(R.string.stop);
            b1.setBackground(getDrawable(R.drawable.roundbutton3));

        }
        else if (b1.getText().toString().equals("Stop")) {

            running=false;
            button.setText(R.string.reset);
            b1.setText(R.string.start);
            b1.setBackground(getDrawable(R.drawable.roundbutton));
        }
    }

//    public void onClickStop(View view)
//    {
//        running=false;
//        button.setEnabled(false);
//    }

    public void onClickResetLap(View view)
    {
        Button button3 = (Button)view;
        if (button3.getText().toString().equals("Reset")) {
            running = false;
            seconds = 0;
            button3.setText(R.string.lap);
            app.setText("");
            lapcount = 0;
            button3.setEnabled(false);
        }
        else if (button3.getText().toString().equals("Lap"))
        {
            lapcount++;
            TextView curr = findViewById(R.id.textView);
            StringBuilder current = new StringBuilder();
            current.append("\nLap ").append(lapcount).append("          ").append(curr.getText().toString());
            current.append(app.getText().toString());
            app.setText(current);
        }
    }

//    public void onClickLap(View view)       //Make lap inactive when stopwatch is stopped
//    {
//        lapcount++;
//        TextView curr = findViewById(R.id.textView);
//        StringBuilder current = new StringBuilder();
//        current.append("\nLap ").append(lapcount).append(" ").append(curr.getText().toString());
//        current.append(app.getText().toString());
//        app.setText(current);
//        app.setMovementMethod(new ScrollingMovementMethod());
//    }
}
