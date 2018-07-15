package com.bw.my_jingdong.mvp.my.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseActivity;
import com.bw.my_jingdong.mvp.my.model.bean.LoginBean;
import com.bw.my_jingdong.mvp.my.model.bean.RegBean;
import com.bw.my_jingdong.mvp.my.presenter.MyLoginPresenter;
import com.bw.my_jingdong.mvp.my.view.fragment.MyFragment;
import com.bw.my_jingdong.mvp.my.view.view.MyLoginView;

public class LoginActivity extends BaseActivity<MyLoginPresenter> implements MyLoginView {


    private EditText login_mobile;
    private EditText login_password;
    private Button btn_login;
    private TextView tv_reg;
    private static final String TAG = "LoginActivity";
    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = login_mobile.getText().toString();
                String p = login_password.getText().toString();
                presenter.Login(m, p);

            }
        });
        //点击跳转注册页面
        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this,RegActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void initViews() {
        login_mobile = findViewById(R.id.login_mobile);
        login_password = findViewById(R.id.login_password);
        tv_reg = findViewById(R.id.tv_reg);
        btn_login = findViewById(R.id.btn_login);

    }

    @Override
    protected MyLoginPresenter provide() {
        return new MyLoginPresenter(this);
    }

    @Override
    protected int provId() {
        return R.layout.activity_login;
    }

    @Override
    public void onSuccess(final LoginBean loginBean) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                if (loginBean.getCode().equals("0")){
                    Intent it = new Intent(LoginActivity.this, MyFragment.class);
                    //传值
                    it.putExtra("mobile",loginBean.getData().getMobile());
                    it.putExtra("image",loginBean.getData().getIcon());
                    setResult(100,it);
                    finish();
                    //Intent it = new Intent(LoginActivity.this, MyFragment.class);
                    SharedPreferences mobile = LoginActivity.this.getSharedPreferences("mobile", MODE_PRIVATE);

                    SharedPreferences.Editor edit = mobile.edit();
                    edit.putString("name",loginBean.getData().getMobile());
                    edit.commit();
                    finish();
                }

            }
        });
    }
    @Override
    public void onFaild(final String error) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, "登录失败" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void RegSuccess(RegBean regBean) {

    }

    @Override
    public void RegFaild(String error) {

    }

    @Override
    public Context cotext() {
        return LoginActivity.this;
    }


}
