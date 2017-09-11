package com.msr.nbmusic.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.msr.nbmusic.R;
import com.msr.nbmusic.bean.response.NewMusicBean;
import com.msr.nbmusic.contract.HomeContract;
import com.msr.nbmusic.presenter.HomePresenterImpl;
import com.msr.nbmusic.ui.adapter.NewMusicAdapter;
import com.msr.nbmusic.ui.base.BaseMVPFragment;
import com.msr.nbmusic.ui.widgets.font.NBTextView;

import butterknife.BindView;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class HomeFragment extends BaseMVPFragment<HomePresenterImpl> implements HomeContract.View {

    @BindView(R.id.home_newmusic_total)
    NBTextView homeNewmusicTotal;
    @BindView(R.id.home_newmusic)
    ListView homeNewMusic;

    private NewMusicAdapter newMusicAdapter;

    @Override
    protected HomePresenterImpl createPresenter() {
        return new HomePresenterImpl();
    }

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        newMusicAdapter = new NewMusicAdapter(getActivity(), null);
        homeNewMusic.setAdapter(newMusicAdapter);
        presenter.getMusicData(3);
    }

    @Override
    public void loadNewMusicSuccess(NewMusicBean newMusicBean) {
        homeNewmusicTotal.setText("Total(" + newMusicBean.getPagebean().getSonglist().size() + ")");
        newMusicAdapter.setData(newMusicBean.getPagebean().getSonglist());
    }
}
