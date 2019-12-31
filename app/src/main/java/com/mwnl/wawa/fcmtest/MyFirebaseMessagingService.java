package com.mwnl.wawa.fcmtest;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService{
    int count=0;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("FCM", "onMessageReceived:"+remoteMessage.getFrom());
        if(count==0) {
            if (remoteMessage.getData().size() > 0) {
                Log.d("FCM", "Message data payload: " + remoteMessage.getData());
            }
            // Check if message contains a notification payload.
            if (remoteMessage.getData() != null) {
                String title = remoteMessage.getData().get("title");
                String body = remoteMessage.getData().get("body");
                Log.d("FCM", "Message Notification Title: " + title);
                Log.d("FCM", "Message Notification Body: " + body);
                Tool tool = new Tool(this);
                tool.setNotification(this, title, body);

                count++;

            }
        }else if(count == 1){
            count++;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Log.d("time","error");
            }
            count=0;
        }

    }
}
