package com.example.myapplication.model.vehicles;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.model.Customer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeInVehicle {

    private static TradeInVehicle instance;
    private DatabaseReference db;
    private String customerIdNo;
    private String model;
    private String year;
    private String price;
    private String chassisNo;
    private String licensePlate;
    private String explanation;
    private String date;
    private Customer customer;

    public TradeInVehicle() {
        super();
        this.db = FirebaseDatabase.getInstance().getReference("tradeinvehicles");
        this.customer = Customer.getInstance();
    }

    public static TradeInVehicle getInstance() {
        if (instance == null) {
            instance = new TradeInVehicle();
        }
        return instance;
    }

    // Firebase Realtime Database'den plaka numarasına göre araç getirme
    public void setVehicle(String licensePlate) {
        if (licensePlate != null && !licensePlate.isEmpty()) {
            try {
                db.child(licensePlate).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            TradeInVehicle vehicle = dataSnapshot.getValue(TradeInVehicle.class);
                            if (vehicle != null) {
                                setModel(vehicle.getModel());
                                setYear(vehicle.getYear());
                                setPrice(vehicle.getPrice());
                                setChassisNo(vehicle.getChassisNo());
                                setLicensePlate(vehicle.getLicensePlate());
                                setExplanation(vehicle.getExplanation());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        databaseError.toException().printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setVehicle(Activity activity) {
        if (getLicensePlate() != null && !getLicensePlate().isEmpty()) {
            try {
                db.child(getLicensePlate()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            TradeInVehicle vehicle = dataSnapshot.getValue(TradeInVehicle.class);
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


    // Firebase Realtime Database'den tüm araç plakalarını getir
    public List<String> getAllVehicleWithLicensePlate() {
        List<String> list = new ArrayList<>();
        Log.i("V", "Get All Vehicle");
        try {
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        list.add(child.getKey());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    databaseError.toException().printStackTrace();
                }
            });
        } catch (Exception e) {

            e.printStackTrace();
            Log.e("V", "DBError", e);
        }
        return list;
    }


    public void save() {
        try {
            if (!TradeInVehicle.getInstance().getLicensePlate().isEmpty()) {
                Map<String, Object> vehicleData = new HashMap<>();
                vehicleData.put("customerIdNo", getCustomerIdNo());
                vehicleData.put("model", getModel());
                vehicleData.put("year", getYear());
                vehicleData.put("price", getPrice());
                vehicleData.put("chassisNo", getChassisNo());
                vehicleData.put("licensePlate", getLicensePlate());
                vehicleData.put("explanation", getExplanation());

                db.child(getLicensePlate()).setValue(vehicleData);
            } else {
                System.out.println("Plaka boş bırakılamaz.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Firebase Realtime Database'den araç silme
    public void deleteVehicle(String licensePlate) {
        try {
            db.child(licensePlate).removeValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Araç plakasına göre var mı kontrolü
    public boolean isExists(String licensePlate) {
        final boolean[] exists = {false};
        try {
            db.child(licensePlate).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    exists[0] = dataSnapshot.exists();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    databaseError.toException().printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists[0];
    }

    // Getter ve Setter'lar
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