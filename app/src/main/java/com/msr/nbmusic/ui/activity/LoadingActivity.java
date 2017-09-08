package com.msr.nbmusic.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.msr.nbmusic.R;
import com.msr.nbmusic.bean.response.EnglishBean;
import com.msr.nbmusic.contract.LoadingContract;
import com.msr.nbmusic.factory.TransformersFactory;
import com.msr.nbmusic.presenter.LoadingPresenter;
import com.msr.nbmusic.ui.base.BaseMVPActivity;
import com.msr.nbmusic.ui.widgets.font.NBTextView;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Ymmmsick on 8/18/17.
 */

public class LoadingActivity extends BaseMVPActivity<LoadingPresenter> implements LoadingContract.View {

    @BindView(R.id.loading_chinese)
    NBTextView loadingChinese;
    @BindView(R.id.loading_english)
    NBTextView loadingEnglish;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_loading;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        presenter.getEnglishWords(1);
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> e) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                e.onNext("");
            }
        }).compose(TransformersFactory.defaultSchedulers())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        baseStartActivity(MainActivity.class);
                        finish();
                    }
                });
    }

    @Override
    public LoadingPresenter createPresenter() {
        return new LoadingPresenter();
    }

    @Override
    public void onBackPressed() {
        //do nothings,can't go back
    }

    @Override
    public void getEnglishWordsSuccess(EnglishBean englishBean) {
        EnglishBean.DataBean data = englishBean.getData().get(0);
        loadingChinese.setText(data.getChinese());
        loadingEnglish.setText(data.getEnglish());
    }

}
