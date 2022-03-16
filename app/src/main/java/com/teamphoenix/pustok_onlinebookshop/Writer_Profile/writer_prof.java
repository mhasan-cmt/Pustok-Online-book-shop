package com.teamphoenix.pustok_onlinebookshop.Writer_Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivityWriterProfBinding;

public class writer_prof extends AppCompatActivity {
    ActivityWriterProfBinding activityWriterProfBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWriterProfBinding = ActivityWriterProfBinding.inflate(getLayoutInflater());
        setContentView(activityWriterProfBinding.getRoot());
        Intent intent = getIntent();
        activityWriterProfBinding.writerName.setText(intent.getStringExtra("writer_name"));
        activityWriterProfBinding.writerDescription.setText(intent.getStringExtra("writer_description"));
        activityWriterProfBinding.writerFollowers.setText(intent.getStringExtra("writer_follower"));
        Picasso.get().load(intent.getStringExtra("writer_img")).into(activityWriterProfBinding.writerImg);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void btprof(View view) {
        Toast.makeText(this, "following", Toast.LENGTH_SHORT).show();
    }
}