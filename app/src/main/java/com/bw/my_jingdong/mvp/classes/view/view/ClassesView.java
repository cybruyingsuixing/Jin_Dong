package com.bw.my_jingdong.mvp.classes.view.view;

import com.bw.my_jingdong.base.IView;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanLeft;
import com.bw.my_jingdong.mvp.classes.model.bean.ClassesBeanRight;

public interface ClassesView extends IView {

    void onLeftSuccess(ClassesBeanLeft classesBeanLeft);
    void onLeftFaild(String error);

   void onRightSuccess(ClassesBeanRight classesBeanRight);
   void onRightFaild(String error);
}
