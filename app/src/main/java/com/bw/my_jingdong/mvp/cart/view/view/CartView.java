package com.bw.my_jingdong.mvp.cart.view.view;

import com.bw.my_jingdong.base.IView;
import com.bw.my_jingdong.mvp.cart.model.bean.CartBean;
import com.bw.my_jingdong.mvp.cart.model.bean.CartRemoveBean;
import com.bw.my_jingdong.mvp.cart.model.bean.CreateOrderBean;

public interface CartView extends IView {

    void onSuccess(CartBean cartBean);
    void onFaild(String error);

    void onRemoveSuccess(CartRemoveBean cartRemoveBean);
    void onRemoveFaild(String error);

    void onCreateOderSuccess(CreateOrderBean createOrderBean);
    void onCreateFaild(String error);
}
