package com.bw.my_jingdong.mvp.home.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseFragment;
import com.bw.my_jingdong.mvp.cart.model.bean.CreateOrderBean;
import com.bw.my_jingdong.mvp.classes.view.activity.SearchActivity;
import com.bw.my_jingdong.mvp.home.model.bean.AddCartBean;
import com.bw.my_jingdong.mvp.home.model.bean.CatagoryBean;
import com.bw.my_jingdong.mvp.home.model.bean.HomeBean;
import com.bw.my_jingdong.mvp.home.model.bean.ProductDetailsBean;
import com.bw.my_jingdong.mvp.home.model.bean.SpikBean;
import com.bw.my_jingdong.mvp.home.presenter.HomePresenter;
import com.bw.my_jingdong.mvp.home.view.activity.ProductDetailsActivity;
import com.bw.my_jingdong.mvp.home.view.adapter.MyCagtCoryAdapter;
import com.bw.my_jingdong.mvp.home.view.adapter.MyGoodsAdapter;
import com.bw.my_jingdong.mvp.home.view.adapter.MySplickAdapter;
import com.bw.my_jingdong.mvp.home.view.view.HomeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunfusheng.marqueeview.MarqueeView;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView,View.OnClickListener{

    private Button btn_sao;
    private EditText editText;
    private Button btn_msg;
    private RecyclerView RecyclerView_maosha;
    private Banner banner;
    private RecyclerView xRecyclerView_show;
    private static final String TAG = "HomeFragment***";
    private RecyclerView recyclerView_goods;
    private RefreshLayout refreshLayout;
    private MyGoodsAdapter myGoodsAdapter;
    private MarqueeView marqueeView;
    private MySplickAdapter mySplickAdapter;
    private MyCagtCoryAdapter myCagtCoryAdapter;


    //获取id
    @Override
    protected void initViews(View view) {

        //轮播图
        banner = view.findViewById(R.id.home_banner);
        //二维码
        btn_sao = view.findViewById(R.id.home_btn_sao);
        //搜索
        editText = view.findViewById(R.id.home_ed_text);
        //消息
        btn_msg = view.findViewById(R.id.home_btn_msg);
        //轮播图下方图片展示
        xRecyclerView_show = view.findViewById(R.id.home_recyler_show);
        //商品秒杀
        RecyclerView_maosha = view.findViewById(R.id.home_recyler_miaosha);
       //商品推介
        recyclerView_goods = view.findViewById(R.id.home_recyler_goods);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        btn_sao.setOnClickListener(this);
        editText.setOnClickListener(this);
        marqueeView = view.findViewById(R.id.marqueeView);

        List<String> info = new ArrayList<>();
        info.add("京东购物狂欢");
        info.add("服装秀");
        info.add("Iphone9即将来临，升降摄像");
        info.add("1块钱买一个G,固态价格雪崩");
// 在代码里设置自己的动画
        marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);
    }

    @Override
    protected void initData() {
        presenter.getHome();
        presenter.getShow();
    }

    @Override
    protected void initListener() {


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });


        //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshHeader(new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true));
//设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));

    }

    @Override
    protected HomePresenter provide() {
        return new HomePresenter((HomeView) this);
    }

    @Override
    protected int provId() {
        return R.layout.homefragment;
    }

    @Override
    public void onSuccess(final HomeBean homeBean) {
        //轮播图
        List<String> list = new ArrayList<>();
        List<String> list_title = new ArrayList<>();
        List<HomeBean.DataBean> data = homeBean.getData();
        for (int i = 0; i <data.size() ; i++) {
            String icon = data.get(i).getIcon();
            String title = data.get(i).getTitle();
            list.add(icon);
            list_title.add(title);
        }
        banner.setImageLoader(new GlideImageloader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImages(list);
        banner.setBannerTitles(list_title);
        banner.start();
//秒杀
        HomeBean.MiaoshaBean miaosha = homeBean.getMiaosha();
        List<HomeBean.MiaoshaBean.ListBeanX> list1 = miaosha.getList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView_maosha.setLayoutManager(linearLayoutManager);
        mySplickAdapter = new MySplickAdapter(list1);
        RecyclerView_maosha.setAdapter(mySplickAdapter);

        mySplickAdapter.setOnClickListener(new MyGoodsAdapter.onClickListener() {
            @Override
            public void onClick(View view, int position) {
                int pid = homeBean.getMiaosha().getList().get(position).getPid();
                Intent intent = new Intent(getActivity(),ProductDetailsActivity.class);
                intent.putExtra("pid",pid);
                startActivityForResult(intent,100);
            }
        });


//推介
        final HomeBean.TuijianBean tuijian = homeBean.getTuijian();
        List<HomeBean.TuijianBean.ListBean> list2 = tuijian.getList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView_goods.setLayoutManager(gridLayoutManager);
        myGoodsAdapter = new MyGoodsAdapter(list2);
        recyclerView_goods.setAdapter(myGoodsAdapter);
//设置点击事件
        myGoodsAdapter.setOnClickListener(new MyGoodsAdapter.onClickListener() {
            @Override
            public void onClick(View view, int position) {
                int pid = homeBean.getTuijian().getList().get(position).getPid();
                Intent intent = new Intent(getActivity(),ProductDetailsActivity.class);
                intent.putExtra("pid",pid);

                startActivityForResult(intent,100);
            }
        });

    }

    @Override
    public Context cotext() {
        return getContext();
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.home_btn_sao:
               Intent intent = new Intent(getContext(), CaptureActivity.class);
               startActivityForResult(intent,0);
               break;
           case R.id.home_ed_text:
               Intent it= new Intent(getContext(), SearchActivity.class);
               startActivity(it);
               break;

       }
    }

    private class GlideImageloader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Uri uri=Uri.parse((String) path);
            imageView.setImageURI(uri);
        }
        @Override
        public ImageView createImageView(Context context) {
            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
            return simpleDraweeView;
        }
    }

    @Override
    public void onFaild(String error) {

    }



    @Override
    public void onSuccessful(CatagoryBean catagoryBean) {
        List<CatagoryBean.DataBean> data = catagoryBean.getData();
        Log.e(TAG, "onSuccessful://///// "+data.get(0).getName() );
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        xRecyclerView_show.setLayoutManager(gridLayoutManager);
        myCagtCoryAdapter = new MyCagtCoryAdapter(data);
        xRecyclerView_show.setAdapter(myCagtCoryAdapter);

        myCagtCoryAdapter.setOnClickListener(new MyCagtCoryAdapter.onClickListener() {
            @Override
            public void onClick( int position) {
                ViewPager viewPager=getActivity().findViewById(R.id.show_viewpager);
                viewPager.setCurrentItem(1);

            }
        });
    }




    @Override
    public void onFailds(String error) {

    }

    @Override
    public void onProductSuccess(ProductDetailsBean productDetailsBean) {

    }

    @Override
    public void onProductFaild(String error) {

    }

    @Override
    public void onAddCart(AddCartBean addCartBean) {

    }

    @Override
    public void onAddCartFaild(String error) {

    }

    @Override
    public void onSpikSuccess(SpikBean spikBean) {

    }

    @Override
    public void onSpikFaild(String error) {

    }

    //点击立即购买
    @Override
    public void onBuySuccess(CreateOrderBean createOrderBean) {

    }

    @Override
    public void onBuyFaild(String error) {

    }


}
