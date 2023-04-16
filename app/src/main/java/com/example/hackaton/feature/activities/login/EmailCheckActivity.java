package com.example.hackaton.feature.activities.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hackaton.R;
import com.example.hackaton.api.emailcode.EmailCodeAPI;
import com.example.hackaton.feature.activities.mainscreen.MainPageActivity;
import com.example.hackaton.model.CodeResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailCheckActivity extends AppCompatActivity {
    String email;
    String userCode;
    TextView textView;
    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_check);
        email = getIntent().getStringExtra("emailStr");
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        textView = findViewById(R.id.nelog);
        textView.setText(email);
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et1.getText().toString().length() == 1){
                    et2.requestFocus();
                    et1.setEnabled(false);
                    et1.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et2.getText().toString().length() == 1){
                    et3.requestFocus();
                    et2.setEnabled(false);
                    et2.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et3.getText().toString().length() == 1){
                    et4.requestFocus();
                    et3.setEnabled(false);
                    et3.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et4.getText().toString().length() == 1){
                    userCode = et1.getText().toString()
                            + et2.getText().toString()
                            + et3.getText().toString()
                            + et4.getText().toString();
                    textView.setText(userCode);
                    EmailCodeAPI.getInstance().checkCode(email, userCode).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                            if(response.body().toString() != null) {
                                try {
                                    String responseString = response.body().string();
                                    Gson gson = new Gson();
                                    CodeResponse loginResponse = gson.fromJson(responseString, CodeResponse.class);
                                    String token = loginResponse.getToken();
                                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                    myEdit.putString("Token", token);
                                    myEdit.apply();
                                    Intent i = new Intent(EmailCheckActivity.this, MainPageActivity.class);
                                    startActivity(i);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Log.d("EMAILBLOCK", "НЕВЕРНЫЙ КОД");
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                            t.printStackTrace();
                        }
                    });


                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}