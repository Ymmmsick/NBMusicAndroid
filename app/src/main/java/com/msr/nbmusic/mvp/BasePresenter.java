package com.msr.nbmusic.mvp;

import java.lang.ref.WeakReference;

/**
 * @param <V> view
 * @param <M> model
 * @author Ymmmsick
 * @date 2017-05-12 09:27:38
 */
public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter {

    private WeakReference<V> viewRef;
    protected M mModel;

    @Override
    public void attachView(IView view) {
        viewRef = new WeakReference(view);
        mModel = (M) createModel();
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    /**
     * 获取 presenter 对应的view
     *
     * @return 如果对应, 返回对应实例, 否则返回 null
     */
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    /**
     * 检查 presenter 是否存在对应View
     *
     * @return 如果存在返回true, 否则返回false
     */
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    protected abstract IModel createModel();
}
