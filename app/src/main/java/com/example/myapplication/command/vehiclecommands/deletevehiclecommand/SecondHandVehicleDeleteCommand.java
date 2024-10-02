package com.example.myapplication.command.vehiclecommands.deletevehiclecommand;

import android.app.Activity;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.model.vehicles.SecondHandVehicle;

public class SecondHandVehicleDeleteCommand implements DeleteVehicleCommand {
    SecondHandVehicle vehicle;
    EditText editTextLicensePlate;

    public SecondHandVehicleDeleteCommand(Activity activity) {
        super();
        this.vehicle = SecondHandVehicle.getInstance();
        editTextLicensePlate = activity.findViewById(R.id.licensePlate);
    }

    @Override
    public void execute() {
        vehicle.setLicensePlate(editTextLicensePlate.getText().toString().trim());


    }
}
