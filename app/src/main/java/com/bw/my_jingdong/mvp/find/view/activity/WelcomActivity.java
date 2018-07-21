package com.bw.my_jingdong.mvp.find.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseActivity;
import com.bw.my_jingdong.mvp.find.model.bean.WelComeBean;
import com.bw.my_jingdong.mvp.find.presenter.WelcomePresenter;
import com.bw.my_jingdong.mvp.find.view.adapter.MyWelcomeAdapter;
import com.bw.my_jingdong.mvp.find.view.view.WelcomeView;
import com.bw.my_jingdong.utils.HttpConfig;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class WelcomActivity extends BaseActivity<WelcomePresenter> implements WelcomeView {


    private XRecyclerView xRecyclerView;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        presenter.getWelcome(HttpConfig.JD_WELCOME);
    }

    @Override
    protected void initViews() {
        xRecyclerView = findViewById(R.id.welcome_xrecyler);
    }

    @Override
    protected WelcomePresenter provide() {
        return new WelcomePresenter(this);
    }

    @Override
    protected int provId() {
        return R.layout.activity_welcom;
    }

    @Override
    public void onWelcomeSuccess(WelComeBean welComeBean) {
        List<WelComeBean.ResultsBean> data = welComeBean.getResults();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        MyWelcomeAdapter myWelcomeAdapter = new MyWelcomeAdapter(data,WelcomActivity.this);
        xRecyclerView.setAdapter(myWelcomeAdapter);
    }

    @Override
    public void onWelcomeFaild(String error) {
        Log.d("tag", "onWelcomeFaild: 错误"+error);
    }

    @Override
    public Context cotext() {
        return this;
    }
}
