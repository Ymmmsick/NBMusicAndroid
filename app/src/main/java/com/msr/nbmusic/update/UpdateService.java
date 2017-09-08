package com.msr.nbmusic.update;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.msr.nbmusic.R;
import com.msr.nbmusic.comm.NBConstants;
import com.msr.nbmusic.utils.ApkUtils;
import com.msr.nbmusic.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


public class UpdateService extends Service {

    private Timer timer;
    private String downloadURL;
    private String versionCode;
    private String savePath;
    private String mApkName;
    private int progress = 0;
    private final int DOWNLOAD = 1;
    private final int DOWNLOAD_FINISH = 2;
    private final int DOWNLOAD_ERROR = 3;
    private boolean cancelUpdate = false;
    //下载显示弹框
    Dialog dialog;
    //
    private TextView mSize;
    private TextView mSpeed;
    private String total;
    private int current;
    private String currentSize;

    private NotificationManager notificationManager;
    private Notification notification;

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 正在下载
                case DOWNLOAD:
                    // 设置进度条位置
                    notifi(UpdateService.this, progress, false);
                    break;
                case DOWNLOAD_FINISH:
                    // 停止计时
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                    notifi(UpdateService.this, progress, true);
                    // 安装文件
                    ApkUtils.installApk(UpdateService.this, savePath + File.separator + mApkName);
                    stopSelf();
                    break;
                case DOWNLOAD_ERROR:
                    // 下载失败
                    timer.cancel();
                    notificationManager.cancel(0);
                    Toast.makeText(UpdateService.this, "下载新版本失败，请检查网络状况", Toast.LENGTH_SHORT).show();
                    stopSelf();
                    break;
                default:
                    break;
            }
        }

        ;
    };


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            downloadURL = intent.getStringExtra("downloadURL");
            versionCode = intent.getStringExtra("versionCode");
            savePath = FileUtils.getCacheDir(this).getAbsolutePath();
            mApkName = NBConstants.APK_NAME;
            // 启动新线程下载软件
            new downloadApkThread().start();

        }
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 下载文件线程
     */
    private class downloadApkThread extends Thread {
        @Override
        public void run() {
            try {
                URL url = new URL(downloadURL);
                // 创建连接
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.connect();
                // 获取文件大小
                int length = conn.getContentLength();
                total = String.valueOf(length / Math.pow(2, 20))
                        .substring(0, 4);
                // 创建输入流
                InputStream is = conn.getInputStream();
                File file = new File(savePath);
                // 判断文件目录是否存在
                if (!file.exists()) {
                    file.mkdir();
                }
                File apkFile = new File(savePath + File.separator + mApkName);
                if (!apkFile.exists()) {
                    apkFile.createNewFile();
                } else {//如果存在本地apk，判断版本进行更新
                    int apkVersionCode = ApkUtils.getApkVersionCode(UpdateService.this, apkFile.getAbsolutePath());
                    if (versionCode.equals(apkVersionCode + "")) {
                        // 安装文件
                        ApkUtils.installApk(UpdateService.this, apkFile.getAbsolutePath());
                        stopSelf();
                        return;
                    }
                }
                FileOutputStream fos = new FileOutputStream(apkFile);
                int count = 0;
                // 启动计时器
                timer = new Timer();
                timer.schedule(new MyTimerTask(), 0, 500);
                // 缓存
                byte buf[] = new byte[1024];
                // 写入到文件中
                do {
                    int numread = is.read(buf);
                    count += numread;
                    current = count;
                    // 计算进度条位置
                    progress = (int) (((float) count / length) * 100);
                    // }
                    if (numread <= 0) {
                        // 下载完成
                        mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                        break;
                    }
                    // 写入文件
                    fos.write(buf, 0, numread);
                } while (!cancelUpdate);// 点击取消就停止下载.
                fos.close();
                is.close();
                // }
            } catch (Exception e) {
                e.printStackTrace();
                mHandler.sendEmptyMessage(DOWNLOAD_ERROR);
            }
        }
    }

    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            // 更新进度
            mHandler.sendEmptyMessage(DOWNLOAD);
            Intent intent = new Intent("module.appupdate.progress");
            intent.putExtra("progress", progress);
            sendBroadcast(intent);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onDestroy();
    }


    private void notifi(Context context, int progress, boolean isFinish) {
        if (isFinish) {
            notificationManager.cancel(0);
        } else {
            if (notificationManager != null && notification != null) {
                RemoteViews contentView = notification.contentView;
                contentView.setTextViewText(R.id.update_percent, progress + "%");
                contentView.setProgressBar(R.id.update_progress, 100, progress, false);
                notificationManager.notify(0, notification);
            } else {
                notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notification = new Notification();
                notification.flags = Notification.FLAG_AUTO_CANCEL;
                //修改2.3.6出现更新的bug
                notification.contentIntent = PendingIntent.getActivity(this, 0, new Intent(), 0);
                RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.view_update_notification);
                contentView.setTextViewText(R.id.update_title, "下载中");
                contentView.setTextViewText(R.id.update_percent, "0%");
                contentView.setProgressBar(R.id.update_progress, 100, progress, false);
                notification.contentView = contentView;
                notification.tickerText = "正在下载";
                notification.icon = android.R.drawable.stat_sys_download;
                notificationManager.notify(0, notification);
            }
        }
    }
}
