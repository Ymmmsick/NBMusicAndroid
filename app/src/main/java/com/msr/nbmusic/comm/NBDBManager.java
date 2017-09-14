package com.msr.nbmusic.comm;

import com.msr.nbmusic.app.NBApplication;
import com.msr.nbmusic.bean.db.DaoMaster;
import com.msr.nbmusic.bean.db.DaoSession;


/**
 *
 * Created by SIMEON on 2017/9/11.
 */

public class NBDBManager {

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private NBDBManager() {
        init();
    }

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder {
        private static final NBDBManager INSTANCE = new NBDBManager();
    }

    /**
     * 对外唯一实例的接口
     *
     * @return
     */
    public static NBDBManager getInstance(){
        return SingleInstanceHolder.INSTANCE;
    }

    /**
     * 初始化数据
     */
    private void init(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(NBApplication.getInstance(),
                "nbmusic.db");
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getmDaoMaster(){
        return mDaoMaster;
    }

    public DaoSession getmDaoSession(){
        return mDaoSession;
    }

    public DaoSession getNewSession(){
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

}
