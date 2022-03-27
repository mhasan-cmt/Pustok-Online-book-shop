package com.teamphoenix.pustok_onlinebookshop.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.entity.UserToken;
import com.teamphoenix.pustok_onlinebookshop.loginandsignup.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private LottieAnimationView animationView;
    private TextView appName;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        animationView = findViewById(R.id.animated_logo);
        appName = findViewById(R.id.app_name);
        auth = FirebaseAuth.getInstance();
        animationView.animate().translationY(0).setDuration(2500).setStartDelay(0);
        appName.animate().translationY(0).setDuration(2500).setStartDelay(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 3000);

    }
}