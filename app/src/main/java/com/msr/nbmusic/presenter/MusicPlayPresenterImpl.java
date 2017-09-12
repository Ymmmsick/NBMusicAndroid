package com.msr.nbmusic.presenter;

import com.msr.nbmusic.contract.MusicPlayContract;
import com.msr.nbmusic.model.MusicPlayModelImpl;
import com.msr.nbmusic.mvp.BasePresenter;
import com.msr.nbmusic.mvp.IModel;

/**
 * Created by Ymmmsick on 9/12/17.
 */

public class MusicPlayPresenterImpl extends BasePresenter<MusicPlayContract.View, MusicPlayModelImpl>
        implements MusicPlayContract.Presenter {

    @Override
    protected IModel createModel() {
        return new MusicPlayModelImpl();
    }
}
