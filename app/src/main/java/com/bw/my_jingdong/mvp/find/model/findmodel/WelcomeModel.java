package com.bw.my_jingdong.mvp.find.model.findmodel;

import com.bw.my_jingdong.mvp.find.model.api.FindApi;
import com.bw.my_jingdong.mvp.find.model.bean.WelComeBean;
import com.bw.my_jingdong.utils.RetrofitManager;

import io.reactivex.Observable;

public class WelcomeModel {

    public Observable<WelComeBean> doWelcome(String url) {
        return RetrofitManager.getDefault().create(FindApi.class).welcome(url);
    }
}
