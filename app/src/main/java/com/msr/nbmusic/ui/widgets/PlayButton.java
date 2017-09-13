package com.msr.nbmusic.ui.widgets;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.msr.nbmusic.R;

/**
 * Created by Ymmmsick on 9/12/17.
 */

public class PlayButton extends FrameLayout {

    private HoloCircularProgressBar pb;
    private ImageView playOrPause;
    private PlayButtonListener playButtonListener;

    public PlayButton(@NonNull Context context) {
        super(context);
        initView();
    }

    public PlayButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PlayButton(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        // 加载布局
        LayoutInflater.from(getContext()).inflate(R.layout.view_playbutton, this);
        pb = (HoloCircularProgressBar) findViewById(R.id.playbutton_pb);
        playOrPause = (ImageView) findViewById(R.id.playbutton_play_pause);
        playOrPause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playButtonListener == null)
                    return;
                boolean isPlay = (boolean) playOrPause.getTag();
                if (isPlay) {
                    playOrPause.setImageResource(R.drawable.ic_player_pause);
                    playButtonListener.pause();
                } else {
                    playOrPause.setImageResource(R.drawable.ic_player_play);
                    playButtonListener.play();
                }
            }
        });
    }

    public PlayButtonListener getPlayButtonListener() {
        return playButtonListener;
    }

    public void setPlayButtonListener(PlayButtonListener playButtonListener) {
        this.playButtonListener = playButtonListener;
    }

    public void setCurrentProgress(float progress) {
        pb.setProgress(progress);
    }

    /**
     * set play status
     *
     * @param isPlay
     */
    public void setPlayOrPauseStatus(boolean isPlay) {
        playOrPause.setImageResource(isPlay ? R.drawable.ic_player_pause : R.drawable.ic_player_play);
        playOrPause.setTag(isPlay);
    }

    public int getMax() {
        return 1;
    }

    public interface PlayButtonListener {
        void play();

        void pause();
    }
}
