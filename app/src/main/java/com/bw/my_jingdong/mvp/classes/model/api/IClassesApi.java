package com.bw.my_jingdong.mvp.classes.model.api;

import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanLeft;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanRight;
import com.bw.my_jingdong.mvp.classes.model.bean.ProductBean;
import com.bw.my_jingdong.mvp.classes.model.bean.SearchBean;
import com.bw.my_jingdong.utils.HttpConfig;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IClassesApi {

    //分类页面的左边
    @GET(HttpConfig.JD_CLASSES_LEFT)
    Observable<ClassesBeanLeft> doLeft();
   //分类页面的右边
    @GET(HttpConfig.JD_CLASSES_RIGHT)
    Observable<ClassesBeanRight> doRight(@Query("cid") int cid);
    //搜索
    @GET(HttpConfig.JD_SEARCH)
    Observable<SearchBean> doSearch(@Query("keywords")String keywords,@Query("sort")int sort);

    //商品详情
    @GET(HttpConfig.JD_CLASSES_PRODUCT)
    Observable<ProductBean> doProduct(@Query("pscid")int pscid,@Query("sort")int sort);

}
