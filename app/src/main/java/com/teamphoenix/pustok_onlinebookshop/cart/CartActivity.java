package com.teamphoenix.pustok_onlinebookshop.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivityCartBinding;
import com.teamphoenix.pustok_onlinebookshop.entity.Cart;
import com.teamphoenix.pustok_onlinebookshop.listeners.onGetAllCartItemsListener;
import com.teamphoenix.pustok_onlinebookshop.payment.PaymentActivity;
import com.teamphoenix.pustok_onlinebookshop.service.FireBaseDbService;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private MaterialToolbar materialToolbar;
    ActivityCartBinding cartBinding;
    CartRecyclerAdapter cartAdapter;

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


        cartBinding.cartPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
                intent.putExtra("price_from_cart", cartBinding.totalBookPrice.getText().toString());
                startActivity(intent);
            }
        });


        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        FireBaseDbService fireBaseDbService = new FireBaseDbService(this);
        fireBaseDbService.getAllCartItems(FirebaseAuth.getInstance().getUid(), new onGetAllCartItemsListener() {
            @Override
            public void onSuccess(ArrayList<Cart> carts) {
                float bookPrice = 0.00f;
                cartAdapter = new CartRecyclerAdapter(CartActivity.this, carts);
                cartBinding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this, RecyclerView.VERTICAL, false));
                cartBinding.cartRecyclerView.setAdapter(cartAdapter);
//                Getting total price
                for (Cart cart : carts) {
                    bookPrice = bookPrice + Float.parseFloat((String) cart.getTotalPrice());
                }
                cartBinding.totalBookPrice.setText("" + bookPrice);
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(CartActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}