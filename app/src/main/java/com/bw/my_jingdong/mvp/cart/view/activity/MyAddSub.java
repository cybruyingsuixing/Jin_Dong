package com.bw.my_jingdong.mvp.cart.view.activity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.my_jingdong.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAddSub extends LinearLayout {


    @BindView(R.id.jian)
    TextView jian;
    @BindView(R.id.product_num)
    TextView productNum;
    @BindView(R.id.jia)
    TextView jia;
    int number = 1;

    public MyAddSub(Context context) {
        this(context, null);
    }

    public MyAddSub(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.cart_my_sub_add, this);

        //初始化
        ButterKnife.bind(view);
    }


    @OnClick({R.id.jian, R.id.product_num, R.id.jia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jian:
                if (number > 1) {
                    --number;
                    productNum.setText(number + "");
                    if (onNumberChangeListener != null) {
                        onNumberChangeListener.onSubNumberChange(number);
                    }
                } else {
                    Toast.makeText(getContext(), "数量不能小于1", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.jia:
                ++number;
                productNum.setText(number + "");
                if (onNumberChangeListener != null) {
                    onNumberChangeListener.onAddNumberChange(number);
                }
                break;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        productNum.setText(number + "");
    }


    private onNumberChangeListener onNumberChangeListener;

    public void setOnNumberChangeListener(MyAddSub.onNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }

    public interface onNumberChangeListener {
        void onAddNumberChange(int product_num);
        void onSubNumberChange(int product_num);
    }

}
