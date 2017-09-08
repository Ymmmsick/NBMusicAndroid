package com.msr.nbmusic.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

/**
 * Created by Ymmmsick on 5/24/17.
 */

public class ApkUtils {

    public static boolean isAppInstalled(Context context, String pkg) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(pkg, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 安装APK文件
     */
    public static void installApk(Context context, String filePath) {
        File apkFile = new File(filePath);
        if (!apkFile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkFile.toString()),
                "application/vnd.android.package-archive");
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    /**
     * get version name
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    /**
     * get app version
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }

    /**
     * 判断服务是否运行
     *
     * @param context
     * @param serviceClassName
     * @return
     */
    public static boolean isServiceWorked(Context context, String serviceClassName) {
        ActivityManager myManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : myManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClassName.equals(service.service.getClassName()))
                return true;
        }
        return false;
    }

    /**
     * @param context
     * @param absPath
     */
    public static int getApkVersionCode(Context context, String absPath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pkgInfo = pm.getPackageArchiveInfo(absPath, PackageManager.GET_ACTIVITIES);
        if (pkgInfo != null)
            return pkgInfo.versionCode;
        return -1;
    }
}
