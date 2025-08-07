package com.example.myapplication.command.vehiclecommands.setvehicleinfocommand;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.vehicles.NewVehicle;

public class NewVehicleSetInfoCommand implements SetVehicleInfoCommand {
    // Araç bilgilerini görüntülemek için EditText alanları

    EditText editTextModel;
    EditText editTextYear;
    EditText editTextPrice;
    EditText editTextChassisNo;
    EditText editTextExplanation;

    NewVehicle vehicle;
    Activity activity;

    public NewVehicleSetInfoCommand(Activity activity) {
        super();
        this.vehicle = NewVehicle.getInstance();
        this.activity = activity;
        // Activity'den XML'deki EditText bileşenlerini alıyoruz
        editTextModel = activity.findViewById(R.id.model);
        editTextYear = activity.findViewById(R.id.year);
        editTextPrice = activity.findViewById(R.id.receivingPrice);
        editTextChassisNo = activity.findViewById(R.id.chassisNo);
        editTextExplanation = activity.findViewById(R.id.explanation);
    }

    @Override
    public void execute() {
        try {

            // EditText'leri araç bilgileri ile güncelliyoruz
            editTextModel.setText(vehicle.getModel());
            editTextYear.setText(vehicle.getYear());
//        // Fiyatı doğru formatta görüntülüyoruz
//        BigDecimal priceAsBigDecimal = new BigDecimal(vehicle.getPrice());
//        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("tr", "TR"));
//        String formattedPrice = numberFormat.format(priceAsBigDecimal) + " TL";
//        editTextPrice.setText(formattedPrice);
            // Fiyatı kontrol et ve yazdır
            editTextPrice.setText(vehicle.getPrice());
            editTextChassisNo.setText(vehicle.getChassisNo());
            editTextExplanation.setText(vehicle.getExplanation());

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "Bir hata oluştu: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
