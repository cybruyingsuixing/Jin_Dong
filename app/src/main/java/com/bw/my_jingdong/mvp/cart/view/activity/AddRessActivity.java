package com.bw.my_jingdong.mvp.cart.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseActivity;
import com.bw.my_jingdong.mvp.cart.model.bean.AddRessBean;
import com.bw.my_jingdong.mvp.cart.model.bean.DefaultAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.GetAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.QueryAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.UpdateAddrBean;
import com.bw.my_jingdong.mvp.cart.presenter.AddRessPresenter;
import com.bw.my_jingdong.mvp.cart.view.adapter.AddrAdapter;
import com.bw.my_jingdong.mvp.cart.view.view.AddRessView;
import com.bw.my_jingdong.mvp.home.view.activity.ProductDetailsActivity;

import java.util.List;

public class AddRessActivity extends BaseActivity<AddRessPresenter> implements AddRessView {


    private RecyclerView recyclerView;
    private Button btn_address;
    int uid;
    private AddrAdapter addrAdapter;

    @Override
    protected void initListener() {
        btn_address.setOnClickListener(new View.OnClickListener() {

            private EditText et_name, et_mobile, et_addr;

            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = LayoutInflater.from(AddRessActivity.this);
                View view = layoutInflater.inflate(R.layout.add_address, null);
                et_name = view.findViewById(R.id.add_address_name);
                et_mobile = view.findViewById(R.id.add_address_mobile);
                et_addr = view.findViewById(R.id.add_address_addr);

                AlertDialog.Builder builder = new AlertDialog.Builder(AddRessActivity.this);
                builder.setTitle("添加地址");
                builder.setView(view);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String addr = et_addr.getText().toString();
                        String mobile = et_mobile.getText().toString();
                        String name = et_name.getText().toString();
                        presenter.arrRess(uid, addr, mobile, name);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    protected void initData() {
        presenter.queryAddr(uid);
    }

    @Override
    protected void initViews() {
        recyclerView = findViewById(R.id.add_address_recyler);
        btn_address = findViewById(R.id.add_shop_address);
        SharedPreferences p = AddRessActivity.this.getSharedPreferences("mobile", MODE_PRIVATE);
        SharedPreferences.Editor edit = p.edit();
        uid = p.getInt("uid", 0);
    }

    @Override
    protected AddRessPresenter provide() {
        return new AddRessPresenter(this);
    }

    @Override
    protected int provId() {
        return R.layout.activity_add_ress;
    }

    @Override
    public void onAddRessSuccess(AddRessBean addRessBean) {
        presenter.queryAddr(uid);
        addrAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAddRessFaild(String error) {
        Log.d("tag", "onAddRessFaild: 999999999999999999999" + error);
    }

    @Override
    public void onQueryAddr(QueryAddrBean queryAddrBean) {
        final List<QueryAddrBean.DataBean> data = queryAddrBean.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddRessActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        addrAdapter = new AddrAdapter(data);
        recyclerView.setAdapter(addrAdapter);
        addrAdapter.setOnClickListener(new AddrAdapter.onClickListener() {
            @Override
            public void onClick(View v, int position) {
                int uid = data.get(position).getUid();
                int status = data.get(position).getStatus();
                int addrid = data.get(position).getAddrid();
                presenter.Default(uid, addrid, status);
            }

            private EditText et_name, et_mobile, et_addr;

            @Override
            public void onUpdateClick(View v, int position) {

                LayoutInflater layoutInflater = LayoutInflater.from(AddRessActivity.this);
                View view = layoutInflater.inflate(R.layout.add_address, null);
                et_name = view.findViewById(R.id.add_address_name);
                et_mobile = view.findViewById(R.id.add_address_mobile);
                et_addr = view.findViewById(R.id.add_address_addr);
                final int addrid = data.get(position).getAddrid();
                AlertDialog.Builder builder = new AlertDialog.Builder(AddRessActivity.this);
                builder.setTitle("修改地址");
                builder.setView(view);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String addr = et_addr.getText().toString();
                        String mobile = et_mobile.getText().toString();
                        String name = et_name.getText().toString();
                        presenter.updateAddr(uid, addrid,addr);
                        Log.d("tag", "update:8888888888888888888 " + name + mobile + addr);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public void onQueryAddrFaild(String error) {

    }

    @Override
    public void OnDefaultAddrSuccess(DefaultAddrBean defaultAddrBean) {
        Intent it = new Intent(AddRessActivity.this, QueryOrderActivity.class);
        startActivity(it);
        finish();
    }

    @Override
    public void onDefaultAddrFaild(String error) {

    }

    @Override
    public void onUpdateAddrSuccess(UpdateAddrBean updateAddrBean) {
        String code = updateAddrBean.getCode();
        Log.d("update", "onUpdateAddrSuccess: "+updateAddrBean.getCode());
        if (code.equals("0")){
            Toast.makeText(AddRessActivity.this,"成功",Toast.LENGTH_SHORT).show();
            initData();
        }
    }

    @Override
    public void onUpdateAddrFaild(String error) {
        Log.d("update", "onUpdateAddrSuccess: "+error);
    }


    @Override
    public Context cotext() {
        return AddRessActivity.this;
    }
}
