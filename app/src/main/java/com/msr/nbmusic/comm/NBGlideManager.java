package com.msr.nbmusic.comm;

import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.msr.nbmusic.app.NBApplication;

/**
 * Created by Ymmmsick on 4/19/17.
 */

public class NBGlideManager {

    /**
     * load image
     *
     * @param url
     * @param imageView
     */
    public static void loadImage(String url, ImageView imageView) {
        DrawableRequestBuilder builder = Glide.with(NBApplication.getInstance())
                .load(url)
                .centerCrop()
                .crossFade();
        builder.into(imageView);
    }

    /**
     * 清除硬盘缓存
     */
    public static void clearDiskCache() {
        Glide.get(NBApplication.getInstance()).clearDiskCache();
    }

}
