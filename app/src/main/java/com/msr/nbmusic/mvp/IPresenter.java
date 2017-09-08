package com.msr.nbmusic.mvp;


/**
 * @author Ymmmsick
 * @date 2017-05-12 09:29:14
 */
public interface IPresenter {
    void attachView(IView view);

    void detachView(boolean retainInstance);
}
