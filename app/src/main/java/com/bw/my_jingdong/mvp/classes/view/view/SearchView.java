package com.bw.my_jingdong.mvp.classes.view.view;

import com.bw.my_jingdong.base.IView;
import com.bw.my_jingdong.mvp.classes.model.bean.SearchBean;

public interface SearchView extends IView {

    void onSuccess(SearchBean searchBean);

    void onFaild(String error);
}
