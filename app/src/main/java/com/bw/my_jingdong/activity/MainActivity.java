package com.bw.my_jingdong.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bw.my_jingdong.R;

public class MainActivity extends AppCompatActivity {

    private TextView tv_timer;
    int i=3;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int z=msg.what;
            tv_timer.setText(z+"S");
            if (z==0){
                Intent it = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(it);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        tv_timer = findViewById(R.id.main_tv_timer);
        new Thread(){
            @Override
            public void run() {
                while (i>0){
                    i--;
                    try {
                        sleep(1000);
                            handler.sendEmptyMessage(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
