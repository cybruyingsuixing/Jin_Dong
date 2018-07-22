package com.bw.my_jingdong.mvp.my.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseFragment;
import com.bw.my_jingdong.mvp.cart.view.activity.QueryOrderActivity;
import com.bw.my_jingdong.mvp.my.model.bean.UpdateAvatorBean;
import com.bw.my_jingdong.mvp.my.model.bean.UpdateNameBean;
import com.bw.my_jingdong.mvp.my.model.bean.UserInforMationBean;
import com.bw.my_jingdong.mvp.my.presenter.MyCenterPresenter;
import com.bw.my_jingdong.mvp.my.view.activity.LoginActivity;
import com.bw.my_jingdong.mvp.my.view.activity.MyCenterActivity;
import com.bw.my_jingdong.mvp.my.view.view.MyCenterView;
import com.facebook.drawee.view.SimpleDraweeView;

import static android.content.Context.MODE_PRIVATE;

public class MyFragment extends BaseFragment<MyCenterPresenter> implements View.OnClickListener,MyCenterView {

    private SimpleDraweeView my_tiao;
    private TextView my_center;
    private String icon;
    private String name;
    private TextView tv_orde;
    private ImageView order_img;
    private ImageView img_wait_buy;
    private SharedPreferences sharedPreferences;
    private int uid;

    @Override
    protected void initViews(View view) {
        my_tiao = view.findViewById(R.id.my_tiao);
        my_tiao.setOnClickListener(this);
        my_center = view.findViewById(R.id.my_text);
        my_center.setOnClickListener(this);
        tv_orde = view.findViewById(R.id.my_order);
        tv_orde.setOnClickListener(this);
        order_img = view.findViewById(R.id.my_order_img);
        order_img.setOnClickListener(this);
        img_wait_buy = view.findViewById(R.id.my_fragment_wait_buy);
        img_wait_buy.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected MyCenterPresenter provide() {
        return new MyCenterPresenter(this);
    }

    @Override
    protected int provId() {
        return R.layout.myfragment;
    }


    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences = getActivity().getSharedPreferences("mobile", MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean("flag", false);
        if (flag) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            name = sharedPreferences.getString("name", "");
            icon = sharedPreferences.getString("icon", null);
            my_center.setText(name);
            my_tiao.setImageURI(icon);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_tiao:
                SharedPreferences mobile = getActivity().getSharedPreferences("mobile", MODE_PRIVATE);
                 uid = mobile.getInt("uid", 0);
                if (uid == 0) {
                    Intent it = new Intent(getContext(), LoginActivity.class);
                    startActivityForResult(it, 100);
                } else {
                    Intent intent = new Intent(getContext(), MyCenterActivity.class);
                    startActivityForResult(intent, 500);
                }
                break;
            case R.id.my_text:
                Intent intent = new Intent(getContext(), MyCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.my_order:
                Intent in = new Intent(getContext(), QueryOrderActivity.class);
                startActivity(in);
                break;
            case R.id.my_order_img:
                Intent intn = new Intent(getContext(), QueryOrderActivity.class);
                startActivity(intn);
                break;
            case R.id.my_fragment_wait_buy:
                Intent intent1 = new Intent(getContext(), QueryOrderActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 500 && resultCode == 500) {
            my_tiao.setImageURI("");
            my_center.setText("个人中心");
            icon = null;
        }
        if (requestCode==500 && resultCode==100){
            Log.e("TAG", "onActivityResult: 111111");
            SharedPreferences mobile = getActivity().getSharedPreferences("mobile", Context.MODE_PRIVATE);
            int u = mobile.getInt("uid", 0);
            presenter.userInfor(u);
        }
    }

    @Override
    public void onNameSuccess(UpdateNameBean updateNameBean) {

    }

    @Override
    public void onNameFaild(String error) {

    }

    @Override
    public void onAvatorSuccess(UpdateAvatorBean updateAvatorBean) {

    }

    @Override
    public void onAvatorFaild(String error) {

    }

    @Override
    public void onUserInforSuccess(UserInforMationBean userInforMationBean) {
        String code = userInforMationBean.getCode();
        String msg = userInforMationBean.getMsg();
        if (code.equals("0")){
            Log.e("TAG", "onActivityResult: 111111"+msg);
            String image = userInforMationBean.getData().getIcon();
            my_tiao.setImageURI(image);
        }else {
            Log.e("TAG", "onActivityResult: 111111"+msg);
        }
    }

    @Override
    public void onUserFaild(String error) {
        Log.e("TAG", "onActivityResult: 111111"+error);
    }

    @Override
    public Context cotext() {
        return getContext();
    }
}
