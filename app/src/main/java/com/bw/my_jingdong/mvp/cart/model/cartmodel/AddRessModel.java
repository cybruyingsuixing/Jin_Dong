package com.bw.my_jingdong.mvp.cart.model.cartmodel;

import com.bw.my_jingdong.mvp.cart.model.api.ICartApi;
import com.bw.my_jingdong.mvp.cart.model.bean.AddRessBean;
import com.bw.my_jingdong.mvp.cart.model.bean.DefaultAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.GetAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.QueryAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.UpdateAddrBean;
import com.bw.my_jingdong.utils.RetrofitManager;

import io.reactivex.Observable;

public class AddRessModel {


    public Observable<AddRessBean> doAddRess(int uid, String addr, String mobile, String name) {
        return RetrofitManager.getDefault().create(ICartApi.class).doAddRess(uid, addr, mobile, name);
    }

    public Observable<QueryAddrBean> doQueryAddr(int uid) {
        return RetrofitManager.getDefault().create(ICartApi.class).doQueryAddr(uid);
    }

    public Observable<DefaultAddrBean> doDefault(int uid, int addrid, int status) {
        return RetrofitManager.getDefault().create(ICartApi.class).doDefaultAddr(uid, addrid, status);
    }

   public Observable<UpdateAddrBean> doUppdateAddr(int uid,int addrid,String addr){
        return RetrofitManager.getDefault().create(ICartApi.class).doUpdateAddr(uid, addrid,addr);
   }



}
