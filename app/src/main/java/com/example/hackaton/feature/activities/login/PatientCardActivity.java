package com.example.hackaton.feature.activities.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hackaton.R;
import com.example.hackaton.api.services.RetrofitDataBaseService;
import com.example.hackaton.feature.activities.mainscreen.MainPageActivity;
import com.example.hackaton.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientCardActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextView skip;
    private EditText name;
    private EditText lastname;
    private EditText mname;
    private EditText bith;
    private String sex;
    private AppCompatButton save;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_card);

        name = findViewById(R.id.e1);
        lastname = findViewById(R.id.e2);
        mname = findViewById(R.id.e3);
        bith = findViewById(R.id.e4);

        save = findViewById(R.id.save);

        spinner = findViewById(R.id.spinner);
        List<String> arr = new ArrayList<>();
        arr.add("Мужской");
        arr.add("Женский");
        ArrayAdapter<?> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        skip = findViewById(R.id.skipText1);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainPageActivity.class);
                startActivity(intent);
            }
        });


        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                enableSubmitIfReady();
            }
        });

        lastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                enableSubmitIfReady();
            }
        });

        mname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                enableSubmitIfReady();
            }
        });
        bith.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                enableSubmitIfReady();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName(name.getText().toString());
                user.setLastname(lastname.getText().toString());
                user.setMiddlename(mname.getText().toString());
                user.setBith(bith.getText().toString());
                user.setSex(spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString());
                Call<User> call = RetrofitDataBaseService.getInstance().getMyApi().addUser(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        System.out.println(response.code());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });

    }
    public void enableSubmitIfReady() {

        boolean first = name.getText().toString().length() > 3;
        boolean second = lastname.getText().toString().length() > 3;
        boolean third = mname.getText().toString().length() > 3;
        boolean fourth = bith.getText().toString().length() > 3;
        save.setEnabled(first && second && third && fourth);
    }
}