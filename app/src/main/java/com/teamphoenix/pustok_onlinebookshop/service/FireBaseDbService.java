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
import com.teamphoenix.pustok_onlinebookshop.entity.Writer;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetAllWritersListener;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetUserDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        User user = dataSnapshot.getValue(User.class);
                        if(user.get_id()==uid){
                            onGetUserDataListener.onSuccess(user);
                            break;
                        }
                    }
                }else{
                    onGetUserDataListener.onError("Data Could not found!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onGetUserDataListener.onError(error.getMessage());
            }
        });
    }

    public void saveWriter(Writer writer) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("writers");
        String referenceKey = reference.push().getKey();
        writer.setWriter_id(referenceKey);
        reference.child(referenceKey).setValue(writer, new DatabaseReference.CompletionListener() {
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

    public void getAllWriters(onGetAllWritersListener onGetAllWritersListener) {
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("writers");
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Writer> writers = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Writer writer = dataSnapshot.getValue(Writer.class);
                        writers.add(writer);
                    }
                    onGetAllWritersListener.onSuccess(writers);
                } else {
                    onGetAllWritersListener.onFailed("Something went wrong!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onGetAllWritersListener.onFailed(error.getMessage());
            }
        });
    }
}
