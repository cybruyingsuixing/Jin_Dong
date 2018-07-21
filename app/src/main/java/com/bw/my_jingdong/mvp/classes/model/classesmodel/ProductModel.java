package com.bw.my_jingdong.mvp.classes.model.classesmodel;

import com.bw.my_jingdong.mvp.classes.model.api.IClassesApi;
import com.bw.my_jingdong.mvp.classes.model.bean.ProductBean;
import com.bw.my_jingdong.utils.RetrofitManager;

import io.reactivex.Observable;

public class ProductModel {

    public Observable<ProductBean> getProduct(int pscid,int sort) {
        return RetrofitManager.getDefault().create(IClassesApi.class).doProduct(pscid,sort);
    }
}
