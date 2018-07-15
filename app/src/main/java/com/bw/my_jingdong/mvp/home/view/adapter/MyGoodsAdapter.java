package com.bw.my_jingdong.mvp.home.view.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.mvp.home.model.bean.HomeBean;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyGoodsAdapter extends RecyclerView.Adapter {

    private List<HomeBean.TuijianBean.ListBean> list;

    public MyGoodsAdapter(List<HomeBean.TuijianBean.ListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(parent.getContext(), R.layout.home_goods_item, null);
        return new myGoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        String[] pic = list.get(position).getImages().split("\\|");
        Uri uri = Uri.parse(pic[0]);
        ((myGoodsHolder) holder).home_goods_img.setImageURI(uri);
        ((myGoodsHolder) holder).tv_title.setText(list.get(position).getTitle());
        ((myGoodsHolder) holder).tv_price.setText(list.get(position).getPrice() + "");

        ((myGoodsHolder) holder).home_goods_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(v, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public class myGoodsHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView home_goods_img;
        private final TextView tv_title;
        private final TextView tv_price;

        public myGoodsHolder(View itemView) {
            super(itemView);
            home_goods_img = itemView.findViewById(R.id.home_goods_img);
            tv_title = itemView.findViewById(R.id.home_goods_title);
            tv_price = itemView.findViewById(R.id.home_goods_price);
        }
    }


    private onClickListener onClickListener;

    public void setOnClickListener(MyGoodsAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface onClickListener {
        void onClick(View view, int position);
    }
}
