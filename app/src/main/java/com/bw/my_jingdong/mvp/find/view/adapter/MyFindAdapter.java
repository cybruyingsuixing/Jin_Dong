package com.bw.my_jingdong.mvp.find.view.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.mvp.find.model.bean.NewsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import retrofit2.http.Url;

public class MyFindAdapter extends RecyclerView.Adapter {

    private List<NewsBean.ResultBean.DataBean> list;
    private View view;
    final int J = 0;
    final int K = 1;

    public MyFindAdapter(List<NewsBean.ResultBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            view = View.inflate(parent.getContext(), R.layout.find_item, null);
            return new MyHolder01(view);
        } else {
            view = View.inflate(parent.getContext(), R.layout.find_item2, null);
        }

        return new MyHolder02(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case J:
                ((MyHolder01) holder).textView.setText(list.get(position).getTitle());
                String p1 = list.get(position).getThumbnail_pic_s();
                Uri uri1 = Uri.parse(p1);
                ((MyHolder01) holder).img1.setImageURI(uri1);

                String p2 = list.get(position).getThumbnail_pic_s();
                Uri uri2 = Uri.parse(p2);
                ((MyHolder01) holder).img2.setImageURI(uri2);

                String p3 = list.get(position).getThumbnail_pic_s();
                Uri uri3 = Uri.parse(p3);
                ((MyHolder01) holder).img3.setImageURI(uri3);
                break;
            case K:
                ((MyHolder02) holder).textView2.setText(list.get(position).getTitle());
                String p = list.get(position).getThumbnail_pic_s();
                Uri uri = Uri.parse(p);
                ((MyHolder02) holder).img.setImageURI(uri);
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
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


    @Override
    public int getItemViewType(int position) {
        if (list.size() % 2 == 0) {
            return K;
        }
        return J;
    }


    public class MyHolder01 extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img1;
        private final SimpleDraweeView img2;
        private final SimpleDraweeView img3;
        private final TextView textView;

        public MyHolder01(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.find_item_img1);
            img2 = itemView.findViewById(R.id.find_item_img2);
            img3 = itemView.findViewById(R.id.find_item_img3);
            textView = itemView.findViewById(R.id.find_item_tv);
        }
    }

    public class MyHolder02 extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView textView2;

        public MyHolder02(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.find_item2_img);
            textView2 = itemView.findViewById(R.id.find_item2_text);
        }
    }

    onClickListener onClickListener;

    public void setOnClickListener(MyFindAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface onClickListener {
        void onClick(View v, int position);
    }


}
