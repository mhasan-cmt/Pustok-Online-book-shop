package com.teamphoenix.pustok_onlinebookshop.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {
    ActivitySettingsBinding settingsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsBinding =ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(settingsBinding.getRoot());

        settingsBinding.settingsTopBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}