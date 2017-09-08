package com.msr.nbmusic.factory;

import com.msr.nbmusic.api.error.HandleFuc;
import com.msr.nbmusic.api.error.HttpResponseFunc;
import com.msr.nbmusic.bean.base.BaseResponseBean;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Ymmmsick
 */
public class TransformersFactory {

    public static <T> ObservableTransformer<T, T> defaultSchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> all_io() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
            }
        };
    }

    public static <T> ObservableTransformer<BaseResponseBean<T>, T> errorTransformer() {
        return new ObservableTransformer<BaseResponseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<BaseResponseBean<T>> upstream) {
                return (Observable<T>) upstream.map(new HandleFuc<T>()).onErrorResumeNext(new HttpResponseFunc<T>());
            }
        };
    }

    /**
     * 适用于单个请求
     *
     * @param lifecycleProvider
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResponseBean<T>, T> commonTransformerA(final LifecycleProvider lifecycleProvider) {
        return new ObservableTransformer<BaseResponseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<BaseResponseBean<T>> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                        .map(new HandleFuc<T>()).onErrorResumeNext(new HttpResponseFunc<T>())
                        .compose(lifecycleProvider.<T>bindUntilEvent(ActivityEvent.DESTROY));
            }
        };
    }

    /**
     * 适用于单个请求
     *
     * @param lifecycleProvider
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseResponseBean<T>, T> commonTransformerF(final LifecycleProvider lifecycleProvider) {
        return new ObservableTransformer<BaseResponseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<BaseResponseBean<T>> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                        .map(new HandleFuc<T>()).onErrorResumeNext(new HttpResponseFunc<T>())
                        .compose(lifecycleProvider.<T>bindUntilEvent(FragmentEvent.DESTROY));
            }
        };
    }

    /**
     * 适用于两个请求顺序执行
     *
     * @param lifecycleProvider
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> otherTransformerF(final LifecycleProvider lifecycleProvider) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                        .onErrorResumeNext(new HttpResponseFunc<T>())
                        .compose(lifecycleProvider.<T>bindUntilEvent(FragmentEvent.DESTROY));
            }
        };
    }

    /**
     * 适用于两个请求顺序执行
     *
     * @param lifecycleProvider
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> otherTransformerA(final LifecycleProvider lifecycleProvider) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                        .onErrorResumeNext(new HttpResponseFunc<T>())
                        .compose(lifecycleProvider.<T>bindUntilEvent(ActivityEvent.DESTROY));
            }
        };
    }
}