package com.teamphoenix.pustok_onlinebookshop.payement;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.cart.CartActivity;
import com.teamphoenix.pustok_onlinebookshop.databinding.ActivityPaymentBinding;

public class PaymentActivity extends AppCompatActivity {
    private final String[] paymentMethods = {"Bkash", "Nogod", "Rocket", "DBBL",  "IBBL"};
    ActivityPaymentBinding paymentActivityBinding;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paymentActivityBinding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(paymentActivityBinding.getRoot());
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, paymentMethods);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        paymentActivityBinding.paymentSpinner.setAdapter(arrayAdapter);
        Intent intent = getIntent();
        paymentActivityBinding.paymentPrice.setText(intent.getStringExtra("price_from_cart"));
        paymentActivityBinding.paymentPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSuccessToast();
                Intent toCartAgain = new Intent(PaymentActivity.this, CartActivity.class);
                startActivity(toCartAgain);
                finish();
            }
        });
    }

    private void showSuccessToast() {
        View toastLayout = getLayoutInflater().inflate(R.layout.payment_success_toast,
                findViewById(R.id.custom_success_toast));
        //Creating the Toast
        Toast successToast = new Toast(PaymentActivity.this);
        successToast.setDuration(Toast.LENGTH_SHORT);
        successToast.setGravity(Gravity.BOTTOM, 0, 15);
        successToast.setView(toastLayout);
        successToast.show();
    }
}