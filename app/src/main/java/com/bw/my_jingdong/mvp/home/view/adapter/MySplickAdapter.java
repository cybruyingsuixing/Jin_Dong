package com.bw.my_jingdong.mvp.home.view.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.mvp.home.model.bean.HomeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MySplickAdapter extends RecyclerView.Adapter {

    private List<HomeBean.MiaoshaBean.ListBeanX> list;

    public MySplickAdapter(List<HomeBean.MiaoshaBean.ListBeanX> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.home_splick_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        String[] pic = list.get(position).getImages().split("\\|");
        Uri uri = Uri.parse(pic[0]);
        ((MyHolder)holder).splick.setImageURI(uri);

        ((MyHolder)holder).splick.setOnClickListener(new View.OnClickListener() {
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

        private final SimpleDraweeView splick;

        public MyHolder(View itemView) {
            super(itemView);
            splick = itemView.findViewById(R.id.home_splick);
        }
    }

    private MyGoodsAdapter.onClickListener onClickListener;

    public void setOnClickListener(MyGoodsAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface onClickListener {
        void onClick(View view, int position);
    }


}
