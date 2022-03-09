package com.teamphoenix.pustok_onlinebookshop.service;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.teamphoenix.pustok_onlinebookshop.entity.User;
import com.teamphoenix.pustok_onlinebookshop.firebase.FireBaseDbSingleton;
import com.teamphoenix.pustok_onlinebookshop.listeners.onUserDataSaveListener;

public class FireBaseDbService {
    Context context;

    public FireBaseDbService(@NonNull Context context) {
        this.context = context;
    }

    FireBaseDbSingleton fireBaseDbSingleton = FireBaseDbSingleton.getInstance(context);

    public void saveUserData(User user, onUserDataSaveListener onUserDataSaveListener) {
        DatabaseReference reference = fireBaseDbSingleton.getFirebaseDatabase().getReference("users");
        reference.child(user.get_id()).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    onUserDataSaveListener.onFailure(error.getMessage());
                } else {
                    onUserDataSaveListener.onSuccess("saved to database!");
                }
            }
        });
    }
}
