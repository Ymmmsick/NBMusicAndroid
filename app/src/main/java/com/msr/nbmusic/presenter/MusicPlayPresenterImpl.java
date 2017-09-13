package com.msr.nbmusic.presenter;

import android.text.Html;

import com.msr.nbmusic.api.error.LObserver;
import com.msr.nbmusic.api.error.ResponeThrowable;
import com.msr.nbmusic.bean.response.LrcBean;
import com.msr.nbmusic.comm.NBLyricManager;
import com.msr.nbmusic.contract.MusicPlayContract;
import com.msr.nbmusic.factory.TransformersFactory;
import com.msr.nbmusic.model.MusicPlayModelImpl;
import com.msr.nbmusic.mvp.BasePresenter;
import com.msr.nbmusic.mvp.IModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.io.File;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ymmmsick on 9/12/17.
 */

public class MusicPlayPresenterImpl extends BasePresenter<MusicPlayContract.View, MusicPlayModelImpl>
        implements MusicPlayContract.Presenter {

    @Override
    protected IModel createModel() {
        return new MusicPlayModelImpl();
    }

    @Override
    public void getMusicLrc(final int musicid) {
        File lyricFile = NBLyricManager.getInstance().getLyricFile(musicid + "");
        if (lyricFile != null) {
            getView().getLocalLrcSuccess(lyricFile);
            return;
        }
        mModel.getMusicLrc(musicid)
                .compose(TransformersFactory.<LrcBean>commonTransformerA((LifecycleProvider) getView()))
                .subscribe(new LObserver<LrcBean>() {
                    @Override
                    public void onError(ResponeThrowable e) {
                        getView().getMusicLrcError(e.getLMessage());
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        getView().getMusicLrcStart();
                    }

                    @Override
                    public void onNext(@NonNull LrcBean lrcBean) {
                        String lrc = Html.fromHtml(lrcBean.getLyric()).toString();
                        lrc = NBLyricManager.getInstance().addLyricNewLine(lrc);
                        File lyricFile = NBLyricManager.getInstance().saveLyric(musicid + "", lrc);
                        getView().getNetLrcSuccess(lyricFile);
                    }
                });
    }
}
