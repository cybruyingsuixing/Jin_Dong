package com.bw.my_jingdong.mvp.classes.view.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {


    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    //测量方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //测量
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //自定义属性
        int width = 0;
        int height = 0;
        int childWidth = 0;
        int childHeight = 0;
        int linWidth = 0;
        int linHeight = 0;
        int totalHeight = 0;
        View viewChild;

        //遍历
        for (int i = 0; i < getChildCount(); i++) {
            //获取子布局宽高
            viewChild = getChildAt(i);
            //获取子布局宽高
            childWidth = viewChild.getMeasuredWidth();
            childHeight = viewChild.getMeasuredHeight();

            if (childWidth > widthSize) {
                throw new IllegalArgumentException("长度过大");
            }
            if (linWidth > widthSize) {
                //换行
                width = widthSize;
                totalHeight += linHeight;
                linWidth = childWidth;
                linHeight = childHeight;
            } else {
                //否则不换行
                linWidth += childWidth;
                linHeight = Math.max(linHeight, childHeight);
                width = Math.max(linWidth, width);
            }
            if (i == getChildCount() - 1) {
                totalHeight = totalHeight + linHeight;
                height = totalHeight;
            }

            width = widthMode == MeasureSpec.EXACTLY ? widthSize : width;
            height = heightMode == MeasureSpec.EXACTLY ? heightSize : height;
            setMeasuredDimension(width, height);

        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //自定义属性
        int width = 0;
        int height = 0;
        int childWidth = 0;
        int childHeight = 0;
        int linWidth = 0;
        int linHeight = 0;
        int totalHeight = 0;
        View viewChild;

        for (int i = 0; i < getChildCount(); i++) {
            viewChild = getChildAt(i);
            childWidth = viewChild.getMeasuredWidth();
            childHeight = viewChild.getMeasuredHeight();
            if (linWidth + childWidth > getMeasuredWidth()) {
                //换行
                linWidth = 0;
                totalHeight += linHeight;
                layoutView(viewChild, linWidth, totalHeight, linWidth + childWidth, totalHeight + childHeight);
                linHeight = childHeight;
                linWidth = childWidth;
            } else {
                //不换行
                layoutView(viewChild, linWidth, totalHeight, linWidth + childWidth, totalHeight + childHeight);
                linWidth += childWidth;
                linHeight = Math.max(linHeight, childHeight);
            }
        }
    }


    private void layoutView(View viewChild, int linWidth, int totalHeight, int i, int i1) {

        linWidth += getPaddingLeft();
        linWidth += getPaddingTop();
        viewChild.layout(linWidth, totalHeight, i, i1);
    }
}
