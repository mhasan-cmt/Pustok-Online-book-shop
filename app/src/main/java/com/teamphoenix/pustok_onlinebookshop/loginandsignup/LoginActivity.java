package com.teamphoenix.pustok_onlinebookshop.loginandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.teamphoenix.pustok_onlinebookshop.R;

public class LoginActivity extends AppCompatActivity {
    EditText txusr, txps;
    Button button1;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        txusr = findViewById(R.id.puser);
        txps = findViewById(R.id.Bpass);
        textView = findViewById(R.id.CNAC);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

    }

    public void cna(View view) {

    }


    public int at() {

        int a = 0;
        if (txusr.getText().toString().isEmpty()) {
            txusr.requestFocus();

            Toast.makeText(this, "Enter an  user name", Toast.LENGTH_SHORT).show();
            return 0;
        } else if (txps.getText().toString().isEmpty()) {
            txps.requestFocus();
            Toast.makeText(this, "Enter pass", Toast.LENGTH_SHORT).show();
            return 0;

        } else if (txps.getText().length() < 6) {
            txps.requestFocus();
            Toast.makeText(this, "Enter valid Pass", Toast.LENGTH_SHORT).show();
            return 0;


        } else {

            a++;
        }
        return a;

    }

    public void logn(View view) {
        if (at() == 1) {

            Toast.makeText(this, "log in success", Toast.LENGTH_SHORT).show();
        }
    }


}