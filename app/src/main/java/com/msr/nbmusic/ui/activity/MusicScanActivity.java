package com.msr.nbmusic.ui.activity;

import android.os.Bundle;

import com.msr.nbmusic.R;
import com.msr.nbmusic.contract.MusicScanContract;
import com.msr.nbmusic.presenter.MusicScanPresenterImpl;
import com.msr.nbmusic.ui.base.BaseMVPActivity;
import com.msr.nbmusic.utils.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

/**
 * Created by Simeon on 2017/9/14.
 */

public class MusicScanActivity extends BaseMVPActivity<MusicScanPresenterImpl> implements MusicScanContract.View {
    @Override
    public int returnLayoutID() {
        return R.layout.activity_musicscan;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(READ_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                if (aBoolean)
                    presenter.scanMusic(getBaseContext());
                else
                    ToastUtils.show(MusicScanActivity.this, "请开启读取sdcard权限");
            }
        });
    }

    @Override
    public MusicScanPresenterImpl createPresenter() {
        return null;
    }
}
