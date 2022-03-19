package com.teamphoenix.pustok_onlinebookshop.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamphoenix.pustok_onlinebookshop.entity.Book;
import com.teamphoenix.pustok_onlinebookshop.entity.Cart;
import com.teamphoenix.pustok_onlinebookshop.entity.Publisher;
import com.teamphoenix.pustok_onlinebookshop.entity.User;
import com.teamphoenix.pustok_onlinebookshop.entity.Writer;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetAllCartItemsListener;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetAllWritersListener;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetBookByIdListener;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetPublisherByIdListener;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetUserDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FireBaseDbService {
    Context context;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User user;

    public FireBaseDbService(@NonNull Context context) {
        this.context = context;
    }

    public void saveUserData(User user) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("users");
        reference.child(user.get_id()).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Saved data to database!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getUserById(String uid, onGetUserDataListener onGetUserDataListener) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<User> users = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        User user = dataSnapshot.getValue(User.class);
                        users.add(user);
                    }
                    for (User user : users) {
                        if (user.get_id().equals(uid)) {
                            onGetUserDataListener.onSuccess(user);
                            break;
                        }
                    }
                } else {
                    onGetUserDataListener.onError("Could not find any user");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getPublisherById(String id, onGetPublisherByIdListener onGetPublisherDataListener) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Publisher");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Publisher> publishers = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Publisher publisher = dataSnapshot.getValue(Publisher.class);
                        publishers.add(publisher);
                    }
                    for (Publisher publisher : publishers) {
                        if (publisher.getPublisher_id().equals(id)) {
                            onGetPublisherDataListener.onSuccess(publisher);
                            break;
                        }
                    }
                } else {
                    onGetPublisherDataListener.onError("Could not find any publisher");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onGetPublisherDataListener.onError(error.getMessage());
            }
        });
    }

    public void getBookById(String id, onGetBookByIdListener onGetBookByIdListener) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Booklist");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Book> books = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Book book = dataSnapshot.getValue(Book.class);
                        books.add(book);
                    }
                    for (Book book : books) {
                        if (book.getBook_id().equals(id)) {
                            onGetBookByIdListener.onSuccess(book);
                            break;
                        }
                    }
                } else {
                    onGetBookByIdListener.onError("Could not find any book");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onGetBookByIdListener.onError(error.getMessage());
            }
        });
    }

    public void saveToCart(Cart cart) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("cart");
        String referenceKey = reference.push().getKey();
        cart.setCart_id(referenceKey);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;
                ArrayList<String> arrayList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Cart cart1 = dataSnapshot.getValue(Cart.class);
                    if(cart.getUser_id().equals(cart1.getUser_id()) && cart.getBook_id().equals(cart1.getBook_id())){
                        count++;
                        arrayList.add(cart1.getCart_id());
                        if (count>1){
                            for (int i =0; i<arrayList.size(); i++){
                                reference.child(arrayList.get(i)).removeValue();
                            }
//                            Toast.makeText(context, "Book Remove from cart!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                reference.child(referenceKey).setValue(cart, new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                        if (error != null) {
//                            Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(context, "Book added to cart!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
            }
        });
        reference.child(referenceKey).setValue(cart, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(context, "Book added to cart!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

//    Method for getting all writers
    public void getAllWriters(onGetAllWritersListener onGetAllWritersListener) {
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("writers");
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Writer> writers = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Writer writer = dataSnapshot.getValue(Writer.class);
                        writers.add(writer);
                    }
                    onGetAllWritersListener.onSuccess(writers);
                } else {
                    onGetAllWritersListener.onFailed("Something went wrong!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onGetAllWritersListener.onFailed(error.getMessage());
            }
        });
    }

//    Method for getting all the cart items
    public void getAllCartItems(String uid, onGetAllCartItemsListener onGetAllCartItemsListener) {
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("cart");
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Cart> carts = new ArrayList<>();
                carts.clear();
//                if data exists
                if (snapshot.exists()) {
//                    Looping through all cart items
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Cart cart = dataSnapshot.getValue(Cart.class);
//                        checking the user and then add it to the cart
                        if (cart.getUser_id().equals(uid)) {
                            carts.add(cart);
                        }
                    }
//                    finally sending the cart list to ui
                    onGetAllCartItemsListener.onSuccess(carts);
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
