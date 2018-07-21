package com.bw.my_jingdong.mvp.cart.presenter;

import android.util.Log;

import com.bw.my_jingdong.base.BasePresenter;
import com.bw.my_jingdong.mvp.cart.model.bean.GetAddrBean;
import com.bw.my_jingdong.mvp.cart.model.cartmodel.QueryOrderModel;
import com.bw.my_jingdong.mvp.cart.model.bean.QueryOrderBean;
import com.bw.my_jingdong.mvp.cart.model.bean.UpdateOrderBean;
import com.bw.my_jingdong.mvp.cart.view.view.QueryOrderView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QueryOrderPresenter extends BasePresenter<QueryOrderView> {


    private QueryOrderModel queryOrderModel;

    public QueryOrderPresenter(QueryOrderView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        queryOrderModel = new QueryOrderModel();
    }

    public void getQueryOrder(int uid, int status) {
        queryOrderModel.queryOrder(uid, status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryOrderBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(QueryOrderBean queryOrderBean) {
                        if (view != null) {
                            view.onQueryOrderSuccess(queryOrderBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onQueryOrderFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getUpdateOrder(int uid, int status, int orderId) {
        queryOrderModel.updateOrder(uid, status, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateOrderBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(UpdateOrderBean updateOrderBean) {
                        if (view != null) {
                            view.onUpdateOrderSuccess(updateOrderBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onUpdateOrderFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getDefault(int uid) {
        queryOrderModel.doGetAddr(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetAddrBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GetAddrBean getAddrBean) {
                        if (view != null) {
                            view.onGetDefaultAddr(getAddrBean);
                            Log.d("tag", "onNext: "+getAddrBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onGetDefaultFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
