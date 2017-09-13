package com.msr.nbmusic.presenter;

import android.content.Context;

import com.facebook.stetho.common.LogUtil;
import com.msr.nbmusic.bean.db.LocalMusic;
import com.msr.nbmusic.contract.MainContract;
import com.msr.nbmusic.model.MainModelImpl;
import com.msr.nbmusic.mvp.BasePresenter;
import com.msr.nbmusic.mvp.IModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class MainPresenterImpl extends BasePresenter<MainContract.View, MainModelImpl>
        implements MainContract.Presenter {

    @Override
    protected IModel createModel() {
        return new MainModelImpl();
    }

    @Override
    public void scanMusic(Context context) {
        mModel.scanMusic(context).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<LocalMusic>>() {
            @Override
            public void accept(@NonNull List<LocalMusic> localMusics) throws Exception {
                for (LocalMusic localMusic:localMusics) {
                    LogUtil.i("xxx",localMusic);
                }
            }
        });
    }
}
