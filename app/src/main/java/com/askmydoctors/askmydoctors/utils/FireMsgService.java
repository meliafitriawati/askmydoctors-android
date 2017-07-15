package com.askmydoctors.askmydoctors.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.views.ChatDetailActivity;
import com.askmydoctors.askmydoctors.views.DaftarPertanyaanActivity;
import com.askmydoctors.askmydoctors.views.DetailPertanyaanActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by meliafitriawati on 6/13/2017.
 */
public class FireMsgService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("Msg", "Message received ["+remoteMessage+"]");

        // Create Notification

        String tag_notif = remoteMessage.getNotification().getTag();
        Intent intent = new Intent();
        if (tag_notif.equals("admin")){
            intent = new Intent(this, DetailPertanyaanActivity.class);
            intent.putExtra("id_pertanyaan", remoteMessage.getNotification().getClickAction());
        }else if (tag_notif.equals("pertanyaan")) {
            //
        }else{
            intent = new Intent(this, ChatDetailActivity.class);
            intent.putExtra("chatWith", remoteMessage.getNotification().getTag());
        }


        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1410, intent,
                PendingIntent.FLAG_ONE_SHOT);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1410, notificationBuilder.build());
    }
}