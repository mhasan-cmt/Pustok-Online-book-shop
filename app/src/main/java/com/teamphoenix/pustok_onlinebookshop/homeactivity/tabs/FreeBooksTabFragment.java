package com.teamphoenix.pustok_onlinebookshop.homeactivity.tabs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.entity.Book;
import com.teamphoenix.pustok_onlinebookshop.firebase.Booklistadapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class FreeBooksTabFragment extends Fragment {

    RecyclerView bookRecyclerView;
    ArrayList<Book> boookclassArrayList;
    Booklistadapter booklistadapter;
    DatabaseReference databaseReference1;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookRecyclerView = getView().findViewById(R.id.bookRecyclerView);

        boookclassArrayList = new ArrayList<>();
        booklistadapter = new Booklistadapter(boookclassArrayList, getContext());
        bookRecyclerView.setAdapter(booklistadapter);
        bookRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Booklist");

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Book book = dataSnapshot.getValue(Book.class);
                    if ("Free".equals(book.getBook_type())){
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_free_books_tab, container, false);
    }
}