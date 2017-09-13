package com.msr.nbmusic.comm;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.util.Log;

public class NBMusicPlayerManager implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener {

    private static class SingletonHolder {
        private static final NBMusicPlayerManager instance = new NBMusicPlayerManager();
    }

    public static NBMusicPlayerManager getInstance() {
        return NBMusicPlayerManager.SingletonHolder.instance;
    }

    public MediaPlayer mediaPlayer; // 媒体播放器
    private Timer mTimer = new Timer(); // 计时器
    private PlayerProgressChangeListener progressChangeListener;

    // 初始化播放器
    public NBMusicPlayerManager() {
        super();
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnPreparedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 每一秒触发一次
        mTimer.schedule(timerTask, 0, 1000);
    }

    // 计时器
    TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            if (mediaPlayer == null)
                return;
            if (mediaPlayer.isPlaying()) {
                handler.sendEmptyMessage(0); // 发送消息
            }
        }
    };

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int position = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            if (duration > 0) {
                // 计算进度（获取进度条最大刻度*当前音乐播放位置 / 当前音乐时长）
                float percent = position / (float) duration;
                if (progressChangeListener != null) {
                    progressChangeListener.onProgressChange(percent, position);
                }
            }
        }
    };

    public void play() {
        mediaPlayer.start();
        if (progressChangeListener != null)
            progressChangeListener.onMusicStart();
    }

    /**
     * @param url url地址
     */
    public void playUrl(String url) {
        reset();
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url); // 设置数据源
            mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    if (progressChangeListener != null)
                        progressChangeListener.onMusicStart();
                }
            });
            mediaPlayer.prepare(); // prepare自动播放
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 暂停
    public void pause() {
        mediaPlayer.pause();
        if (progressChangeListener != null)
            progressChangeListener.onMusicPause();
    }

    // 停止
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void reset() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
    }

    // 播放准备
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        Log.e("mediaPlayer", "onPrepared");
    }

    // 播放完成
    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.e("mediaPlayer", "onCompletion");
    }

    /**
     * 缓冲更新
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        if (progressChangeListener != null)
            progressChangeListener.onBufferingUpdate(percent / 100.f);
        Log.e("% play", percent + " buffer");
    }

    public PlayerProgressChangeListener getProgressChangeListener() {
        return progressChangeListener;
    }

    public void setProgressChangeListener(PlayerProgressChangeListener progressChangeListener) {
        this.progressChangeListener = progressChangeListener;
    }

    public interface PlayerProgressChangeListener {
        void onBufferingUpdate(float percent);

        void onProgressChange(float percent, long current);

        void onMusicStart();

        void onMusicPause();
    }
}