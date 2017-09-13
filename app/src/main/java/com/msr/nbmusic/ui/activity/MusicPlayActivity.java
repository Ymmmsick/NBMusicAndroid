package com.msr.nbmusic.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.msr.nbmusic.R;
import com.msr.nbmusic.bean.response.NewMusicBean;
import com.msr.nbmusic.comm.NBGlideManager;
import com.msr.nbmusic.comm.NBMusicPlayerManager;
import com.msr.nbmusic.contract.MusicPlayContract;
import com.msr.nbmusic.presenter.MusicPlayPresenterImpl;
import com.msr.nbmusic.ui.base.BaseMVPActivity;
import com.msr.nbmusic.ui.widgets.DiscView;
import com.msr.nbmusic.ui.widgets.PlayButton;
import com.msr.nbmusic.ui.widgets.font.NBTextView;
import com.msr.nbmusic.ui.widgets.loadingandretry.LoadingAndRetryManager;
import com.msr.nbmusic.ui.widgets.loadingandretry.OnLoadingAndRetryListener;
import com.msr.nbmusic.ui.widgets.lrc.LyricView;
import com.orhanobut.logger.Logger;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ymmmsick on 9/12/17.
 */

public class MusicPlayActivity extends BaseMVPActivity<MusicPlayPresenterImpl>
        implements MusicPlayContract.View, NBMusicPlayerManager.PlayerProgressChangeListener {

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
    @BindView(R.id.musicplay_song_ll)
    LinearLayout musicplaySongLl;
    @BindView(R.id.musicplay_playorpause)
    PlayButton musicplayPlayorpause;
    @BindView(R.id.musicplay_lrc)
    LyricView musicplayLrc;
    @BindView(R.id.musicplay_player)
    RelativeLayout musicplayPlayer;

    private NewMusicBean.PagebeanBean.SonglistBean songlistBean;
    private LoadingAndRetryManager loadingAndRetryManager;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_musicplay;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setActionBarBackEnableAndListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        loadingAndRetryManager = LoadingAndRetryManager.generate(musicplayLrc, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {
                retryView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });
        musicplayLrc.setOnLyricClickListener(new LyricView.OnLyricClickListener() {
            @Override
            public void onLyricClicked(View view) {
                //switch display song info
                musicplayPlayer.setVisibility(View.VISIBLE);
                musicplayLrc.setVisibility(View.GONE);
            }
        });
        songlistBean = (NewMusicBean.PagebeanBean.SonglistBean) getIntent().getExtras().getSerializable("musicData");
        NBGlideManager.loadImage(songlistBean.getAlbumpic_big(), musicplayDisc.getDiscImg());
        musicplayDisc.startRotation();

        musicplaySongname.setText(songlistBean.getSongname());
        musicplaySinger.setText(songlistBean.getSingername());

        NBMusicPlayerManager.getInstance().setProgressChangeListener(this);
        NBMusicPlayerManager.getInstance().playUrl(songlistBean.getUrl());
        presenter.getMusicLrc(songlistBean.getSongid());
    }

    @Override
    public MusicPlayPresenterImpl createPresenter() {
        return new MusicPlayPresenterImpl();
    }

    @OnClick({R.id.musicplay_song_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.musicplay_song_ll://switch display lyric
                musicplayPlayer.setVisibility(View.GONE);
                musicplayLrc.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onBufferingUpdate(float percent) {

    }

    @Override
    public void onProgressChange(float percent, long current) {
        musicplayPlayorpause.setCurrentProgress(percent);
        musicplayLrc.setCurrentTimeMillis(current);
        Logger.i("current:" + current);
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

    @Override
    public void getMusicLrcStart() {
        loadingAndRetryManager.showLoading();
    }

    @Override
    public void getMusicLrcError(String message) {
        loadingAndRetryManager.showRetry();
    }

    @Override
    public void getLocalLrcSuccess(File lyricFile) {
        musicplayLrc.setLyricFile(lyricFile);
    }

    @Override
    public void getNetLrcSuccess(File lyricFile) {
        loadingAndRetryManager.showContent();
        musicplayLrc.setLyricFile(lyricFile);
    }

}
