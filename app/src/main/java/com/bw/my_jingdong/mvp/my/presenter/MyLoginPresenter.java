package com.bw.my_jingdong.mvp.my.presenter;

import android.util.Log;

import com.bw.my_jingdong.base.BasePresenter;
import com.bw.my_jingdong.mvp.my.model.mymodel.MyLoginModel;
import com.bw.my_jingdong.mvp.my.model.bean.LoginBean;
import com.bw.my_jingdong.mvp.my.model.bean.RegBean;
import com.bw.my_jingdong.mvp.my.view.view.MyLoginView;
import com.bw.my_jingdong.utils.HttpConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.support.constraint.Constraints.TAG;

public class MyLoginPresenter extends BasePresenter<MyLoginView> {


    private MyLoginModel myLoginModel;

    public MyLoginPresenter(MyLoginView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        myLoginModel = new MyLoginModel();
    }


    public void Login(String mobile, String password) {
        Log.d(TAG, "Login: " + mobile + password);
        if (mobile.equals("") && mobile.length() < 1) {
            if (view != null) {
                view.onFaild("手机号不能为空");
            }
            return;
        }

        if (mobile.length() < 11) {
            if (view != null) {
                view.onFaild("手机号长度不够");
            }
        }

        if (password.equals("") && password.length() < 1) {
            if (view != null) {
                view.onFaild("密码不能为空");
            }
        }
        if (password.length() < 6 && password.length() > 1) {
            if (view != null) {
                view.onFaild("密码长度不够");
            }
        }
        myLoginModel.getLogin(mobile, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if ("0".equals(loginBean.getCode())) {
                            if (view != null) {
                                view.onSuccess(loginBean);
                                HttpConfig.isLogin=true;
                                HttpConfig.ID=loginBean.getData().getUid();
                            }
                        } else {
                            if (view != null) {
                                view.onFaild("登录失败！");
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void Reg(String mobile, String password) {
        if (mobile.equals("") && mobile.length() < 1) {
            if (view != null) {
                view.onFaild("手机号不能为空");
            }
            return;
        }

        if (mobile.length() < 11) {
            if (view != null) {
                view.onFaild("手机号长度不够");
            }
        }

        if (password.equals("") && password.length() < 1) {
            if (view != null) {
                view.onFaild("密码不能为空");
            }
        }
        if (password.length() < 6 && password.length() > 1) {
            if (view != null) {
                view.onFaild("密码长度不够");
            }
        }

        myLoginModel.getReg(mobile, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegBean regBean) {
                        if ("0".equals(regBean.getCode())) {
                            if (view != null) {
                                view.RegSuccess(regBean);
                            }
                        } else {
                            if (view != null) {
                                view.onFaild("登录失败！");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
