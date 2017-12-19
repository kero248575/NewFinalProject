package com.example.auser.newfinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MainTest";
    EditText etAccount, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("finalExam");

        etAccount = (EditText)findViewById(R.id.etAccount);
        etPassword = (EditText)findViewById(R.id.etPassword);

    }

    void clearET(){
        etAccount.setText("");
        etPassword.setText("");
    }

    //getMethod method
    public void btnAdd(View v){
//        Log.d(TAG,"btn clicked.");
        if(etAccount.getText().toString().equals("") || etPassword.getText().toString().equals("")){
            Toast.makeText(this, "請勿留白", Toast.LENGTH_SHORT).show();
        }else{
            OkHttpClient client = new OkHttpClient();
            String param = "account=" + etAccount.getText().toString() + "&password=" + etPassword.getText().toString();
            Log.d("param",param);
            Request request = new Request.Builder()
                    .url("http://192.168.58.018:8080/code/newfinalproject/get_api_return.php?" + param)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, "fail");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "連線失敗", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d(TAG, "ok");
                    String json = response.body().string();
                    Log.d(TAG, json);
                    int flag = json.compareTo("0");
                    if (flag == 0){
                        Log.d(TAG, "0");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "新增失敗，使用者ID已存在", Toast.LENGTH_SHORT).show();
                                clearET();
                            }
                        });
                    } else {
                        Log.d(TAG, "1-android");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "新增資料", Toast.LENGTH_SHORT).show();
                                clearET();
                            }
                        });
                    }
                }
            });
        }

    }

    public void btnEdit(View v){
//        Log.d(TAG,"btn clicked.");
        if(etAccount.getText().toString().equals("") || etPassword.getText().toString().equals("")){
            Toast.makeText(this, "請勿留白", Toast.LENGTH_SHORT).show();
        } else {
            OkHttpClient client = new OkHttpClient();
            String param = "account=" + etAccount.getText().toString() + "&password=" + etPassword.getText().toString();
            Log.d("param",param);
            Request request = new Request.Builder()
                    .url("http://192.168.58.018:8080/code/newfinalproject/api_update.php?" + param)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, "fail");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "連線失敗", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d(TAG, "ok");
                    String json = response.body().string();
                    Log.d(TAG, json);
                    int flag = json.compareTo("0");
                    if (flag == 0){
                        Log.d(TAG, "0");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "資料已修改", Toast.LENGTH_SHORT).show();
                                clearET();
                            }
                        });
                    } else {
                        Log.d(TAG, "1-android");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "無此資料", Toast.LENGTH_SHORT).show();
                                clearET();
                            }
                        });
                    }
                }
            });
        }
    }

    public void btnDelete(View v){
//        Log.d(TAG,"btn clicked.");
        if(etAccount.getText().toString().equals("")){
            Toast.makeText(this, "帳號請勿留白", Toast.LENGTH_SHORT).show();
        } else {
            OkHttpClient client = new OkHttpClient();
            String param = "account=" + etAccount.getText().toString() + "&password=" + etPassword.getText().toString();
            Log.d("param",param);
            Request request = new Request.Builder()
                    .url("http://192.168.58.018:8080/code/newfinalproject/api_delete.php?" + param)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, "fail");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "連線失敗", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d(TAG, "ok");
                    String json = response.body().string();
                    Log.d(TAG, json);
                    int flag = json.compareTo("0");
                    if (flag == 0){
                        Log.d(TAG, "0");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "刪除成功", Toast.LENGTH_SHORT).show();
                                clearET();
                            }
                        });
                    } else {
                        Log.d(TAG, "1-android");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "無此資料", Toast.LENGTH_SHORT).show();
                                clearET();
                            }
                        });
                    }
                }
            });
        }

    }
}

