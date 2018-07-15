package com.bw.my_jingdong.mvp.cart.view.view;

import com.bw.my_jingdong.base.IView;
import com.bw.my_jingdong.mvp.cart.model.bean.CartBean;

public interface CartView extends IView {

    void onSuccess(CartBean cartBean);
    void onFaild(String error);

}
