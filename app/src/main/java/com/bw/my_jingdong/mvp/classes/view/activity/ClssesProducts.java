package com.bw.my_jingdong.mvp.classes.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseActivity;
import com.bw.my_jingdong.mvp.classes.model.bean.ProductBean;
import com.bw.my_jingdong.mvp.classes.persenter.ProductPresenter;
import com.bw.my_jingdong.mvp.classes.view.adapter.MyClassProductAdapter;
import com.bw.my_jingdong.mvp.classes.view.view.ProductView;
import com.bw.my_jingdong.mvp.home.view.activity.ProductDetailsActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ClssesProducts extends BaseActivity<ProductPresenter> implements ProductView, View.OnClickListener {

    int sort = 0;
    private XRecyclerView xRecyclerView;
    private Button btn_moren;
    private Button btn_seller;
    private Button btn_price;
    private MyClassProductAdapter myClassProductAdapter;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent it = getIntent();
        int pscid = it.getIntExtra("pscid", 1);
        presenter.getProduct(pscid, sort);
    }

    @Override
    protected void initViews() {
        xRecyclerView = findViewById(R.id.classes_product_xrecyler);
        btn_moren = findViewById(R.id.classes_product_moren);
        btn_seller = findViewById(R.id.classes_product_seller);
        btn_price = findViewById(R.id.classes_product_price);
        btn_moren.setOnClickListener(this);
        btn_seller.setOnClickListener(this);
        btn_price.setOnClickListener(this);
    }

    @Override
    protected ProductPresenter provide() {
        return new ProductPresenter(this);
    }

    @Override
    protected int provId() {
        return R.layout.activity_clsses_products;
    }

    @Override
    public void onSuccess(ProductBean productBean) {

        final List<ProductBean.DataBean> data = productBean.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ClssesProducts.this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(gridLayoutManager);
      /*  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ClssesProducts.this);
        xRecyclerView.setLayoutManager(linearLayoutManager);*/
        myClassProductAdapter = new MyClassProductAdapter(data);
        xRecyclerView.setAdapter(myClassProductAdapter);
        myClassProductAdapter.setOnClickListener(new MyClassProductAdapter.onClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent it = new Intent(ClssesProducts.this, ProductDetailsActivity.class);
                int pid = data.get(position).getPid();
                it.putExtra("pid",pid);
                startActivity(it);
            }
        });
    }

    @Override
    public void onFaild(String error) {

    }

    @Override
    public Context cotext() {
        return ClssesProducts.this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.classes_product_moren:
                sort = 0;
                initData();
                break;
            case R.id.classes_product_seller:
                sort = 1;
                initData();
                break;
            case R.id.classes_product_price:
                sort = 2;
                initData();
                break;
        }
    }
}
