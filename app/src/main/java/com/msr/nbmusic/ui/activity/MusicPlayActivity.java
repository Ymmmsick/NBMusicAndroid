package com.msr.nbmusic.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.msr.nbmusic.R;
import com.msr.nbmusic.bean.response.NewMusicBean;
import com.msr.nbmusic.comm.NBGlideManager;
import com.msr.nbmusic.comm.NBMusicPlayer;
import com.msr.nbmusic.contract.MusicPlayContract;
import com.msr.nbmusic.presenter.MusicPlayPresenterImpl;
import com.msr.nbmusic.ui.base.BaseMVPActivity;
import com.msr.nbmusic.ui.widgets.DiscView;
import com.msr.nbmusic.ui.widgets.PlayButton;
import com.msr.nbmusic.ui.widgets.font.NBTextView;

import butterknife.BindView;

/**
 * Created by Ymmmsick on 9/12/17.
 */

public class MusicPlayActivity extends BaseMVPActivity<MusicPlayPresenterImpl>
        implements MusicPlayContract.View, NBMusicPlayer.PlayerProgressChangeListener {

    @BindView(R.id.musicplay_share)
    ImageView musicplayShare;
    @BindView(R.id.musicplay_download)
    ImageView musicplayDownload;
    @BindView(R.id.musicplay_ring)
    ImageView musicplayRing;
    @BindView(R.id.musicplay_disc)
    DiscView musicplayDisc;
    @BindView(R.id.musicplay_songname)
    NBTextView musicplaySongname;
    @BindView(R.id.musicplay_singer)
    NBTextView musicplaySinger;
    @BindView(R.id.musicplay_playorpause)
    PlayButton musicplayPlayorpause;

    private NewMusicBean.PagebeanBean.SonglistBean songlistBean;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_musicplay;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setActionBarBackEnable();
        songlistBean = (NewMusicBean.PagebeanBean.SonglistBean) getIntent().getExtras().getSerializable("musicData");
        NBGlideManager.loadImage(songlistBean.getAlbumpic_big(), musicplayDisc.getDiscImg());
        musicplayDisc.startRotation();

        musicplaySongname.setText(songlistBean.getSongname());
        musicplaySinger.setText(songlistBean.getSingername());

        NBMusicPlayer.getInstance().setProgressChangeListener(this);
        NBMusicPlayer.getInstance().playUrl(songlistBean.getUrl());
    }

    @Override
    public MusicPlayPresenterImpl createPresenter() {
        return new MusicPlayPresenterImpl();
    }

    @Override
    public void onBufferingUpdate(float percent) {

    }

    @Override
    public void onProgressChange(float percent) {
        musicplayPlayorpause.setCurrentProgress(percent);
    }

    @Override
    public void onMusicStart() {
        musicplayPlayorpause.setPlayOrPauseStatus(true);
    }

    @Override
    public void onMusicPause() {
        musicplayPlayorpause.setPlayOrPauseStatus(false);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
