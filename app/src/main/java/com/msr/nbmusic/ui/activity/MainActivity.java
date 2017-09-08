package com.msr.nbmusic.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.msr.nbmusic.R;
import com.msr.nbmusic.contract.MainContract;
import com.msr.nbmusic.event.BADialogListener;
import com.msr.nbmusic.presenter.MainPresenterImpl;
import com.msr.nbmusic.ui.adapter.MainPagerAdapter;
import com.msr.nbmusic.ui.base.BaseMVPActivity;
import com.msr.nbmusic.ui.fragment.HomeFragment;
import com.msr.nbmusic.ui.fragment.TestFragment;
import com.msr.nbmusic.ui.widgets.BanSlideViewPager;
import com.msr.nbmusic.ui.widgets.DialogHelper;
import com.msr.nbmusic.utils.ToastUtils;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import butterknife.BindView;

public class MainActivity extends BaseMVPActivity<MainPresenterImpl> implements MainContract.View {

    @BindView(R.id.main_tabs)
    AdvancedPagerSlidingTabStrip mainTabs;
    @BindView(R.id.main_viewpager)
    BanSlideViewPager mainViewpager;

    private Class[] fragments = {HomeFragment.class, TestFragment.class, TestFragment.class, TestFragment.class};
    private MainPagerAdapter adapter;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        mainViewpager.setOffscreenPageLimit(MainPagerAdapter.VIEW_SIZE);
        mainViewpager.setScrollEnable(false);
        adapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        mainViewpager.setAdapter(adapter);
        mainTabs.setViewPager(mainViewpager);
        mainViewpager.setCurrentItem(0);
        mainTabs.showDot(0, "99+");

        DialogHelper.openAdsDialog(this, new BADialogListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(getActivity(), "you click ads!");
            }
        });
    }

    @Override
    public MainPresenterImpl createPresenter() {
        return new MainPresenterImpl();
    }

}
