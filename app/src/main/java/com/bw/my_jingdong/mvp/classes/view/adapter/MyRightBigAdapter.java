package com.bw.my_jingdong.mvp.classes.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanRight;

import java.util.List;

public class MyRightBigAdapter extends RecyclerView.Adapter {

    private  RecyclerView recyclerView;
    private List<ClassesBeanRight.DataBean> list;
    private Context context;

    public MyRightBigAdapter(List<ClassesBeanRight.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(parent.getContext(), R.layout.classes_base1, null);
        return new MyBigHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyBigHolder)holder).textView.setText(list.get(position).getName());
        MyRightSmallAdapter myRightSmallAdapter = new MyRightSmallAdapter(list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(myRightSmallAdapter);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public class MyBigHolder extends RecyclerView.ViewHolder {


        private final TextView textView;

        public MyBigHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.base01_recyler);
            textView = itemView.findViewById(R.id.base01_tv);
        }

    }
}
