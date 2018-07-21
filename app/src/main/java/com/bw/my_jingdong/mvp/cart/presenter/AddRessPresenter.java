package com.bw.my_jingdong.mvp.cart.presenter;

import android.util.Log;

import com.bw.my_jingdong.base.BasePresenter;
import com.bw.my_jingdong.mvp.cart.model.bean.AddRessBean;
import com.bw.my_jingdong.mvp.cart.model.bean.DefaultAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.GetAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.QueryAddrBean;
import com.bw.my_jingdong.mvp.cart.model.bean.UpdateAddrBean;
import com.bw.my_jingdong.mvp.cart.model.cartmodel.AddRessModel;
import com.bw.my_jingdong.mvp.cart.view.view.AddRessView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddRessPresenter extends BasePresenter<AddRessView> {


    private AddRessModel addRessModel;

    public AddRessPresenter(AddRessView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        addRessModel = new AddRessModel();
    }


    public void arrRess(int uid, String addr, String mobile, String name) {
        addRessModel.doAddRess(uid, addr, mobile, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddRessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(AddRessBean addRessBean) {
                        if (view != null) {
                            view.onAddRessSuccess(addRessBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onAddRessFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void queryAddr(int uid) {
        addRessModel.doQueryAddr(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryAddrBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(QueryAddrBean queryAddrBean) {
                        Log.d("tag", "onNext:999999999999999999999 " + queryAddrBean.getCode());
                        if (queryAddrBean.getCode().equals("0")) {
                            if (view != null) {
                                view.onQueryAddr(queryAddrBean);
                            }
                        } else {
                            if (view != null) {
                                view.onQueryAddrFaild("错误");
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("tag", "onNext:33333333333333333 " + e.toString());
                        if (view != null) {
                            view.onQueryAddrFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void Default(int uid, int addrid, int status) {
        addRessModel.doDefault(uid, addrid, status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DefaultAddrBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DefaultAddrBean defaultAddrBean) {
                        if (view != null) {
                            view.OnDefaultAddrSuccess(defaultAddrBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onDefaultAddrFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updateAddr(int uid, int addrid,String addr) {
        addRessModel.doUppdateAddr(uid, addrid,addr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateAddrBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UpdateAddrBean updateAddrBean) {
                        if (view != null) {
                            view.onUpdateAddrSuccess(updateAddrBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onUpdateAddrFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
