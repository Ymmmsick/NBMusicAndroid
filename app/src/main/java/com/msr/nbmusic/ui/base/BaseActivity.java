package com.msr.nbmusic.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Ymmmsick on 3/6/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    public abstract int returnLayoutID();

    public abstract void TODO(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(returnLayoutID());
        unbinder = ButterKnife.bind(this);
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
