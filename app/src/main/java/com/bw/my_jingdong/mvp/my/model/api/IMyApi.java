package com.bw.my_jingdong.mvp.my.model.api;

import com.bw.my_jingdong.mvp.my.model.bean.LoginBean;
import com.bw.my_jingdong.mvp.my.model.bean.RegBean;
import com.bw.my_jingdong.mvp.my.model.bean.UpdateAvatorBean;
import com.bw.my_jingdong.mvp.my.model.bean.UpdateNameBean;
import com.bw.my_jingdong.mvp.my.model.bean.UserInforMationBean;
import com.bw.my_jingdong.utils.HttpConfig;


import java.io.File;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IMyApi {

    //登录
   @GET(HttpConfig.JD_MY_CENTER_LOGIN)
   Observable<LoginBean> doLogin(@Query("mobile")String mobile,@Query("password")String password);
//注册
    @GET(HttpConfig.JD_MY_CENTER_REG)
   Observable<RegBean> doReg(@Query("mobile")String mobile,@Query("password")String password);
//修改昵称
    @GET(HttpConfig.JD_UPDATE_NAME)
    Observable<UpdateNameBean> doUpdate_Name(@Query("uid")int uid);
 //上传头像
   @Multipart
  @POST(HttpConfig.JD_UPDATE_AVATOR)
  Observable<UpdateAvatorBean> doUpdate_Avator(@Query("uid")int uid, @Part MultipartBody.Part file);
//获取用户信息
    @GET(HttpConfig.JD_USER_INFORMATION)
    Observable<UserInforMationBean> doUserInfor(@Query("uid")int uid);

}
