package com.bw.my_jingdong.mvp.home.model;

import com.bw.my_jingdong.mvp.home.model.bean.AddCartBean;
import com.bw.my_jingdong.mvp.home.model.bean.CatagoryBean;
import com.bw.my_jingdong.mvp.home.model.bean.HomeBean;
import com.bw.my_jingdong.mvp.home.model.bean.ProductDetailsBean;
import com.bw.my_jingdong.utils.HttpConfig;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IHomeApi {

    @GET(HttpConfig.JD_HOME_LUNBO)
    Observable<HomeBean> home();

    @GET(HttpConfig.JD_HOME_SHOW)
    Observable<CatagoryBean> show();

    @GET(HttpConfig.JD_PRODUCT_DETAILS)
    Observable<ProductDetailsBean> product(@Query("pid")int pid);

    @GET(HttpConfig.JD_ADD_CART)
    Observable<AddCartBean> addCart(@Query("uid")int uid,@Query("pid")int pid);


}
