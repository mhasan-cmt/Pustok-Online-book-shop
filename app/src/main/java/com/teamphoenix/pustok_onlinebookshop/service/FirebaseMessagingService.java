package com.teamphoenix.pustok_onlinebookshop.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.RemoteMessage;
import com.teamphoenix.pustok_onlinebookshop.util.NotificationHelper;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
Context context;

    public FirebaseMessagingService(Context context) {
        this.context = context;
    }

    public FirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        if (message.getNotification() != null) {
            String title = message.getNotification().getTitle();
            String body = message.getNotification().getBody();
            NotificationHelper notificationHelper = new NotificationHelper();
            notificationHelper.displayNotification(context, title, body);
        }
    }
}
