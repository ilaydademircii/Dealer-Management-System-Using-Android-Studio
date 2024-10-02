package com.example.myapplication.command.vehiclecommands.deletevehiclecommand;

import android.app.Activity;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.model.vehicles.NewVehicle;

public class NewVehicleDeleteCommand implements DeleteVehicleCommand {
    NewVehicle vehicle;
    EditText editTextChassisNo;

    public NewVehicleDeleteCommand(Activity activity) {
        super();
        this.vehicle = NewVehicle.getInstance();
        editTextChassisNo = activity.findViewById(R.id.chassisNo);
    }

    @Override
    public void execute() {

        vehicle.setChassisNo(editTextChassisNo.getText().toString().trim());


    }
}
