package com.bw.my_jingdong.mvp.find.presenter;

import com.bw.my_jingdong.base.BasePresenter;
import com.bw.my_jingdong.mvp.find.model.findmodel.NewsModel;
import com.bw.my_jingdong.mvp.find.model.bean.NewsBean;
import com.bw.my_jingdong.mvp.find.view.view.NewsView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter extends BasePresenter<NewsView> {


    private NewsModel newsModel;

    public NewsPresenter(NewsView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        newsModel = new NewsModel();
    }

    public void getNews(String url) {
        newsModel.doNews(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        if (view != null) {
                            view.onNewsSuccess(newsBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onNewsFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
