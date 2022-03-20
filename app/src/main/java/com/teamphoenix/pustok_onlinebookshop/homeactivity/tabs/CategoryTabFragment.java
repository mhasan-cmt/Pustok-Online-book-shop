package com.teamphoenix.pustok_onlinebookshop.homeactivity.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamphoenix.pustok_onlinebookshop.entity.CatagModal;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.category.Cateadapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CategoryTabFragment extends Fragment {

    RecyclerView recyclerView;
    Cateadapter cateadapter;
    ArrayList<CatagModal> arrayList;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_tab, container, false);
        recyclerView = view.findViewById(R.id.Catagorybooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Catagory");

        arrayList = new ArrayList<>();
        cateadapter = new Cateadapter(arrayList, getContext());
        recyclerView.setAdapter(cateadapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CatagModal catagModal = dataSnapshot.getValue(CatagModal.class);
                    arrayList.add(catagModal);

                }
                cateadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}
