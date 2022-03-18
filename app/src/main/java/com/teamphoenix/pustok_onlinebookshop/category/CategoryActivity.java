package com.teamphoenix.pustok_onlinebookshop.category;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivityCategoryBinding;
import com.teamphoenix.pustok_onlinebookshop.entity.Book;
import com.teamphoenix.pustok_onlinebookshop.firebase.Booklistadapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private ActivityCategoryBinding categoryBinding;

    RecyclerView bookRecyclerView;
    ArrayList<Book> boookclassArrayList;
    Booklistadapter booklistadapter;
    DatabaseReference databaseReference1;

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
        categoryBinding.categoryName.setText(intent.getStringExtra("category_id"));
        categoryBinding.categoryName.setText(intent.getStringExtra("category_name"));
        categoryBinding.categoryTotalBooks.setText(intent.getStringExtra("category_count"));
        Picasso.get().load(intent.getStringExtra("category_img")).into(categoryBinding.categoryIcon);
        progressDialog.dismiss();

        bookRecyclerView = findViewById(R.id.categoryRecycler);

        boookclassArrayList = new ArrayList<>();
        booklistadapter = new Booklistadapter(boookclassArrayList, getApplicationContext());
        bookRecyclerView.setAdapter(booklistadapter);
        bookRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Booklist");

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Book book = dataSnapshot.getValue(Book.class);
                    if (book.getCategory_id().equals(intent.getStringExtra("category_id"))){
                        boookclassArrayList.add(book);
                    }
                }
                booklistadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    }
}