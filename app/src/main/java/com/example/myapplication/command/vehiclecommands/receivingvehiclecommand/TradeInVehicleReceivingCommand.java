package com.example.myapplication.command.vehiclecommands.receivingvehiclecommand;

import android.app.Activity;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.model.vehicles.TradeInVehicle;

public class TradeInVehicleReceivingCommand implements VehicleReceivingCommand {

    TradeInVehicle vehicle;

    // EditText fields
    EditText editTextModel;
    EditText editTextYear;
    EditText editTextPrice;
    EditText editTextChassisNo;
    EditText editTextLicensePlate;
    EditText editTextExplanation;

    public TradeInVehicleReceivingCommand(Activity activity) {
        super();
        this.vehicle = TradeInVehicle.getInstance();

        // Activity'den gelen XML'deki EditText bileşenlerini alıyoruz
        editTextModel = activity.findViewById(R.id.model);
        editTextYear = activity.findViewById(R.id.year);
        editTextPrice = activity.findViewById(R.id.receivingPrice);
        editTextChassisNo = activity.findViewById(R.id.chassisNo);
        editTextLicensePlate = activity.findViewById(R.id.licensePlate);
        editTextExplanation = activity.findViewById(R.id.explanation);
    }

    @Override
    public void execute() {
        // EditText'lerden veriyi alıp araç nesnesine atıyoruz
        vehicle.setModel(editTextModel.getText().toString().trim());
        vehicle.setYear(editTextYear.getText().toString().trim());

        String formattedPrice = editTextPrice.getText().toString().trim();
        String priceWithoutDot = formattedPrice.replace(".", "");
        vehicle.setPrice(priceWithoutDot);

        vehicle.setChassisNo(editTextChassisNo.getText().toString().trim());
        vehicle.setLicensePlate(editTextLicensePlate.getText().toString().trim());
        vehicle.setExplanation(editTextExplanation.getText().toString().trim());
    }
}
