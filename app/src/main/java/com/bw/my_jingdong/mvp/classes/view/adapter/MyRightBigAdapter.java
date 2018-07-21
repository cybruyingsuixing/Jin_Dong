package com.bw.my_jingdong.mvp.classes.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanRight;
import com.bw.my_jingdong.mvp.classes.view.activity.ClssesProducts;
import com.bw.my_jingdong.mvp.classes.view.activity.ShowSuccessActivity;

import java.util.List;

public class MyRightBigAdapter extends RecyclerView.Adapter {

    private RecyclerView recyclerView;
    private List<ClassesBeanRight.DataBean> list;
    private Context context;
    private MyRightSmallAdapter myRightSmallAdapter;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MyBigHolder) holder).textView.setText(list.get(position).getName());
        List<ClassesBeanRight.DataBean.ListBean> list1 = this.list.get(position).getList();
        Log.d("tag", "onBindViewHolder:999999999999999999999 "+list1.get(position).getName());
        myRightSmallAdapter = new MyRightSmallAdapter(list1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(myRightSmallAdapter);

        myRightSmallAdapter.setOnClickListener(new MyRightSmallAdapter.onClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent it = new Intent(context, ClssesProducts.class);
                int pscid = MyRightBigAdapter.this.list.get(position).getList().get(position).getPscid();
                it.putExtra("pscid",pscid);
                context.startActivity(it);
            }
        });
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
