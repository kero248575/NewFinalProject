package com.example.auser.newfinalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText pw;
    Button btn;
    TextView tv,tipText2,tipText3;
    private LinearLayout description;
    private ImageView imageView1;

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
        playFrameAnimation();
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
        description = (LinearLayout)findViewById(R.id.description);

        tipText2 = (TextView) findViewById(R.id.tipText2);
        tipText3 = (TextView) findViewById(R.id.tipText3);
        imageView1=(ImageView)findViewById(R.id.imageView1);
    }

    void setListeners() {
        btn.setOnClickListener(listener);
        tv.setOnClickListener(tvListener);
        description.setOnClickListener(descListener);
    }
    int clickCount = 1;
    private View.OnClickListener descListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if(clickCount ==1){
                tipText2.setVisibility(View.VISIBLE);
                clickCount++;
            }else if(clickCount ==2){
                tipText3.setVisibility(View.VISIBLE);
                clickCount++;
            }else
                description.setVisibility(View.GONE);
        }
    };
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState !=null)
            outState.putInt("clickCount",clickCount);
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
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            clickCount = savedInstanceState.getInt("clickCount");
            if (clickCount>=3)
                description.setVisibility(View.GONE);
            else if (clickCount==2){
                description.setVisibility(View.VISIBLE);
                tipText2.setVisibility(View.VISIBLE);
                tipText3.setVisibility(View.VISIBLE);
            }

        }




    }

    void playFrameAnimation(){
        AnimationDrawable anim1=(AnimationDrawable) imageView1.getBackground();
        anim1.start();

    }


}
