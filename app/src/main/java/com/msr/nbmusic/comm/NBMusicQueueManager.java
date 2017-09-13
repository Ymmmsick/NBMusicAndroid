package com.msr.nbmusic.comm;

/**
 * Created by Ymmmsick on 2017-09-11.
 */

public class NBMusicQueueManager {
    private static class SingletonHolder {
        private static final NBMusicQueueManager instance = new NBMusicQueueManager();
    }

    public static NBMusicQueueManager getInstance() {
        return NBMusicQueueManager.SingletonHolder.instance;
    }
}
