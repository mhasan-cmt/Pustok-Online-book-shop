package com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeTabAdapter extends FragmentPagerAdapter {
    public final ArrayList<Fragment>fragmentArrayList=new ArrayList<>();
    public final ArrayList<String>stringArrayList=new ArrayList<>();


    public HomeTabAdapter(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void addfrg(Fragment fragment,String title){
        fragmentArrayList.add(fragment);
        stringArrayList.add(title);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringArrayList.get(position);
    }
}
