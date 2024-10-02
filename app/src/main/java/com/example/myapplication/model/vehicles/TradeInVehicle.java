package com.example.myapplication.model.vehicles;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.model.Customer;
import com.example.myapplication.model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//public class TradeInVehicle {
//
//    private static TradeInVehicle instance;
//    DatabaseConnection db;
//    String customerIdNo;
//    String model;
//    String year;
//    String price;
//    String chassisNo;
//    String licensePlate;
//    String explanation;
//    String date;
//    Customer customer;
//    private PreparedStatement pstat = null;
//    private Connection conn = null;
//    private Statement stat = null;
//
//    public TradeInVehicle() {
//        super();
//        this.db = DatabaseConnection.getInstance();
//        this.conn = db.getConnection();
//        this.customer = Customer.getInstance();
//    }
//
//    public static TradeInVehicle getInstance() {
//        if (instance == null) {
//            instance = new TradeInVehicle();
//        }
//        return instance;
//    }
//
//    public void setVehicle(String licensePlate) {
//        if (licensePlate != null && !licensePlate.isEmpty()) {
//            try {
//                String query = "Select * from tradeinvehicles where LicensePlate=?";
//                getVehicleWithPreparedStatement(query, licensePlate);
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            }
//        }
//    }
//
//    public List<String> getAllVehicleWithLicensePlate() {
//        List<String> list = new ArrayList<>();
//        try {
//            String query = "SELECT LicensePlate FROM tradeinvehicles";
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
//            if (!TradeInVehicle.getInstance().getLicensePlate().isEmpty()) {
//                if (!isExists(TradeInVehicle.getInstance().getLicensePlate())) {
//
//                    String query = "insert into tradeinvehicles(CustomerId,Model,Year,Price,ChassisNo,LicensePlate,Explanation)values((Select id from customers where IdNo=?),?,?,?,?,?,?)";
//                    setVehicleWithPrepaeredStatement(query);
//
////                    JOptionPane.showMessageDialog(null, "Takas araç başarıyla eklendi.", "  ",
////                            JOptionPane.INFORMATION_MESSAGE);
//                } else {
//                    String query = "UPDATE tradeinvehicles SET CustomerId=(Select id from customers where IdNo=?), Model=?,Year=?,Price=?,LicensePlate=?,Explanation=? WHERE ChassisNo=?";
//                    setVehicleWithPrepaeredStatement(query);
//
////                    JOptionPane.showMessageDialog(null, "Takas araç başarıyla güncellendi.", "  ",
////                            JOptionPane.INFORMATION_MESSAGE);
//
//                }
//            } else {
////                JOptionPane.showMessageDialog(null, "Plaka boş bırakılamaz.", " Hata ", JOptionPane.ERROR_MESSAGE);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteVehicle(String licensePlate) {
//        try {
//            String query = "Delete from tradeinvehicles where LicensePlate =?";
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
//            String query = "SELECT EXISTS (SELECT 1 FROM tradeinvehicles WHERE LicensePlate=?) AS var_mi";
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, licensePlate);
//
//            ResultSet rs = pstat.executeQuery();
//            rs.next();
//            int sonuc = rs.getInt("var_mi");
//
//            pstat.close();
//            rs.close();
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
//    public void setVehicleWithPrepaeredStatement(String query) {
//        try {
//            TradeInVehicle vehicle = TradeInVehicle.getInstance();
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
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getVehicleWithStatement(String query) {
//        TradeInVehicle vehicle = TradeInVehicle.getInstance();
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
//            TradeInVehicle vehicle = TradeInVehicle.getInstance();
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, licensePlate);
//            ResultSet rs = pstat.executeQuery();
//
//            if (rs.next()) {
//                vehicle.setModel(rs.getString("Model"));
//                vehicle.setYear(rs.getString("Year"));
//                vehicle.setPrice(rs.getString("Price"));
//                vehicle.setChassisNo(rs.getString("ChassisNo"));
//                vehicle.setLicensePlate(rs.getString("LicensePlate"));
//                vehicle.setExplanation(rs.getString("Explanation"));
//            }
//
//            rs.close();
//            pstat.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String getCustomerIdNo() {
//        return customerIdNo;
//    }
//
//    public void setCustomerIdNo(String customerId) {
//        this.customerIdNo = customerId;
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
//}


import com.example.myapplication.model.Customer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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
        Log.i("V","Get All Vehicle");
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
            Log.e("V","DBError",e);
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