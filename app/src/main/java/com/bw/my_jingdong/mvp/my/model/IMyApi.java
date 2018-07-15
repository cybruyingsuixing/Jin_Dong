package com.bw.my_jingdong.mvp.my.model;

import com.bw.my_jingdong.mvp.my.model.bean.LoginBean;
import com.bw.my_jingdong.mvp.my.model.bean.RegBean;
import com.bw.my_jingdong.utils.HttpConfig;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMyApi {

   @GET(HttpConfig.JD_MY_CENTER_LOGIN)
   Observable<LoginBean> doLogin(@Query("mobile")String mobile,@Query("password")String password);

    @GET(HttpConfig.JD_MY_CENTER_REG)
   Observable<RegBean> doReg(@Query("mobile")String mobile,@Query("password")String password);

}
