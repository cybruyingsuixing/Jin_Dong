package com.bw.my_jingdong.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment <P extends BasePresenter> extends Fragment{
    protected  P presenter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(provId(),null);
          presenter=provide();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews(view);
        initData();
        initListener();
    }

    protected abstract void initViews(View view);

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract P provide();

    protected abstract int provId();

    //懒加载模式
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (presenter == null) {
                return;
            }else{
                noVisible();
            }
            initData();
        }
    }

    protected void noVisible() {
    }

    //解决内存泄漏
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }
}
