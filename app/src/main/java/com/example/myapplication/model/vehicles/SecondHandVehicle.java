package com.example.myapplication.model.vehicles;

import android.app.Activity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//public class SecondHandVehicle {
//
//    private static SecondHandVehicle instance;
//    DatabaseConnection db;
//    String customerIdNo;
//    String model;
//    String year;
//    String price;
//    String chassisNo;
//    String licensePlate;
//    String explanation;
//    String date;
//    private PreparedStatement pstat = null;
//    private Connection conn = null;
//    private Statement stat = null;
//
//    public SecondHandVehicle() {
//        super();
//        this.db = DatabaseConnection.getInstance();
//        this.conn = db.getConnection();
//
//    }
//
//    public static SecondHandVehicle getInstance() {
//        if (instance == null) {
//            instance = new SecondHandVehicle();
//        }
//        return instance;
//    }
//
//    public void setVehicle(String licensePlate) {
//
//        if (licensePlate != null && !licensePlate.isEmpty()) {
//            try {
//                String query = "SELECT * FROM secondhandvehicles WHERE LicensePlate=?";
//
//                getVehicleWithPreparedStatement(query, licensePlate);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public List<String> getAllVehicleWithLicensePlate() {
//        List<String> list = new ArrayList<>();
//        try {
//            String query = "SELECT LicensePlate FROM secondhandvehicles";
//            stat = conn.createStatement();
//            ResultSet rs = stat.executeQuery(query);
//
//            while (rs.next()) {
//                list.add(rs.getString("LicensePlate"));
//            }
//
//            rs.close();
//            stat.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return list;
//    }
//
//    public void save() {
//        try {
//            if (!SecondHandVehicle.getInstance().getLicensePlate().isEmpty()) {
//                if (!isExists(SecondHandVehicle.getInstance().getLicensePlate())) {
//
//                    String query = "insert into secondhandvehicles(CustomerId,Model,Year,Price,ChassisNo,LicensePlate,Explanation)values((Select id from customers where IdNo=?),?,?,?,?,?,?)";
//                    setVehicleWithPreparedStatement(query);
//
////                    JOptionPane.showMessageDialog(null, "İkinci el araç başarıyla eklendi.", "  ",
////                            JOptionPane.INFORMATION_MESSAGE);
//                } else {
//                    String query = "UPDATE secondhandvehicles SET CustomerId=(Select id from customers where IdNo=?), Model=?,Year=?,Price=?,LicensePlate=?,Explanation=? WHERE ChassisNo=?";
//                    setVehicleWithPreparedStatement(query);
//
////                    JOptionPane.showMessageDialog(null, "İkinci el araç başarıyla güncellendi.", "  ",
////                            JOptionPane.INFORMATION_MESSAGE);
//
//                }
//            } else {
////                JOptionPane.showMessageDialog(null, "Plaka  boş bırakılamaz.", " Hata ", JOptionPane.ERROR_MESSAGE);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//
//    public void deleteVehicle(String licensePlate) {
//        try {
//            String query = "Delete from secondhandvehicles where LicensePlate =?";
//
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, licensePlate);
//
//            pstat.executeUpdate();
//            pstat.close();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//
//    public boolean isExists(String licensePlate) {
//        try {
//            String query = "SELECT EXISTS (SELECT 1 FROM secondhandvehicles WHERE LicensePlate=?) AS var_mi";
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, licensePlate);
//
//            ResultSet rs = pstat.executeQuery();
//            rs.next();
//            int sonuc = rs.getInt("var_mi");
//
//            rs.close();
//            pstat.close();
//
//            return sonuc == 1;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//
//        }
//    }
//
//
//    public void setVehicleWithPreparedStatement(String query) {
//        try {
//            SecondHandVehicle vehicle = SecondHandVehicle.getInstance();
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, getCustomerIdNo());
//            pstat.setString(2, vehicle.getModel());
//            pstat.setString(3, vehicle.getYear());
//            pstat.setString(4, vehicle.getPrice());
//            pstat.setString(5, vehicle.getChassisNo());
//            pstat.setString(6, vehicle.getLicensePlate());
//            pstat.setString(7, vehicle.getExplanation());
//
//            pstat.executeUpdate();
//            pstat.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getVehicleWithStatement(String query) {
//        SecondHandVehicle vehicle = SecondHandVehicle.getInstance();
//        try {
//
//            stat = conn.createStatement();
//            ResultSet rs = stat.executeQuery(query);
//
//            if (rs.next()) {
//                vehicle.setCustomerIdNo(rs.getString("CustomerId"));
//                vehicle.setModel(rs.getString("Model"));
//                vehicle.setYear(rs.getString("Year"));
//                vehicle.setPrice(rs.getString("Price"));
//                vehicle.setChassisNo(rs.getString("ChassisNo"));
//                vehicle.setLicensePlate(rs.getString("LicensePlate"));
//                vehicle.setExplanation(rs.getString("Explanation"));
//
//            }
//            rs.close();
//            stat.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getVehicleWithPreparedStatement(String query, String licensePlate) {
//        try {
//            SecondHandVehicle vehicle = SecondHandVehicle.getInstance();
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, licensePlate);
//            ResultSet rs = pstat.executeQuery();
//
//            if (rs.next()) {
//                vehicle.setModel(rs.getString("Model"));
//                vehicle.setYear(rs.getString("Year"));
//                vehicle.setPrice(rs.getString("Price"));
//                vehicle.setChassisNo(rs.getString("ChassisNo"));
//                vehicle.setLicensePlate(licensePlate);
//                vehicle.setExplanation(rs.getString("Explanation"));
//            }
//
//            rs.close();
//            pstat.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public String getYear() {
//        return year;
//    }
//
//    public void setYear(String year) {
//        this.year = year;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public String getChassisNo() {
//        return chassisNo;
//    }
//
//    public void setChassisNo(String chasisNo) {
//        this.chassisNo = chasisNo;
//    }
//
//    public String getLicensePlate() {
//        return licensePlate;
//    }
//
//    public void setLicensePlate(String licensePlate) {
//        this.licensePlate = licensePlate;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getExplanation() {
//        return explanation;
//    }
//
//    public void setExplanation(String explanation) {
//        this.explanation = explanation;
//    }
//
//    public String getCustomerIdNo() {
//        return customerIdNo;
//    }
//
//    public void setCustomerIdNo(String customerID) {
//        this.customerIdNo = customerID;
//    }
//}


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
            });        }
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