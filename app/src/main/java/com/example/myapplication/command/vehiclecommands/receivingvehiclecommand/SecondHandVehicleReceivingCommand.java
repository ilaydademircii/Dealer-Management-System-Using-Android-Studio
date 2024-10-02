package com.example.myapplication.command.vehiclecommands.receivingvehiclecommand;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.vehicles.SecondHandVehicle;

public class SecondHandVehicleReceivingCommand implements VehicleReceivingCommand {

    SecondHandVehicle vehicle;

    // EditText fields
    EditText editTextModel;
    EditText editTextYear;
    EditText editTextPrice;
    EditText editTextChassisNo;
    EditText editTextLicensePlate;
    EditText editTextExplanation;
    EditText editTextIdNumber;

    Activity activity;

    public SecondHandVehicleReceivingCommand(Activity activity) {
        super();
        this.vehicle = SecondHandVehicle.getInstance();

        this.activity = activity;
        // Activity'den gelen XML'deki EditText bileşenlerini alıyoruz
        editTextModel = activity.findViewById(R.id.model);
        editTextYear = activity.findViewById(R.id.year);
        editTextPrice = activity.findViewById(R.id.receivingPrice);
        editTextChassisNo = activity.findViewById(R.id.chassisNo);
        editTextLicensePlate = activity.findViewById(R.id.licensePlate);
        editTextExplanation = activity.findViewById(R.id.explanation);
        editTextIdNumber = activity.findViewById(R.id.customerTcNo);


    }

    @Override
    public void execute() {
        // EditText'lerden veriyi alıp araç nesnesine atıyoruz
        // Input validation for all EditText fields
        if (editTextModel.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, "Model is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editTextYear.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, "Year is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editTextPrice.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, "Price is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editTextChassisNo.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, "Chassis No is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editTextLicensePlate.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, "License Plate is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editTextExplanation.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, "Explanation is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editTextIdNumber.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, "ID Number is required", Toast.LENGTH_SHORT).show();
            return;
        }

        vehicle.setModel(editTextModel.getText().toString().trim());
        vehicle.setYear(editTextYear.getText().toString().trim());

        String formattedPrice = editTextPrice.getText().toString().trim();
        String priceWithoutDot = formattedPrice.replace(".", "");
        vehicle.setPrice(priceWithoutDot);

        vehicle.setChassisNo(editTextChassisNo.getText().toString().trim());
        vehicle.setLicensePlate(editTextLicensePlate.getText().toString().trim());
        vehicle.setExplanation(editTextExplanation.getText().toString().trim());
        vehicle.setCustomerIdNo(editTextIdNumber.getText().toString().trim());

    }
}
