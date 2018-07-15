package com.bw.my_jingdong.app;

import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.util.Log;

/**
 *
 */

public class MyUncaughtException implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("tag", "打印出错误信息 + " + e.getMessage());

        Process.killProcess(Process.myPid());
        Process.killProcess(Process.myPid());
    }

    private static MyUncaughtException myUncaughtException = new MyUncaughtException();

    public static MyUncaughtException getInstance() {
        return myUncaughtException;
    }

    Context context;
    Handler handler;

    public void initUncaughtException(Context context) {
        this.context = context;
        handler = new Handler() {
        };
        Thread.setDefaultUncaughtExceptionHandler(myUncaughtException);

    }
}
