package com.bw.my_jingdong.mvp.home.model.homemodel;

import com.bw.my_jingdong.mvp.cart.model.bean.CreateOrderBean;
import com.bw.my_jingdong.mvp.home.model.api.IHomeApi;
import com.bw.my_jingdong.mvp.home.model.bean.AddCartBean;
import com.bw.my_jingdong.mvp.home.model.bean.CatagoryBean;
import com.bw.my_jingdong.mvp.home.model.bean.HomeBean;
import com.bw.my_jingdong.mvp.home.model.bean.ProductDetailsBean;
import com.bw.my_jingdong.mvp.home.model.bean.SpikBean;
import com.bw.my_jingdong.utils.RetrofitManager;

import io.reactivex.Observable;

public class HomeModel {

   public Observable<HomeBean> doHome(){
       return RetrofitManager.getDefault().create(IHomeApi.class).home();
   }

    public Observable<CatagoryBean> doShow(){
        return RetrofitManager.getDefault().create(IHomeApi.class).show();
    }

    public Observable<ProductDetailsBean> doProduct(int pid){
       return RetrofitManager.getDefault().create(IHomeApi.class).product(pid);
    }

    //点击电脑加入购物车
   public Observable<AddCartBean> doAddCart(int uid,int pid){
       return RetrofitManager.getDefault().create(IHomeApi.class).addCart(uid, pid);
   }

   //点击秒杀加入购物车
    public Observable<SpikBean> doSpik(int pid){
        return RetrofitManager.getDefault().create(IHomeApi.class).addSpik(pid);
    }
    //点击立即购买
    public Observable<CreateOrderBean> doBuy(int uid,float price){
       return RetrofitManager.getDefault().create(IHomeApi.class).goBuy(uid, price);
    }

}


