package com.example.myapplication.model.vehicles;

import android.app.Activity;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondHandVehicle {

    private static SecondHandVehicle instance;
    DatabaseReference db;
    String customerIdNo;
    String model;
    String year;
    String price;
    String chassisNo;
    String licensePlate;
    String explanation;
    String date;

    public SecondHandVehicle() {
        super();
        this.db = FirebaseDatabase.getInstance().getReference("secondhandvehicles");
    }

    public static SecondHandVehicle getInstance() {
        if (instance == null) {
            instance = new SecondHandVehicle();
        }
        return instance;
    }

    public void save() {
        if (licensePlate != null && !licensePlate.isEmpty()) {
            Map<String, Object> vehicleData = new HashMap<>();
            vehicleData.put("customerIdNo", customerIdNo);
            vehicleData.put("model", model);
            vehicleData.put("year", year);
            vehicleData.put("price", price);
            vehicleData.put("chassisNo", chassisNo);
            vehicleData.put("licensePlate", licensePlate);
            vehicleData.put("explanation", explanation);

            db.child(licensePlate).setValue(vehicleData).addOnSuccessListener(aVoid -> {
                System.out.println("Araç başarıyla kaydedildi.");
            });
        }
    }

    // Araç silme işlemi
    public void deleteVehicle(String licensePlate) {
        db.child(licensePlate).removeValue().addOnSuccessListener(aVoid -> {
            System.out.println("Araç başarıyla silindi.");
        });
    }

    public void setVehicle(Activity activity) {
        if (getLicensePlate() != null && !getLicensePlate().isEmpty()) {
            try {
                db.child(getLicensePlate()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            SecondHandVehicle vehicle = dataSnapshot.getValue(SecondHandVehicle.class);
                            if (vehicle != null) {
                                setModel(vehicle.getModel());
                                setYear(vehicle.getYear());
                                setPrice(vehicle.getPrice());
                                setChassisNo(vehicle.getChassisNo());
                                setLicensePlate(vehicle.getLicensePlate());
                                setExplanation(vehicle.getExplanation());

                            } else {
                                Toast.makeText(activity, "Araç bilgisi alınamadı.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(activity, "Veritabanında şasi numarasına ait araç bulunamadı.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(activity, "Veritabanı hatası: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        databaseError.toException().printStackTrace();
                    }
                });
            } catch (Exception e) {
                Toast.makeText(activity, "Bir hata oluştu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(activity, "Geçersiz şasi numarası.", Toast.LENGTH_SHORT).show();
        }
    }
//    // Plaka ile araç bilgilerini getirme
//    public void setVehicle(String licensePlate) {
//        if (licensePlate != null && !licensePlate.isEmpty()) {
//            try {
//                db.child(licensePlate).get().addOnSuccessListener(dataSnapshot -> {
//                            if (dataSnapshot.exists()) {
//                                // Veriyi Firebase Realtime Database'den al ve SecondHandVehicle nesnesine aktar
//                                Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();
//                                if (data != null) {
//                                    setCustomerIdNo((String) data.get("customerIdNo"));
//                                    setModel((String) data.get("model"));
//                                    setYear((String) data.get("year"));
//                                    setPrice((String) data.get("price"));
//                                    setChassisNo((String) data.get("chassisNo"));
//                                    setLicensePlate((String) data.get("licensePlate"));
//                                    setExplanation((String) data.get("explanation"));
//                                    setDate((String) data.get("date"));
//
//                                    // Başarıyla veri alındı
//                                    System.out.println("Vehicle data successfully retrieved.");
//                                }
//                            } else {
//                                System.out.println("No vehicle found with the given license plate.");
//                            }
//                        })
//                        .addOnFailureListener(e -> {
//                            // Hata durumunda
//                            e.printStackTrace();
//                        });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public List<String> getAllVehicleWithLicensePlate() {
        List<String> list = new ArrayList<>();
        try {
            db.get().addOnSuccessListener(dataSnapshot -> {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            String licensePlate = child.child("licensePlate").getValue(String.class);
                            if (licensePlate != null) {
                                list.add(licensePlate);
                            }
                        }
                    })
                    .addOnFailureListener(e -> {
                        e.printStackTrace();
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

//    // Tüm araçları alma işlemi
//    public void getAllVehicles() {
//        db.child("secondhandvehicles").get().addOnSuccessListener(dataSnapshot -> {
//                    for (DataSnapshot child : dataSnapshot.getChildren()) {
//                        System.out.println(child.getKey() + " => " + child.getValue());
//                    }
//                })
//                .addOnFailureListener(e -> {
//                    e.printStackTrace();
//                });
//    }

    // Getters and Setters

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getCustomerIdNo() {
        return customerIdNo;
    }

    public void setCustomerIdNo(String customerIdNo) {
        this.customerIdNo = customerIdNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}