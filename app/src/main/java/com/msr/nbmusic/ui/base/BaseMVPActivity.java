package com.msr.nbmusic.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.msr.nbmusic.mvp.IPresenter;
import com.msr.nbmusic.mvp.IView;
import com.michaelflisar.rxbus2.rx.RxDisposableManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Ymmmsick on 3/6/17.
 */

public abstract class BaseMVPActivity<P extends IPresenter> extends RxAppCompatActivity {

    private Unbinder unbinder;
    protected P presenter;

    public abstract int returnLayoutID();

    public abstract void TODO(Bundle savedInstanceState);

    public abstract P createPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(returnLayoutID());
        unbinder = ButterKnife.bind(this);
        presenter = createPresenter();
        presenter.attachView((IView) this);
        TODO(savedInstanceState);
    }

    /**
     * get activity
     *
     * @return
     */
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(false);
        unbinder.unbind();
        RxDisposableManager.unsubscribe(this);
    }

    public void baseStartActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void baseStartActivity(Class cls, Bundle data) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(data);
        startActivity(intent);
    }

    public void baseStartActivityForResult(Class cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    public void baseStartActivityForResult(Class cls, Bundle data, int requestCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(data);
        startActivityForResult(intent, requestCode);
    }

    public void finish() {
        super.finish();
    }
}
