package com.msr.nbmusic.contract;

import com.msr.nbmusic.bean.base.BaseResBean;
import com.msr.nbmusic.bean.response.NewMusicBean;
import com.msr.nbmusic.mvp.IModel;
import com.msr.nbmusic.mvp.IPresenter;
import com.msr.nbmusic.mvp.IView;

import io.reactivex.Observable;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public interface MusicContract {
    interface Model extends IModel {
        Observable<BaseResBean<NewMusicBean>> getMusicData(int topid);
    }

    interface View extends IView {
        void loadNewMusicSuccess(NewMusicBean newMusicBean);
    }

    interface Presenter extends IPresenter {
        void getMusicData(int topid);
    }
}
