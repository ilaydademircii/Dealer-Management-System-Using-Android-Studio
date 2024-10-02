package com.example.myapplication.controller.vehicles;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.command.vehiclecommands.receivingvehiclecommand.NewVehicleReceivingCommand;
import com.example.myapplication.model.vehicles.NewVehicle;

public class NewVehicleActivity extends AppCompatActivity {


    NewVehicle vehicle;
    NewVehicleReceivingCommand vehicleReceivingCommand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_vehicle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.vehicle = NewVehicle.getInstance();
        this.vehicleReceivingCommand = new NewVehicleReceivingCommand(this);

    }


    public void onSaveButtonClicked(View view) {
        try {

            vehicleReceivingCommand.execute();
            vehicle.save();
            Toast.makeText(this, "Yeni araç başarıyla kaydedildi...", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Bir hata oluştu: " + e.getMessage(), Toast.LENGTH_LONG).show();

            throw new RuntimeException(e);

        }
    }
}