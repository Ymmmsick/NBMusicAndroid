package com.msr.nbmusic.contract;

import android.content.Context;

import com.msr.nbmusic.bean.db.LocalMusic;
import com.msr.nbmusic.mvp.IModel;
import com.msr.nbmusic.mvp.IPresenter;
import com.msr.nbmusic.mvp.IView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Simeon on 2017/9/14.
 */

public interface MusicScanContract {

    interface Model extends IModel {
        Observable<List<LocalMusic>> scanMusic(Context context);
    }

    interface View extends IView {
    }

    interface Presenter extends IPresenter {
        void scanMusic(Context context);
    }

}
