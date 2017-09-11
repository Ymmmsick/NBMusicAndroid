package com.msr.nbmusic.model;

import com.msr.nbmusic.api.ShowApi;
import com.msr.nbmusic.bean.base.BaseResBean;
import com.msr.nbmusic.bean.response.NewMusicBean;
import com.msr.nbmusic.contract.HomeContract;
import com.msr.nbmusic.factory.ShowApiServiceFactory;

import io.reactivex.Observable;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class HomeModelImpl implements HomeContract.Model {

    @Override
    public Observable<BaseResBean<NewMusicBean>> getMusicData(int topid) {
        ShowApi api = ShowApiServiceFactory.getInstance().createService(ShowApi.class);
        return api.getHotMusic(topid);
    }
}
