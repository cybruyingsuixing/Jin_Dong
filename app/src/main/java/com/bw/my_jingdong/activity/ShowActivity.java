package com.bw.my_jingdong.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.adapter.MyFragmentAdapter;
import com.bw.my_jingdong.mvp.cart.view.fragment.CartFragment;
import com.bw.my_jingdong.mvp.classes.view.fragment.ClassesFragment;
import com.bw.my_jingdong.mvp.home.view.fragment.HomeFragment;
import com.bw.my_jingdong.mvp.my.view.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RadioGroup radio;
   private List<Fragment> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        initViews();
    }

    private void initViews() {
        viewPager = findViewById(R.id.show_viewpager);
        radio = findViewById(R.id.show_radio);
        radio.check(R.id.show_home);
        list.add(new HomeFragment());
        list.add(new ClassesFragment());
        list.add(new CartFragment());
        list.add(new MyFragment());

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        radio.check(R.id.show_home);
                        break;
                    case 1:
                        radio.check(R.id.show_classes);
                        break;
                        case 2:
                        radio.check(R.id.show_cart);
                        break;
                    case 3:
                        radio.check(R.id.show_my);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.show_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.show_classes:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.show_cart:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.show_my:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });
    }
}
