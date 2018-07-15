package com.bw.my_jingdong.mvp.cart.model;

import com.bw.my_jingdong.mvp.cart.model.bean.CartBean;
import com.bw.my_jingdong.utils.RetrofitManager;

import io.reactivex.Observable;

public class CartModel {

   public Observable<CartBean> doCart(int uid){
       return RetrofitManager.getDefault().create(ICartApi.class).doCart(uid);
   }

}
