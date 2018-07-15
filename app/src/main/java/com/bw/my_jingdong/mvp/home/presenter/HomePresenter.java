package com.bw.my_jingdong.mvp.home.presenter;

import com.bw.my_jingdong.base.BasePresenter;
import com.bw.my_jingdong.mvp.home.model.HomeModel;
import com.bw.my_jingdong.mvp.home.model.bean.AddCartBean;
import com.bw.my_jingdong.mvp.home.model.bean.CatagoryBean;
import com.bw.my_jingdong.mvp.home.model.bean.HomeBean;
import com.bw.my_jingdong.mvp.home.model.bean.ProductDetailsBean;
import com.bw.my_jingdong.mvp.home.view.view.HomeView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeView> {


    private HomeModel homeModel;

    public HomePresenter(HomeView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    public void getHome() {
        homeModel.doHome()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (view != null) {
                            view.onSuccess(homeBean);
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

    public void getShow() {
        homeModel.doShow()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatagoryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CatagoryBean catagoryBean) {
                        if (view != null) {
                            view.onSuccessful(catagoryBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onFailds(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getProduct(int pid) {
        homeModel.doProduct(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ProductDetailsBean productDetailsBean) {
                        if (view != null) {
                            view.onProductSuccess(productDetailsBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onProductFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAddCart(int uid, int pid) {
        homeModel.doAddCart(uid, pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddCartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(AddCartBean addCartBean) {
                        if (view != null) {
                            view.onAddCart(addCartBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onAddCartFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
