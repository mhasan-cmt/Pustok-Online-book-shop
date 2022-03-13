package com.teamphoenix.pustok_onlinebookshop.homeactivity.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.entity.Writer;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter.WriterProfileAdapter;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetAllWritersListener;
import com.teamphoenix.pustok_onlinebookshop.service.FireBaseDbService;

import java.util.ArrayList;


public class WriterTabFragment extends Fragment {
    private WriterProfileAdapter writerProfileAdapter;
    private RecyclerView writer_recyclerView;
    FireBaseDbService fireBaseDbService = new FireBaseDbService(getContext());
    @Override
    public void onStart() {
        super.onStart();
        writer_recyclerView = getView().findViewById(R.id.writer_recyclerView);
        fireBaseDbService.getAllWriters(new onGetAllWritersListener() {
            @Override
            public void onSuccess(ArrayList<Writer> writers) {
                writerProfileAdapter = new WriterProfileAdapter(getContext(), writers);
                writer_recyclerView.setAdapter(writerProfileAdapter);
                writer_recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                writerProfileAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String errMsg) {
                Toast.makeText(getActivity(), errMsg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_writer_tab, container, false);
    }
}