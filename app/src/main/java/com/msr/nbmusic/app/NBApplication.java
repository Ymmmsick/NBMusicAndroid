package com.msr.nbmusic.app;


import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.facebook.stetho.Stetho;
import com.msr.nbmusic.comm.NBSPManager;
import com.msr.nbmusic.utils.ProcessUtils;

/**
 * Created by Ymmmsick on 17/5/7.
 */
public class NBApplication extends Application {

    private static NBApplication linlinApplication;

    public static NBApplication getInstance() {
        return linlinApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        linlinApplication = this;
        //便面多进程重复初始化
        if (getApplicationInfo().packageName.equals(ProcessUtils.getCurProcessName(getApplicationContext()))) {
            registerActivityLifecycle();
            initOther();
        }
    }

    private void initOther() {
//        LLDBManager.getInstance().init(this);//db init
//        LLBSManager.getInstance().init(this);//lbs init
        NBSPManager.getInstance().init(this);//secure sp init
//        LLUserManager.getInstance().loadUserDataFromSP();//从SP中读取用户数据,必须在LLRongCloudManager之前，因为LLRongCloudManager会进行用户登录
        Stetho.initializeWithDefaults(this);//初始化facebook stetho调试工具
    }

    /**
     * manager activity
     */
    private void registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                NBAppManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                NBAppManager.getInstance().removeActivity(activity);
            }
        });
    }
}
