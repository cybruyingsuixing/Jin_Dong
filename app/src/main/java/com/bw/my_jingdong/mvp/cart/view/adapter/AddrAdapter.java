package com.bw.my_jingdong.mvp.cart.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.mvp.cart.model.bean.QueryAddrBean;

import java.util.List;

public class AddrAdapter extends RecyclerView.Adapter {
    private List<QueryAddrBean.DataBean> list;

    public AddrAdapter(List<QueryAddrBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.address_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MyHolder) holder).tv_name.setText("用户名：" + list.get(position).getName());

        ((MyHolder) holder).tv_mobile.setText("手机号：" + list.get(position).getMobile());

        ((MyHolder) holder).tv_addr.setText("收货地址：" + list.get(position).getAddr());


        ((MyHolder) holder).tv_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(v, position);
                }
            }
        });

        ((MyHolder)holder).tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null){
                    onClickListener.onUpdateClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final TextView tv_name;
        private final TextView tv_mobile;
        private final TextView tv_addr;
        private final TextView tv_default;
        private final TextView tv_update;

        public MyHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.address_item_name);
            tv_mobile = itemView.findViewById(R.id.address_item_mobile);
            tv_addr = itemView.findViewById(R.id.address_item);
            tv_default = itemView.findViewById(R.id.address_default);
            tv_update = itemView.findViewById(R.id.address_update);
        }
    }

    onClickListener onClickListener;

    public void setOnClickListener(AddrAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface onClickListener {
        void onClick(View v, int position);
        void onUpdateClick(View v,int position);
    }

}
