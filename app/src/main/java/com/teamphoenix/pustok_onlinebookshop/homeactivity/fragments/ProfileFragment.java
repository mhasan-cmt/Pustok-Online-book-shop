package com.teamphoenix.pustok_onlinebookshop.homeactivity.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.gson.Gson;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.entity.User;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.adapter.ProfileRecyclerViewAdapter;
import com.teamphoenix.pustok_onlinebookshop.settings.SettingsActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private RecyclerView recyclerViewFirst, recyclerViewSecond;
    private ProfileRecyclerViewAdapter profileRecyclerViewAdapter, profileRecyclerViewAdapter2;
    private ArrayList<String> texts, texts2;
    private BarChart read_duration_chart;
    private Button settingBtn;
    private User user;
    private SharedPreferences profileSharedPreferences;
    private TextView profile_user_name, profile_user_mobile;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        finding all views
        recyclerViewFirst = getView().findViewById(R.id.profile_first_recycler_view);
        recyclerViewSecond = getView().findViewById(R.id.profile_second_recycler_view);
        read_duration_chart = getView().findViewById(R.id.read_duration_chart);
        settingBtn = getView().findViewById(R.id.settingBtn);
        profile_user_name = getView().findViewById(R.id.profile_user_name);
        profile_user_mobile = getView().findViewById(R.id.profile_user_mobile);
        profileSharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        settingProfileData(getUserData());
        settingUpListenerToSettingsButton();
        settingUpRecyclerViews();
        settingUpChart();
    }

    /**
     * THis Method is for setting user data to views
     *
     * @param user
     */
    private void settingProfileData(User user) {
        profile_user_name.setText(user.getUserName());
        profile_user_mobile.setText(user.getPhoneNumber());
    }

    /**
     * This Method is for getting the data from intent
     *
     * @return a User
     */
    private User getUserData() {
        Gson gson = new Gson();
        String userData = profileSharedPreferences.getString("UserData", "");
        this.user = gson.fromJson(userData, User.class);
        return this.user;
    }

    /**
     * This Method is for setting listener to the setting button
     */
    private void settingUpListenerToSettingsButton() {
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                getActivity().finish();
            }
        });
    }

    /**
     * This Method is for setting profile options data to the recyclerview
     */
    private void settingUpRecyclerViews() {
        texts = new ArrayList<>();
        texts2 = new ArrayList<>();

        texts.add("??????????????????");
        texts.add("???????????? ??????????????? ??????????????????");
        texts.add("????????????????????? ??????????????????");
        texts.add("???????????? ??????????????????");
        texts.add("???????????????????????? ???????????????");


        texts2.add("????????????????????? ????????? ????????????????????????");
        texts2.add("????????????????????? ??????????????????????????? ??????????????????????????????");
        texts2.add("?????????????????? ??????????????? ?????? ??????????????? ?????????");

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

        profileRecyclerViewAdapter = new ProfileRecyclerViewAdapter(texts, icons);
        profileRecyclerViewAdapter2 = new ProfileRecyclerViewAdapter(texts2, icons2);
        recyclerViewFirst.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewFirst.setAdapter(profileRecyclerViewAdapter);
        recyclerViewSecond.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSecond.setAdapter(profileRecyclerViewAdapter2);
    }

    /**
     * This method is for setting up bar chart data
     *
     * @return BAR ENTRY
     */
    private ArrayList<BarEntry> barDataValues() {
        ArrayList<BarEntry> dataValues = new ArrayList<>();
        dataValues.add(new BarEntry(0, 3));
        dataValues.add(new BarEntry(1, 4));
        dataValues.add(new BarEntry(2, 6));
        dataValues.add(new BarEntry(3, 10));
        dataValues.add(new BarEntry(4, 10));
        dataValues.add(new BarEntry(5, 10));
        dataValues.add(new BarEntry(6, 10));
        return dataValues;
    }

    /**
     * This Method is for setting up the chart in profile
     */
    void settingUpChart() {
        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Tue");
        xAxisLabel.add("Wed");
        xAxisLabel.add("Thu");
        xAxisLabel.add("Fri");
        xAxisLabel.add("Sat");
        xAxisLabel.add("Sun");
        xAxisLabel.add("Mon");

        XAxis xAxis = read_duration_chart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

        ValueFormatter valueFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xAxisLabel.get((int) value);
            }
        };

        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(valueFormatter);

        BarDataSet barDataSet = new BarDataSet(barDataValues(), "????????????????????? ??? ?????????");
        barDataSet.setColor(getView().getResources().getColor(R.color.primary));
        barDataSet.setBarShadowColor(getView().getResources().getColor(R.color.bar_bg));
        BarData barData = new BarData();
        barData.addDataSet(barDataSet);
        read_duration_chart.setPinchZoom(false);
        read_duration_chart.setDrawBarShadow(true);
        read_duration_chart.getAxisRight().setEnabled(false);
        read_duration_chart.getAxisLeft().setEnabled(false);
        read_duration_chart.setTouchEnabled(false);
        read_duration_chart.getDescription().setEnabled(false);
        read_duration_chart.setData(barData);
        read_duration_chart.invalidate();
    }
}