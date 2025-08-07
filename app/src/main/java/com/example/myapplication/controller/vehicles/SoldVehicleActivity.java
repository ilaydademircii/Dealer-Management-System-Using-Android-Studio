package com.example.myapplication.controller.vehicles;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.command.customercommands.receivingcustomercommand.CustomerrReceivingCommand;
import com.example.myapplication.command.paymentcommands.receivingpaymentcommands.ReceivingPaymentForSoldVehicleCommand;
import com.example.myapplication.command.vehiclecommands.deletevehiclecommand.NewVehicleDeleteCommand;
import com.example.myapplication.command.vehiclecommands.deletevehiclecommand.SecondHandVehicleDeleteCommand;
import com.example.myapplication.command.vehiclecommands.deletevehiclecommand.TradeInVehicleDeleteCommand;
import com.example.myapplication.command.vehiclecommands.getvehiclemodelscommand.NewVehicleGetModelsCommand;
import com.example.myapplication.command.vehiclecommands.getvehiclemodelscommand.SecondHandVehicleGetModelsCommand;
import com.example.myapplication.command.vehiclecommands.getvehiclemodelscommand.TradeInVehicleGetModelsCommand;
import com.example.myapplication.command.vehiclecommands.receivingvehiclecommand.SoldVehicleReceivingCommand;
import com.example.myapplication.command.vehiclecommands.setvehicleinfocommand.NewVehicleSetInfoCommand;
import com.example.myapplication.command.vehiclecommands.setvehicleinfocommand.SecondHandVehicleSetInfoCommand;
import com.example.myapplication.command.vehiclecommands.setvehicleinfocommand.TradeInVehicleSetInfoCommand;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.payments.ReceivingPayment;
import com.example.myapplication.model.vehicles.NewVehicle;
import com.example.myapplication.model.vehicles.SecondHandVehicle;
import com.example.myapplication.model.vehicles.SoldVehicle;
import com.example.myapplication.model.vehicles.TradeInVehicle;

import java.util.ArrayList;
import java.util.List;

public class SoldVehicleActivity extends AppCompatActivity {

    // Models
    SoldVehicle soldVehicle;
    Customer customer;
    SecondHandVehicle secondHandVehicle;
    TradeInVehicle tradeInVehicle;
    NewVehicle newVehicle;
    ReceivingPayment receivingPayment;

    // UI Components
    Spinner vehicleTypeSpinner;
    Spinner vehicleModelSpinner;
    EditText editTextModel;
    EditText editTextYear;
    EditText editTextPrice;
    EditText editTextChassisNo;
    EditText editTextExplanation;
    EditText editTextLicensePlate;


    // Commands
    CustomerrReceivingCommand customerrReceivingCommand;
    SoldVehicleReceivingCommand vehicleReceivingCommand;

    NewVehicleSetInfoCommand newVehicleSetInfoCommand;
    SecondHandVehicleSetInfoCommand secondHandVehicleSetInfoCommand;
    TradeInVehicleSetInfoCommand tradeInVehicleSetInfoCommand;

    NewVehicleDeleteCommand deleteNewVehicleCommand;
    SecondHandVehicleDeleteCommand deleteSecondHandVehicleCommand;
    TradeInVehicleDeleteCommand deleteTradeInVehicleCommand;

    NewVehicleGetModelsCommand newVehicleGetModelsCommand;
    SecondHandVehicleGetModelsCommand secondHandVehicleGetModelsCommand;
    TradeInVehicleGetModelsCommand tradeInVehicleGetModelsCommand;

    ReceivingPaymentForSoldVehicleCommand receivingPaymentCommand;

    // Vehicle type and models
    private List<String> vehicleTypes = new ArrayList<>();
    private List<String> vehicleModels = new ArrayList<>();
    private String selectedType = "";
    private String selectedModel = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sold_vehicle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Activity'den XML'deki EditText bileşenlerini alıyoruz
        this.editTextModel = findViewById(R.id.model);
        this.editTextYear = findViewById(R.id.year);
        this.editTextPrice = findViewById(R.id.receivingPrice);
        this.editTextLicensePlate = findViewById(R.id.licensePlate);
        this.editTextChassisNo = findViewById(R.id.chassisNo);
        this.editTextExplanation = findViewById(R.id.explanation);

        this.soldVehicle = SoldVehicle.getInstance();
        this.customer = Customer.getInstance();

        this.secondHandVehicle = SecondHandVehicle.getInstance();
        this.tradeInVehicle = TradeInVehicle.getInstance();
        this.newVehicle = NewVehicle.getInstance();
        this.receivingPayment = ReceivingPayment.getInstance();

        this.customerrReceivingCommand = new CustomerrReceivingCommand(this);
        this.vehicleReceivingCommand = new SoldVehicleReceivingCommand(this);

        this.newVehicleSetInfoCommand = new NewVehicleSetInfoCommand(this);
        this.secondHandVehicleSetInfoCommand = new SecondHandVehicleSetInfoCommand(this);
        this.tradeInVehicleSetInfoCommand = new TradeInVehicleSetInfoCommand(this);

        this.deleteNewVehicleCommand = new NewVehicleDeleteCommand(this);
        this.deleteSecondHandVehicleCommand = new SecondHandVehicleDeleteCommand(this);
        this.deleteTradeInVehicleCommand = new TradeInVehicleDeleteCommand(this);

        this.newVehicleGetModelsCommand = new NewVehicleGetModelsCommand();
        this.secondHandVehicleGetModelsCommand = new SecondHandVehicleGetModelsCommand();
        this.tradeInVehicleGetModelsCommand = new TradeInVehicleGetModelsCommand();

        this.receivingPaymentCommand = new ReceivingPaymentForSoldVehicleCommand(this);

        vehicleTypes.add("Yeni Araç");
        vehicleTypes.add("İkinci El Araç");
        vehicleTypes.add("Takas Araç");
        vehicleTypeSpinner = (Spinner) findViewById(R.id.vehicle_type_spinner);
        vehicleModelSpinner = (Spinner) findViewById(R.id.vehicle_model_spinner);
        setUpSpinners();

    }


    public void onSaveButtonClicked(View view) {
        try {
            customerrReceivingCommand.execute();
            customer.save();

            soldVehicle.setCustomerIdNo(customer.getIdNumber());

            vehicleReceivingCommand.execute();
            soldVehicle.save();

            receivingPaymentCommand.execute();
            Toast.makeText(this, "1A.", Toast.LENGTH_LONG).show();

            receivingPayment.savePayment();
            Toast.makeText(this, "2A.", Toast.LENGTH_LONG).show();

            receivingPayment.setRemainingPaymentAmount(this);
            Toast.makeText(this, "3A.", Toast.LENGTH_LONG).show();

            if (selectedType.equals("Takas Araç")) {
                deleteTradeInVehicleCommand.execute();
                tradeInVehicle.deleteVehicle(soldVehicle.getLicensePlate());
            } else if (selectedType.equals("İkinci El Araç")) {
                deleteSecondHandVehicleCommand.execute();
                secondHandVehicle.deleteVehicle(soldVehicle.getLicensePlate());
            } else if (selectedType.equals("Yeni Araç")) {
                deleteNewVehicleCommand.execute();
                newVehicle.deleteVehicle(soldVehicle.getChassisNo());
            }

            Toast.makeText(this, "Araç başarıyla satıldı.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Bir hata oluştu: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public void setUpSpinners() {
        try {
            Log.i("V", "Load Spinner Start");

            ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, vehicleTypes);
            typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            vehicleTypeSpinner.setAdapter(typeAdapter);
            vehicleTypeSpinner.setOnItemSelectedListener(new VehicleTypeSelectedListener());
            vehicleTypeSpinner.setSelection(0);
            setSelectedType("Yeni Araç");
            updateVehicleModels();
            Log.i("V", "Load Spinner End");

        } catch (Exception e) {
            Log.e("V", "Err Spinner", e);

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }

    }

    private void updateVehicleModels() {
        try {
            if (getSelectedType() == "Araç türü seçiniz." || getSelectedModel() == "Araç modeli seçiniz.") {
                return;
            }
            vehicleModels.clear();
            if (getSelectedType().equals("Takas Araç")) {
                vehicleModels = tradeInVehicleGetModelsCommand.execute();
            } else if (getSelectedType().equals("İkinci El Araç")) {
                vehicleModels = secondHandVehicleGetModelsCommand.execute();
            } else if (getSelectedType().equals("Yeni Araç")) {
                vehicleModels = newVehicleGetModelsCommand.execute();
            }
            vehicleModels.add("Araç modeli seçiniz.");

            if (vehicleModels.isEmpty()) {
                Toast.makeText(this, "Bu araç tipi için model bulunamadı.", Toast.LENGTH_LONG).show();
            }
            ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, vehicleModels);
            modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            vehicleModelSpinner.setAdapter(modelAdapter);
            modelAdapter.notifyDataSetChanged();
            vehicleModelSpinner.setOnItemSelectedListener(new VehicleModelSelectedListener()); // Aç

        } catch (Exception e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }

    }

//    //araç bilgisi belirleme
//    private void updateVehicleInfo(String vehicleModel) {
//        // Fetch and update vehicle information based on selected model
//
//
//        if (vehicleModel != null) {
//            vehicle.setModel(vehicleModel);
//            setVehicleModel(vehicleModel);
//
//
//            if (getType().equals("Takas Araç")) {
//                tradeInVehicle.setVehicle(getVehicleModel());
//                tradeInVehicleSetInfoCommand.execute();
//            } else if (getType().equals("İkinci El Araç")) {
//                secondHandVehicle.setVehicle(getVehicleModel());
//                secondHandVehicleSetInfoCommand.execute();
//                // Fetch and update information for Used Vehicle
//            } else if (getType().equals("Yeni Araç")) {
//                newVehicle.setVehicle(getVehicleModel());
//                newVehicleSetInfoCommand.execute();
//                // Fetch and update information for New Vehicle
//            }
//        }
//    }

    private void updateVehicleInfo() {
        try {
            if (getSelectedType() == "Araç türü seçiniz." || getSelectedModel() == "Araç modeli seçiniz.") {
                return;
            }
            if (getSelectedType().equals("Takas Araç")) {
                tradeInVehicle.setLicensePlate(getSelectedModel());
                tradeInVehicle.setVehicle(this);
                tradeInVehicleSetInfoCommand.execute();
            } else if (getSelectedType().equals("İkinci El Araç")) {
                secondHandVehicle.setLicensePlate(getSelectedModel());
                secondHandVehicle.setVehicle(this);
                secondHandVehicleSetInfoCommand.execute();
            } else if (getSelectedType().equals("Yeni Araç")) {
                newVehicle.setChassisNo(getSelectedModel());
                newVehicle.setVehicle(this);
                newVehicleSetInfoCommand.execute();
            }
        } catch (NullPointerException e) {
            Toast.makeText(this, "Lütfen araç tipi ve modelini seçtiğinizden emin olun!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void clearUI() {
        editTextModel.setText("");
        editTextYear.setText("");
        editTextPrice.setText("");
        editTextLicensePlate.setText("");
        editTextChassisNo.setText("");
        editTextExplanation.setText("");
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;

    }

    public String getSelectedModel() {
        return selectedModel;
    }

    public void setSelectedModel(String selectedModel) {
        this.selectedModel = selectedModel;
        clearUI();

    }

    public List<String> getVehicleModels() {
        return vehicleModels;
    }

    public void setVehicleModels(List<String> vehicleModels) {
        this.vehicleModels = vehicleModels;
    }

    // TODO: Bunu dene OnClickListener
    //spinner listeners
    private class VehicleTypeSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            setSelectedType(parent.getItemAtPosition(position).toString());
            updateVehicleModels();
            clearUI();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    private class VehicleModelSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            setSelectedModel(parent.getItemAtPosition(position).toString());
            clearUI();
            updateVehicleInfo();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    // Tüm değişkenler aynı şekilde tanımlanmış

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_sold_vehicle);
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        // Model ve araç verilerini yükle
//        this.vehicle = SoldVehicle.getInstance();
//        this.customer = Customer.getInstance();
//        this.secondHandVehicle = SecondHandVehicle.getInstance();
//        this.tradeInVehicle = TradeInVehicle.getInstance();
//        this.newVehicle = NewVehicle.getInstance();
//        this.receivingPayment = ReceivingPayment.getInstance();
//
//        // Komut sınıflarını yükle
//        this.customerrReceivingCommand = new CustomerrReceivingCommand(this);
//        this.vehicleReceivingCommand = new SoldVehicleReceivingCommand(this);
//        this.newVehicleSetInfoCommand = new NewVehicleSetInfoCommand(this);
//        this.secondHandVehicleSetInfoCommand = new SecondHandVehicleSetInfoCommand(this);
//        this.tradeInVehicleSetInfoCommand = new TradeInVehicleSetInfoCommand(this);
//        this.deleteNewVehicleCommand = new NewVehicleDeleteCommand(this);
//        this.deleteSecondHandVehicleCommand = new SecondHandVehicleDeleteCommand(this);
//        this.deleteTradeInVehicleCommand = new TradeInVehicleDeleteCommand(this);
//        this.newVehicleGetModelsCommand = new NewVehicleGetModelsCommand();
//        this.secondHandVehicleGetModelsCommand = new SecondHandVehicleGetModelsCommand();
//        this.tradeInVehicleGetModelsCommand = new TradeInVehicleGetModelsCommand();
//        this.receivingPaymentCommand = new ReceivingPaymentForSoldVehicleCommand(this);
//
//        // Spinner'lar için veri hazırlığı
//        this.vehicle_types = new ArrayList<>();
//        this.vehicle_types.add("Yeni Araç");
//        this.vehicle_types.add("İkinci El Araç");
//        this.vehicle_types.add("Takas Araç");
//
//        this.vehicle_models = new ArrayList<>();
//
//        // Spinner'ları başlat
//        vehicleTypeSpinner = findViewById(R.id.vehicle_type_spinner);
//        vehicleModelSpinner = findViewById(R.id.vehicle_model_spinner);
//
//        // Spinner'ları ayarlama
//        setUpSpinners();
//    }
//
//    // Kaydet butonuna basıldığında işlemleri başlat
//    public void onSaveButtonClicked(View view) {
//        try {
//            customerrReceivingCommand.execute();
//            vehicle.setCustomerIdNo(customer.getIdNumber());
//            customer.save();
//            vehicleReceivingCommand.execute();
//            vehicle.save();
//            receivingPaymentCommand.execute();
//            receivingPayment.savePayment();
//            receivingPayment.setRemainingPaymentAmount();
//
//            // Araç silme komutlarını duruma göre çalıştır
//            if (getType().equals("Takas Araç")) {
//                deleteTradeInVehicleCommand.execute();
//                tradeInVehicle.deleteVehicle(vehicle.getLicensePlate());
//            } else if (getType().equals("İkinci El Araç")) {
//                deleteSecondHandVehicleCommand.execute();
//                secondHandVehicle.deleteVehicle(vehicle.getLicensePlate());
//            } else if (getType().equals("Yeni Araç")) {
//                deleteNewVehicleCommand.execute();
//                newVehicle.deleteVehicle(vehicle.getChassisNo());
//            }
//
//            Toast.makeText(this, "Araç başarıyla satıldı.", Toast.LENGTH_LONG).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Bir hata oluştu: " + e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//    }
//
//    // Spinner'ları ayarlama işlemi
//    public void setUpSpinners() {
//        // Araç tipi Spinner'ını ayarla
//        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, vehicle_types);
//        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        vehicleTypeSpinner.setAdapter(typeAdapter);
//        vehicleTypeSpinner.setOnItemSelectedListener(new VehicleTypeSelectedListener());
//
//        // Model Spinner'ını boş başlat, seçili araç tipine göre güncellenecek
//        updateVehicleModels(vehicle_types.get(0));
//    }
//
//    // Araç model bilgilerini güncelleme
//    private void updateVehicleModels(String vehicleType) {
//        vehicle_models = new ArrayList<>();
//        switch (vehicleType) {
//            case "Takas Araç":
//                vehicle_models = tradeInVehicleGetModelsCommand.execute();
//                break;
//            case "İkinci El Araç":
//                vehicle_models = secondHandVehicleGetModelsCommand.execute();
//                break;
//            case "Yeni Araç":
//                vehicle_models = newVehicleGetModelsCommand.execute();
//                break;
//            default:
//                vehicle_models = new ArrayList<>();
//                break;
//        }
//
//        // Eğer model listesi boşsa kullanıcıyı bilgilendir
//        if (vehicle_models.isEmpty()) {
//            Toast.makeText(this, "Bu araç tipi için model bulunamadı.", Toast.LENGTH_LONG).show();
//        }
//
//        // Model Spinner'ını güncelle
//        ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, vehicle_models);
//        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        vehicleModelSpinner.setAdapter(modelAdapter);
//
//        vehicleModelSpinner.setOnItemSelectedListener(new VehicleModelSelectedListener());
//    }
//
//    // Araç bilgilerini güncelle
//    private void updateVehicleInfo(String vehicleModel) {
//        if (vehicleModel != null) {
//            setVehicleModel(vehicleModel);
//
//            if (getType().equals("Takas Araç")) {
//                tradeInVehicle.setVehicle(getVehicleModel());
//                tradeInVehicleSetInfoCommand.execute();
//                Toast.makeText(this, "Trade-In Vehicle info updated.", Toast.LENGTH_SHORT).show();
//            } else if (getType().equals("İkinci El Araç")) {
//                secondHandVehicle.setVehicle(getVehicleModel());
//                secondHandVehicleSetInfoCommand.execute();
//                Toast.makeText(this, "Second Hand Vehicle info updated.", Toast.LENGTH_SHORT).show();
//            } else if (getType().equals("Yeni Araç")) {
//                newVehicle.setVehicle(getVehicleModel());
//                newVehicleSetInfoCommand.execute();
//                Toast.makeText(this, "New Vehicle info updated.", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    // Araç tipi ve modeli getter/setter metodları
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getVehicleModel() {
//        return vehicleModel;
//    }
//
//    public void setVehicleModel(String vehicleModel) {
//        this.vehicleModel = vehicleModel;
//    }
//
//    // Araç tipi seçimi listener
//    private class VehicleTypeSelectedListener implements AdapterView.OnItemSelectedListener {
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            String selectedType = (String) parent.getItemAtPosition(position);
//            setType(selectedType);
//            updateVehicleModels(selectedType);
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> parent) {
//        }
//    }
//
//    // Araç modeli seçimi listener
//    private class VehicleModelSelectedListener implements AdapterView.OnItemSelectedListener {
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            String selectedModel = (String) parent.getItemAtPosition(position);
//            setVehicleModel(selectedModel);
//            updateVehicleInfo(selectedModel);
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> parent) {
//        }
//    }

}


