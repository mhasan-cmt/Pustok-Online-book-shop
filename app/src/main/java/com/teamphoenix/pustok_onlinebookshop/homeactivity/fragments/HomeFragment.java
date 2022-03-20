package com.teamphoenix.pustok_onlinebookshop.homeactivity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.cart.CartActivity;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter.HomeTabAdapter;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.tabs.WriterTabFragment;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.tabs.HomeTabFragment;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.tabs.CategoryTabFragment;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.tabs.FreeBooksTabFragment;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.tabs.PublisherTabFragment;

import org.jetbrains.annotations.NotNull;


public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    private MaterialToolbar topActionBar;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment
        return view;
    }

    /**
     * This Method is for Setup Home Action Bar
     *
     * @param view
     */
    private void setUpActionBar(View view) {
        //        Top Action Bar Setup
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
    }

    /**
     * This Method is for setting the tab layout and viewpager
     */
    private void setupTabLayoutWithViewPager() {
        tabLayout = getView().findViewById(R.id.tblay);
        viewPager = getView().findViewById(R.id.vp);
        tabLayout.setupWithViewPager(viewPager);
        HomeTabAdapter homeTabAdapter = new HomeTabAdapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        homeTabAdapter.addfrg(new HomeTabFragment(), "হোম");
        homeTabAdapter.addfrg(new CategoryTabFragment(), "ক্যাটাগরি");
        homeTabAdapter.addfrg(new FreeBooksTabFragment(), "ফ্রী বই");
        homeTabAdapter.addfrg(new WriterTabFragment(), "লেখক");
        homeTabAdapter.addfrg(new PublisherTabFragment(), "প্রকাশক");
        viewPager.setAdapter(homeTabAdapter);
        homeTabAdapter.notifyDataSetChanged();
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpActionBar(view);
        setupTabLayoutWithViewPager();
    }


}