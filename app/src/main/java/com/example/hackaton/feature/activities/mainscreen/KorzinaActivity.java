package com.example.hackaton.feature.activities.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.feature.adapters.KorzinaAdapter;
import com.example.hackaton.model.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hackaton.R;
import com.example.hackaton.model.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class KorzinaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AppCompatButton back;
    AppCompatButton delAll;
    TextView sumpres;
    AppCompatButton btnnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korzina);
        recyclerView = (RecyclerView) findViewById(R.id.recforkorz);
        back = (AppCompatButton) findViewById(R.id.backag);
        delAll = (AppCompatButton) findViewById(R.id.delALL);
        sumpres = (TextView) findViewById(R.id.summof);
        btnnext = (AppCompatButton) findViewById(R.id.appCompatButton2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(KorzinaActivity.this, MainPageActivity.class);
                startActivity(i);
            }
        });
        delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        String json = getIntent().getStringExtra("korzina");
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<Order>>(){}.getType();
        ArrayList<Order> list = gson.fromJson(json, collectionType);

        KorzinaAdapter korzinaAdapter = new KorzinaAdapter(list, sumpres);
        sumpres.setText(korzinaAdapter.getSum() + "₽");
        recyclerView.setAdapter(korzinaAdapter);
        LinearLayoutManager lm2 = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm2);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });



    }
}