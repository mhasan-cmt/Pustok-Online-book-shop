package com.teamphoenix.pustok_onlinebookshop.service;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.teamphoenix.pustok_onlinebookshop.entity.User;
import com.teamphoenix.pustok_onlinebookshop.listeners.onSignInListener;
import com.teamphoenix.pustok_onlinebookshop.listeners.onSignupListener;

public class FirebaseAuthService {
    Context context;
    User user;

    public FirebaseAuthService(Context context) {
        this.context = context;
    }

    public void createUserWithEmailAndPassword(User user, onSignupListener onSignupListener) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        this.user = user;
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("LoginDebug", "onComplete: account created.");
                            user.set_id(firebaseAuth.getUid());
                            onSignupListener.onSuccess(user);
                        } else {
                            Log.d("LoginDebug", "onComplete: account creation failed due to some error!");
                            onSignupListener.onError(task.getException().getMessage());
                        }
                    }
                });
    }

    public void login(String email, String password, onSignInListener onSignInListener) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        onSignInListener.onSignInSuccess("Login Success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        onSignInListener.onSignInFailed(e.getMessage());
                    }
                });
    }

    public boolean checkUserSignedIn() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            return true;
        } else {
            return false;
        }
    }
}
