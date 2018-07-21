package com.bw.my_jingdong.mvp.classes.persenter;

import com.bw.my_jingdong.base.BasePresenter;
import com.bw.my_jingdong.mvp.classes.model.classesmodel.SearchModel;
import com.bw.my_jingdong.mvp.classes.model.bean.SearchBean;
import com.bw.my_jingdong.mvp.classes.view.view.SearchView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter extends BasePresenter<SearchView> {


    private SearchModel searchModel;

    public SearchPresenter(SearchView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        searchModel = new SearchModel();
    }

    public void Search(String keywords,int sort) {
        searchModel.getSearch(keywords,sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        if (view != null) {
                            view.onSuccess(searchBean);
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
