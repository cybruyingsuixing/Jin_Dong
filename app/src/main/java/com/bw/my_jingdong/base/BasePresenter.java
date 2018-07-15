package com.bw.my_jingdong.base;

import android.content.Context;

import com.bw.my_jingdong.app.MyApp;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter <V extends IView>{

    protected CompositeDisposable compositeDisposable=new CompositeDisposable();

    protected  V view;

    public BasePresenter(V view) {
        this.view = view;
        initModel();
    }

    protected abstract void initModel();
    //解决内存泄漏
    void onDestory(){
        view=null;
        compositeDisposable.clear();
    }

    //如果view.content（）环境变量为null的话就提供全局环境变量
   protected Context context() {
        if (view != null && view.cotext()==null) {
            return view.cotext();
        }
        return MyApp.getAppContext();
    }
}
