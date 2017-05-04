package com.example.administrator.handlermessageprogess;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
    ProgressBar bar = null;
    Button start = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (ProgressBar) findViewById(R.id.bar);
        start = (Button) findViewById(R.id.startButton);
        start.setOnClickListener(new ButtonListener());
    }

    class ButtonListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            bar.setVisibility(View.VISIBLE);
            updateBarHandler.post(updateThread);
        }
    }

    Handler updateBarHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            bar.setProgress(msg.arg1);
            updateBarHandler.post(updateThread);
        }

    };

    Runnable updateThread = new Runnable() {
        int i = 0;

        @Override
        public void run() {
            System.out.println("Begin Thread");
            i = i + 20;
            Message msg = updateBarHandler.obtainMessage();
            msg.arg1 = i;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            updateBarHandler.sendMessage(msg);
            if (i == 100) {
                updateBarHandler.removeCallbacks(updateThread);
            }
        }
    };
}