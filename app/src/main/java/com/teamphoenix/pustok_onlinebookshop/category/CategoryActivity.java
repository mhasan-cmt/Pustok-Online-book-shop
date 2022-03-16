package com.teamphoenix.pustok_onlinebookshop.category;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivityCategoryBinding;

public class CategoryActivity extends AppCompatActivity {
    private ActivityCategoryBinding categoryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryBinding = ActivityCategoryBinding.inflate(getLayoutInflater());
        View view = categoryBinding.getRoot();
        setContentView(view);


//        getting data from intents and setting them into view
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        Intent intent = getIntent();
        categoryBinding.categoryName.setText(intent.getStringExtra("category_name"));
        categoryBinding.categoryTotalBooks.setText(intent.getStringExtra("category_count"));
        Picasso.get().load(intent.getStringExtra("category_img")).into(categoryBinding.categoryIcon);
        progressDialog.dismiss();
    }
}