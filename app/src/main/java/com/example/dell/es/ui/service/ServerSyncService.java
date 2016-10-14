package com.example.dell.es.ui.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import com.example.dell.es.utils.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ServerSyncService extends IntentService {
    public ServerSyncService() {
        super("ServerSyncService");
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        String name= (String) intent.getExtras().get("nameItem");
        String[] nameSrearch=name.split("[. ]+");
        StringBuilder strName=new StringBuilder();
        for (int i=0;i<nameSrearch.length;i++){
            strName.append(nameSrearch[i]);
        }
String ggg=strName.toString().toLowerCase();
        int id = getStringResourceByName(strName.toString().toLowerCase());
        String str = "";
        StringBuffer buf = new StringBuffer();
        InputStream is = getApplicationContext().getResources().openRawResource(id);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            try {
                while ((str = reader.readLine()) != null) {
                    buf.append(str + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String[] array=buf.toString().trim().split(";");
        sendResultMessage(array);


        stopSelf();
    }

    private void sendResultMessage(String[] data) {
        Intent intent = new Intent(Constant.FILTER);
        intent.putExtra(Constant.DATA,data );
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }



    private int getStringResourceByName(String aString) {
        String packageName = "com.example.dell.es";
        int resId = getResources()
                .getIdentifier(aString, "raw", packageName);
        return resId;

    }


}
