package com.teamphoenix.pustok_onlinebookshop.loginandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.teamphoenix.pustok_onlinebookshop.R;

public class signupform extends AppCompatActivity {
EditText tName,tpas,teml,tconps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupform);
tName=findViewById(R.id.puser);
tpas=findViewById(R.id.pass);
tconps=findViewById(R.id.conpass);
teml=findViewById(R.id.Bmail);

    }

    public int at2(){
        String  EMAIL=teml.getText().toString();
        int a=0;
        if (EMAIL.isEmpty()) {
            teml.requestFocus();

            Toast.makeText(this, "Enter an   e-mail", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(EMAIL).matches()){
            teml.requestFocus();
            Toast.makeText(this, "Entar valid email", Toast.LENGTH_SHORT).show();

            return 0;

        }
        else if (tpas.getText().toString().isEmpty()) {
            tpas.requestFocus();
            Toast.makeText(this, "Enter pass", Toast.LENGTH_SHORT).show(); return 0;

        } else if (tpas.getText().length() <6) {
            tpas.requestFocus();
            Toast.makeText(this, "Enter valid Pass", Toast.LENGTH_SHORT).show(); return 0;


        } else if (tconps.getText().toString().isEmpty()) {
            tconps.requestFocus();
            Toast.makeText(this, "COnfirm pass", Toast.LENGTH_SHORT).show();return 0;

        } else if (!tpas.getText().toString().equals(tconps.getText().toString())) {
            tconps.requestFocus();
            Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show();
            return 0;

        }  else if (tName.getText().toString().isEmpty()) {
            tName.requestFocus();
            Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
            return 0;
        }

        else {

            a++;
        }
        return a;

    }

    public void sing(View view) {
        if(at2()==1){

            Toast.makeText(this, "sign up success", Toast.LENGTH_SHORT).show();

        }

    }

    public void ibt(View view) {

    }
}