package com.msr.nbmusic.presenter;

import com.msr.nbmusic.api.error.LObserver;
import com.msr.nbmusic.api.error.ResponeThrowable;
import com.msr.nbmusic.bean.response.EnglishBean;
import com.msr.nbmusic.contract.LoadingContract;
import com.msr.nbmusic.factory.TransformersFactory;
import com.msr.nbmusic.model.LoadingModel;
import com.msr.nbmusic.mvp.BasePresenter;
import com.msr.nbmusic.mvp.IModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ymmmsick on 9/8/17.
 */

public class LoadingPresenter extends BasePresenter<LoadingContract.View, LoadingModel>
        implements LoadingContract.Presenter {

    @Override
    protected IModel createModel() {
        return new LoadingModel();
    }

    @Override
    public void getEnglishWords(int count) {
        mModel.getEnglishWords(count)
                .compose(TransformersFactory.<EnglishBean>commonTransformerA((LifecycleProvider) getView()))
                .subscribe(new LObserver<EnglishBean>() {
                    @Override
                    public void onError(ResponeThrowable e) {

                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull EnglishBean englishBeen) {
                        getView().getEnglishWordsSuccess(englishBeen);
                    }
                });
    }
}
