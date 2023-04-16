package com.example.hackaton.feature.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hackaton.R;

import java.util.ArrayList;
import java.util.List;

public class PatientCardActivity extends AppCompatActivity {

    Spinner spinner;
    TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_card);

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
    }
}