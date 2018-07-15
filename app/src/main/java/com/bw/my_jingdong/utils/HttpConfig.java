package com.bw.my_jingdong.utils;

/**
 * 存放接口
 */
public class HttpConfig {

    public static final String BASE_URL = "https://www.zhaoapi.cn/";
    //第一页面的轮播图
    public static final String JD_HOME_LUNBO = "ad/getAd";
    public static final String JD_HOME_SHOW = "product/getCatagory";
    //第一页面点击商品进入商品详情
    public static final String JD_PRODUCT_DETAILS="product/getProductDetail";
    //第二页面的左边
    public static final String JD_CLASSES_LEFT = "product/getCatagory";
    //第三页面的右边
    public static final String JD_CLASSES_RIGHT = "product/getProductCatagory";
    //第三页面购物车
    public static final String JD_SHOPPING_CART = "product/getCarts";
    //添加购物车
    public static final String JD_ADD_CART="product/addCart";
    //判断是否登录
    public static boolean isLogin=false;
    //记录当前用户id
    public static int ID;
    //第四页面我的个人中心页面(登录)
    public static final String JD_MY_CENTER_LOGIN = "user/login";
    //注册
    public static final String JD_MY_CENTER_REG="user/reg";


}
