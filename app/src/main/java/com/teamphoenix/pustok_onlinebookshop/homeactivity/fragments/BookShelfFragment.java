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
import com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter.bookSelfBookViewAdapter;

public class BookShelfFragment extends Fragment {
<<<<<<< HEAD
    RecyclerView singleBookViewBookSelf;
    String[] bookName = {"৯ নম্বর ননসেন্স", "ইমি", "শয়তানি", "শয়তানি", "মধ্যবিত্ত", "মেঘপিয়ন"};
    int[] bookImage={R.drawable.book,R.drawable.book1,R.drawable.book2,R.drawable.book3,R.drawable.book4,R.drawable.book5};
=======

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookShelfFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookShelfFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookShelfFragment newInstance(String param1, String param2) {
        BookShelfFragment fragment = new BookShelfFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
>>>>>>> aef1f89757a4072d64eebc60ede03129fdd3c149

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_shelf, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookSelfBookViewAdapter adapter = new bookSelfBookViewAdapter(getContext(),bookName,bookImage);
        singleBookViewBookSelf=getView().findViewById(R.id.singleBookViewBookSelf);
        singleBookViewBookSelf.setAdapter(adapter);
        singleBookViewBookSelf.setLayoutManager(new GridLayoutManager(getContext(),3) ) ;
    }
}