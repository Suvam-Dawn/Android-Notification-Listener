package com.suvamdawn.notificationlistener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class NotificationService extends NotificationListenerService {
    Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String packageName = sbn.getPackageName();
        Bundle extras = sbn.getNotification().extras;
        String titleData = extras.getString("android.title");
        String textData = extras.getCharSequence("android.text").toString();
        Log.d("Package", packageName);
        Log.d("Title", titleData);
        Log.d("Text", textData);
        Intent msgrcv = new Intent("Msg");
        msgrcv.putExtra("package", packageName);
        msgrcv.putExtra("title", titleData);
        msgrcv.putExtra("text", textData);
        LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv);
    }
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.d("Msg", "Notification Removed");
    }
}