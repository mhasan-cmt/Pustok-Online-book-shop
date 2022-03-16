package com.teamphoenix.pustok_onlinebookshop.loginandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.HomeActivity;
import com.teamphoenix.pustok_onlinebookshop.listeners.onSignInListener;
import com.teamphoenix.pustok_onlinebookshop.service.FirebaseAuthService;

public class LoginActivity extends AppCompatActivity {
    EditText txusr, txps;
    Button loginBtn;
    TextView textView;
    FirebaseAuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        txusr = findViewById(R.id.puser);
        txps = findViewById(R.id.Bpass);
        textView = findViewById(R.id.CNAC);
        loginBtn = findViewById(R.id.btn_login);
        authService = new FirebaseAuthService(this);

        //        Checking user already signed in or not
        if (authService.checkUserSignedIn()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });

    }

    public void cna(View view) {

    }
    public int validateUserInput() {

        int a = 0;
        if (txusr.getText().toString().isEmpty()) {
            txusr.requestFocus();
            Toast.makeText(this, "Enter your email", Toast.LENGTH_SHORT).show();
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

    public void logIn() {
        ProgressDialog  progressDialog= new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging into your Pustok Account!");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        if (validateUserInput() == 1) {
            authService.login(txusr.getText().toString(), txps.getText().toString(), new onSignInListener() {
                @Override
                public void onSignInSuccess(String msg) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                }

                @Override
                public void onSignInFailed(String error) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}