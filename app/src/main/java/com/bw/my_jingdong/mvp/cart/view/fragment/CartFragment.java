package com.bw.my_jingdong.mvp.cart.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseFragment;
import com.bw.my_jingdong.mvp.cart.model.bean.CartBean;
import com.bw.my_jingdong.mvp.cart.model.bean.CartRemoveBean;
import com.bw.my_jingdong.mvp.cart.model.bean.CreateOrderBean;
import com.bw.my_jingdong.mvp.cart.presenter.CartPresenter;
import com.bw.my_jingdong.mvp.cart.view.activity.QueryOrderActivity;
import com.bw.my_jingdong.mvp.cart.view.adapter.ExpandViewAdapter;
import com.bw.my_jingdong.mvp.cart.view.view.CartView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CartFragment extends BaseFragment<CartPresenter> implements CartView {

    private ExpandableListView cart_expand;
    private CheckBox ck_quanxuan;
    private TextView tv_he;
    private Button btn_num;
    private ExpandViewAdapter adapter;
    private List<CartBean.DataBean> data;
    private Button btn_create_order;
    private float totalPrice;
    private int uid;

    //获取id
    @Override
    protected void initViews(View view) {
        cart_expand = view.findViewById(R.id.cart_expand);
        ck_quanxuan = view.findViewById(R.id.quanxuan);
        tv_he = view.findViewById(R.id.he);
        btn_num = view.findViewById(R.id.btn_buy);
        //去结算
        btn_create_order = view.findViewById(R.id.cart_create_order);

    }

    @Override
    protected void initData() {
        SharedPreferences p = getContext().getSharedPreferences("mobile", MODE_PRIVATE);
        boolean flag = p.getBoolean("flag", false);
        uid = p.getInt("uid", 0);
        if (flag) {
            presenter.getCart(uid);
        }

    }

    @Override
    protected void initListener() {

        ck_quanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //底部全选按钮
                boolean allProductSelected = adapter.isAllProductSelected();
                adapter.changeAllProductStatus(!allProductSelected);
                //刷新适配器
                adapter.notifyDataSetChanged();
                reFreshSelectedAndToTalPriceAndTotalAllNumber();
            }
        });
        btn_create_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalPrice < 1) {
                    Toast.makeText(getContext(), "商品未选中", Toast.LENGTH_SHORT).show();
                }else{
                    presenter.CreateOrder(uid,totalPrice);
                }
            }
        });
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

        data = cartBean.getData();
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

            @Override
            public void onRemoveCartListener(int groupPosition, int childPosition) {
                int pid = data.get(groupPosition).getList().get(childPosition).getPid();
                SharedPreferences p = getActivity().getSharedPreferences("mobile", MODE_PRIVATE);
                SharedPreferences.Editor edit = p.edit();
                int uid = p.getInt("uid", 0);
                presenter.removeCartGoods(uid, pid);
            }
        });

        //展开二级列表
        for (int i = 0; i < data.size(); i++) {
            cart_expand.expandGroup(i);
        }

    }

    @Override
    public void onFaild(String error) {
        if (adapter != null) {
            data.clear();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRemoveSuccess(CartRemoveBean cartRemoveBean) {
        initData();
        //刷新适配器
        adapter.notifyDataSetChanged();
        reFreshSelectedAndToTalPriceAndTotalAllNumber();
    }

    @Override
    public void onRemoveFaild(String error) {


    }

    @Override
    public void onCreateOderSuccess(CreateOrderBean createOrderBean) {

        String code = createOrderBean.getCode();
        if ("0".equals(code)){

            Intent it = new Intent(getContext(), QueryOrderActivity.class);
            startActivity(it);
            Toast.makeText(getContext(), "订单创建成功", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onCreateFaild(String error) {

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
        totalPrice = adapter.calcuteTotalPrice();
        tv_he.setText("总价格(" + totalPrice + ")");
        //总数量
        int totalNum = adapter.cacuteTotalNum();
        btn_num.setText("总数量" + totalNum);
    }



}
