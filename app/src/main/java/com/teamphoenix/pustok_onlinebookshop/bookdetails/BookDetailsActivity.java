package com.teamphoenix.pustok_onlinebookshop.bookdetails;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.teamphoenix.pustok_onlinebookshop.Publisher_Profile.Publisher_Profile;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.Writer_Profile.writer_prof;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivityBookDetailsBinding;
import com.teamphoenix.pustok_onlinebookshop.entity.Book;
import com.teamphoenix.pustok_onlinebookshop.entity.Cart;
import com.teamphoenix.pustok_onlinebookshop.entity.Publisher;
import com.teamphoenix.pustok_onlinebookshop.entity.Writer;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetPublisherByIdListener;
import com.teamphoenix.pustok_onlinebookshop.service.FireBaseDbService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("writers");
    DatabaseReference referenceBook = FirebaseDatabase.getInstance().getReference("Booklist");
    ArrayList<Book> bookArrayList;


    Writer writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookDetailsBinding = ActivityBookDetailsBinding.inflate(getLayoutInflater());
        View view = bookDetailsBinding.getRoot();
        setContentView(view);
        fireBaseDbService = new FireBaseDbService(this);
        relatedBookRecycle = findViewById(R.id.relatedBookRecycle);
        getAndSetBookData();
        bookDetailsBinding.writerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWriterProfile();
            }
        });
        bookDetailsBinding.writerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWriterProfile();
            }
        });

    }

    private void goToWriterProfile() {
        Intent intent = new Intent(getApplicationContext(), writer_prof.class);
        intent.putExtra("writer_id", writer.getWriter_id());
        intent.putExtra("writer_name", writer.getWriter_name());
        intent.putExtra("writer_description", writer.getDescription());
        intent.putExtra("writer_img", writer.getProfile_pic());
        intent.putExtra("writer_follower", writer.getFollowers() + " জন অনুসারী");
        startActivity(intent);
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
                intent.getStringExtra("category_id"), intent.getStringExtra("publisher_id"),
                intent.getStringExtra("book_type"),
                intent.getStringExtra("writer_name"));

        bookArrayList = new ArrayList<>();

        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("cart");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Cart cart1 = dataSnapshot.getValue(Cart.class);
                    bookDetailsBinding.addToCart.setImageResource(R.drawable.ic_add_shopping_cart);
                    if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(cart1.getUser_id()) && intent.getStringExtra("book_id").equals(cart1.getBook_id())) {
                        bookDetailsBinding.addToCart.setImageResource(R.drawable.ic_remove_shopping);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        referenceBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Book bookWithWriterMatch = dataSnapshot.getValue(Book.class);
                    if (book.getWriter_id().equals(bookWithWriterMatch.getWriter_id())) {
                        if (bookWithWriterMatch.getBook_id().equals(book.getBook_id())) {
                            continue;
                        }
                        bookArrayList.add(bookWithWriterMatch);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        BookDetailAdapter adapter = new BookDetailAdapter(this, bookArrayList);
        relatedBookRecycle.setAdapter(adapter);
        relatedBookRecycle.setLayoutManager(new GridLayoutManager(this, 3));

        reference.child(book.getWriter_id()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                writer = snapshot.getValue(Writer.class);
                Picasso.get().load(writer.getProfile_pic()).into(bookDetailsBinding.writerImage);
                bookDetailsBinding.bookWriter.setText(writer.getWriter_name());
                bookDetailsBinding.bookWriterNameBottom.setText(writer.getWriter_name());
                bookDetailsBinding.getWriterFollowers.setText(writer.getFollowers() + " জন অনুসারী");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        bookDetailsBinding.bookTitle.setText(book.getBook_name());
        Picasso.get().load(book.getBook_image()).into(bookDetailsBinding.bookImage);
        bookDetailsBinding.priceTaka.setText(book.getPrice());
        bookDetailsBinding.pageNumbers.setText(book.getPage_number());
        bookDetailsBinding.language.setText(book.getLanguage());
        fireBaseDbService.getPublisherById(book.getPublisher_id(), new onGetPublisherByIdListener() {
            @Override
            public void onSuccess(Publisher publisher) {
                bookDetailsBinding.publisher.setText(publisher.getPublisher_name());
                bookDetailsBinding.publisher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), Publisher_Profile.class);
                        intent.putExtra("publisher_id", publisher.getPublisher_id());
                        intent.putExtra("publisher_name", publisher.getPublisher_name());
                        intent.putExtra("publisher_profile_pic", publisher.getPublisher_img());
                        intent.putExtra("publisher_total_book", publisher.getTotal_books());
                        intent.putExtra("publisher_follower", publisher.getTotal_followers());
                        startActivity(intent);
                    }
                });
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
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("cart");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Cart cart1 = dataSnapshot.getValue(Cart.class);
                    if (cart.getUser_id().equals(cart1.getUser_id()) && cart.getBook_id().equals(cart1.getBook_id())) {
                        bookDetailsBinding.addToCart.setImageResource(R.drawable.ic_remove_shopping);
                    } else {
                        bookDetailsBinding.addToCart.setImageResource(R.drawable.ic_add_shopping_cart);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        fireBaseDbService.saveToCart(cart);
        progressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}