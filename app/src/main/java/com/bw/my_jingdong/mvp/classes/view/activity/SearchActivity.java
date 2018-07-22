package com.bw.my_jingdong.mvp.classes.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.my_jingdong.R;

public class SearchActivity extends AppCompatActivity {

    private EditText editText;
    private Button btn_sousuo;
    private Button btn_remove;
    private FlowLayout flowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
    }

    private void initViews() {
        editText = findViewById(R.id.search_sousuo);
        btn_sousuo = findViewById(R.id.btn_sousuo);
        btn_remove = findViewById(R.id.serch_btn_remove);
        flowLayout = findViewById(R.id.flow);
        btn_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                if (name!=null){
                    TextView textView = new TextView(SearchActivity.this);
                    textView.setText(name);
                    flowLayout.addView(textView);
                    Intent it = new Intent(SearchActivity.this, ShowSuccessActivity.class);
                    it.putExtra("name", name);
                    startActivityForResult(it, 100);
                }else{
                    Toast.makeText(SearchActivity.this,"输入内容不能为空",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowLayout.removeAllViews();
            }
        });
    }
}
