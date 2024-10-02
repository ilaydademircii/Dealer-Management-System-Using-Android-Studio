package com.example.myapplication.command.vehiclecommands.deletevehiclecommand;

import android.app.Activity;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.model.vehicles.TradeInVehicle;

public class TradeInVehicleDeleteCommand implements DeleteVehicleCommand {
    TradeInVehicle vehicle;
    EditText editTextLicensePlate;

    public TradeInVehicleDeleteCommand(Activity activity) {
        super();
        this.vehicle = TradeInVehicle.getInstance();
        editTextLicensePlate = activity.findViewById(R.id.licensePlate);
    }

    @Override
    public void execute() {
        vehicle.setLicensePlate(editTextLicensePlate.getText().toString().trim());


    }
}