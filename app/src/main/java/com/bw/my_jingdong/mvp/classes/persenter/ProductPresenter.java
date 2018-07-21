package com.bw.my_jingdong.mvp.classes.persenter;

import com.bw.my_jingdong.base.BasePresenter;
import com.bw.my_jingdong.mvp.classes.model.classesmodel.ProductModel;
import com.bw.my_jingdong.mvp.classes.model.bean.ProductBean;
import com.bw.my_jingdong.mvp.classes.view.view.ProductView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductPresenter extends BasePresenter<ProductView> {


    private ProductModel productModel;

    public ProductPresenter(ProductView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        productModel = new ProductModel();
    }

    public void getProduct(int pscid, int sort) {
        productModel.getProduct(pscid, sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ProductBean productBean) {
                        if (view != null) {
                            view.onSuccess(productBean);
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
