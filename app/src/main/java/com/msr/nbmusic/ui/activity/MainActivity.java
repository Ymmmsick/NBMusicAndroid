package com.msr.nbmusic.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.msr.nbmusic.R;
import com.msr.nbmusic.contract.MainContract;
import com.msr.nbmusic.presenter.MainPresenterImpl;
import com.msr.nbmusic.ui.adapter.MainPagerAdapter;
import com.msr.nbmusic.ui.base.BaseMVPActivity;
import com.msr.nbmusic.ui.fragment.HomeFragment;
import com.msr.nbmusic.ui.fragment.TestFragment;
import com.msr.nbmusic.ui.widgets.BanSlideViewPager;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseMVPActivity<MainPresenterImpl> implements MainContract.View {

    @BindView(R.id.main_tab_music)
    RadioButton mainTabMusic;
    @BindView(R.id.main_tab_mine)
    RadioButton mainTabMine;
    @BindView(R.id.main_viewpager)
    BanSlideViewPager mainViewpager;
    @BindView(R.id.main_play)
    ImageView mainPlay;

    private Class[] fragments = {HomeFragment.class, TestFragment.class, TestFragment.class, TestFragment.class};
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
    }

    @Override
    public MainPresenterImpl createPresenter() {
        return new MainPresenterImpl();
    }

    @OnClick({R.id.main_tab_music, R.id.main_tab_mine,R.id.main_play})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tab_music://Music
                mainViewpager.setCurrentItem(0);
                break;
            case R.id.main_tab_mine://Mine
                mainViewpager.setCurrentItem(1);
                break;
            case R.id.main_play:
                break;
        }
    }
}
