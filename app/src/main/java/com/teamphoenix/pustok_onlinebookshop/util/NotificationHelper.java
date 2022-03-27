package com.teamphoenix.pustok_onlinebookshop.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.HomeActivity;
import com.teamphoenix.pustok_onlinebookshop.payment.PaymentActivity;

public class NotificationHelper {
    //    Notification
    public static final String CHANNEL_ID = "NotificationChannel1";
    public static final String CHANNEL_NAME = "NotificationName";
    public static final String CHANNEL_DESCRIPTION = "NotificationDescription";
    public static void displayNotification(Context context, String title, String body){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(title);
        builder.setContentText(body);
        //for cancel notification onclick
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1, builder.build());
    }
}
