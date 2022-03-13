package com.teamphoenix.pustok_onlinebookshop.homeactivity.FRAGMENTFORTABLAYOUT;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.entity.Publisher;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter.PublisherProfileAdapter;

import java.util.ArrayList;


public class PublisherTabFragment extends Fragment {

    RecyclerView publisher_recyclerView;
    ArrayList<Publisher> publisherDataArray = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
        publisher_recyclerView = getView().findViewById(R.id.publisher_recyclerView);

        //custom model data
        publisherDataArray.add(new Publisher("12","আবির প্রকাশন","R.drawable.writer","12","১২ টি বই"));

        //adapter
        PublisherProfileAdapter adapter = new PublisherProfileAdapter(getContext(),publisherDataArray);
        publisher_recyclerView.setAdapter(adapter);
        publisher_recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contentfragment5, container, false);
    }
}