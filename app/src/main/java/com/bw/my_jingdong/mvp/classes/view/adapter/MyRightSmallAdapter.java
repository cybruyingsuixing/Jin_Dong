package com.bw.my_jingdong.mvp.classes.view.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanRight;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyRightSmallAdapter extends RecyclerView.Adapter {

    private List<ClassesBeanRight.DataBean.ListBean> list;

    public MyRightSmallAdapter(List<ClassesBeanRight.DataBean.ListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.class_small, null);
        return new MySmallHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        //Log.d("tag", "onBindViewHolder:88888888888888 "+list.get(position).getList().get(position).getName());
        ((MySmallHolder) holder).tv_name.setText(list.get(position).getName());
        String pic = list.get(position).getIcon();
        Uri uri = Uri.parse(pic);
        ((MySmallHolder) holder).small_img.setImageURI(uri);
        ((MySmallHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
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

    public class MySmallHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView small_img;
        private final TextView tv_name;

        public MySmallHolder(View itemView) {
            super(itemView);
            small_img = itemView.findViewById(R.id.small_img);
            tv_name = itemView.findViewById(R.id.small_tv);
        }
    }

    onClickListener onClickListener;

    public void setOnClickListener(MyRightSmallAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface onClickListener {
        void onClick(View v, int position);
    }
}
