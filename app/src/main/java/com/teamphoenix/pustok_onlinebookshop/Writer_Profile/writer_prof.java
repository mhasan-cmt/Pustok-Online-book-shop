package com.teamphoenix.pustok_onlinebookshop.Writer_Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.bookdetails.BookDetailAdapter;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivityWriterProfBinding;
import com.teamphoenix.pustok_onlinebookshop.entity.Book;
import com.teamphoenix.pustok_onlinebookshop.entity.Writer;

import java.util.ArrayList;

public class writer_prof extends AppCompatActivity {
    ActivityWriterProfBinding activityWriterProfBinding;

    RecyclerView writerMoreBookRecycle;

    ArrayList<Book> bookArrayList;
    DatabaseReference referenceBook = FirebaseDatabase.getInstance().getReference("Booklist");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWriterProfBinding = ActivityWriterProfBinding.inflate(getLayoutInflater());
        setContentView(activityWriterProfBinding.getRoot());

        bookArrayList = new ArrayList<>();
        writerMoreBookRecycle = findViewById(R.id.writerMoreBookRecycle);

        Intent intent = getIntent();
        activityWriterProfBinding.writerName.setText(intent.getStringExtra("writer_name"));
        activityWriterProfBinding.writerDescription.setText(intent.getStringExtra("writer_description"));
        activityWriterProfBinding.writerFollowers.setText(intent.getStringExtra("writer_follower"));
        Picasso.get().load(intent.getStringExtra("writer_img")).into(activityWriterProfBinding.writerImg);



        BookDetailAdapter adapter = new BookDetailAdapter(this, bookArrayList);
        writerMoreBookRecycle.setAdapter(adapter);
        writerMoreBookRecycle.setLayoutManager(new GridLayoutManager(this, 3));


        referenceBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Book bookWithWriterMatch = dataSnapshot.getValue(Book.class);
                    if (intent.getStringExtra("writer_id").equals(bookWithWriterMatch.getWriter_id())){
                        bookArrayList.add(bookWithWriterMatch);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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