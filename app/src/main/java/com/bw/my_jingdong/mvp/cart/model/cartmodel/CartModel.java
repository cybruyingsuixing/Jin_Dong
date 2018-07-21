package com.bw.my_jingdong.mvp.cart.model.cartmodel;

import com.bw.my_jingdong.mvp.cart.model.api.ICartApi;
import com.bw.my_jingdong.mvp.cart.model.bean.CartBean;
import com.bw.my_jingdong.mvp.cart.model.bean.CartRemoveBean;
import com.bw.my_jingdong.mvp.cart.model.bean.CreateOrderBean;
import com.bw.my_jingdong.utils.RetrofitManager;

import io.reactivex.Observable;

public class CartModel {

    //查询购物车
   public Observable<CartBean> doCart(int uid){
       return RetrofitManager.getDefault().create(ICartApi.class).doCart(uid);
   }
  //删除购物车
   public Observable<CartRemoveBean> doRemoveGoods(int uid,int pid){
       return RetrofitManager.getDefault().create(ICartApi.class).doRemoveCart(uid,pid);
   }
   //创建订单
    public Observable<CreateOrderBean> createOrder(int uid, float price){
        return RetrofitManager.getDefault().create(ICartApi.class).doCreateOrder(uid,price);
    }

}
