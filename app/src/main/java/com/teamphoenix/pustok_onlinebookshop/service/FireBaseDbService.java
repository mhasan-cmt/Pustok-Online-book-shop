package com.teamphoenix.pustok_onlinebookshop.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamphoenix.pustok_onlinebookshop.entity.User;

public class FireBaseDbService {
    Context context;

    public FireBaseDbService(@NonNull Context context) {
        this.context = context;
    }

    public void saveUserData(User user) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("users");
        reference.child(user.get_id()).setValue(user);
    }
}
