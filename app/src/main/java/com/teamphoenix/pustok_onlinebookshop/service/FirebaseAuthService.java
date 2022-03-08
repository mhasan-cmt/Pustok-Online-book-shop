package com.teamphoenix.pustok_onlinebookshop.service;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.teamphoenix.pustok_onlinebookshop.entity.User;
import com.teamphoenix.pustok_onlinebookshop.firebase.FireBaseAuthSingleton;
import com.teamphoenix.pustok_onlinebookshop.listeners.onSignupListener;

public class FirebaseAuthService {
    Context context;
    User user;
    public FirebaseAuthService(Context context) {
        this.context = context;
    }
    FireBaseAuthSingleton fireBaseAuthSingleton = FireBaseAuthSingleton.getInstance(context);

    public void createUserWithEmailAndPassword(User user, onSignupListener onSignupListener){
        this.user=user;
        FirebaseAuth firebaseAuth = fireBaseAuthSingleton.getFirebaseAuth();
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d("LoginDebug", "onComplete: account created.");
                            onSignupListener.onSuccess();
                        }else{
                            Log.d("LoginDebug", "onComplete: account creation failed due to some error!");
                            onSignupListener.onError(task.getException().getMessage());
                        }
                    }
                });
    }
    public boolean checkUserSignedIn(){
        if(fireBaseAuthSingleton.getFirebaseAuth().getCurrentUser()!=null){
            return true;
        }else{
            return false;
        }
    }
}
