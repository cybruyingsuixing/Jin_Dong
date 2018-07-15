package com.bw.my_jingdong.mvp.home.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MyPageAdapter extends PagerAdapter {

    private List<ImageView> list;
    private Context context;

    public MyPageAdapter(List<ImageView> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {

        if (list != null) {

            return Integer.MAX_VALUE;
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull

    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = list.get(position % list.size());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }

}
