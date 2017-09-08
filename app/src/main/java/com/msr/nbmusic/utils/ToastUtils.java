package com.msr.nbmusic.utils;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * @author Ymmmsick
 * @date 2017-05-11 18:47:50
 */
public class ToastUtils {

    static Toast toast = null;

    public static void show(Context context, String text) {
        try {
            if (toast != null) {
                toast.setText(text);
            } else {
                toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            }
            toast.show();
        } catch (Exception e) {//子线程中Toast异常情况处理
            Looper.prepare();
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    public static void show(Context context, @StringRes int resId) {
        show(context, context.getString(resId));
    }

    public static void showInOtherThread(final Context context, final String text) {
        ThreadUtil.getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
