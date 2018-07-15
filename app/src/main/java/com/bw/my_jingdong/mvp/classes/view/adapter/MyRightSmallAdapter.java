package com.bw.my_jingdong.mvp.classes.view.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanRight;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyRightSmallAdapter extends RecyclerView.Adapter {

    private List<ClassesBeanRight.DataBean> list;

    public MyRightSmallAdapter(List<ClassesBeanRight.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.class_small, null);
        return new MySmallHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MySmallHolder) holder).tv_name.setText(list.get(position).getList().get(position).getName());
        String pic = list.get(position).getList().get(position).getIcon();
        Uri uri = Uri.parse(pic);
        ((MySmallHolder) holder).small_img.setImageURI(uri);
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

}
