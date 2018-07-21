package com.bw.my_jingdong.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit工具类
 */
public class RetrofitManager {

    private static final String BAS_URL =HttpConfig.BASE_URL;
    private Retrofit retrofit;


    private static class SingleHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager(BAS_URL);
    }

    public static RetrofitManager getDefault() {
        return SingleHolder.INSTANCE;
    }


    public RetrofitManager(String basUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(basUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildOkHttpClient())
                .build();
    }

    private OkHttpClient buildOkHttpClient() {
        //拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}
