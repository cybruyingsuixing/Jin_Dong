package com.bw.my_jingdong.mvp.my.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseActivity;
import com.bw.my_jingdong.mvp.my.model.bean.LoginBean;
import com.bw.my_jingdong.mvp.my.model.bean.RegBean;
import com.bw.my_jingdong.mvp.my.presenter.MyLoginPresenter;
import com.bw.my_jingdong.mvp.my.view.view.MyLoginView;

public class RegActivity extends BaseActivity<MyLoginPresenter> implements MyLoginView {


    private EditText reg_password;
    private EditText reg_mobile;
    private Button btn_reg;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = reg_mobile.getText().toString();
                String p = reg_password.getText().toString();
                presenter.Reg(m,p);
            }
        });
    }

    @Override
    protected void initViews() {
        reg_mobile = findViewById(R.id.reg_mobile);
        reg_password = findViewById(R.id.reg_password);
        btn_reg = findViewById(R.id.btn_reg);
    }

    @Override
    protected MyLoginPresenter provide() {
        return new MyLoginPresenter((MyLoginView) this);
    }

    @Override
    protected int provId() {
        return R.layout.activity_reg;
    }

    @Override
    public void onSuccess(LoginBean loginBean) {

    }

    @Override
    public void onFaild(String error) {

    }

    @Override
    public void RegSuccess(RegBean regBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegActivity.this, "注册成功" , Toast.LENGTH_SHORT).show();
               // Intent it = new Intent(RegActivity.this, LoginActivity.class);
                //startActivity(it);
                finish();
            }
        });
    }

    @Override
    public void RegFaild(final String error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegActivity.this, "注册失败"+error , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public Context cotext() {
        return RegActivity.this;
    }
}
