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
    //第二页面的右边
    public static final String JD_CLASSES_RIGHT = "product/getProductCatagory";
    //搜索
    public static final String JD_SEARCH="product/searchProducts";

    //分类点击子条目进入详情
    public static final String JD_CLASSES_PRODUCT="product/getProducts";
    //第三页面购物车
    public static final String JD_SHOPPING_CART = "product/getCarts";
    //添加购物车
    public static final String JD_ADD_CART="product/addCart";
    //点击秒杀添加购物车
    public static final String JD_MIAOSHA_ADD_CART="product/getProductDetail";
    //删除购物车商品
    public static final String JD_CART_REMOVE_GOODS="product/deleteCart";
    //判断是否登录
    public static boolean isLogin=false;
    //记录当前用户id
    public static int ID;
    //第四页面我的个人中心页面(登录)
    public static final String JD_MY_CENTER_LOGIN = "user/login";
    //注册
    public static final String JD_MY_CENTER_REG="user/reg";
   //修改昵称
    public static final String JD_UPDATE_NAME="user/updateNickName";
    //上传头像
    public static final String JD_UPDATE_AVATOR="file/upload";
    //获取用户信息
    public static final String JD_USER_INFORMATION="user/getUserInfo";

    //发现
    public static final String JD_FIND="http://v.juhe.cn/toutiao/index?type=top&key=76214828aaa56637bb64eebae67032ed ";


    //发现点击条目跳转
    public static final String JD_WELCOME="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1";

//创建订单
    public static final String JD_CREATE_ORDER="product/createOrder";

//查询订单
    public static final String JD_QUERY_ORDER="product/getOrders";

//修改订单
    public static final String JD_UPDATE_ORDER="product/updateOrder";
//添加收获地址
   public static final String JD_ADD_ADDRESS="user/addAddr";
 //查询收获地址
    public static final String JD_QUERY_ADDRESS="user/getAddrs";
//设置默认地址
    public static final String JD_DEFAULT_ADDR="user/setAddr";
//获取默认地址
    public static final String JD_GET_DEFAULT_ADDR="user/getDefaultAddr";
//修改地址
    public static final String JD_UPDATE_ADDR="user/updateAddr";

}
