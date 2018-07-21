package com.bw.my_jingdong.mvp.cart.view.adapter;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.mvp.cart.model.bean.CartBean;
import com.bw.my_jingdong.mvp.cart.view.activity.MyAddSub;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpandViewAdapter extends BaseExpandableListAdapter {

    private List<CartBean.DataBean> seller;
    private static final String TAG = "适配器********";

    public ExpandViewAdapter(List<CartBean.DataBean> seller) {
        this.seller = seller;
    }

    @Override
    public int getGroupCount() {
        return seller == null ? 0 : seller.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return seller.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return seller.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        CartBean.DataBean dataBean = seller.get(groupPosition);
        ParentHolder parentHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.cart_parent, null);
            parentHolder = new ParentHolder(convertView);
            convertView.setTag(parentHolder);
        } else {
            parentHolder = (ParentHolder) convertView.getTag();
        }
        //赋值商家名称
        parentHolder.sellerNameTv.setText(dataBean.getSellerName());

        boolean b = productStatus(groupPosition);
        parentHolder.sellerCb.setChecked(b);
        parentHolder.sellerCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCartListChangeListener != null) {
                    onCartListChangeListener.SellerCheckChange(groupPosition);
                }
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {

        CartBean.DataBean.ListBean listBean = seller.get(groupPosition).getList().get(childPosition);
        ChildHolder childHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.cart_child, null);

            childHolder = new ChildHolder(convertView);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        //赋值子类名称
        childHolder.productTitleNameTv.setText(listBean.getTitle());
        childHolder.childPrice.setText(listBean.getBargainPrice() + "");
        String[] pic = listBean.getImages().split("\\|");
        Uri uri = Uri.parse(pic[0]);
        childHolder.productImg.setImageURI(uri);
        boolean flag = listBean.getSelected() == 1 ? true : false;
        childHolder.childCb.setChecked(flag);

        childHolder.childCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCartListChangeListener != null) {
                    onCartListChangeListener.onProductCheckedChange(groupPosition, childPosition);
                    Toast.makeText(parent.getContext(), "kkkkk", Toast.LENGTH_SHORT).show();
                }
            }
        });

        childHolder.mySubAdd.setNumber(listBean.getNum());
        childHolder.mySubAdd.setOnNumberChangeListener(new MyAddSub.onNumberChangeListener() {
            @Override
            public void onAddNumberChange(int product_num) {
                if (onCartListChangeListener != null) {
                    onCartListChangeListener.onProductNumberChange(groupPosition, childPosition, product_num);
                }
            }

            @Override
            public void onSubNumberChange(int product_num) {
                if (onCartListChangeListener != null) {
                    onCartListChangeListener.onProductNumberChange(groupPosition, childPosition, product_num);
                }
            }

        });

        childHolder.cartChildRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCartListChangeListener != null) {
                    onCartListChangeListener.onRemoveCartListener(groupPosition, childPosition);
                }
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    //关于商家按钮
    public boolean productStatus(int groupPosition) {

        List<CartBean.DataBean.ListBean> list = seller.get(groupPosition).getList();
        for (CartBean.DataBean.ListBean listBean : list) {
            if (listBean.getSelected() == 0) {
                return false;
            }
        }
        return true;
    }

    //子类按钮跟着改变
    public void noProductStatus(int groupPosition, boolean b) {

        List<CartBean.DataBean.ListBean> list = seller.get(groupPosition).getList();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSelected(b ? 1 : 0);
        }
    }

    //判断所有商品是否选中
    public boolean isAllProductSelected() {
        for (int i = 0; i < seller.size(); i++) {
            List<CartBean.DataBean.ListBean> list = seller.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSelected() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    //关于底部全选按钮
    public void changeAllProductStatus(boolean selected) {

        for (int i = 0; i < seller.size(); i++) {
            List<CartBean.DataBean.ListBean> list = seller.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                list.get(j).setSelected(selected ? 1 : 0);
            }
        }
    }

    //关于子类按钮
    public void changeCurrentProductStatus(int groupPosition, int childPosition) {

        List<CartBean.DataBean.ListBean> list = seller.get(groupPosition).getList();
        CartBean.DataBean.ListBean listBean = list.get(childPosition);
        listBean.setSelected(listBean.getSelected() == 0 ? 1 : 0);

    }

    //总价格
    public float calcuteTotalPrice() {
        float totlaPrice = 0;
        for (int i = 0; i < seller.size(); i++) {
            List<CartBean.DataBean.ListBean> list = seller.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSelected() == 1) {
                    float price = list.get(j).getBargainPrice();
                    int num = list.get(j).getNum();
                    totlaPrice += num * price;
                }

            }
        }
        return totlaPrice;
    }

    //总数量
    public int cacuteTotalNum() {
        int totalNum = 0;
        for (int i = 0; i < seller.size(); i++) {
            List<CartBean.DataBean.ListBean> list = seller.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSelected() == 1) {
                    int num = list.get(j).getNum();
                    totalNum += num;
                }

            }
        }
        return totalNum;
    }

    //设置加减按钮
    public void changeCurrentNumberProduct(int groupPosition, int childPosition, int number) {
        List<CartBean.DataBean.ListBean> list = seller.get(groupPosition).getList();

        CartBean.DataBean.ListBean listBean = list.get(childPosition);

        listBean.setNum(number);
    }


    //父类
    static class ParentHolder {
        @BindView(R.id.seller_cb)
        CheckBox sellerCb;
        @BindView(R.id.seller_name_tv)
        TextView sellerNameTv;

        ParentHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    //子类
    onCartListChangeListener onCartListChangeListener;

    public void setOnCartListChangeListener(ExpandViewAdapter.onCartListChangeListener onCartListChangeListener) {
        this.onCartListChangeListener = onCartListChangeListener;
    }

    //接口回调
    public interface onCartListChangeListener {
        void SellerCheckChange(int groupPosition);

        void onProductCheckedChange(int groupPosition, int childPosition);

        void onProductNumberChange(int groupPosition, int childPosition, int number);

        void onRemoveCartListener(int groupPosition, int childPosition);
    }


    static class ChildHolder {
        @BindView(R.id.child_cb)
        CheckBox childCb;
        @BindView(R.id.productImg)
        SimpleDraweeView productImg;
        @BindView(R.id.product_title_name_tv)
        TextView productTitleNameTv;
        @BindView(R.id.child_price)
        TextView childPrice;
        @BindView(R.id.my_sub_add)
        MyAddSub mySubAdd;
        @BindView(R.id.cart_child_remove)
        Button cartChildRemove;

        ChildHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
