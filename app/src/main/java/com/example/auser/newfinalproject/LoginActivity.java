package com.example.auser.newfinalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText pw;
    Button btn;
    TextView tv;
    SharedPreferences settings;
    private final String PREF_NAME = "USERNAME";
    private final String PREF_PASSWORD = "PASSWORD";
    private final String data = "DATA";
    String t_name;
    String t_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        settings = getSharedPreferences(data,0);
        findViews();
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        settings = getSharedPreferences(data,0);
        t_name = settings.getString(PREF_NAME, "");
        t_pw = settings.getString(PREF_PASSWORD, "");

        username.setText(t_name);
        pw.setText(t_pw);
    }

    void findViews() {
        username = (EditText)findViewById(R.id.etUserName);
        pw = (EditText)findViewById(R.id.etPassword);
        btn = (Button)findViewById(R.id.btnSignIn);
        tv = (TextView)findViewById(R.id.tvRegister);
    }

    void setListeners() {
        btn.setOnClickListener(listener);
        tv.setOnClickListener(tvListener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (username.getText().toString().equals("") || pw.getText().toString().equals("")) {
                Toast.makeText(LoginActivity.this, "不可空白", Toast.LENGTH_SHORT).show();
            } else {
                t_name = settings.getString(PREF_NAME, "");
                t_pw = settings.getString(PREF_PASSWORD, "");

                if (t_name.equals(username.getText().toString()) && t_pw.equals(pw.getText().toString())) {
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, DummyNote.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "帳號驗證失敗", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    View.OnClickListener tvListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    };
}
