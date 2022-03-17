package com.teamphoenix.pustok_onlinebookshop.bookdetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivityBookDetailsBinding;
import com.teamphoenix.pustok_onlinebookshop.entity.Publisher;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetPublisherByIdListener;
import com.teamphoenix.pustok_onlinebookshop.service.FireBaseDbService;

public class BookDetailsActivity extends AppCompatActivity {
    RecyclerView relatedBookRecycle;
    private ActivityBookDetailsBinding bookDetailsBinding;
    private FireBaseDbService fireBaseDbService;
    String[] bookNameList = {"স্বর্ণভূমি", "রাজতন্ত্র", "আলাদিন জিন্দাবাদ", "মকবরা", "হিজলতলি", "মধ্যবিত্ত"};
    int[] bookImgList = {R.drawable.book, R.drawable.book1, R.drawable.book2, R.drawable.book3, R.drawable.book4, R.drawable.book5};
    int[] bookReviewList = {10, 4, 31, 34, 23, 48};
    int[] bookRatingList = {3, 2, 4, 3, 2, 5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookDetailsBinding = ActivityBookDetailsBinding.inflate(getLayoutInflater());
        View view = bookDetailsBinding.getRoot();
        setContentView(view);
        fireBaseDbService= new FireBaseDbService(this);
        getAndSetBookData();
        relatedBookRecycle = findViewById(R.id.relatedBookRecycle);
        BookDetailAdapter adapter = new BookDetailAdapter(this, bookNameList, bookImgList, bookReviewList, bookRatingList);
        relatedBookRecycle.setAdapter(adapter);
        relatedBookRecycle.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private void getAndSetBookData() {
        //Getting and setting book data
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("setting up book data");
        Intent intent = getIntent();
        bookDetailsBinding.bookTitle.setText(intent.getStringExtra("book_name"));
        bookDetailsBinding.bookWriter.setText(intent.getStringExtra("writer_name"));
        Picasso.get().load(intent.getStringExtra("image")).into(bookDetailsBinding.bookImage);
        bookDetailsBinding.priceTaka.setText(intent.getStringExtra("price"));
        bookDetailsBinding.pageNumbers.setText(intent.getStringExtra("page_number"));
        bookDetailsBinding.language.setText(intent.getStringExtra("language"));
        fireBaseDbService.getPublisherById(intent.getStringExtra("publisher_id"), new onGetPublisherByIdListener() {
            @Override
            public void onSuccess(Publisher publisher) {
                bookDetailsBinding.publisher.setText(publisher.getPublisher_name());
            }

            @Override
            public void onError(String errorMsg) {
                Toast.makeText(BookDetailsActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
        progressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}