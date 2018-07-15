package com.bw.my_jingdong.mvp.my.view.view;

import com.bw.my_jingdong.base.IView;
import com.bw.my_jingdong.mvp.my.model.bean.LoginBean;
import com.bw.my_jingdong.mvp.my.model.bean.RegBean;

public interface MyLoginView extends IView {

    void  onSuccess(LoginBean loginBean);
    void onFaild(String error);

    void RegSuccess(RegBean regBean);
    void RegFaild(String error);
}
