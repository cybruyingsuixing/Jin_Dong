package com.bw.my_jingdong.mvp.classes.view.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.mvp.classes.model.bean.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MySearchAdapter extends RecyclerView.Adapter {

    private List<SearchBean.DataBean> list;

    public MySearchAdapter(List<SearchBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(parent.getContext(), R.layout.showsuccess_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyHolder) holder).tv_title.setText(list.get(position).getTitle());
        ((MyHolder) holder).tv_price.setText(list.get(position).getPrice() + "");
        String[] pic = list.get(position).getImages().split("\\|");
        Uri uri = Uri.parse(pic[0]);
        ((MyHolder) holder).img.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView tv_title;
        private final TextView tv_price;

        public MyHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.show_success_item_img);
            tv_title = itemView.findViewById(R.id.show_success_item_title);
            tv_price = itemView.findViewById(R.id.show_success_item_price);
        }
    }
}
