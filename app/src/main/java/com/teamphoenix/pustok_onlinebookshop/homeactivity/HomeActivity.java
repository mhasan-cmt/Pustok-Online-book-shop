package com.teamphoenix.pustok_onlinebookshop.homeactivity;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivityHomeBinding;
import com.teamphoenix.pustok_onlinebookshop.entity.User;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.fragments.ProfileFragment;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetUserDataListener;
import com.teamphoenix.pustok_onlinebookshop.service.FireBaseDbService;
import com.teamphoenix.pustok_onlinebookshop.util.FirebaseTokenHelper;
import com.teamphoenix.pustok_onlinebookshop.util.NotificationHelper;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements onGetUserDataListener {

    private NavHostController navHostController;
    ActivityHomeBinding homeBinding;
    private Dialog exitDialog;
    private Button exit_btn_oky, exit_btn_no;
    FireBaseDbService fireBaseDbService;
    ProfileFragment profileFragment;
    User user;
    public SharedPreferences homeSharePreferences;
    private FirebaseTokenHelper tokenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = homeBinding.getRoot();
        setContentView(view);
        homeSharePreferences = getPreferences(MODE_PRIVATE);
        fireBaseDbService = new FireBaseDbService(HomeActivity.this);
        settingUpRefreshLayout();
        setNavController();
        showExitDialog();
        getAndShowUserData();

        tokenHelper = new FirebaseTokenHelper(this);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    String token = task.getResult();
                    tokenHelper.saveToken(token);
                } else {
                    Toast.makeText(HomeActivity.this, "Failed to get the token!", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        Create Notification Channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NotificationHelper.CHANNEL_ID, NotificationHelper.CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(NotificationHelper.CHANNEL_DESCRIPTION);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
//        exitButtonOnclick
        exit_btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitDialog.dismiss();
            }
        });
    }

    private void getAndShowUserData() {
        fireBaseDbService.getUserById(FirebaseAuth.getInstance().getUid(), this);
    }

    private void setNavController() {
        NavController navController = Navigation.findNavController(HomeActivity.this, R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(homeBinding.bottomNavView, navController);
    }

    private void settingUpRefreshLayout() {
        //        Setting Refresh event
        homeBinding.getRoot().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(HomeActivity.this, "Refreshed..!", Toast.LENGTH_SHORT).show();
                recreate();
                homeBinding.getRoot().setRefreshing(false);
            }
        });
    }

    private void showExitDialog() {
        //Exit Dialog
        exitDialog = new Dialog(this);
        exitDialog.setContentView(R.layout.custom_exit_dialog);
        exitDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_exit_dialog_bg));
        exitDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        exitDialog.setCancelable(false);
        exitDialog.setCanceledOnTouchOutside(false);
        exitDialog.getWindow().getAttributes().windowAnimations = R.style.exit_dialog_animation;
        exit_btn_no = exitDialog.findViewById(R.id.exit_btn_no);
        exit_btn_oky = exitDialog.findViewById(R.id.exit_btn_oky);
        exit_btn_oky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitDialog.dismiss();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        exitDialog.show();
    }

    @Override
    public void onSuccess(User user) {
        this.user = user;
        Gson gson = new Gson();
        Editor editor = homeSharePreferences.edit();
        String userJson = gson.toJson(user);
        editor.putString("UserData", userJson);
        editor.commit();
    }

    @Override
    public void onError(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }
}