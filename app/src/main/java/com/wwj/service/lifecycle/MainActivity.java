package com.wwj.service.lifecycle;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button startService,stopService,bindService,unbindService,startForegroundService;
    private final String TAG=getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        startService=(Button)findViewById(R.id.startService);
        stopService=(Button)findViewById(R.id.stopService);
        bindService=(Button)findViewById(R.id.bindService);
        unbindService=(Button)findViewById(R.id.unbindService);
        startForegroundService= (Button) findViewById(R.id.startForegroundService);

        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        startForegroundService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this, ServiceLifecycle.class);
        switch (v.getId()){
            case R.id.startService:
                intent.putExtra("hello","hello world");
                startService(intent);
                break;
            case R.id.stopService:
                stopService(intent);
                break;
            case R.id.bindService:
                bindService(intent, mServcieConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbindService:
                unbindService(mServcieConnection);
                break;
            case R.id.startForegroundService:
                intent.setClass(this,ForegroundService.class);
                startService(intent);
                break;
        }
    }

    private ServiceConnection mServcieConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "===========onServiceConnected===========");
            ServiceLifecycle.MyBinder serviceLifecycle=(ServiceLifecycle.MyBinder)service;
            int result=serviceLifecycle.getService().add(5,6);
            int state=serviceLifecycle.getService().getServiceState();
//            Log.d(TAG, "===========result="+ result+"========state="+state);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "===========onServiceDisconnected===========");
        }
    };

}
