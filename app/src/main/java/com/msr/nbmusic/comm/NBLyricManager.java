package com.msr.nbmusic.comm;

import android.text.TextUtils;

import com.msr.nbmusic.app.NBApplication;
import com.msr.nbmusic.utils.ACacheUtils;

import java.io.File;

/**
 * Created by Ymmmsick on 9/13/17.
 */

public class NBLyricManager {

    private static class SingletonHolder {
        private static final NBLyricManager instance = new NBLyricManager();
    }

    public static NBLyricManager getInstance() {
        return NBLyricManager.SingletonHolder.instance;
    }

    private ACacheUtils aCacheUtils;

    public NBLyricManager() {
        aCacheUtils = ACacheUtils.get(NBApplication.getInstance());
    }

    public File saveLyric(String songId, String lyricText) {
        aCacheUtils.put(songId, lyricText);
        return aCacheUtils.file(songId);
    }

    public String getLyric(String songId) {
        return aCacheUtils.getAsString(songId);
    }

    public File getLyricFile(String songId) {
        return aCacheUtils.file(songId);
    }

    public String addLyricNewLine(String lyric) {
        String[] lines = lyric.split("\\[");
        if (lines.length == 0)
            return lyric;
        StringBuffer sb = new StringBuffer();
        for (String line : lines) {
            if (!TextUtils.isEmpty(line))
                sb.append("[" + line + "\n");
        }
        return sb.toString();
    }
}
