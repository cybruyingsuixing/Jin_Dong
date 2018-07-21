package com.bw.my_jingdong.mvp.my.view.view;

import com.bw.my_jingdong.base.IView;
import com.bw.my_jingdong.mvp.my.model.bean.LoginBean;
import com.bw.my_jingdong.mvp.my.model.bean.UpdateAvatorBean;
import com.bw.my_jingdong.mvp.my.model.bean.UpdateNameBean;
import com.bw.my_jingdong.mvp.my.model.bean.UserInforMationBean;

public interface MyCenterView extends IView{

    void  onNameSuccess(UpdateNameBean updateNameBean);
    void onNameFaild(String error);

    void  onAvatorSuccess(UpdateAvatorBean updateAvatorBean);
    void onAvatorFaild(String error);

    void onUserInforSuccess(UserInforMationBean userInforMationBean);
    void onUserFaild(String error);
}
