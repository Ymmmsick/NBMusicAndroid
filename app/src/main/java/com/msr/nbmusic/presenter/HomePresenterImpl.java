package com.msr.nbmusic.presenter;


import com.msr.nbmusic.api.error.LObserver;
import com.msr.nbmusic.api.error.ResponeThrowable;
import com.msr.nbmusic.bean.response.NewMusicBean;
import com.msr.nbmusic.contract.HomeContract;
import com.msr.nbmusic.factory.TransformersFactory;
import com.msr.nbmusic.model.HomeModelImpl;
import com.msr.nbmusic.mvp.BasePresenter;
import com.msr.nbmusic.mvp.IModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class HomePresenterImpl extends BasePresenter<HomeContract.View, HomeModelImpl>
        implements HomeContract.Presenter {

    @Override
    protected IModel createModel() {
        return new HomeModelImpl();
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
                    public void onNext(@NonNull NewMusicBean newMusicBean) {
                        getView().loadNewMusicSuccess(newMusicBean);
                    }
                });
    }
}
