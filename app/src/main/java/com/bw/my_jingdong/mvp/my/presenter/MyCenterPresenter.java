package com.bw.my_jingdong.mvp.my.presenter;

import com.bw.my_jingdong.base.BasePresenter;
import com.bw.my_jingdong.mvp.my.model.mymodel.MyCenterModel;
import com.bw.my_jingdong.mvp.my.model.bean.UpdateAvatorBean;
import com.bw.my_jingdong.mvp.my.model.bean.UpdateNameBean;
import com.bw.my_jingdong.mvp.my.model.bean.UserInforMationBean;
import com.bw.my_jingdong.mvp.my.view.view.MyCenterView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class MyCenterPresenter extends BasePresenter<MyCenterView> {


    private MyCenterModel myCenterModel;

    public MyCenterPresenter(MyCenterView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        myCenterModel = new MyCenterModel();
    }

    public void updateName(int uid) {
        myCenterModel.getUpdateName(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateNameBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UpdateNameBean updateNameBean) {
                        if (view != null) {
                            view.onNameSuccess(updateNameBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onNameFaild(e.toString());
                        }
                    }


                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updateAvator(int uid, MultipartBody.Part file) {
        myCenterModel.getUpdateAvator(uid, file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateAvatorBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UpdateAvatorBean updateAvatorBean) {
                        if (view != null) {
                            view.onAvatorSuccess(updateAvatorBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onAvatorFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void userInfor(int uid) {
        myCenterModel.getUserInfor(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInforMationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UserInforMationBean userInforMationBean) {
                        if (view != null) {
                            view.onUserInforSuccess(userInforMationBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onUserFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
