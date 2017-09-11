package com.msr.nbmusic.api;

import com.msr.nbmusic.bean.base.BaseResBean;
import com.msr.nbmusic.bean.response.EnglishBean;
import com.msr.nbmusic.bean.response.NewMusicBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * show api
 * Created by Ymmmsick on 17/5/7.
 *
 * @link https://www.showapi.com/
 */
public interface ShowApi {

    String BASE_URL = "http://route.showapi.com/";//base address

    @GET("1211-1")
    Observable<BaseResBean<EnglishBean>> getEnglishWords(@Query("count") int count);//获取英文装逼语句

    @GET("213-4")
    Observable<BaseResBean<NewMusicBean>> getHotMusic(@Query("topid") int topid);//获取热门榜单歌曲
}
