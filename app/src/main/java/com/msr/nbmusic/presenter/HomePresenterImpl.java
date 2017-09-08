package com.msr.nbmusic.presenter;


import com.msr.nbmusic.contract.HomeContract;
import com.msr.nbmusic.model.HomeModelImpl;
import com.msr.nbmusic.mvp.BasePresenter;
import com.msr.nbmusic.mvp.IModel;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class HomePresenterImpl extends BasePresenter<HomeContract.View, HomeModelImpl>
        implements HomeContract.Presenter {

    @Override
    protected IModel createModel() {
        return new HomeModelImpl();
    }
}
