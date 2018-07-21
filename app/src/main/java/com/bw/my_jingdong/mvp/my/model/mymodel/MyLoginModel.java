package com.bw.my_jingdong.mvp.my.model.mymodel;

import com.bw.my_jingdong.mvp.my.model.api.IMyApi;
import com.bw.my_jingdong.mvp.my.model.bean.LoginBean;
import com.bw.my_jingdong.mvp.my.model.bean.RegBean;
import com.bw.my_jingdong.utils.RetrofitManager;

import io.reactivex.Observable;

public class MyLoginModel {

    public Observable<LoginBean> getLogin(String mobile,String password){

        return RetrofitManager.getDefault().create(IMyApi.class).doLogin(mobile,password);
    }

    public Observable<RegBean> getReg(String mobile, String password){

        return RetrofitManager.getDefault().create(IMyApi.class).doReg(mobile,password);
    }


}
