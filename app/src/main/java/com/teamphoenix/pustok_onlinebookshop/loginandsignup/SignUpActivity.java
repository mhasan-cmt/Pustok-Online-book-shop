package com.teamphoenix.pustok_onlinebookshop.loginandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.teamphoenix.pustok_onlinebookshop.R;
import com.teamphoenix.pustok_onlinebookshop.entity.User;
import com.teamphoenix.pustok_onlinebookshop.homeactivity.HomeActivity;
import com.teamphoenix.pustok_onlinebookshop.listeners.onSignupListener;
import com.teamphoenix.pustok_onlinebookshop.service.FirebaseAuthService;

public class SignUpActivity extends AppCompatActivity implements onSignupListener {
    EditText etUserName, etPassword, etEmail, etConfirmPassword, etPhone;
    ProgressDialog progressDialog;
    //    User Entity Object
    User user;
    //    Firebase Authentication Service Object
    FirebaseAuthService firebaseAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etUserName = findViewById(R.id.puser);
        etPassword = findViewById(R.id.pass);
        etConfirmPassword = findViewById(R.id.conpass);
        etEmail = findViewById(R.id.Bmail);
        etPhone = findViewById(R.id.BphoneNumber);
//        Creating a new Object for firebase auth service
        firebaseAuthService = new FirebaseAuthService(this);
        if (firebaseAuthService.checkUserSignedIn()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    public int at2(){
            if (etUserName.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
                etUserName.requestFocus();
            } else if (etEmail.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter an e-mail", Toast.LENGTH_SHORT).show();
                etEmail.requestFocus();
                return 0;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
                Toast.makeText(this, "Enter a valid email!", Toast.LENGTH_SHORT).show();
                etEmail.requestFocus();
                return 0;
            } else if (etPhone.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter a Mobile Number!", Toast.LENGTH_SHORT);
                etPhone.requestFocus();
                return 0;
            } else if (etPassword.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter a password to continue!", Toast.LENGTH_SHORT).show();
                etPassword.requestFocus();
                return 0;

            } else if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                Toast.makeText(this, "Password does not match!", Toast.LENGTH_SHORT).show();
                etConfirmPassword.requestFocus();
                return 0;
            } else return 1;
        return 0;
    }
        public void signUp (View view){
            if (at2() == 1) {
                progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Please Wait...!");
                progressDialog.setMessage("Creating Account!");
                progressDialog.show();
                user = new User(etUserName.getText().toString(),
                        etEmail.getText().toString(),
                        etPhone.getText().toString(),
                        etConfirmPassword.getText().toString());
                firebaseAuthService.createUserWithEmailAndPassword(user, this);
                progressDialog.dismiss();
            }else{
                Toast.makeText(this, "broken", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onSuccess () {
            Toast.makeText(this, "Account Created...!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }

        @Override
        public void onError (String msg){
            Toast.makeText(this, "Account creation failed dut to some errors!", Toast.LENGTH_SHORT);
        }
    }