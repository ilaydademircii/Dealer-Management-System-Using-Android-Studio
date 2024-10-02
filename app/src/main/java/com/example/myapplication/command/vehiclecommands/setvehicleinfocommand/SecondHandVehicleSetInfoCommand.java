package com.example.myapplication.command.vehiclecommands.setvehicleinfocommand;

import android.app.Activity;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.model.vehicles.SecondHandVehicle;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class SecondHandVehicleSetInfoCommand implements SetVehicleInfoCommand {
    // Araç bilgilerini görüntülemek için EditText alanları

    EditText editTextModel;
    EditText editTextYear;
    EditText editTextPrice;
    EditText editTextChassisNo;
    EditText editTextExplanation;
    EditText editTextLicensePlate;

    SecondHandVehicle vehicle;

    public SecondHandVehicleSetInfoCommand(Activity activity) {
        super();
        this.vehicle = SecondHandVehicle.getInstance();

        // Activity'den XML'deki EditText bileşenlerini alıyoruz
        editTextModel = activity.findViewById(R.id.model);
        editTextYear = activity.findViewById(R.id.year);
        editTextPrice = activity.findViewById(R.id.receivingPrice);
        editTextLicensePlate = activity.findViewById(R.id.licensePlate);
        editTextChassisNo = activity.findViewById(R.id.chassisNo);
        editTextExplanation = activity.findViewById(R.id.explanation);

    }

    @Override
    public void execute() {

        // EditText'leri araç bilgileri ile güncelliyoruz
        editTextModel.setText(vehicle.getModel());
        editTextYear.setText(vehicle.getYear());

        // Fiyatı doğru formatta görüntülüyoruz
        BigDecimal priceAsBigDecimal = new BigDecimal(vehicle.getPrice());
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("tr", "TR"));
        String formattedPrice = numberFormat.format(priceAsBigDecimal) + " TL";
        editTextPrice.setText(formattedPrice);

        editTextLicensePlate.setText(vehicle.getLicensePlate());
        editTextChassisNo.setText(vehicle.getChassisNo());
        editTextExplanation.setText(vehicle.getExplanation());
    }
}
