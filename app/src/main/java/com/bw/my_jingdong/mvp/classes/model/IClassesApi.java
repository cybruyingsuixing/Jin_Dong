package com.bw.my_jingdong.mvp.classes.model;

import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanLeft;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanRight;
import com.bw.my_jingdong.utils.HttpConfig;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IClassesApi {

    @GET(HttpConfig.JD_CLASSES_LEFT)
    Observable<ClassesBeanLeft> doLeft();

    @GET(HttpConfig.JD_CLASSES_RIGHT)
    Observable<ClassesBeanRight> doRight(@Query("cid") int cid);

}
