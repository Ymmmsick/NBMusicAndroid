package com.msr.nbmusic.contract;

import com.msr.nbmusic.bean.base.BaseResBean;
import com.msr.nbmusic.bean.response.LrcBean;
import com.msr.nbmusic.mvp.IModel;
import com.msr.nbmusic.mvp.IPresenter;
import com.msr.nbmusic.mvp.IView;

import java.io.File;

import io.reactivex.Observable;

/**
 * Created by Ymmmsick on 9/12/17.
 */

public interface MusicPlayContract {
    interface Model extends IModel {
        Observable<BaseResBean<LrcBean>> getMusicLrc(int musicid);
    }

    interface View extends IView {
        void getMusicLrcStart();

        void getMusicLrcError(String message);

        void getLocalLrcSuccess(File lyricFile);

        void getNetLrcSuccess(File lyricFile);
    }

    interface Presenter extends IPresenter {
        void getMusicLrc(int musicid);
    }
}
