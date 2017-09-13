package com.msr.nbmusic.model;

import com.msr.nbmusic.api.ShowApi;
import com.msr.nbmusic.bean.base.BaseResBean;
import com.msr.nbmusic.bean.response.LrcBean;
import com.msr.nbmusic.contract.MusicPlayContract;
import com.msr.nbmusic.factory.ShowApiServiceFactory;

import io.reactivex.Observable;

/**
 * Created by Ymmmsick on 9/12/17.
 */

public class MusicPlayModelImpl implements MusicPlayContract.Model {

    ShowApi api = ShowApiServiceFactory.getInstance().createService(ShowApi.class);

    @Override
    public Observable<BaseResBean<LrcBean>> getMusicLrc(int musicid) {
        return api.getMusicLrc(musicid);
    }
}
