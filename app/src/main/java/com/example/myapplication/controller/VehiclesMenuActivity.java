package com.example.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.controller.vehicles.NewVehicleActivity;
import com.example.myapplication.controller.vehicles.SecondHandVehicleActivity;
import com.example.myapplication.controller.vehicles.SoldVehicleActivity;
import com.example.myapplication.controller.vehicles.TradeInVehicleActivity;

public class VehiclesMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vehicles_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void onIkinciElAracAlimButtonClick(View view) {

        Intent intent = new Intent(VehiclesMenuActivity.this, SecondHandVehicleActivity.class);
        startActivity(intent);
    }

    public void onSifirAracAlimButtonClick(View view) {

        Intent intent = new Intent(VehiclesMenuActivity.this, NewVehicleActivity.class);
        startActivity(intent);
    }

    public void onTakasAracAlimButtonClick(View view) {

        Intent intent = new Intent(VehiclesMenuActivity.this, TradeInVehicleActivity.class);
        startActivity(intent);
    }

    public void onSatisButtonClick(View view) {

        Intent intent = new Intent(VehiclesMenuActivity.this, SoldVehicleActivity.class);
        startActivity(intent);
    }

}