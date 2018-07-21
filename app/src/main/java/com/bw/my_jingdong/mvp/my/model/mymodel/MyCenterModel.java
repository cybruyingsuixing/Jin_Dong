package com.bw.my_jingdong.mvp.my.model.mymodel;

import com.bw.my_jingdong.mvp.my.model.api.IMyApi;
import com.bw.my_jingdong.mvp.my.model.bean.UpdateAvatorBean;
import com.bw.my_jingdong.mvp.my.model.bean.UpdateNameBean;
import com.bw.my_jingdong.mvp.my.model.bean.UserInforMationBean;
import com.bw.my_jingdong.utils.RetrofitManager;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

public class MyCenterModel {

    public Observable<UpdateNameBean> getUpdateName(int uid){
        return RetrofitManager.getDefault().create(IMyApi.class).doUpdate_Name(uid);
    }
   public Observable<UpdateAvatorBean> getUpdateAvator(int uid, MultipartBody.Part file){
        return RetrofitManager.getDefault().create(IMyApi.class).doUpdate_Avator(uid,file);
    }

    public Observable<UserInforMationBean> getUserInfor(int uid){
        return RetrofitManager.getDefault().create(IMyApi.class).doUserInfor(uid);
    }
}
