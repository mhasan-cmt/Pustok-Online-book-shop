package com.teamphoenix.pustok_onlinebookshop.homeactivity.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.FRAGMENTFORTABLAYOUT.TAbadapter;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.FRAGMENTFORTABLAYOUT.contentFragment4;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.FRAGMENTFORTABLAYOUT.contentfragment1;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.FRAGMENTFORTABLAYOUT.contentfragment2;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.FRAGMENTFORTABLAYOUT.contentfragment3;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.FRAGMENTFORTABLAYOUT.contentfragment5;

import org.jetbrains.annotations.NotNull;


import com.google.android.material.appbar.MaterialToolbar;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.cart.CartActivity;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.HomeActivity;


public class HomeFragment extends Fragment {

<<<<<<< HEAD
    private TabLayout tabLayout;
    private ViewPager viewPager;
||||||| 6b4405a

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
  private   TabLayout tabLayout;
   private ViewPager viewPager;
=======

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TabLayout tabLayout;
    private ViewPager viewPager;
>>>>>>> main

    private MaterialToolbar topActionBar;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
<<<<<<< HEAD


||||||| 6b4405a

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);



=======
>>>>>>> main
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
<<<<<<< HEAD


||||||| 6b4405a



=======
>>>>>>> main
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        topActionBar = view.findViewById(R.id.home_toolbar);
        topActionBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.top_bar_cart_item) {
                    startActivity(new Intent(getActivity(), CartActivity.class));
                } else if (item.getItemId() == R.id.top_bar_search_item) {
                    Toast.makeText(getActivity(), "Search Clicked", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = getView().findViewById(R.id.tblay);
        viewPager = getView().findViewById(R.id.vp);

        tabLayout.setupWithViewPager(viewPager);

<<<<<<< HEAD
        TAbadapter tAbadapter = new TAbadapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tAbadapter.addfrg(new contentfragment1(), "HOME");
        tAbadapter.addfrg(new contentfragment2(), "CATAGORY");
        tAbadapter.addfrg(new contentfragment3(), "FREE BOOK");
        tAbadapter.addfrg(new contentFragment4(), "WRITER");
        tAbadapter.addfrg(new contentfragment5(), "PUBLICATION");
||||||| 6b4405a
        TAbadapter tAbadapter=new TAbadapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tAbadapter.addfrg(new contentfragment1() ,"HOME");
        tAbadapter.addfrg(new contentfragment2() ,"CATAGORY");
        tAbadapter.addfrg(new contentfragment3() ,"FREE BOOK");
        tAbadapter.addfrg(new contentFragment4() ,"WRITER");
        tAbadapter.addfrg(new contentfragment5() ,"PUBLICATION");
=======
        TAbadapter tAbadapter = new TAbadapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tAbadapter.addfrg(new contentfragment1(), "হোম");
        tAbadapter.addfrg(new contentfragment2(), "ক্যাটাগরি");
        tAbadapter.addfrg(new contentfragment3(), "ফ্রী বই");
        tAbadapter.addfrg(new contentFragment4(), "লেখক");
        tAbadapter.addfrg(new contentfragment5(), "প্রকাশক");
>>>>>>> main
        viewPager.setAdapter(tAbadapter);

    }


}