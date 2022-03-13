package com.teamphoenix.pustok_onlinebookshop.service;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamphoenix.pustok_onlinebookshop.entity.User;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetUserDataListener;

import org.json.JSONException;
import org.json.JSONObject;

public class FireBaseDbService {
    Context context;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User user;

    public FireBaseDbService(@NonNull Context context) {
        this.context = context;
    }

    public void saveUserData(User user) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("users");
        reference.child(user.get_id()).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Saved data to database!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getUserById(String uid, onGetUserDataListener onGetUserDataListener) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
        database.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onGetUserDataListener.onSuccess(snapshot.getValue(User.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onGetUserDataListener.onError(error.getMessage());
            }
        });
    }
}
