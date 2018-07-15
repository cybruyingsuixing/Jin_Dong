package com.bw.my_jingdong.mvp.classes.model;


import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanLeft;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanRight;
import com.bw.my_jingdong.mvp.home.model.IHomeApi;
import com.bw.my_jingdong.mvp.home.model.bean.CatagoryBean;
import com.bw.my_jingdong.utils.RetrofitManager;

import io.reactivex.Observable;

public class ClassesModel {

    public Observable<ClassesBeanLeft> getLeft(){
        return RetrofitManager.getDefault().create(IClassesApi.class).doLeft();
    }

    public Observable<ClassesBeanRight> getRight(int cid){
        return RetrofitManager.getDefault().create(IClassesApi.class).doRight(cid);
    }

}
