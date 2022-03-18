package com.teamphoenix.pustok_onlinebookshop.Publisher_Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.bookdetails.BookDetailAdapter;
import com.teamphoenix.pustok_onlinebookshop.entity.Book;

import java.util.ArrayList;

public class Publisher_Profile extends AppCompatActivity {
    ImageView publisher_profile_pic;
    TextView publisher_name, publisher_follower, publisher_total_book;
    RecyclerView publisherBookRecycle;


    ArrayList<Book> bookArrayList;
    DatabaseReference referenceBook = FirebaseDatabase.getInstance().getReference("Booklist");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher_profile);
        publisher_profile_pic = findViewById(R.id.publisher_profile_pic);
        publisher_name = findViewById(R.id.publisher_name);
        publisher_follower = findViewById(R.id.publisher_follower);
        publisher_total_book = findViewById(R.id.publisher_total_book);
        publisherBookRecycle = findViewById(R.id.publisherBookRecycle);

        bookArrayList = new ArrayList<>();

        Intent intent = getIntent();
        publisher_name.setText(intent.getStringExtra("publisher_name"));
        publisher_follower.setText(intent.getStringExtra("publisher_follower"));
        publisher_total_book.setText(intent.getStringExtra("publisher_total_book"));
        Glide.with(getApplicationContext()).asBitmap().load(intent.getStringExtra("publisher_profile_pic"))
                .into(publisher_profile_pic);
//        Picasso.get().load(intent.getStringExtra("publisher_profile_pic")).into(publisher_profile_pic);



        BookDetailAdapter adapter = new BookDetailAdapter(this, bookArrayList);
        publisherBookRecycle.setAdapter(adapter);
        publisherBookRecycle.setLayoutManager(new GridLayoutManager(this, 3));

        referenceBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Book bookWithWriterMatch = dataSnapshot.getValue(Book.class);
                    if (intent.getStringExtra("publisher_id").equals(bookWithWriterMatch.getPublisher_id())){
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
    public void btprof(View view) {
        Toast.makeText(this, "following", Toast.LENGTH_SHORT).show();
    }

}