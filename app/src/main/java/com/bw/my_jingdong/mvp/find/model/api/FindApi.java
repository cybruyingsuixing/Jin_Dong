package com.bw.my_jingdong.mvp.find.model.api;

import com.bw.my_jingdong.mvp.find.model.bean.NewsBean;
import com.bw.my_jingdong.mvp.find.model.bean.WelComeBean;
import com.bw.my_jingdong.utils.HttpConfig;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface FindApi {

    @GET("")
    Observable<NewsBean> news(@Url String url);

    @GET("")
    Observable<WelComeBean> welcome(@Url String url);
}
