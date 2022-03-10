package com.teamphoenix.pustok_onlinebookshop.service;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
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
        reference.child(user.get_id()).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error!=null){
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Saved data to database!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
