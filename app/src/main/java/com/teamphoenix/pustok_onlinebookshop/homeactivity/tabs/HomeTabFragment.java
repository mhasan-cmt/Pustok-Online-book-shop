package com.teamphoenix.pustok_onlinebookshop.homeactivity.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.teamphoenix.pustok_onlinebookshop.R;

import java.util.ArrayList;


public class HomeTabFragment extends Fragment {

    private ArrayList<SlideModel> imgList;
    private ImageSlider home_slider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpImageSlider();
        home_slider= getView().findViewById(R.id.home_slider);
        home_slider.setImageList(imgList);
    }
    private void setUpImageSlider() {
        imgList = new ArrayList<>();
        imgList.add(new SlideModel("https://bit.ly/2YoJ77H","The animal population decreased by 58 percent in 42 years.", ScaleTypes.CENTER_CROP));
        imgList.add(new SlideModel("https://bit.ly/2BteuF2","Elephants and tigers may become extinct.", ScaleTypes.CENTER_CROP));
        imgList.add(new SlideModel("https://bit.ly/3fLJf72","And people do that.", ScaleTypes.CENTER_CROP));
    }
}