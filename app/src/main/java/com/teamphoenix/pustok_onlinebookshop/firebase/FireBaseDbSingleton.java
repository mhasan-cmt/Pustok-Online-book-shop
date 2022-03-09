package com.teamphoenix.pustok_onlinebookshop.firebase;

import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

public class FireBaseDbSingleton {
    private static FireBaseDbSingleton instance;
    private FirebaseDatabase firebaseDatabase;
    Context context;

    private FireBaseDbSingleton(Context context) {
        this.context = context;
    }

    public static synchronized FireBaseDbSingleton getInstance(Context context){
        if(instance == null){
            instance= new FireBaseDbSingleton(context);
        }
        return instance;
    }
    public FirebaseDatabase getFirebaseDatabase(){
        if(firebaseDatabase == null){
            firebaseDatabase = FirebaseDatabase.getInstance();
        }
        return  firebaseDatabase;
    }
}
