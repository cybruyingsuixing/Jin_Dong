package com.bw.my_jingdong.mvp.cart.view.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.mvp.cart.model.bean.QueryOrderBean;

import java.util.List;

public class MyQueryOrderAdapter extends RecyclerView.Adapter {

    private List<QueryOrderBean.DataBean> list;

    public MyQueryOrderAdapter(List<QueryOrderBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(parent.getContext(), R.layout.query_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ((MyHolder) holder).tv_title.setText(list.get(position).getTitle());
        ((MyHolder) holder).tv_price.setText(list.get(position).getPrice() + "");
        ((MyHolder) holder).tv_time.setText(list.get(position).getCreatetime());
        int status = list.get(position).getStatus();
        if (status==0){
            ((MyHolder)holder).btn.setText("未支付");
        }else if(status==1){
            ((MyHolder)holder).btn.setText("已支付");
            ((MyHolder)holder).btn.setTextColor(Color.WHITE);
            ((MyHolder)holder).btn.setBackgroundColor(Color.BLUE);
        }else if (status==2){
            ((MyHolder)holder).btn.setText("取消支付");
            ((MyHolder)holder).btn.setTextColor(Color.WHITE);
            ((MyHolder)holder).btn.setBackgroundColor(Color.RED);
        }


        ((MyHolder)holder).btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null){
                    onClickListener.onClick(v,position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final TextView tv_price;
        private final TextView tv_title;
        private final TextView tv_time;
        private final Button btn;
        public MyHolder(View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.query_orders_time);
            tv_title = itemView.findViewById(R.id.query_orders_title);
            tv_price = itemView.findViewById(R.id.query_orders_price);
            btn = itemView.findViewById(R.id.query_orders_btn);
        }
    }


    onClickListener onClickListener;

    public void setOnClickListener(MyQueryOrderAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface onClickListener{
        void onClick(View v,int position);
    }

}
