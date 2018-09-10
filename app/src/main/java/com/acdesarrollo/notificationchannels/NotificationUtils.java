package com.acdesarrollo.notificationchannels;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationUtils extends ContextWrapper {

    private NotificationManager mManager;
    public static final String ANDROID_CHANNEL_ID = "com.acdesarrollo.notificationchannels.ANDROID";
    public static final String IOS_CHANNEL_ID = "com.acdesarrollo.notificationchannels.IOS";
    public static final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";
    public static final String IOS_CHANNEL_NAME = "IOS CHANNEL";
    private final long[] vibrate = {0,100,200,300};

    public NotificationUtils(Context base) {
        super(base);
        createChannels();
    }

    public void createChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // create android channel
            NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                    ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            // Sets whether notifications posted to this channel should display notification lights
            androidChannel.enableLights(true);
            // Sets whether notification posted to this channel should vibrate.
            androidChannel.enableVibration(true);
            // Sets the notification light color for notifications posted to this channel
            androidChannel.setLightColor(Color.GREEN);
            // Sets whether notifications posted to this channel appear on the lockscreen or not
//            androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannel(androidChannel);

            // create ios channel
//            NotificationChannel iosChannel = new NotificationChannel(IOS_CHANNEL_ID,
//                    IOS_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
//            iosChannel.enableLights(true);
//            iosChannel.enableVibration(true);
//            iosChannel.setLightColor(Color.GRAY);
//            getManager().createNotificationChannel(iosChannel);
        }
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            //String groupId = "group_id_101";    //Optional
            //CharSequence groupName = "Mateo test";    //optional
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            //mManager.createNotificationChannelGroup(new NotificationChannelGroup(groupId, groupName));
        }
        return mManager;
    }


    public NotificationCompat.Builder getAndroidChannelNotification(String title, String body) {
        PendingIntent pendingIntent = pendingIntent("da4s6da5", "mateo");
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(getApplicationContext(), ANDROID_CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(getApplicationContext());
        }

        builder.setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVibrate(vibrate)
                .setLights(0xff00ff00, 300, 1000)
                .setContentIntent(pendingIntent);

        return builder;
    }




//    public NotificationCompat.Builder getIosChannelNotification(String title, String body) {
//        PendingIntent pendingIntent = pendingIntent("da4s6da5", "mateo");
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        return new NotificationCompat.Builder(getApplicationContext(), IOS_CHANNEL_ID)
//                .setContentTitle(title)
//                .setContentText(body)
//                .setSmallIcon(android.R.drawable.stat_notify_more)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setVibrate(vibrate)
//                .setLights(0xff00ff00, 300, 1000)
//                .setContentIntent(pendingIntent);
//    }



    public PendingIntent pendingIntent(String chatId, String userName) {
        //**add this line**
        int requestID = (int) System.currentTimeMillis();

        Intent intent = new Intent(this, TestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("EXTRA_CHAT_ID", chatId);
        intent.putExtra("EXTRA_USER", userName);

//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID , intent, PendingIntent.FLAG_ONE_SHOT);

        return pendingIntent;
    }

}
