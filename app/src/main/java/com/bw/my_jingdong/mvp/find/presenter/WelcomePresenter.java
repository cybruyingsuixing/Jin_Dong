package com.bw.my_jingdong.mvp.find.presenter;

import com.bw.my_jingdong.base.BasePresenter;
import com.bw.my_jingdong.mvp.find.model.findmodel.WelcomeModel;
import com.bw.my_jingdong.mvp.find.model.bean.WelComeBean;
import com.bw.my_jingdong.mvp.find.view.view.WelcomeView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WelcomePresenter extends BasePresenter<WelcomeView> {

    private WelcomeModel welcomeModel;

    public WelcomePresenter(WelcomeView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        welcomeModel = new WelcomeModel();
    }

    public void getWelcome(String url) {
        welcomeModel.doWelcome(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WelComeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WelComeBean welComeBean) {
                        if (view != null) {
                            view.onWelcomeSuccess(welComeBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onWelcomeFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
