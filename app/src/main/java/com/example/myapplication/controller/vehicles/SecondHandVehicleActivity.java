package com.example.myapplication.controller.vehicles;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.command.customercommands.receivingcustomercommand.CustomerrReceivingCommand;
import com.example.myapplication.command.vehiclecommands.receivingvehiclecommand.SecondHandVehicleReceivingCommand;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.vehicles.SecondHandVehicle;

public class SecondHandVehicleActivity extends AppCompatActivity {

    SecondHandVehicle vehicle;
    Customer customer;

    CustomerrReceivingCommand customerrReceivingCommand;
    SecondHandVehicleReceivingCommand vehicleReceivingCommand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_hand_vehicle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.vehicle = SecondHandVehicle.getInstance();
        this.customer = Customer.getInstance();
        this.customerrReceivingCommand = new CustomerrReceivingCommand(this);
        this.vehicleReceivingCommand = new SecondHandVehicleReceivingCommand(this);
    }


    public void onSaveButtonClicked(View view) {
        try {
            customerrReceivingCommand.execute();
            vehicle.setCustomerIdNo(Customer.getInstance().getIdNumber());
            customer.save();
            vehicleReceivingCommand.execute();
            vehicle.save();
            Toast.makeText(this, "ikinci el araç başarıyla kaydedildi...", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Bir hata oluştu: " + e.getMessage(), Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);

        }
    }

}




