package com.bw.my_jingdong.mvp.my.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseFragment;
import com.bw.my_jingdong.mvp.my.view.activity.LoginActivity;
import com.facebook.drawee.view.SimpleDraweeView;

public class MyFragment extends Fragment implements View.OnClickListener{

    private SimpleDraweeView my_tiao;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment, null);
        my_tiao = view.findViewById(R.id.my_tiao);
        textView = view.findViewById(R.id.my_text);
        my_tiao.setOnClickListener(this);
        SharedPreferences mobile = getActivity().getSharedPreferences("mobile", Context.MODE_PRIVATE);
        String name = mobile.getString("name", "");
        textView.setText(name);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_tiao:
               // Toast.makeText(getContext(),"调走了",Toast.LENGTH_SHORT).show();
                Intent it = new Intent(getContext(),LoginActivity.class);
                startActivityForResult(it,100);
                break;
        }
    }

   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==100){
            String mobile = data.getStringExtra("mobile");
            String image = data.getStringExtra("image");
            Log.d("tag", "onActivityResult: "+image);
            Log.d("tag", "onActivityResult: "+mobile);
            textView.setText(mobile);
            Uri uri = Uri.parse(image);
            my_tiao.setImageURI(uri);
        }
    }
}
