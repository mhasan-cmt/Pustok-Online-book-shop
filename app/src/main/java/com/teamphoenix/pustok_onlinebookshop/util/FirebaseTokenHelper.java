package com.teamphoenix.pustok_onlinebookshop.util;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamphoenix.pustok_onlinebookshop.entity.UserToken;
import com.teamphoenix.pustok_onlinebookshop.splash.SplashActivity;

public class FirebaseTokenHelper {
    private FirebaseAuth auth;
    Context context;


    public FirebaseTokenHelper(Context context) {
        this.context = context;
    }

    public void saveToken(String token) {
        auth = FirebaseAuth.getInstance();
        String email = auth.getCurrentUser().getEmail();
        UserToken user = new UserToken(email, token);
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("usersToken");
        userReference.child(auth.getCurrentUser().getUid())
                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(context, "Cant save the token to Database!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
