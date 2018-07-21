package com.bw.my_jingdong.mvp.cart.model.api;

import com.bw.my_jingdong.mvp.cart.model.bean.AddRessBean;
import com.bw.my_jingdong.mvp.cart.model.bean.CartBean;
import com.bw.my_jingdong.mvp.cart.model.bean.CartRemoveBean;
import com.bw.my_jingdong.mvp.cart.model.bean.CreateOrderBean;
import com.bw.my_jingdong.mvp.cart.model.bean.DefaultAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.GetAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.QueryAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.QueryOrderBean;
import com.bw.my_jingdong.mvp.cart.model.bean.UpdateAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.UpdateOrderBean;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanLeft;
import com.bw.my_jingdong.utils.HttpConfig;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICartApi {

    //查询购物车
    @GET(HttpConfig.JD_SHOPPING_CART)
    Observable<CartBean> doCart(@Query("uid") int uid);

    //删除购物车 商品
    @GET(HttpConfig.JD_CART_REMOVE_GOODS)
    Observable<CartRemoveBean> doRemoveCart(@Query("uid") int uid, @Query("pid") int pid);

    //创建订单
    @GET(HttpConfig.JD_CREATE_ORDER)
    Observable<CreateOrderBean> doCreateOrder(@Query("uid") int uid, @Query("price") float price);

    //查询订单
    @GET(HttpConfig.JD_QUERY_ORDER)
    Observable<QueryOrderBean> doQueryOrder(@Query("uid") int uid, @Query("status") int status);

    //修改订单
    @GET(HttpConfig.JD_UPDATE_ORDER)
    Observable<UpdateOrderBean> doUpdateOrder(@Query("uid") int uid, @Query("status") int status, @Query("orderId") int orderId);

    //添加地址
    @GET(HttpConfig.JD_ADD_ADDRESS)
    Observable<AddRessBean> doAddRess(@Query("uid") int uid, @Query("addr") String addr, @Query("mobile") String mobile, @Query("name") String name);

    //查询地址
    @GET(HttpConfig.JD_QUERY_ADDRESS)
    Observable<QueryAddrBean> doQueryAddr(@Query("uid")int uid);

    //设置默认地址
     @GET(HttpConfig.JD_DEFAULT_ADDR)
    Observable<DefaultAddrBean> doDefaultAddr(@Query("uid")int uid,@Query("addrid")int addrid,@Query("status")int status);
   //获取默认地址
    @GET(HttpConfig.JD_GET_DEFAULT_ADDR)
    Observable<GetAddrBean> doGetDefaultAddr(@Query("uid")int uid);

    @GET(HttpConfig.JD_UPDATE_ADDR)
    Observable<UpdateAddrBean> doUpdateAddr(@Query("uid")int uid,@Query("addrid")int addrid,@Query("addr")String addr);
}
