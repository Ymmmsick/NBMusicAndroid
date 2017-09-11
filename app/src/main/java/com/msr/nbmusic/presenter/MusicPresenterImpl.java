package com.msr.nbmusic.presenter;


import com.msr.nbmusic.api.error.LObserver;
import com.msr.nbmusic.api.error.ResponeThrowable;
import com.msr.nbmusic.bean.response.NewMusicBean;
import com.msr.nbmusic.contract.MusicContract;
import com.msr.nbmusic.factory.TransformersFactory;
import com.msr.nbmusic.model.MusicModelImpl;
import com.msr.nbmusic.mvp.BasePresenter;
import com.msr.nbmusic.mvp.IModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class MusicPresenterImpl extends BasePresenter<MusicContract.View, MusicModelImpl>
        implements MusicContract.Presenter {

    private NewMusicBean newMusicBean;

    @Override
    protected IModel createModel() {
        return new MusicModelImpl();
    }

    @Override
    public void getMusicData(int topid) {
        mModel.getMusicData(topid)
                .compose(TransformersFactory.<NewMusicBean>commonTransformerF((LifecycleProvider) getView()))
                .subscribe(new LObserver<NewMusicBean>() {
                    @Override
                    public void onError(ResponeThrowable e) {

                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NewMusicBean bean) {
                        newMusicBean = bean;
                        getView().loadNewMusicSuccess(newMusicBean);
                    }
                });
    }

    public NewMusicBean getNewMusicBean() {
        return newMusicBean;
    }
}
