package com.bw.my_jingdong.mvp.home.view.view;

import com.bw.my_jingdong.base.IView;
import com.bw.my_jingdong.mvp.cart.model.bean.CreateOrderBean;
import com.bw.my_jingdong.mvp.home.model.bean.AddCartBean;
import com.bw.my_jingdong.mvp.home.model.bean.CatagoryBean;
import com.bw.my_jingdong.mvp.home.model.bean.HomeBean;
import com.bw.my_jingdong.mvp.home.model.bean.ProductDetailsBean;
import com.bw.my_jingdong.mvp.home.model.bean.SpikBean;

public interface HomeView extends IView {
    //轮播图
    void onSuccess(HomeBean homeBean);

    void onFaild(String error);

    //九宫格
    void onSuccessful(CatagoryBean catagoryBean);

    void onFailds(String error);

    //秒杀+商品
    void onProductSuccess(ProductDetailsBean productDetailsBean);

    void onProductFaild(String error);

    //添加购物车
    void onAddCart(AddCartBean addCartBean);

    void onAddCartFaild(String error);

    //秒杀商品添加购物车
    void onSpikSuccess(SpikBean spikBean);

    void onSpikFaild(String error);

    //点击立即购买
    void onBuySuccess(CreateOrderBean createOrderBean);

    void onBuyFaild(String error);

}

