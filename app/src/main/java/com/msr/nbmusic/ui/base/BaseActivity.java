package com.msr.nbmusic.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.msr.nbmusic.R;
import com.msr.nbmusic.ui.widgets.StatusBarUtil;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Ymmmsick on 3/6/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;
    private ActionBar actionBar;
    private ImageView back;

    public abstract int returnLayoutID();

    public abstract void TODO(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(returnLayoutID());
        unbinder = ButterKnife.bind(this);
        TODO(savedInstanceState);
    }

    private void initActionBar() {
        StatusBarUtil.setStatusBarColor(this, R.color.white);
        StatusBarUtil.setStatusBarLightMode(this);
        actionBar = getSupportActionBar();
        if (actionBar == null)
            return;
        ViewGroup rootView = (ViewGroup) getWindow().getDecorView();
        rootView.setClipChildren(false);
        rootView.setClipToPadding(false);
        actionBar.setElevation(0);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL;
        View actionbarLayout = LayoutInflater.from(this).inflate(R.layout.view_actionbar, null);
        actionBar.setCustomView(actionbarLayout, layoutParams);
        Toolbar parent = (Toolbar) actionbarLayout.getParent();
        parent.setContentInsetsAbsolute(0, 0);
        back = (ImageView) actionbarLayout.findViewById(R.id.view_actionbar_back);
    }

    /**
     * set actionbar back icon and cilck finish
     */
    protected void setActionBarBackEnable() {
        if (actionBar == null)
            throw new RuntimeException("actionbar is null,check your theme actionbar enable");
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
        unbinder.unbind();
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

    @Override
    public void finish() {
        super.finish();
    }


    //Umeng errors need
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    //Umeng errors need
    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
