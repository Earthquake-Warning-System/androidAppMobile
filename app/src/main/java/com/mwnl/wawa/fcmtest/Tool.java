package com.mwnl.wawa.fcmtest;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import br.com.goncalves.pugnotification.notification.PugNotification;


public class Tool {

    public Tool(Context context){
        sharedPreferences = context.getSharedPreferences("DATA",0);
    }

    SharedPreferences sharedPreferences;

    public void saveData(String key, String value){
        String ret = sharedPreferences.getString(key,"");
        if(!ret.equals(value)){
            sharedPreferences.edit().putString(key,value).apply();
        }

    }

    public String getData(String key){
        String value = sharedPreferences.getString(key,"");
        return value;
    }

    public void setNotification(Context context,String title,String content){
            Log.d("FCM", "get FCM");
            Intent notifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cwb.gov.tw/V8/C/E/index.html"));
            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingItent = PendingIntent.getActivities(context, 0, new Intent[] {notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);
            PugNotification.with(context)
                    .load()
                    .title(title)
                    .message(content)
                    .click(pendingItent)
                    .smallIcon(R.drawable.pugnotification_ic_launcher)
                    .largeIcon(R.drawable.pugnotification_ic_launcher)
                    .flags(Notification.DEFAULT_ALL)
                    .simple()
                    .build();

    }

}
