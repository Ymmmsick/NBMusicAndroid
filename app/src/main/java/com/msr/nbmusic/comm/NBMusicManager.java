package com.msr.nbmusic.comm;

/**
 * Created by Ymmmsick on 2017-09-11.
 */

public class NBMusicManager {
    private static class SingletonHolder {
        private static final NBMusicManager instance = new NBMusicManager();
    }

    public static NBMusicManager getInstance() {
        return NBMusicManager.SingletonHolder.instance;
    }
}
