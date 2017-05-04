package com.example.administrator.handlerthread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button start = null;
    private Button end = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.startbutton);
        start.setOnClickListener(new Startlistener());
        end = (Button) findViewById(R.id.endbutton);
        end.setOnClickListener(new Endlistener());
    }

    Handler handler = new Handler();

    class Startlistener implements OnClickListener{
        @Override
        public void onClick(View v) {
            handler.post(MyThread);
        }
    }

    class Endlistener implements OnClickListener {
        @Override
        public void onClick(View v) {
        handler.removeCallbacks(MyThread);
       }
    }

    Runnable MyThread = new Runnable() {
        @Override
        public void run() {
            System.out.println("Call");
            Log.v("tag","MyThread");
            handler.postDelayed(MyThread,2000);
        }
    };
}
