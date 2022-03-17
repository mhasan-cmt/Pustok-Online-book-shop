package com.teamphoenix.pustok_onlinebookshop.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivityCartBinding;
import com.teamphoenix.pustok_onlinebookshop.entity.Cart;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
private MaterialToolbar materialToolbar;
ActivityCartBinding cartBinding;
CartRecyclerAdapter cartAdapter;
ArrayList<Cart> cartModelList;
    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartBinding = ActivityCartBinding.inflate(getLayoutInflater());

        setContentView(cartBinding.getRoot());
        materialToolbar = cartBinding.checkoutToolbar;

        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cartModelList = new ArrayList<>();
        cartModelList.add(new Cart("test_userid", "23-02-3","jsdkfj", "sdfsdf", "ljsldf","sdfskfdj"));
        cartAdapter = new CartRecyclerAdapter(this, cartModelList);
        cartBinding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        cartBinding.cartRecyclerView.setAdapter(cartAdapter);

    }
}