package com.msr.nbmusic.api.error;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * 对第一个请求的错误进行重新发射到下一个Obs
 *
 * @param <T>
 * @author Ymmmsick
 * @date 2017-05-12 16:59:34
 */
public class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(Throwable throwable) throws Exception {
        if (throwable instanceof ResponeThrowable) {//避免throwable重复包装
            return Observable.error(throwable);
        }
        return Observable.error(ExceptionHandle.handleException(throwable));
    }
}