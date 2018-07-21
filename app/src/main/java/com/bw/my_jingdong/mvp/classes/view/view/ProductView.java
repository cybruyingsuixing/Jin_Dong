package com.bw.my_jingdong.mvp.classes.view.view;

import com.bw.my_jingdong.base.IView;
import com.bw.my_jingdong.mvp.classes.model.bean.ProductBean;

public interface ProductView extends IView {
    void onSuccess(ProductBean productBean);
    void onFaild(String error);
}
