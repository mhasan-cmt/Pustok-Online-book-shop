package com.teamphoenix.pustok_onlinebookshop.bookdetails;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivityBookDetailsBinding;
import com.teamphoenix.pustok_onlinebookshop.entity.Book;
import com.teamphoenix.pustok_onlinebookshop.entity.Cart;
import com.teamphoenix.pustok_onlinebookshop.entity.Publisher;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetPublisherByIdListener;
import com.teamphoenix.pustok_onlinebookshop.service.FireBaseDbService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookDetailsActivity extends AppCompatActivity {
    RecyclerView relatedBookRecycle;
    private ActivityBookDetailsBinding bookDetailsBinding;
    private FireBaseDbService fireBaseDbService;
    String[] bookNameList = {"স্বর্ণভূমি", "রাজতন্ত্র", "আলাদিন জিন্দাবাদ", "মকবরা", "হিজলতলি", "মধ্যবিত্ত"};
    int[] bookImgList = {R.drawable.book, R.drawable.book1, R.drawable.book2, R.drawable.book3, R.drawable.book4, R.drawable.book5};
    int[] bookReviewList = {10, 4, 31, 34, 23, 48};
    int[] bookRatingList = {3, 2, 4, 3, 2, 5};
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookDetailsBinding = ActivityBookDetailsBinding.inflate(getLayoutInflater());
        View view = bookDetailsBinding.getRoot();
        setContentView(view);
        fireBaseDbService = new FireBaseDbService(this);
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
        book = new Book(intent.getStringExtra("book_id"),
                intent.getStringExtra("price"),
                intent.getStringExtra("book_name"), intent.getStringExtra("image"),
                intent.getStringExtra("language"), "intent",
                intent.getStringExtra("writer_id"),
                intent.getStringExtra("page_number"),
                "", intent.getStringExtra("publisher_id"),
                intent.getStringExtra("book_type"),
                intent.getStringExtra("writer_name"));

        bookDetailsBinding.bookTitle.setText(book.getBook_name());
        bookDetailsBinding.bookWriter.setText(book.getWriter_name());
        Picasso.get().load(book.getBook_image()).into(bookDetailsBinding.bookImage);
        bookDetailsBinding.priceTaka.setText(book.getPrice());
        bookDetailsBinding.pageNumbers.setText(book.getPage_number());
        bookDetailsBinding.language.setText(book.getLanguage());
        fireBaseDbService.getPublisherById(book.getPublisher_id(), new onGetPublisherByIdListener() {
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

        bookDetailsBinding.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });
    }

    private void addToCart() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait..!");
        progressDialog.setMessage("Adding book to cart...");
        progressDialog.setCancelable(false);
        String currentTime, currentDate;
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDateFormat = new SimpleDateFormat("MM dd, yyyy");
        currentDate = currentDateFormat.format(calendar.getTime());


        SimpleDateFormat currentTimeFormat = new SimpleDateFormat("HH:mm:ss a");
        currentTime = currentTimeFormat.format(calendar.getTime());

        Cart cart = new Cart(FirebaseAuth.getInstance().getCurrentUser().getUid(),
                currentTime,
                book.getBook_id(),
                currentDate,
                book.getPrice(),
                "1");
        fireBaseDbService.saveToCart(cart);
        progressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}