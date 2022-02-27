package com.teamphoenix.pustok_onlinebookshop.homeactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.fragments.BookShelfFragment;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.fragments.HomeFragment;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.fragments.ProfileFragment;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private NavHostController navHostController;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ;
        setContentView(R.layout.activity_home);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavController navController = Navigation.findNavController(HomeActivity.this, R.id.fragmentContainerView);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}