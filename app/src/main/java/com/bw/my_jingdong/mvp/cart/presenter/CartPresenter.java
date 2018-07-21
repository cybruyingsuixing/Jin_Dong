package com.bw.my_jingdong.mvp.cart.presenter;


import android.util.Log;

import com.bw.my_jingdong.base.BasePresenter;
import com.bw.my_jingdong.mvp.cart.model.cartmodel.CartModel;
import com.bw.my_jingdong.mvp.cart.model.bean.CartBean;
import com.bw.my_jingdong.mvp.cart.model.bean.CartRemoveBean;
import com.bw.my_jingdong.mvp.cart.model.bean.CreateOrderBean;
import com.bw.my_jingdong.mvp.cart.view.view.CartView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CartPresenter extends BasePresenter<CartView> {


    private CartModel cartModel;

    public CartPresenter(CartView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        cartModel = new CartModel();
    }

    //查询购物车
    public void getCart(int uid) {
        cartModel.doCart(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(CartBean cartBean) {
                        Log.e("tag", "onNext: " + cartBean.getMsg());
                        if (view != null) {
                            view.onSuccess(cartBean);
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

    //删除购物车商品
    public void removeCartGoods(int uid, int pid) {


        cartModel.doRemoveGoods(uid, pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartRemoveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(CartRemoveBean cartRemoveBean) {

                        String code = cartRemoveBean.getCode();
                        if ("0".equals(code)) {
                            if (view != null) {
                                view.onRemoveSuccess(cartRemoveBean);
                            }
                        } else {
                            if (view != null) {
                                view.onRemoveFaild("访问错误");
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onRemoveFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //创建订单
    public void CreateOrder(int uid, float price) {
        cartModel.createOrder(uid, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CreateOrderBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(CreateOrderBean createOrderBean) {
                        if (view != null) {
                            view.onCreateOderSuccess(createOrderBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onCreateFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
