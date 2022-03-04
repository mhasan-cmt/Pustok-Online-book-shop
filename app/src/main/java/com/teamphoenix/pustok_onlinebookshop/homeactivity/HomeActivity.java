package com.teamphoenix.pustok_onlinebookshop.homeactivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private NavHostController navHostController;
    ActivityHomeBinding homeBinding;
    private Dialog exitDialog;
    private Button exit_btn_oky,exit_btn_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = homeBinding.getRoot();
        setContentView(view);
//        getSupportActionBar().hide();
        NavController navController = Navigation.findNavController(HomeActivity.this, R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(homeBinding.bottomNavView, navController);

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
        exit_btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        exitDialog.show();
    }
}