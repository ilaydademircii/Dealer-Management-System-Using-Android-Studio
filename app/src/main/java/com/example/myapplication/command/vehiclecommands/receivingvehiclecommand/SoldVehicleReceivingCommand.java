package com.example.myapplication.command.vehiclecommands.receivingvehiclecommand;

import android.app.Activity;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.model.vehicles.SoldVehicle;

public class SoldVehicleReceivingCommand implements VehicleReceivingCommand {

    SoldVehicle vehicle;

    // EditText fields
    EditText editTextModel;
    EditText editTextYear;
    EditText editTextReceivingPrice;
    EditText editTextSalePrice;
    EditText editTextReceivedPayment;
    EditText editTextChassisNo;
    EditText editTextLicensePlate;
    EditText editTextExplanation;


    public SoldVehicleReceivingCommand(Activity activity) {
        super();
        this.vehicle = SoldVehicle.getInstance();

        // Activity'den gelen XML'deki EditText bileşenlerini alıyoruz
        editTextModel = activity.findViewById(R.id.model);
        editTextYear = activity.findViewById(R.id.year);
        editTextSalePrice = activity.findViewById(R.id.salePrice);
        editTextReceivingPrice = activity.findViewById(R.id.receivingPrice);
        editTextReceivedPayment = activity.findViewById(R.id.receivedPayment);
        editTextChassisNo = activity.findViewById(R.id.chassisNo);
        editTextLicensePlate = activity.findViewById(R.id.licensePlate);
        editTextExplanation = activity.findViewById(R.id.explanation);
    }

    @Override
    public void execute() {
        // EditText'lerden veriyi alıp araç nesnesine atıyoruz
        vehicle.setModel(editTextModel.getText().toString().trim());
        vehicle.setYear(editTextYear.getText().toString().trim());

        String formattedPrice = editTextSalePrice.getText().toString().trim();
        String priceWithoutDot = formattedPrice.replace(".", "");
        vehicle.setSalePrice(priceWithoutDot);


        formattedPrice = editTextReceivingPrice.getText().toString().trim();
        priceWithoutDot = formattedPrice.replace(".", "");
        vehicle.setReceivingPrice(priceWithoutDot);


        vehicle.setRemainingPaymentAmount(editTextSalePrice.getText().toString().trim());

        formattedPrice = editTextReceivedPayment.getText().toString().trim();
        priceWithoutDot = formattedPrice.replace(".", "");
        vehicle.setReceivedPayment(priceWithoutDot);


        vehicle.setChassisNo(editTextChassisNo.getText().toString().trim());
        vehicle.setLicensePlate(editTextLicensePlate.getText().toString().trim());
        vehicle.setExplanation(editTextExplanation.getText().toString().trim());
    }
}
