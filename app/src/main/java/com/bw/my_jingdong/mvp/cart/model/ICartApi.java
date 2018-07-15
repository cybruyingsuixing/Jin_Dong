package com.bw.my_jingdong.mvp.cart.model;

import com.bw.my_jingdong.mvp.cart.model.bean.CartBean;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanLeft;
import com.bw.my_jingdong.utils.HttpConfig;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICartApi {

    @GET(HttpConfig.JD_SHOPPING_CART)
    Observable<CartBean> doCart(@Query("uid") int uid);

}
