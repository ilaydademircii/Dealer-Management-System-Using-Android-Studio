package com.example.myapplication.command.vehiclecommands.receivingvehiclecommand;

import android.app.Activity;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.model.vehicles.NewVehicle;

public class NewVehicleReceivingCommand implements VehicleReceivingCommand {

    NewVehicle vehicle;

    // EditText fields
    EditText editTextModel;
    EditText editTextYear;
    EditText editTextPrice;
    EditText editTextChassisNo;
    //   EditText editTextLicensePlate;
    EditText editTextExplanation;

    public NewVehicleReceivingCommand(Activity activity) {
        super();
        this.vehicle = NewVehicle.getInstance();

        editTextModel = activity.findViewById(R.id.model);
        editTextYear = activity.findViewById(R.id.year);
        editTextPrice = activity.findViewById(R.id.receivingPrice);
        editTextChassisNo = activity.findViewById(R.id.chassisNo);
        editTextExplanation = activity.findViewById(R.id.explanation);
    }

    @Override
    public void execute() {
        vehicle.setModel(editTextModel.getText().toString().trim());
        vehicle.setYear(editTextYear.getText().toString().trim());

        String formattedPrice = editTextPrice.getText().toString().trim();
        String priceWithoutDot = formattedPrice.replace(".", "");
        vehicle.setPrice(priceWithoutDot);

        vehicle.setChassisNo(editTextChassisNo.getText().toString().trim());
        vehicle.setExplanation(editTextExplanation.getText().toString().trim());
    }
}
