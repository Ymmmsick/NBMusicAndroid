package com.msr.nbmusic.model;

import com.msr.nbmusic.api.ShowApi;
import com.msr.nbmusic.bean.base.BaseResBean;
import com.msr.nbmusic.bean.response.EnglishBean;
import com.msr.nbmusic.contract.LoadingContract;
import com.msr.nbmusic.factory.ShowApiServiceFactory;

import io.reactivex.Observable;

/**
 * Created by Ymmmsick on 9/8/17.
 */

public class LoadingModel implements LoadingContract.Model {

    @Override
    public Observable<BaseResBean<EnglishBean>> getEnglishWords(int count) {
        return ShowApiServiceFactory.getInstance().createService(ShowApi.class)
                .getEnglishWords(count);
    }

}
