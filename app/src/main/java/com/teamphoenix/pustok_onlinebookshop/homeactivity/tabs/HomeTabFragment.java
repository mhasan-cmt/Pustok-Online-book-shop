package com.teamphoenix.pustok_onlinebookshop.homeactivity.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamphoenix.pustok_onlinebookshop.MODALs.Boookclass;
import com.teamphoenix.pustok_onlinebookshop.MODALs.CatagModal;
import com.teamphoenix.pustok_onlinebookshop.R;

import com.teamphoenix.pustok_onlinebookshop.entity.Book;


import com.teamphoenix.pustok_onlinebookshop.firebase.Booklistadapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class HomeTabFragment extends Fragment {

    ArrayList<Book> boookclassArrayList;
    RecyclerView recyclerView1;
    private ShimmerFrameLayout shimmerFrameLayout;
    FirebaseDatabase database;
    DatabaseReference databaseReference1;
    Booklistadapter booklistadapter;
    private ArrayList<SlideModel> imgList;
    private ImageSlider home_slider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_tab, container, false);
        recyclerView1 = view.findViewById(R.id.l2);
        recyclerView1.setLayoutManager(new GridLayoutManager(getContext(), 2));
        database = FirebaseDatabase.getInstance();
        databaseReference1 = database.getReference("Booklist");
        recyclerView1.setHasFixedSize(true);
        boookclassArrayList = new ArrayList<>();
        booklistadapter = new Booklistadapter(boookclassArrayList, getContext());
        recyclerView1.setAdapter(booklistadapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpImageSlider();
        home_slider = getView().findViewById(R.id.home_slider);
        home_slider.setImageList(imgList);
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                startShimmer();
                boookclassArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Book book = dataSnapshot.getValue(Book.class);
                    boookclassArrayList.add(book);
                }
                booklistadapter.notifyDataSetChanged();
                stopShimmer();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    }

    /**
     * This Method is for setting the image slider
     */
    private void setUpImageSlider() {
        imgList = new ArrayList<>();
        imgList.add(new SlideModel("https://bit.ly/2YoJ77H", "The animal population decreased by 58 percent in 42 years.", ScaleTypes.CENTER_CROP));
        imgList.add(new SlideModel("https://bit.ly/2BteuF2", "Elephants and tigers may become extinct.", ScaleTypes.CENTER_CROP));
        imgList.add(new SlideModel("https://bit.ly/3fLJf72", "And people do that.", ScaleTypes.CENTER_CROP));
    }

    /**
     * This Method is for start the shimmer in home tab
     */
    private void startShimmer() {
        shimmerFrameLayout = getView().findViewById(R.id.shimmer_view_container);
        shimmerFrameLayout.startShimmer();
    }

    /**
     * This Method is for stop the shimmer in home tab and showing the actual recyclerview with data
     */
    private void stopShimmer() {
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.hideShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        recyclerView1.setVisibility(View.VISIBLE);
    }
}