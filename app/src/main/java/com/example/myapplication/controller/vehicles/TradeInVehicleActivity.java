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
import com.example.myapplication.command.customercommands.receivingcustomercommand.CustomerrReceivingCommand;
import com.example.myapplication.command.vehiclecommands.receivingvehiclecommand.TradeInVehicleReceivingCommand;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.vehicles.TradeInVehicle;

public class TradeInVehicleActivity extends AppCompatActivity {


    TradeInVehicle vehicle;
    Customer customer;

    CustomerrReceivingCommand customerrReceivingCommand;
    TradeInVehicleReceivingCommand vehicleReceivingCommand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trade_in_vehicle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.vehicle = TradeInVehicle.getInstance();
        this.customer = Customer.getInstance();
        this.customerrReceivingCommand = new CustomerrReceivingCommand(this);
        this.vehicleReceivingCommand = new TradeInVehicleReceivingCommand(this);
    }


    public void onSaveButtonClicked(View view) {
        try {
            customerrReceivingCommand.execute();
            vehicle.setCustomerIdNo(Customer.getInstance().getIdNumber());
            customer.save();

            vehicleReceivingCommand.execute();
            vehicle.save();

            Toast.makeText(this, "Takas araç başarıyla kaydedildi...", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Bir hata oluştu: " + e.getMessage(), Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);

        }
    }

}