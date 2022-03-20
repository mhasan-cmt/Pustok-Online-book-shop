package com.teamphoenix.pustok_onlinebookshop.homeactivity.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter.BookSelfAdapter;

public class BookShelfFragment extends Fragment {

    RecyclerView singleBookViewBookSelf;
    String[] bookName = {"৯ নম্বর ননসেন্স", "ইমি", "শয়তানি", "শয়তানি", "মধ্যবিত্ত", "মেঘপিয়ন"};
    int[] bookImage = {R.drawable.book, R.drawable.book1, R.drawable.book2, R.drawable.book3, R.drawable.book4, R.drawable.book5};


    public BookShelfFragment() {
        // Required empty public constructor
    }

    public static BookShelfFragment newInstance(String param1, String param2) {
        BookShelfFragment fragment = new BookShelfFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_shelf, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BookSelfAdapter adapter = new BookSelfAdapter(getContext(), bookName, bookImage);
        singleBookViewBookSelf = getView().findViewById(R.id.singleBookViewBookSelf);
        singleBookViewBookSelf.setAdapter(adapter);
        singleBookViewBookSelf.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }
}