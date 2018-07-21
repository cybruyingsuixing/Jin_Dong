package com.bw.my_jingdong.mvp.my.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseFragment;
import com.bw.my_jingdong.mvp.cart.view.activity.QueryOrderActivity;
import com.bw.my_jingdong.mvp.my.view.activity.LoginActivity;
import com.bw.my_jingdong.mvp.my.view.activity.MyCenterActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import static android.content.Context.MODE_PRIVATE;

public class MyFragment extends Fragment implements View.OnClickListener {

    private SimpleDraweeView my_tiao;
    private TextView my_center;
    private String icon;
    private String name;
    private TextView tv_orde;
    private ImageView order_img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment, null);
        my_tiao = view.findViewById(R.id.my_tiao);
        my_tiao.setOnClickListener(this);
        my_center = view.findViewById(R.id.my_text);
        my_center.setOnClickListener(this);
        tv_orde = view.findViewById(R.id.my_order);
        tv_orde.setOnClickListener(this);
        order_img = view.findViewById(R.id.my_order_img);
        order_img.setOnClickListener(this);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences mobile = getActivity().getSharedPreferences("mobile", MODE_PRIVATE);
        boolean flag = mobile.getBoolean("flag", false);
        if (flag) {
            SharedPreferences.Editor edit = mobile.edit();
            name = mobile.getString("name", "");
            icon = mobile.getString("icon", null);
            my_center.setText(name);
            my_tiao.setImageURI(icon);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_tiao:
                SharedPreferences mobile = getActivity().getSharedPreferences("mobile", MODE_PRIVATE);
                int uid = mobile.getInt("uid", 0);
                if (uid==0) {
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
    }
}
