package com.bw.my_jingdong.mvp.find.view.view;

import com.bw.my_jingdong.base.IView;
import com.bw.my_jingdong.mvp.find.model.bean.NewsBean;

public interface NewsView extends IView {

    void onNewsSuccess(NewsBean newsBean);
    void onNewsFaild(String error);

}
