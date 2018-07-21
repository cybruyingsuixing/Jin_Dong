package com.bw.my_jingdong.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.eventbus.EventBus;

public class MyApp extends Application {
    private static MyApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();
       // myApp = this;
        Fresco.initialize(this);
        //MyUncaughtException.getInstance().initUncaughtException(this);

    }

  public static Context getAppContext() {
        return myApp;
    }

}
