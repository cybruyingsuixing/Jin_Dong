package com.bw.my_jingdong.mvp.home.view.view;

import com.bw.my_jingdong.base.IView;
import com.bw.my_jingdong.mvp.home.model.bean.AddCartBean;
import com.bw.my_jingdong.mvp.home.model.bean.CatagoryBean;
import com.bw.my_jingdong.mvp.home.model.bean.HomeBean;
import com.bw.my_jingdong.mvp.home.model.bean.ProductDetailsBean;

public interface HomeView extends IView{
    void onSuccess(HomeBean homeBean);
    void onFaild(String error);

   void onSuccessful(CatagoryBean catagoryBean);
    void onFailds(String error);

    void onProductSuccess(ProductDetailsBean productDetailsBean);
    void onProductFaild(String error);

    void onAddCart(AddCartBean addCartBean);
    void onAddCartFaild(String error);
}

