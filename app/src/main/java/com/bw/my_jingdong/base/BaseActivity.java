package com.bw.my_jingdong.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity <P extends BasePresenter> extends AppCompatActivity{

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provId());
        presenter=provide();
        initViews();
        initData();
        initListener();
    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initViews();

    protected abstract P provide();

    protected abstract int provId();

    //解决内存泄漏

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }
}
