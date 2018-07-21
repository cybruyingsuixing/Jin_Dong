package com.bw.my_jingdong.mvp.cart.view.activity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseActivity;
import com.bw.my_jingdong.mvp.cart.model.bean.GetAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.QueryOrderBean;
import com.bw.my_jingdong.mvp.cart.model.bean.UpdateOrderBean;
import com.bw.my_jingdong.mvp.cart.presenter.QueryOrderPresenter;
import com.bw.my_jingdong.mvp.cart.view.adapter.MyQueryOrderAdapter;
import com.bw.my_jingdong.mvp.cart.view.view.QueryOrderView;

import java.util.List;

public class QueryOrderActivity extends BaseActivity<QueryOrderPresenter> implements QueryOrderView {


    private RecyclerView recyclerView;
    private MyQueryOrderAdapter myQueryOrderAdapter;
    private String[] arr = {"未支付", "已支付", "取消支付"};
    private TextView tv0;
    private TextView tv1;
    private TextView tv2;

    private int status = 0;
    private TextView tv_address;


    @Override
    protected void initListener() {

        tv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 0;
                initData();
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 1;
                initData();
                Log.d("tag", "onUpdateOrderSuccess: " + status);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 2;
                initData();
            }
        });

        tv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(QueryOrderActivity.this, AddRessActivity.class);
                startActivity(it);
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        SharedPreferences mobile = QueryOrderActivity.this.getSharedPreferences("mobile", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = mobile.edit();
        int uid = mobile.getInt("uid", 0);
        presenter.getQueryOrder(uid, status);
        presenter.getDefault(uid);
    }

    @Override
    protected void initViews() {
        recyclerView = findViewById(R.id.query_order_recyler);
        tv_address = findViewById(R.id.query_order_address);
        tv0 = findViewById(R.id.query_tv0);
        tv1 = findViewById(R.id.query_tv1);
        tv2 = findViewById(R.id.query_tv2);
    }

    @Override
    protected QueryOrderPresenter provide() {
        return new QueryOrderPresenter(this);
    }

    @Override
    protected int provId() {
        return R.layout.activity_query_order;
    }

    @Override
    public void onQueryOrderSuccess(QueryOrderBean queryOrderBean) {
        final List<QueryOrderBean.DataBean> data = queryOrderBean.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QueryOrderActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        myQueryOrderAdapter = new MyQueryOrderAdapter(data);
        recyclerView.setAdapter(myQueryOrderAdapter);
        myQueryOrderAdapter.setOnClickListener(new MyQueryOrderAdapter.onClickListener() {

            @Override
            public void onClick(View v, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QueryOrderActivity.this);
                final int orderid = data.get(position).getOrderid();
                final int uid = data.get(position).getUid();
                status = data.get(position).getStatus();
                builder.setItems(arr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                presenter.getUpdateOrder(uid, 0, orderid);
                                break;
                            case 1:
                                presenter.getUpdateOrder(uid, 1, orderid);
                                break;
                            case 2:
                                presenter.getUpdateOrder(uid, 2, orderid);
                                break;
                        }
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public void onQueryOrderFaild(String error) {

    }

    @Override
    public void onUpdateOrderSuccess(UpdateOrderBean updateOrderBean) {
        Log.d("tag", "onUpdateOrderSuccess: " + updateOrderBean.getCode());
        initData();
        myQueryOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onUpdateOrderFaild(String error) {
        Log.d("tag", "onUpdateOrderFaild: " + error);
    }

    @Override
    public void onGetDefaultAddr(GetAddrBean getAddrBean) {
      String code = getAddrBean.getCode();
        Log.e("queryorder", "onGetDefaultAddr: "+code );
        String addr = getAddrBean.getData().getAddr();
        tv_address.setText("当前默认地址为："+addr+"");

    }

    @Override
    public void onGetDefaultFaild(String error) {
        Log.d("error", "onGetDefaultFaild: "+error);
    }

    @Override
    public Context cotext() {
        return QueryOrderActivity.this;
    }
}
