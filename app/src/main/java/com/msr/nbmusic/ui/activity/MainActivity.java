package com.msr.nbmusic.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.msr.nbmusic.R;
import com.msr.nbmusic.contract.MainContract;
import com.msr.nbmusic.presenter.MainPresenterImpl;
import com.msr.nbmusic.ui.adapter.MainPagerAdapter;
import com.msr.nbmusic.ui.base.BaseMVPActivity;
import com.msr.nbmusic.ui.fragment.MineFragment;
import com.msr.nbmusic.ui.fragment.MusicFragment;
import com.msr.nbmusic.ui.widgets.BanSlideViewPager;
import com.msr.nbmusic.utils.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MainActivity extends BaseMVPActivity<MainPresenterImpl> implements MainContract.View {

    @BindView(R.id.main_tab_music)
    RadioButton mainTabMusic;
    @BindView(R.id.main_tab_mine)
    RadioButton mainTabMine;
    @BindView(R.id.main_viewpager)
    BanSlideViewPager mainViewpager;

    private Class[] fragments = {MusicFragment.class, MineFragment.class};
    private MainPagerAdapter adapter;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        adapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        mainViewpager.setOffscreenPageLimit(fragments.length);
        mainViewpager.setAdapter(adapter);
        mainViewpager.setCurrentItem(0);
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(READ_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                if (aBoolean)
                    presenter.scanMusic(getBaseContext());
                else
                    ToastUtils.show(MainActivity.this,"请开启读取sdcard权限");
            }
        });

    }

    @Override
    public MainPresenterImpl createPresenter() {
        return new MainPresenterImpl();
    }

    @OnClick({R.id.main_tab_music, R.id.main_tab_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tab_music://Music
                mainViewpager.setCurrentItem(0);
                break;
            case R.id.main_tab_mine://Mine
                mainViewpager.setCurrentItem(1);
                break;
        }
    }
}
