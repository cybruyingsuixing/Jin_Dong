package com.bw.my_jingdong.mvp.my.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Environment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.bw.my_jingdong.R;
import com.bw.my_jingdong.base.BaseActivity;
import com.bw.my_jingdong.mvp.cart.view.activity.AddRessActivity;
import com.bw.my_jingdong.mvp.my.model.bean.UpdateAvatorBean;
import com.bw.my_jingdong.mvp.my.model.bean.UpdateNameBean;
import com.bw.my_jingdong.mvp.my.model.bean.UserInforMationBean;
import com.bw.my_jingdong.mvp.my.presenter.MyCenterPresenter;
import com.bw.my_jingdong.mvp.my.view.view.MyCenterView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

import java.io.FileOutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MyCenterActivity extends BaseActivity<MyCenterPresenter> implements View.OnClickListener, MyCenterView {

    private TextView tv_name;
    private SimpleDraweeView img;
    private Button btn_resect;
    private SharedPreferences.Editor edit;
    private Button btn_back;
    private int uid;
    //定义图片的路径
    private String path = Environment.getExternalStorageDirectory() + "/photo.png";
    private TextView tv_addr;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        btn_resect = findViewById(R.id.my_resect);
        btn_resect.setOnClickListener(this);
        tv_name = findViewById(R.id.lmy_center_name);
        btn_back = findViewById(R.id.my_center_back);
        btn_back.setOnClickListener(this);
        img = findViewById(R.id.my_center_avator_img);
        img.setOnClickListener(this);
        tv_addr = findViewById(R.id.my_center_addr);
        tv_addr.setOnClickListener(this);
        SharedPreferences mobile = MyCenterActivity.this.getSharedPreferences("mobile", MODE_PRIVATE);
        edit = mobile.edit();
        String name = mobile.getString("name", "");
        Log.d("tag", "onCreate:6666666666666666 " + name);
        String icon = mobile.getString("icon", null);
        uid = mobile.getInt("uid", 0);
        tv_name.setText(name);
        img.setImageURI(icon);

    }

    @Override
    protected MyCenterPresenter provide() {
        return new MyCenterPresenter(this);
    }


    @Override
    protected int provId() {
        return R.layout.activity_my_center;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_resect:
                Intent intent = new Intent();
                edit.clear();
                edit.commit();
                setResult(500, intent);
                finish();
                break;
            case R.id.my_center_avator_img:
                //打开相机
                Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //将图片放到SD card
                in.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(in, 100);
                Toast.makeText(MyCenterActivity.this, "ooooo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_center_back:
                Intent i = new Intent();
                setResult(100, i);
                finish();
                break;

              case R.id.my_center_addr:
                  Intent intent1 = new Intent(MyCenterActivity.this,AddRessActivity.class);
                  startActivity(intent1);
                  break;
        }
    }

    @Override
    public void onNameSuccess(UpdateNameBean updateNameBean) {

    }

    @Override
    public void onNameFaild(String error) {

    }

    @Override
    public void onAvatorSuccess(UpdateAvatorBean updateAvatorBean) {
        presenter.userInfor(uid);
    }

    @Override
    public void onAvatorFaild(String error) {

    }

    @Override
    public void onUserInforSuccess(UserInforMationBean userInforMationBean) {
        String icon = userInforMationBean.getData().getIcon();
        img.setImageURI(icon);
    }

    @Override
    public void onUserFaild(String error) {

    }

    @Override
    public Context cotext() {
        return this;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 相机调用裁剪功能
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到拍完的照片进行裁剪
            it.setDataAndType(Uri.fromFile(new File(path)), "image/*");
            //设置是否支持裁剪
            it.putExtra("crop", true);
            //设置框的宽高比
            it.putExtra("aspactX", 1);
            it.putExtra("aspactY", 1);
            //设置输出的照片
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //将图片返回
            it.putExtra("return-data", true);

            startActivityForResult(it, 300);

        }

        //设置裁剪
        if (requestCode == 200 && resultCode == RESULT_OK) {
            //得到图片路径
            Uri uri = data.getData();
            //调用系统裁剪功能
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到照片进行裁剪
            it.setDataAndType(uri, "image/*");
            //设置是否支持裁剪
            it.putExtra("crop", true);
            //设置框的宽高比
            it.putExtra("aspactX", 1);
            it.putExtra("aspactY", 1);
            //设置输出图片大小
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //将图片返回
            it.putExtra("return-data", true);

            startActivityForResult(it, 300);
        }
        //裁剪完后回到设置图片
        if (requestCode == 300 && resultCode == RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            //获取文件路径
            File file = new File(getFilesDir().getAbsolutePath());
            if (!file.exists()) {
                //如果路径不存在就创建
                file.mkdirs();
            }
            //创建文件
            File file1 = new File(file, "photo.png");
            FileOutputStream fileOutputStream;
            try {
                //文件输出流
                fileOutputStream = new FileOutputStream(file1);
                //将bitmap写入文件流
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                //刷新此输出流并强制将所有缓冲的输出字节被写出
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //RequestBody封装了文件和文件的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file1);
            // MultipartBody.Part封装了接受的key和文件名字和RequestBody
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file1.getName(), requestBody);

            //调用上传头像
            presenter.updateAvator(uid, part);
        }
    }

}
