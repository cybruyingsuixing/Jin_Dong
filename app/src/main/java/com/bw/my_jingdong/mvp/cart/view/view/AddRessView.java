package com.bw.my_jingdong.mvp.cart.view.view;

import com.bw.my_jingdong.base.IView;
import com.bw.my_jingdong.mvp.cart.model.bean.AddRessBean;
import com.bw.my_jingdong.mvp.cart.model.bean.DefaultAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.GetAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.QueryAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.UpdateAddrBean;

public interface AddRessView extends IView {

    void onAddRessSuccess(AddRessBean addRessBean);
    void onAddRessFaild(String error);
    void onQueryAddr(QueryAddrBean queryAddrBean);
    void onQueryAddrFaild(String error);
    void OnDefaultAddrSuccess(DefaultAddrBean defaultAddrBean);
    void onDefaultAddrFaild(String error);
    void onUpdateAddrSuccess(UpdateAddrBean updateAddrBean);
    void onUpdateAddrFaild(String error);
}
