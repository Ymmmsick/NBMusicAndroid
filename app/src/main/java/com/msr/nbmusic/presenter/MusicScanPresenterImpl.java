package com.msr.nbmusic.presenter;

import android.content.Context;
import android.util.Log;

import com.msr.nbmusic.bean.db.LocalMusic;
import com.msr.nbmusic.bean.db.LocalMusicDao;
import com.msr.nbmusic.comm.NBDBManager;
import com.msr.nbmusic.contract.MusicScanContract;
import com.msr.nbmusic.factory.TransformersFactory;
import com.msr.nbmusic.model.MusicScanModelImpl;
import com.msr.nbmusic.mvp.BasePresenter;
import com.msr.nbmusic.mvp.IModel;
import com.msr.nbmusic.utils.ToastUtils;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Simeon on 2017/9/14.
 */

public class MusicScanPresenterImpl extends BasePresenter<MusicScanContract.View, MusicScanModelImpl>
        implements MusicScanContract.Presenter {

    @Override
    protected IModel createModel() {
        return new MusicScanModelImpl();
    }


    @Override
    public void scanMusic(final Context context) {
        mModel.scanMusic(context)
                .compose(TransformersFactory.<List<LocalMusic>>defaultSchedulersA((LifecycleProvider) getView()))
                .subscribe(new Consumer<List<LocalMusic>>() {
                    @Override
                    public void accept(@NonNull List<LocalMusic> localMusics) throws Exception {
                        for (LocalMusic localMusic : localMusics) {
                            Log.i("JOKER", localMusic.toString());
                        }
                        ToastUtils.show(context, "扫描成功共" + localMusics.size() + "歌曲");
                    }
                });
    }


    /**
     * insert musics
     *
     * @param localMusics
     */
    public void insertUserList(List<LocalMusic> localMusics) {
        if (localMusics == null || localMusics.isEmpty()) {
            return;
        }
        LocalMusicDao musicDao = NBDBManager.getInstance().getmDaoSession().getLocalMusicDao();
        musicDao.insertInTx(localMusics);
    }

}
