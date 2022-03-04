package com.teamphoenix.pustok_onlinebookshop.homeactivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = homeBinding.getRoot();
        setContentView(view);
//        getSupportActionBar().hide();
        NavController navController = Navigation.findNavController(HomeActivity.this, R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(homeBinding.bottomNavView, navController);

    }
}