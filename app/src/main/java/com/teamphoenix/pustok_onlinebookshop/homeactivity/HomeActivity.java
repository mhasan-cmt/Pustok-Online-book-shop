package com.teamphoenix.pustok_onlinebookshop.homeactivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivityHomeBinding;
import com.teamphoenix.pustok_onlinebookshop.entity.User;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.fragments.ProfileFragment;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetUserDataListener;
import com.teamphoenix.pustok_onlinebookshop.service.FireBaseDbService;

public class HomeActivity extends AppCompatActivity implements onGetUserDataListener{

    private NavHostController navHostController;
    ActivityHomeBinding homeBinding;
    private Dialog exitDialog;
    private Button exit_btn_oky, exit_btn_no;
    FireBaseDbService fireBaseDbService;
    ProfileFragment profileFragment;
    User user;
    public SharedPreferences homeSharePreferences;
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
//        exitButtonOnclick
        exit_btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitDialog.dismiss();
            }
        });
    }

    private void getAndShowUserData() {
        fireBaseDbService.getUserById(FirebaseAuth.getInstance().getUid(),this);
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
        editor.putString("UserData",userJson );
        editor.commit();
    }

    @Override
    public void onError(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }
}