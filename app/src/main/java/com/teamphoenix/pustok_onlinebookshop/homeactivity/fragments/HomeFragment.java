package com.teamphoenix.pustok_onlinebookshop.homeactivity.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.FRAGMENTFORTABLAYOUT.TAbadapter;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.FRAGMENTFORTABLAYOUT.contentFragment4;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.FRAGMENTFORTABLAYOUT.contentfragment1;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.FRAGMENTFORTABLAYOUT.contentfragment2;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.FRAGMENTFORTABLAYOUT.contentfragment3;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.FRAGMENTFORTABLAYOUT.contentfragment5;

import org.jetbrains.annotations.NotNull;


public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
  private   TabLayout tabLayout;
   private ViewPager viewPager;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
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