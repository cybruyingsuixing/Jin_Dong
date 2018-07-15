package com.bw.my_jingdong.mvp.home.model;

import com.bw.my_jingdong.mvp.home.model.bean.AddCartBean;
import com.bw.my_jingdong.mvp.home.model.bean.CatagoryBean;
import com.bw.my_jingdong.mvp.home.model.bean.HomeBean;
import com.bw.my_jingdong.mvp.home.model.bean.ProductDetailsBean;
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


}


