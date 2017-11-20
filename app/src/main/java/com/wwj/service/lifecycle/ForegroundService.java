package com.wwj.service.lifecycle;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
public class ForegroundService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //添加下列代码将后台Service变成前台Service
        //构建"点击通知后打开MainActivity"的Intent对象
        Intent notificationIntent = new Intent(this,MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        //新建Builer对象
        Notification.Builder builer = new Notification.Builder(this);
        builer.setContentTitle("前台服务通知的标题");//设置通知的标题
        builer.setContentText("前台服务通知的内容");//设置通知的内容
        builer.setSmallIcon(R.mipmap.ic_launcher);//设置通知的图标
        builer.setWhen(System.currentTimeMillis());
        builer.setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND); //向通知添加声音、闪灯和振动效果的最简单、
        // 最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
        builer.setContentIntent(pendingIntent);//设置点击通知后的操作


        Notification notification = builer.build();//将Builder对象转变成普通的notification
        startForeground(1, notification);//让Service变成前台Service,并在系统的状态栏显示出来

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }
}
