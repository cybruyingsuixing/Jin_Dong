package com.bw.my_jingdong.mvp.find.model.findmodel;


import com.bw.my_jingdong.mvp.find.model.api.FindApi;
import com.bw.my_jingdong.mvp.find.model.bean.NewsBean;
import com.bw.my_jingdong.utils.RetrofitManager;

import io.reactivex.Observable;

public class NewsModel {

    public Observable<NewsBean> doNews(String url) {
        return RetrofitManager.getDefault().create(FindApi.class).news(url);
    }



}
