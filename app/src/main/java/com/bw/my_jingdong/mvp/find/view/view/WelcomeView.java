package com.bw.my_jingdong.mvp.find.view.view;

import com.bw.my_jingdong.base.IView;
import com.bw.my_jingdong.mvp.find.model.bean.WelComeBean;

public interface WelcomeView extends IView {

    void onWelcomeSuccess(WelComeBean welComeBean);
    void onWelcomeFaild(String error);
}
