package com.teamphoenix.pustok_onlinebookshop.homeactivity.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter.ProfileRecyclerViewAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerViewFirst, recyclerViewSecond;
    private ProfileRecyclerViewAdapter profileRecyclerViewAdapter, profileRecyclerViewAdapter2;
    private ArrayList<String> texts, texts2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
        setExitTransition(inflater.inflateTransition(R.transition.fade));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewFirst = getView().findViewById(R.id.profile_first_recycler_view);
        recyclerViewSecond =getView().findViewById(R.id.profile_second_recycler_view);
        texts = new ArrayList<>();
        texts2 = new ArrayList<>();

        texts.add("প্রোমো");
        texts.add("আমার ইচ্ছে তালিকা");
        texts.add("ক্রয়কৃত বইসমূহ");
        texts.add("গিফট পয়েন্ট");
        texts.add("বন্ধুদের জানান");


        texts2.add("সাহায্য এবং অন্যান্য");
        texts2.add("সাচরাচর জিজ্ঞাসিত প্রশ্নাবলী");
        texts2.add("পুস্তক আ্যাপ কে রেটিং দিন");

        int[] icons2 = {
                R.drawable.ic_giftcard,
                R.drawable.ic_giftcard,
                R.drawable.ic_giftcard
        };

        int[] icons = {
                R.drawable.ic_giftcard, R.drawable.ic_giftcard, R.drawable.ic_giftcard,
                R.drawable.ic_giftcard,
                R.drawable.ic_giftcard
        };

        profileRecyclerViewAdapter = new ProfileRecyclerViewAdapter(texts,icons);
        profileRecyclerViewAdapter2 = new ProfileRecyclerViewAdapter(texts2, icons2);
        recyclerViewFirst.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewFirst.setAdapter(profileRecyclerViewAdapter);
        recyclerViewSecond.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSecond.setAdapter(profileRecyclerViewAdapter2);

    }
}