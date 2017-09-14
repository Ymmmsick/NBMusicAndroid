package com.msr.nbmusic.model;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.msr.nbmusic.bean.db.LocalMusic;
import com.msr.nbmusic.comm.NBConstants;
import com.msr.nbmusic.contract.MainContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class MainModelImpl implements MainContract.Model {

    @Override
    public Observable<List<LocalMusic>> scanMusic(final Context context) {
        return Observable.create(new ObservableOnSubscribe<List<LocalMusic>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<LocalMusic>> observableEmitter) throws Exception {
                List<LocalMusic> localMusics = new ArrayList<>();
                Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                        null, MediaStore.Audio.AudioColumns.IS_MUSIC);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        LocalMusic localMusic = new LocalMusic();
                        localMusic.setFileName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
                        localMusic.setSinger(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                        localMusic.setFilePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                        localMusic.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
                        localMusic.setFileSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));
                        if (localMusic.getFileSize() > NBConstants.LOCAL_MUSIC_MIN_SIZE) {
                            // 注释部分是切割标题，分离出歌曲名和歌手 （本地媒体库读取的歌曲信息不规范）
                            if (localMusic.getFileName().contains("-")) {
                                String[] str = localMusic.getFileName().split("-");
                                localMusic.setSinger(str[0]);
                                localMusic.setFileName(str[1]);
                            }
                            localMusics.add(localMusic);
                        }
                    }
                    cursor.close();
                }
                observableEmitter.onNext(localMusics);
            }
        }).subscribeOn(Schedulers.io());
    }
}
