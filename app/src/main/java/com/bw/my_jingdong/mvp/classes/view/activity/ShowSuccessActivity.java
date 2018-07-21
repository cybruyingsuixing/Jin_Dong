package com.bw.my_jingdong.mvp.classes.view.activity;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseActivity;
import com.bw.my_jingdong.mvp.classes.model.bean.SearchBean;
import com.bw.my_jingdong.mvp.classes.persenter.SearchPresenter;
import com.bw.my_jingdong.mvp.classes.view.adapter.MySearchAdapter;
import com.bw.my_jingdong.mvp.classes.view.view.SearchView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ShowSuccessActivity extends BaseActivity<SearchPresenter> implements SearchView, View.OnClickListener {


    private XRecyclerView xRecyclerView;
    int sort = 0;
    private Button btn_moren;
    private Button btn_seller;
    private Button btn_price;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent it = getIntent();
        String name = it.getStringExtra("name");
        presenter.Search(name, sort);

    }

    @Override
    protected void initViews() {
        xRecyclerView = findViewById(R.id.show_success_xrecyler);
        btn_moren = findViewById(R.id.show_success_moren);
        btn_seller = findViewById(R.id.show_success_show_seller);
        btn_price = findViewById(R.id.show_success_price);
        btn_moren.setOnClickListener(this);
        btn_price.setOnClickListener(this);
        btn_seller.setOnClickListener(this);

    }

    @Override
    protected SearchPresenter provide() {
        return new SearchPresenter((SearchView) this);
    }

    @Override
    protected int provId() {
        return R.layout.activity_show_success;
    }

    @Override
    public void onSuccess(SearchBean searchBean) {
        List<SearchBean.DataBean> data = searchBean.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ShowSuccessActivity.this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(gridLayoutManager);
        MySearchAdapter mySearchAdapter = new MySearchAdapter(data);
        xRecyclerView.setAdapter(mySearchAdapter);
    }

    @Override
    public void onFaild(String error) {

    }

    @Override
    public Context cotext() {
        return ShowSuccessActivity.this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_success_moren:
                sort = 0;
                initData();
                break;
            case R.id.show_success_show_seller:
                sort = 1;
                Toast.makeText(ShowSuccessActivity.this,"dddd",Toast.LENGTH_SHORT).show();
                initData();
                break;
            case R.id.show_success_price:
                sort = 2;
                initData();
                break;
        }
    }
}
