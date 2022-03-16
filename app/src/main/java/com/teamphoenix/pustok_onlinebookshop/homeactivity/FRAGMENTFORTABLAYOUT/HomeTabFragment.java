package com.teamphoenix.pustok_onlinebookshop.homeactivity.FRAGMENTFORTABLAYOUT;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamphoenix.pustok_onlinebookshop.MODALs.Boookclass;
import com.teamphoenix.pustok_onlinebookshop.MODALs.CatagModal;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.databinding.FragmentContentfragment1Binding;
import com.teamphoenix.pustok_onlinebookshop.firebase.Booklistadapter;
import com.teamphoenix.pustok_onlinebookshop.firebase.Cateadapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class HomeTabFragment extends Fragment {
private  ArrayList<Boookclass>boookclassArrayList;
RecyclerView recyclerView1;
    FirebaseDatabase database;
    DatabaseReference databaseReference1;
Booklistadapter booklistadapter;
    private ArrayList<SlideModel> imgList;
    private ImageSlider home_slider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
  View view= inflater.inflate(R.layout.fragment_contentfragment1, container, false);

        recyclerView1 =view.findViewById(R.id.l2);
        recyclerView1.setLayoutManager(new GridLayoutManager(getContext(),2));
        database = FirebaseDatabase.getInstance();
        databaseReference1 = database.getReference("Booklist");
recyclerView1.setHasFixedSize(true);
        boookclassArrayList = new ArrayList<>();
        booklistadapter = new Booklistadapter(boookclassArrayList, getContext());
        recyclerView1.setAdapter(booklistadapter);

  return view;  }

    @Override
    public void onStart() {
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                boookclassArrayList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                Boookclass boookclass=dataSnapshot.getValue(Boookclass.class);
                    boookclassArrayList.add(boookclass);

                }
                booklistadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpImageSlider();
        home_slider= getView().findViewById(R.id.home_slider);
        home_slider.setImageList(imgList);
    }
    private void setUpImageSlider() {
        imgList = new ArrayList<>();
        imgList.add(new SlideModel("https://bit.ly/2YoJ77H","The animal population decreased by 58 percent in 42 years.", ScaleTypes.CENTER_CROP));
        imgList.add(new SlideModel("https://bit.ly/2BteuF2","Elephants and tigers may become extinct.", ScaleTypes.CENTER_CROP));
        imgList.add(new SlideModel("https://bit.ly/3fLJf72","And people do that.", ScaleTypes.CENTER_CROP));
    }
}