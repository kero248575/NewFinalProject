package com.example.auser.newfinalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText email;
    EditText username;
    EditText pw;
    EditText age;
    Button btn;

    SharedPreferences settings;
    private final String PREF_NAME = "USERNAME";
    private final String PREF_PASSWORD = "PASSWORD";
    String data = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.etEmail);
        username = (EditText) findViewById(R.id.etUserName);
        pw = (EditText) findViewById(R.id.etPassword);
        age = (EditText) findViewById(R.id.etAge);
        btn=(Button) findViewById(R.id.btnRegister);

        setListeners();
    }

    void setListeners(){
        btn.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String mail = email.getText().toString();
            String name = username.getText().toString();
            String password = pw.getText().toString();
            String old = age.getText().toString();

            if(mail == "" || name == "" || password =="" || old =="")   {
                Toast.makeText(RegisterActivity.this,"不可空白", Toast.LENGTH_SHORT).show();
            }   else {
                settings = getSharedPreferences(data,0);
                settings.edit().putString("USERNAME", username.getText().toString()).commit();
                settings.edit().putString("PASSWORD", pw.getText().toString()).commit();

                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, UserHomePage.class);
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        settings = getSharedPreferences(data,0);
        settings.edit().putString(PREF_NAME, username.getText().toString()).commit();
        settings.edit().putString(PREF_PASSWORD, pw.getText().toString()).commit();
    }
}
