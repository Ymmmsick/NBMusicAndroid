package com.msr.nbmusic.ui.fragment;

import android.os.Bundle;

import com.msr.nbmusic.R;
import com.msr.nbmusic.contract.HomeContract;
import com.msr.nbmusic.presenter.HomePresenterImpl;
import com.msr.nbmusic.ui.base.BaseMVPFragment;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class HomeFragment extends BaseMVPFragment<HomePresenterImpl> implements HomeContract.View {

    @Override
    protected HomePresenterImpl createPresenter() {
        return new HomePresenterImpl();
    }

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void TODO(android.view.View view, Bundle savedInstanceState) {

    }
}
