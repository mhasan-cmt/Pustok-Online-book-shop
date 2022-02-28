package com.teamphoenix.pustok_onlinebookshop.homeactivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

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
    private BottomNavigationView bottomNavigationView;
    ActivityHomeBinding homeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavController navController = Navigation.findNavController(HomeActivity.this, R.id.fragmentContainerView);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }
}