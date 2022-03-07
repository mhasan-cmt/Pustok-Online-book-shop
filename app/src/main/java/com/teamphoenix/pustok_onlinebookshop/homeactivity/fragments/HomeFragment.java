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





  private   TabLayout tabLayout;
   private ViewPager viewPager;

    private MaterialToolbar topActionBar;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        topActionBar =view.findViewById(R.id.home_toolbar);
        topActionBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.top_bar_cart_item){
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
        tabLayout=getView().findViewById(R.id.tblay);
        viewPager=getView().findViewById(R.id.vp);

        tabLayout.setupWithViewPager(viewPager);

        TAbadapter tAbadapter=new TAbadapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tAbadapter.addfrg(new contentfragment1() ,"HOME");
        tAbadapter.addfrg(new contentfragment2() ,"CATAGORY");
        tAbadapter.addfrg(new contentfragment3() ,"FREE BOOK");
        tAbadapter.addfrg(new contentFragment4() ,"WRITER");
        tAbadapter.addfrg(new contentfragment5() ,"PUBLICATION");
        viewPager.setAdapter(tAbadapter);

    }


}