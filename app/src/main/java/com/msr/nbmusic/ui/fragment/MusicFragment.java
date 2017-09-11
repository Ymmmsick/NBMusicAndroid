package com.msr.nbmusic.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.msr.nbmusic.R;
import com.msr.nbmusic.bean.response.NewMusicBean;
import com.msr.nbmusic.contract.MusicContract;
import com.msr.nbmusic.presenter.MusicPresenterImpl;
import com.msr.nbmusic.ui.activity.NewMusicActivity;
import com.msr.nbmusic.ui.adapter.MusicNewAdapter;
import com.msr.nbmusic.ui.base.BaseMVPFragment;
import com.msr.nbmusic.ui.widgets.font.NBTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class MusicFragment extends BaseMVPFragment<MusicPresenterImpl> implements MusicContract.View {

    @BindView(R.id.music_newmusic_total)
    NBTextView musicNewmusicTotal;
    @BindView(R.id.music_newmusic)
    ListView musicNewMusic;

    private MusicNewAdapter musicNewAdapter;

    @Override
    protected MusicPresenterImpl createPresenter() {
        return new MusicPresenterImpl();
    }

    @Override
    public int returnLayoutID() {
        return R.layout.fragment_music;
    }

    @Override
    public void TODO(View view, Bundle savedInstanceState) {
        musicNewAdapter = new MusicNewAdapter(getActivity(), null);
        musicNewMusic.setAdapter(musicNewAdapter);
        presenter.getMusicData(3);
    }

    @OnClick({R.id.music_newmusic_total})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.music_newmusic_total:
                if (presenter.getNewMusicBean() != null) {
                    Bundle data = new Bundle();
                    data.putSerializable("newMusicData", presenter.getNewMusicBean());
                    baseStartActivity(NewMusicActivity.class, data);
                }
                break;
        }
    }

    @Override
    public void loadNewMusicSuccess(NewMusicBean newMusicBean) {
        musicNewmusicTotal.setText("Total(" + newMusicBean.getPagebean().getSonglist().size() + ")");
        musicNewAdapter.setData(newMusicBean.getPagebean().getSonglist());
    }

}
