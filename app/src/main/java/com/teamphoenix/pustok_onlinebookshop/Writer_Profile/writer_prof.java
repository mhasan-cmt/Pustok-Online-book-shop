package com.teamphoenix.pustok_onlinebookshop.Writer_Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.teamphoenix.pustok_onlinebookshop.R;

public class writer_prof extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writer_prof);
    }

    public void btprof(View view) {
        Toast.makeText(this, "following", Toast.LENGTH_SHORT).show();
    }
}