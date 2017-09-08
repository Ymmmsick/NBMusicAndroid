package com.msr.nbmusic.api.error;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 适用场景：doOnError,需要配合
 *
 * @see com.ehuu.linlin.factory.TransformersFactory otherTransformer
 * Created by Ymmmsick on 5/18/17.
 */

public abstract class LConsumer implements Consumer<Throwable> {

    @Override
    public void accept(@NonNull Throwable e) throws Exception {
        e.printStackTrace();
        if (e instanceof ResponeThrowable) {
            accept((ResponeThrowable) e);
        } else {
            accept(new ResponeThrowable(e, ERROR.UNKNOWN));
        }
    }

    public abstract void accept(ResponeThrowable e) throws Exception;
}
