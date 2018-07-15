package com.bw.my_jingdong.mvp.home.view.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.mvp.home.model.bean.CatagoryBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class MyCagtCoryAdapter extends RecyclerView.Adapter<MyCagtCoryAdapter.MyHolder> {
    private List<CatagoryBean.DataBean> list;

    private static final String TAG = "MyCagtCoryAdapter*****";
    public MyCagtCoryAdapter(List<CatagoryBean.DataBean> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_cagtcory, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder: "+ list.get(position).getName() );
        String icon = list.get(position).getIcon();

        Uri uri = Uri.parse(icon);
         holder.img.setImageURI(uri);
       holder.tv_text.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+list.size());
        Log.e(TAG, "onBindViewHolder: "+ list.get(0).getName() );
        return list.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView tv_text;

        public MyHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.cagtcory_img);
            tv_text = itemView.findViewById(R.id.cartcory_tv);
        }
    }
}
