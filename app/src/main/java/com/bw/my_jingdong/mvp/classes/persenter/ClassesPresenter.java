package com.bw.my_jingdong.mvp.classes.persenter;

import com.bw.my_jingdong.base.BasePresenter;
import com.bw.my_jingdong.mvp.classes.model.classesmodel.ClassesModel;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanLeft;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanRight;
import com.bw.my_jingdong.mvp.classes.view.view.ClassesView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClassesPresenter extends BasePresenter<ClassesView> {


    private ClassesModel classesModel;

    public ClassesPresenter(ClassesView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        classesModel = new ClassesModel();
    }


    public void getLeftGoods() {
        classesModel.getLeft()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassesBeanLeft>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassesBeanLeft classesBeanLeft) {
                        if (view != null) {
                            view.onLeftSuccess(classesBeanLeft);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onRightFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getRightGoods(int cid){
        classesModel.getRight(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassesBeanRight>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassesBeanRight classesBeanRight) {
                        if (view!=null){
                            view.onRightSuccess(classesBeanRight);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onRightFaild(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




}
