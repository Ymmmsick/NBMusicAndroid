package com.msr.nbmusic.api.error;

import io.reactivex.Observer;


/**
 * 封装onerror  统一网络请求错误
 * 适用场景：单个请求的onSubscribe
 *
 * @param <T>
 * @author Ymmmsick
 * @date 2017-05-16 17:48:08
 */
public abstract class LObserver<T> implements Observer<T> {

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ResponeThrowable) {
            onError((ResponeThrowable) e);
        } else {
            onError(new ResponeThrowable(e, ERROR.UNKNOWN));
        }
    }

    public abstract void onError(ResponeThrowable e);

    @Override
    public void onComplete() {

    }
}
