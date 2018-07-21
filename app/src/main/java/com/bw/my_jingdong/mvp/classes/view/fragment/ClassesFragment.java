package com.bw.my_jingdong.mvp.classes.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseFragment;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanLeft;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanRight;
import com.bw.my_jingdong.mvp.classes.persenter.ClassesPresenter;
import com.bw.my_jingdong.mvp.classes.view.activity.SearchActivity;
import com.bw.my_jingdong.mvp.classes.view.adapter.MyLeftAdapter;
import com.bw.my_jingdong.mvp.classes.view.adapter.MyRightBigAdapter;
import com.bw.my_jingdong.mvp.classes.view.view.ClassesView;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class ClassesFragment extends BaseFragment<ClassesPresenter> implements ClassesView, View.OnClickListener {


    private ListView listView;
    private RecyclerView recyclerView;
    int cid = 1;
    private Button btn_sao;
    private EditText editText;
    private MyRightBigAdapter myRightBigAdapter;

    @Override
    protected void initViews(View view) {

        listView = view.findViewById(R.id.class_listview);
        recyclerView = view.findViewById(R.id.class_recyler);
        btn_sao = view.findViewById(R.id.classes_btn_sao);
        editText = view.findViewById(R.id.classes_ed_text);
        editText.setOnClickListener(this);
        btn_sao.setOnClickListener(this);
    }

    /*@Override
    public void onResume() {
        super.onResume();
        Log.i("zzz","onResume");
       *//* SharedPreferences goods = getActivity().getSharedPreferences("gs", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = goods.edit();
        int cidd = goods.getInt("cid", -1);
        Log.d("zzz", "onResume:***************** "+cidd);
        if (cidd!=-1){
            presenter.getLeftGoods();
            presenter.getRightGoods(cidd);
        }else {
            edit.clear();
            edit.commit();
        }*//*
    }*/
   /* @Subscribe
    public void getCid(int cid){
        Log.i("xxx",cid+"-----");
        presenter.getRightGoods(cid);
    }*/
    @Override
    protected void initData() {
        presenter.getLeftGoods();
       /* SharedPreferences goods = getActivity().getSharedPreferences("gs", Context.MODE_PRIVATE);
        int mCid = goods.getInt("cid", -1);
        if (mCid != -1) {
            presenter.getRightGoods(mCid);
            SharedPreferences.Editor edit = goods.edit();
            edit.clear();
            edit.commit();
        }else {

        }*/
        presenter.getRightGoods(1);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected ClassesPresenter provide() {
        return new ClassesPresenter((ClassesView) this);
    }

    @Override
    protected int provId() {
        return R.layout.classesfragment;
    }

    @Override
    public void onLeftSuccess(ClassesBeanLeft classesBeanLeft) {
        final List<ClassesBeanLeft.DataBean> data = classesBeanLeft.getData();
        MyLeftAdapter myLeftAdapter = new MyLeftAdapter(data);
        listView.setAdapter(myLeftAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int cid = data.get(position).getCid();
                presenter.getRightGoods(cid);
            }
        });

    }

    @Override
    public void onLeftFaild(String error) {

    }

    @Override
    public void onRightSuccess(ClassesBeanRight classesBeanRight) {
        List<ClassesBeanRight.DataBean> data = classesBeanRight.getData();
        myRightBigAdapter = new MyRightBigAdapter(data, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myRightBigAdapter);

    }

    @Override
    public void onRightFaild(String error) {

    }

    @Override
    public Context cotext() {
        return getContext();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_btn_sao:
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.classes_ed_text:
                Intent it = new Intent(getContext(), SearchActivity.class);
                startActivity(it);
                break;
        }
    }


}
