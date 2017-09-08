package com.msr.nbmusic.ui.activity;

import android.os.Bundle;

import com.msr.nbmusic.R;
import com.msr.nbmusic.factory.TransformersFactory;
import com.msr.nbmusic.ui.base.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class LoadingActivity extends BaseActivity {

    @Override
    public int returnLayoutID() {
        return R.layout.activity_loading;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> e) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                e.onNext("");
            }
        }).compose(TransformersFactory.defaultSchedulers())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        baseStartActivity(GuideActivity.class);
                        finish();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        //do nothings,can't go back
    }
}
