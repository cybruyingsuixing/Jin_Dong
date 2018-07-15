package com.bw.my_jingdong.mvp.cart.presenter;


import android.util.Log;

import com.bw.my_jingdong.base.BasePresenter;
import com.bw.my_jingdong.mvp.cart.model.CartModel;
import com.bw.my_jingdong.mvp.cart.model.bean.CartBean;
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

}
