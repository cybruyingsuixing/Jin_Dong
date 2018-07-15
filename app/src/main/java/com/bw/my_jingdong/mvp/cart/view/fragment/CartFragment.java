package com.bw.my_jingdong.mvp.cart.view.fragment;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseFragment;
import com.bw.my_jingdong.mvp.cart.model.bean.CartBean;
import com.bw.my_jingdong.mvp.cart.presenter.CartPresenter;
import com.bw.my_jingdong.mvp.cart.view.adapter.ExpandViewAdapter;
import com.bw.my_jingdong.mvp.cart.view.view.CartView;

import java.util.List;

public class CartFragment extends BaseFragment<CartPresenter> implements CartView, View.OnClickListener {

    private ExpandableListView cart_expand;
    private CheckBox ck_quanxuan;
    private TextView tv_he;
    private Button btn_buy;
    private ExpandViewAdapter adapter;


    //获取id
    @Override
    protected void initViews(View view) {
        cart_expand = view.findViewById(R.id.cart_expand);
        ck_quanxuan = view.findViewById(R.id.quanxuan);
        tv_he = view.findViewById(R.id.he);
        btn_buy = view.findViewById(R.id.btn_buy);
        ck_quanxuan.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        presenter.getCart(14789);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected CartPresenter provide() {
        return new CartPresenter((CartView) this);
    }

    @Override
    protected int provId() {
        return R.layout.cartfragment;
    }

    //成功
    @Override
    public void onSuccess(CartBean cartBean) {

        List<CartBean.DataBean> data = cartBean.getData();
        Log.e("tag", "onSuccess----------: " + data.size());
        //创建适配器
        adapter = new ExpandViewAdapter(data);
        cart_expand.setAdapter(adapter);
        reFreshSelectedAndToTalPriceAndTotalAllNumber();
     adapter.setOnCartListChangeListener(new ExpandViewAdapter.onCartListChangeListener() {
            @Override
            public void SellerCheckChange(int groupPosition) {
                //设置商家
                boolean b = adapter.productStatus(groupPosition);
                //子类按钮跟着改变
                adapter.noProductStatus(groupPosition, !b);
                //刷新适配器
                adapter.notifyDataSetChanged();
                reFreshSelectedAndToTalPriceAndTotalAllNumber();

            }

            @Override
            public void onProductCheckedChange(int groupPosition, int childPosition) {
                adapter.changeCurrentProductStatus(groupPosition, childPosition);
                //刷新适配器
                adapter.notifyDataSetChanged();
                reFreshSelectedAndToTalPriceAndTotalAllNumber();
            }

            @Override
            public void onProductNumberChange(int groupPosition, int childPosition, int number) {
                //设置加减按钮
             adapter.changeCurrentNumberProduct(groupPosition, childPosition, number);
                //刷新适配器
                adapter.notifyDataSetChanged();
                reFreshSelectedAndToTalPriceAndTotalAllNumber();
            }
        });

        //展开二级列表
        for (int i = 0; i < data.size(); i++) {
            cart_expand.expandGroup(i);
        }

    }

    @Override
    public void onFaild(String error) {

    }

    @Override
    public Context cotext() {
        return getContext();
    }


    public void reFreshSelectedAndToTalPriceAndTotalAllNumber() {
        //判断是否全部选中
        boolean allProductSelected = adapter.isAllProductSelected();
        ck_quanxuan.setChecked(allProductSelected);

        //设置总价格
        float totalPrice = adapter.calcuteTotalPrice();
        tv_he.setText("总价格(" + totalPrice + ")");
        //总数量
        int totalNum = adapter.cacuteTotalNum();
        btn_buy.setText("总数量" + totalNum);
    }


    @Override
    public void onClick(View v) {
        //底部全选按钮
        boolean allProductSelected = adapter.isAllProductSelected();
        adapter.changeAllProductStatus(!allProductSelected);
        //刷新适配器
       adapter.notifyDataSetChanged();
        reFreshSelectedAndToTalPriceAndTotalAllNumber();
    }
}
