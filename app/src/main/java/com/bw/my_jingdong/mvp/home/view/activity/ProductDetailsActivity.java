package com.bw.my_jingdong.mvp.home.view.activity;

import android.content.Context;
import android.content.Intent;
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
import com.bw.my_jingdong.mvp.home.model.bean.AddCartBean;
import com.bw.my_jingdong.mvp.home.model.bean.CatagoryBean;
import com.bw.my_jingdong.mvp.home.model.bean.HomeBean;
import com.bw.my_jingdong.mvp.home.model.bean.ProductDetailsBean;
import com.bw.my_jingdong.mvp.home.presenter.HomePresenter;
import com.bw.my_jingdong.mvp.home.view.fragment.HomeFragment;
import com.bw.my_jingdong.mvp.home.view.view.HomeView;
import com.bw.my_jingdong.utils.HttpConfig;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDetailsActivity extends BaseActivity<HomePresenter> implements HomeView {
    private int pid;

    private static final String TAG = "ProductDetailsActivity";
    private Banner product_banner;
    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_price;
    private List<String> imgs = new ArrayList<>();
    private Button add_car;

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
        product_banner = findViewById(R.id.product_banner);
        tv_title = findViewById(R.id.product_title);
        tv_content = findViewById(R.id.product_content);
        tv_price = findViewById(R.id.product_price);
        add_car = findViewById(R.id.add_cart);

        Intent intent = getIntent();
        pid = intent.getIntExtra("pid", 57);
        Log.e("tag", "initViews--------: " + pid);
        add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.getAddCart(14789, pid);
            }
        });
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
    public Context cotext() {
        return ProductDetailsActivity.this;
    }


}
