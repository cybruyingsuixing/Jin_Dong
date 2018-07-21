package com.bw.my_jingdong.mvp.classes.model.classesmodel;

import com.bw.my_jingdong.mvp.classes.model.api.IClassesApi;
import com.bw.my_jingdong.mvp.classes.model.bean.SearchBean;
import com.bw.my_jingdong.utils.RetrofitManager;

import io.reactivex.Observable;

public class SearchModel {

    public Observable<SearchBean> getSearch(String keywords,int sort){
        return RetrofitManager.getDefault().create(IClassesApi.class).doSearch(keywords,sort);
    }
}
