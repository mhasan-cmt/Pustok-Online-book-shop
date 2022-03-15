package com.teamphoenix.pustok_onlinebookshop.Publisher_Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.R;

public class Publisher_Profile extends AppCompatActivity {
    ImageView publisher_profile_pic;
    TextView publisher_name, publisher_follower, publisher_total_book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher_profile);
        publisher_profile_pic = findViewById(R.id.publisher_profile_pic);
        publisher_name = findViewById(R.id.publisher_name);
        publisher_follower = findViewById(R.id.publisher_follower);
        publisher_total_book = findViewById(R.id.publisher_total_book);

        Intent intent = getIntent();
        publisher_name.setText(intent.getStringExtra("publisher_name"));
        publisher_follower.setText(intent.getStringExtra("publisher_name"));
        publisher_total_book.setText(intent.getStringExtra("publisher_name"));
        Glide.with(getApplicationContext()).asBitmap().load(intent.getStringExtra("publisher_profile_pic"))
                .into(publisher_profile_pic);
//        Picasso.get().load(intent.getStringExtra("publisher_profile_pic")).into(publisher_profile_pic);
    }
    public void btprof(View view) {
        Toast.makeText(this, "following", Toast.LENGTH_SHORT).show();
    }

}