package com.example.hackaton.feature.ui.analizi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hackaton.model.Banner;
import com.example.hackaton.feature.adapters.CustomBannerAdapter;
import com.example.hackaton.api.emaicode.EmailCodeAPI;
import com.example.hackaton.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AnaliziFragment extends Fragment {
    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_analizi, container, false);
        recyclerView = v.findViewById(R.id.horizontalScrollView);
        EmailCodeAPI.getInstance().getBanners().enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(@NonNull Call<List<Banner>> call, @NonNull Response<List<Banner>> response) {
                List<Banner> ar = response.body();
                Log.d("LIST", ar.get(0).getName());
                CustomBannerAdapter customBannerAdapter = new CustomBannerAdapter(ar);
                recyclerView.setAdapter(customBannerAdapter);
                LinearLayoutManager lm = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(lm);
            }

            @Override
            public void onFailure(@NonNull Call<List<Banner>> call, @NonNull Throwable t) {

            }
        });


        return v;
    }

}