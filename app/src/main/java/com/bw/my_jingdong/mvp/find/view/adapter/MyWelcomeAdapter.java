package com.bw.my_jingdong.mvp.find.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bw.my_jingdong.R;
import com.bw.my_jingdong.mvp.find.model.bean.WelComeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyWelcomeAdapter extends RecyclerView.Adapter {

    private List<WelComeBean.ResultsBean> list;
    private Context context;

    public MyWelcomeAdapter(List<WelComeBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.welcome_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       /* String pic = list.get(position).getUrl();
        Uri uri = Uri.parse(pic);
        ((MyHolder) holder).img.setImageURI(uri);*/
        Glide.with(context).load(list.get(position).getUrl()).into(((MyHolder) holder).img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;

        public MyHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.welcome_img);
        }

    }
}
