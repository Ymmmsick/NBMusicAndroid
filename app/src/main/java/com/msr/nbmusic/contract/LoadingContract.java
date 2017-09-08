package com.msr.nbmusic.contract;

import com.msr.nbmusic.bean.base.BaseResBean;
import com.msr.nbmusic.bean.response.EnglishBean;
import com.msr.nbmusic.mvp.IModel;
import com.msr.nbmusic.mvp.IPresenter;
import com.msr.nbmusic.mvp.IView;

import io.reactivex.Observable;

/**
 * Created by Ymmmsick on 9/8/17.
 */

public interface LoadingContract {
    interface Model extends IModel {
        Observable<BaseResBean<EnglishBean>> getEnglishWords(int count);
    }

    interface View extends IView {
        void getEnglishWordsSuccess(EnglishBean englishBean);
    }

    interface Presenter extends IPresenter {
        void getEnglishWords(int count);
    }
}
