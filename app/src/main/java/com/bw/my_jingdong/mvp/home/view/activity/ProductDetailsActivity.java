package com.bw.my_jingdong.mvp.home.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseActivity;
import com.bw.my_jingdong.mvp.cart.model.bean.CreateOrderBean;
import com.bw.my_jingdong.mvp.cart.view.activity.QueryOrderActivity;
import com.bw.my_jingdong.mvp.cart.view.fragment.CartFragment;
import com.bw.my_jingdong.mvp.home.model.bean.AddCartBean;
import com.bw.my_jingdong.mvp.home.model.bean.CatagoryBean;
import com.bw.my_jingdong.mvp.home.model.bean.HomeBean;
import com.bw.my_jingdong.mvp.home.model.bean.ProductDetailsBean;
import com.bw.my_jingdong.mvp.home.model.bean.SpikBean;
import com.bw.my_jingdong.mvp.home.presenter.HomePresenter;
import com.bw.my_jingdong.mvp.home.view.fragment.HomeFragment;
import com.bw.my_jingdong.mvp.home.view.view.HomeView;
import com.bw.my_jingdong.utils.HttpConfig;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDetailsActivity extends BaseActivity<HomePresenter> implements HomeView, View.OnClickListener {
    private int pid;

    private static final String TAG = "ProductDetailsActivity";
    private Banner product_banner;
    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_price;
    private List<String> imgs = new ArrayList<>();
    private Button add_car;
    private Button shangjia;
    private Button kefu;
    private Button add_crt;
    private Button cart;
    private Button go_buy;
    private int uid;
    private float price;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int pid = intent.getIntExtra("pid", 57);
        presenter.getProduct(pid);
    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
        product_banner = findViewById(R.id.product_banner);
        tv_title = findViewById(R.id.product_title);
        tv_content = findViewById(R.id.product_content);
        tv_price = findViewById(R.id.product_price);
        add_car = findViewById(R.id.add_cart);
        shangjia = findViewById(R.id.shangjia);
        kefu = findViewById(R.id.kefu);
        add_crt = findViewById(R.id.add_cart);
        add_crt.setOnClickListener(this);
        cart = findViewById(R.id.cart_tiao);
        go_buy = findViewById(R.id.go_buy);
        go_buy.setOnClickListener(this);
        cart.setOnClickListener(this);
        shangjia.setOnClickListener(this);
        kefu.setOnClickListener(this);
        Intent intent = getIntent();
        pid = intent.getIntExtra("pid", 57);
        SharedPreferences p = ProductDetailsActivity.this.getSharedPreferences("mobile", MODE_PRIVATE);
        SharedPreferences.Editor edit = p.edit();
        uid = p.getInt("uid", 0);
        Log.e("tag", "initViews--------: " + pid);
        add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getAddCart(uid, pid);
            }
        });
    }
    @Subscribe
    public void getCid(int cid){
        Log.i("xxx",cid+"-----");
       // presenter.getRightGoods(cid);
    }
    @Override
    protected HomePresenter provide() {
        return new HomePresenter((HomeView) this);
    }

    @Override
    protected int provId() {
        return R.layout.activity_product_details;
    }

    @Override
    public void onSuccess(HomeBean homeBean) {

    }

    @Override
    public void onFaild(String error) {

    }

    @Override
    public void onSuccessful(CatagoryBean catagoryBean) {

    }

    @Override
    public void onFailds(String error) {

    }

    @Override
    public void onProductSuccess(ProductDetailsBean productDetailsBean) {
        ProductDetailsBean.DataBean data = productDetailsBean.getData();
        tv_title.setText(data.getTitle());
        tv_content.setText(data.getSubhead());
        tv_price.setText(data.getPrice() + "");
        price = data.getBargainPrice();
        String[] pic = data.getImages().split("\\|");
        List<String> list = Arrays.asList(pic);
        for (int i = 0; i < list.size(); i++) {
            imgs.add(pic[i]);
        }
        product_banner.setImageLoader(new GlideImageloader());
        product_banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        product_banner.setImages(list);
        Log.d(TAG, "onProductSuccess:++++++++++++++ " + list.size());
        product_banner.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shangjia:
                Toast.makeText(ProductDetailsActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.kefu:
                Toast.makeText(ProductDetailsActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cart_tiao:
                //Toast.makeText(ProductDetailsActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
                Toast.makeText(ProductDetailsActivity.this, "购物车", Toast.LENGTH_SHORT).show();
                break;
                case R.id.go_buy:
                       presenter.getBuy(uid,price);
                    break;
        }
    }

    private class GlideImageloader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }

        @Override
        public ImageView createImageView(Context context) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
            return simpleDraweeView;
        }
    }


    @Override
    public void onProductFaild(String error) {

    }

    @Override
    public void onAddCart(AddCartBean addCartBean) {
        Toast.makeText(ProductDetailsActivity.this, "successful" + addCartBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddCartFaild(String error) {
        Toast.makeText(ProductDetailsActivity.this, "faild" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSpikSuccess(SpikBean spikBean) {
        Toast.makeText(ProductDetailsActivity.this, "successful" + spikBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSpikFaild(String error) {
        Toast.makeText(ProductDetailsActivity.this, "失败" + error, Toast.LENGTH_SHORT).show();
    }

    //点击立即购买
    @Override
    public void onBuySuccess(CreateOrderBean createOrderBean) {
        String code = createOrderBean.getCode();
        if ("0".equals(code)){
            Intent it = new Intent(ProductDetailsActivity.this, QueryOrderActivity.class);
            startActivity(it);
            Toast.makeText(ProductDetailsActivity.this, "订单创建成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBuyFaild(String error) {

    }

    @Override
    public Context cotext() {
        return ProductDetailsActivity.this;
    }


}
