package com.wwj.service.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017/11/16 0016.
 */
public class ServiceLifecycle extends Service {

    private final String TAG=getClass().getSimpleName();
    private int STATE=0;
    private final int SERVICE_CREATE=1;
    private final int SERVICE_DESTROY=2;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
       Log.e(TAG,"===========onBind============");
        return new MyBinder();
    }

    class  MyBinder extends Binder{
        ServiceLifecycle getService(){
            return ServiceLifecycle.this;
        }
    }

    public int add(int a,int b){
        return a+b;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG,"===========onUnbind============");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String values=intent.getStringExtra("hello");
        Log.e(TAG,"===========onStartCommand===========values="+values);
        return super.onStartCommand(intent, flags, startId);
    }

//    @Override
//    public void onStart(Intent intent, int startId) {
//        super.onStart(intent, startId);
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        STATE=SERVICE_CREATE;
       Log.e(TAG, "===========onCreate========STATE="+STATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        STATE=SERVICE_DESTROY;
       Log.e(TAG, "===========onDestroy==========STATE="+STATE);
    }

    public int getServiceState(){
        return STATE;
    }
}
