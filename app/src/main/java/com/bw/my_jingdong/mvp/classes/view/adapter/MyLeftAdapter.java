package com.bw.my_jingdong.mvp.classes.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanLeft;

import java.util.List;

public class MyLeftAdapter extends BaseAdapter {
    private List<ClassesBeanLeft.DataBean> list;

    public MyLeftAdapter(List<ClassesBeanLeft.DataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
                convertView=View.inflate(parent.getContext(), R.layout.classes_listview_item,null);
                 holder = new ViewHolder();
                holder.textView=convertView.findViewById(R.id.list_tv);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.textView.setText(list.get(position).getName());
        return convertView;
    }
    class ViewHolder{
     TextView textView;
    }
}
