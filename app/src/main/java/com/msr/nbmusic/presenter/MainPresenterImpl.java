package com.msr.nbmusic.presenter;

import com.msr.nbmusic.contract.MainContract;
import com.msr.nbmusic.model.MainModelImpl;
import com.msr.nbmusic.mvp.BasePresenter;
import com.msr.nbmusic.mvp.IModel;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class MainPresenterImpl extends BasePresenter<MainContract.View, MainModelImpl>
        implements MainContract.Presenter {

    @Override
    protected IModel createModel() {
        return new MainModelImpl();
    }

}
