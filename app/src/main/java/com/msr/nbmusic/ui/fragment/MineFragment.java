package com.msr.nbmusic.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.msr.nbmusic.R;
import com.msr.nbmusic.contract.MineContract;
import com.msr.nbmusic.presenter.MinePresenterImpl;
import com.msr.nbmusic.ui.base.BaseMVPFragment;

/**
 * Mine
 * Created by Ymmmsick on 8/18/17.
 */

public class MineFragment extends BaseMVPFragment<MinePresenterImpl> implements MineContract.View {

    @Override
    protected MinePresenterImpl createPresenter() {
        return new MinePresenterImpl();
    }

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_mine;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {

    }
}
