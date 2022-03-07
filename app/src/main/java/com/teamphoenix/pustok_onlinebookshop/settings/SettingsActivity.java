package com.teamphoenix.pustok_onlinebookshop.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {
    ActivitySettingsBinding settingsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsBinding =ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(settingsBinding.getRoot());
    }
}