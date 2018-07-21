package com.bw.my_jingdong.mvp.cart.view.view;

import com.bw.my_jingdong.base.IView;
import com.bw.my_jingdong.mvp.cart.model.bean.CreateOrderBean;
import com.bw.my_jingdong.mvp.cart.model.bean.GetAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.QueryOrderBean;
import com.bw.my_jingdong.mvp.cart.model.bean.UpdateOrderBean;

public interface QueryOrderView extends IView {

        void onQueryOrderSuccess(QueryOrderBean queryOrderBean);
        void onQueryOrderFaild(String error);
        void onUpdateOrderSuccess(UpdateOrderBean updateOrderBean);
        void onUpdateOrderFaild(String error);
        void onGetDefaultAddr(GetAddrBean getAddrBean);
        void onGetDefaultFaild(String error);
}
