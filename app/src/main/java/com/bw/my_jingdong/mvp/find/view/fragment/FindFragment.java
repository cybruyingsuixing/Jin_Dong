package com.bw.my_jingdong.mvp.find.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseFragment;
import com.bw.my_jingdong.mvp.classes.view.activity.SearchActivity;
import com.bw.my_jingdong.mvp.find.model.bean.NewsBean;
import com.bw.my_jingdong.mvp.find.presenter.NewsPresenter;
import com.bw.my_jingdong.mvp.find.view.activity.WelcomActivity;
import com.bw.my_jingdong.mvp.find.view.adapter.MyFindAdapter;
import com.bw.my_jingdong.mvp.find.view.view.NewsView;
import com.bw.my_jingdong.utils.HttpConfig;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class FindFragment extends BaseFragment<NewsPresenter> implements NewsView,View.OnClickListener{


    private XRecyclerView xRecyclerView;
    private Button btn_sao;
    private EditText editText;
    private Button btn_msg;
    private MyFindAdapter myFindAdapter;

    @Override
    protected void initViews(View view) {
        xRecyclerView = view.findViewById(R.id.find_xrecyler);
        btn_sao = view.findViewById(R.id.find_btn_sao);
        editText = view.findViewById(R.id.find_ed_text);
        btn_msg = view.findViewById(R.id.find_btn_msg);
        editText.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        presenter.getNews(HttpConfig.JD_FIND);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected NewsPresenter provide() {
        return new NewsPresenter(this);
    }

    @Override
    protected int provId() {
        return R.layout.findfragment;
    }

    @Override
    public void onNewsSuccess(NewsBean newsBean) {
        List<NewsBean.ResultBean.DataBean> data = newsBean.getResult().getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        xRecyclerView.setLayoutManager(linearLayoutManager);
        myFindAdapter = new MyFindAdapter(data);
        xRecyclerView.setAdapter(myFindAdapter);
       myFindAdapter.setOnClickListener(new MyFindAdapter.onClickListener() {
           @Override
           public void onClick(View v, int position) {
               Intent it = new Intent(getContext(),WelcomActivity.class);
               startActivity(it);
           }
       });
    }

    @Override
    public void onNewsFaild(String error) {

    }

    @Override
    public Context cotext() {
        return getActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.find_ed_text:
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
