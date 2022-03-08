package com.teamphoenix.pustok_onlinebookshop.firebase;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;

public class FireBaseAuthSingleton {
    private static FireBaseAuthSingleton instance;
    private FirebaseAuth firebaseAuth;
    private static Context context;
    private FireBaseAuthSingleton(Context context){
        this.context = context;
    }

    public static synchronized FireBaseAuthSingleton getInstance(Context context){
        if (instance==null){
            instance = new FireBaseAuthSingleton(context);
        }
        return instance;
    }
    public FirebaseAuth getFirebaseAuth(){
        if(firebaseAuth==null){
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }
}
