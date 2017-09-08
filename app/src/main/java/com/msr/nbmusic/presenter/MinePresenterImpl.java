package com.msr.nbmusic.presenter;

import com.msr.nbmusic.contract.MineContract;
import com.msr.nbmusic.model.MineModelImpl;
import com.msr.nbmusic.mvp.BasePresenter;
import com.msr.nbmusic.mvp.IModel;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class MinePresenterImpl extends BasePresenter<MineContract.View, MineModelImpl>
        implements MineContract.Presenter {

    @Override
    protected IModel createModel() {
        return new MineModelImpl();
    }
}
