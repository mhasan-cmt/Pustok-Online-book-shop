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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.entity.Publisher;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter.PublisherProfileAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class PublisherTabFragment extends Fragment {

    RecyclerView publisher_recyclerView;
    ArrayList<Publisher> publisherDataArray = new ArrayList<>();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Publisher");

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        publisher_recyclerView = getView().findViewById(R.id.publisher_recyclerView);

        //custom model data
        publisherDataArray.add(new Publisher("12","আবির প্রকাশন","R.drawable.writer","12","১২ টি বই"));

//        reference.child(reference.push().getKey()).setValue(publisherDataArray.get(0));

        //adapter
        PublisherProfileAdapter adapter = new PublisherProfileAdapter(getContext(),publisherDataArray);
        publisher_recyclerView.setAdapter(adapter);
        publisher_recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                publisherDataArray.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Publisher publisher = dataSnapshot.getValue(Publisher.class);
                    publisherDataArray.add(publisher);
                }
                adapter.notifyDataSetChanged();
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
        return inflater.inflate(R.layout.fragment_publication_tab, container, false);
    }
}