package com.example.hackaton.feature.ui.analizi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hackaton.api.emaicode.EmailCodeAPI;
import com.example.hackaton.feature.activities.mainscreen.KorzinaActivity;
import com.example.hackaton.feature.adapters.CatalogAdapter;
import com.example.hackaton.feature.adapters.CustomBannerAdapter;
import com.example.hackaton.model.Banner;
import com.example.hackaton.R;
import com.example.hackaton.model.Order;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AnaliziFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Order> pop = new ArrayList<>();
    ArrayList<Order> covid = new ArrayList<>();
    ArrayList<Order> comp = new ArrayList<>();

    RecyclerView recyclerView2;
    AppCompatButton b1;
    AppCompatButton b2;
    AppCompatButton b3;
    CardView korz;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_analizi, container, false);
        recyclerView = v.findViewById(R.id.horizontalScrollView);
        recyclerView2 = v.findViewById(R.id.catalogrec);
        korz = v.findViewById(R.id.cardforkorz);
        b1 = v.findViewById(R.id.b1);
        b2 = v.findViewById(R.id.b2);
        b3 = v.findViewById(R.id.b3);


        EmailCodeAPI.getInstance().getBanners().enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(@NonNull Call<List<Banner>> call, @NonNull Response<List<Banner>> response) {
                List<Banner> ar = response.body();

                Log.d("LIST", ar.get(0).getName());
                CustomBannerAdapter customBannerAdapter = new CustomBannerAdapter(ar, getContext());
                recyclerView.setAdapter(customBannerAdapter);
                LinearLayoutManager lm = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(lm);


            }

            @Override
            public void onFailure(@NonNull Call<List<Banner>> call, @NonNull Throwable t) {

            }
        });
        EmailCodeAPI.getInstance().getCatalog().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {
                List<Order> aror = response.body();
                for(int i = 0; i < aror.size(); i ++) {
                    if(aror.get(i).getCategory().equals("Популярные")) {
                        pop.add(aror.get(i));
                    } else if(aror.get(i).getCategory().equals("COVID")) {
                        covid.add(aror.get(i));
                    } else {
                        comp.add(aror.get(i));
                    }
                }

                CatalogAdapter catalogAdapter = new CatalogAdapter(pop);
                recyclerView2.setAdapter(catalogAdapter);
                LinearLayoutManager lm2 = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView2.setLayoutManager(lm2);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        catalogAdapter.setLocalDataSet(pop);
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        catalogAdapter.setLocalDataSet(covid);
                    }
                });
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        catalogAdapter.setLocalDataSet(comp);
                    }
                });
                korz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Gson gson = new Gson();
                        String json = gson.toJson(catalogAdapter.korza);
                        Intent i = new Intent(getContext(), KorzinaActivity.class);
                        i.putExtra("korzina", json);
                        startActivity(i);
                    }
                });

            }

            @Override
            public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {

            }
        });


        return v;
    }

}