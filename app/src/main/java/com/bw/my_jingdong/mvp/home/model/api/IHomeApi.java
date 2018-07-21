package com.bw.my_jingdong.mvp.home.model.api;

import com.bw.my_jingdong.mvp.cart.model.bean.CreateOrderBean;
import com.bw.my_jingdong.mvp.home.model.bean.AddCartBean;
import com.bw.my_jingdong.mvp.home.model.bean.CatagoryBean;
import com.bw.my_jingdong.mvp.home.model.bean.HomeBean;
import com.bw.my_jingdong.mvp.home.model.bean.ProductDetailsBean;
import com.bw.my_jingdong.mvp.home.model.bean.SpikBean;
import com.bw.my_jingdong.utils.HttpConfig;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IHomeApi {

    //首页的轮播图
    @GET(HttpConfig.JD_HOME_LUNBO)
    Observable<HomeBean> home();

    //首页的九宫格
    @GET(HttpConfig.JD_HOME_SHOW)
    Observable<CatagoryBean> show();

    //首页的商品展示
    @GET(HttpConfig.JD_PRODUCT_DETAILS)
    Observable<ProductDetailsBean> product(@Query("pid") int pid);

    //点击商品加入购物车
    @GET(HttpConfig.JD_ADD_CART)
    Observable<AddCartBean> addCart(@Query("uid") int uid, @Query("pid") int pid);

    //点击秒杀的商品加入购物车
    @GET(HttpConfig.JD_MIAOSHA_ADD_CART)
    Observable<SpikBean> addSpik(@Query("pid") int pid);

    //点击立即购买
    @GET(HttpConfig.JD_CREATE_ORDER)
    Observable<CreateOrderBean> goBuy(@Query("uid") int uid, @Query("price") float price);

}
