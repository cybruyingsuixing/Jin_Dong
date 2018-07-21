package com.bw.my_jingdong.mvp.cart.model.cartmodel;

import com.bw.my_jingdong.mvp.cart.model.api.ICartApi;
import com.bw.my_jingdong.mvp.cart.model.bean.GetAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.QueryOrderBean;
import com.bw.my_jingdong.mvp.cart.model.bean.UpdateOrderBean;
import com.bw.my_jingdong.utils.RetrofitManager;

import io.reactivex.Observable;

public class QueryOrderModel {

    public Observable<QueryOrderBean> queryOrder(int uid,int status) {
        return RetrofitManager.getDefault().create(ICartApi.class).doQueryOrder(uid,status);
    }

    //修改订单
    public Observable<UpdateOrderBean> updateOrder(int uid,int status,int orderId){
        return RetrofitManager.getDefault().create(ICartApi.class).doUpdateOrder(uid,status,orderId);
    }

    public Observable<GetAddrBean> doGetAddr(int uid){
        return RetrofitManager.getDefault().create(ICartApi.class).doGetDefaultAddr(uid);
    }
}
