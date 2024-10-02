//package com.example.myapplication.model.vehicles;
//
//import android.content.Context;
//import android.widget.Toast;
//
//import com.example.myapplication.model.DatabaseConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//
//import android.content.Context;
//import android.widget.Toast;
//
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class NewVehicle {
//    private static NewVehicle instance;
//    DatabaseConnection db;
//    String model;
//    String year;
//    String price;
//    String chassisNo;
//    double kdvRate;
//    String explanation;
//    String date;
//    private PreparedStatement pstat = null;
//    private Connection conn = null;
//    private Statement stat = null;
//
//    public NewVehicle() {
//        super();
//        this.db = DatabaseConnection.getInstance();
//        this.conn = db.getConnection();
//    }
//
//    public static NewVehicle getInstance() {
//        if (instance == null) {
//            instance = new NewVehicle();
//        }
//        return instance;
//    }
//
//    public void setVehicle(String chassisNo) {
//        if (chassisNo != null && !chassisNo.isEmpty()) {
//            try {
//                String query = "Select * from newvehicles where ChassisNo=" + chassisNo;
//                getVehicleWithStatement(query);
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            }
//        }
//    }
//
//    public List<String> getAllVehicleWithChassisNo() {
//        List<String> list = new ArrayList<>();
//        try {
//            String query = "SELECT ChassisNo FROM newvehicles";
//            stat = conn.createStatement();
//            ResultSet rs = stat.executeQuery(query);
//
//            while (rs.next()) {
//                list.add(rs.getString("ChassisNo"));
//            }
//            rs.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return list;
//    }
//
//    public void getVehicleId() {
//        try {
//            if (isExists(NewVehicle.getInstance().getChassisNo())) {
//                String query = "insert into newvehicles(SoldCustomerId ,VehicleId)values(?,?)";
//
//                pstat = conn.prepareStatement(query);
//                pstat.setString(1, NewVehicle.getInstance().getChassisNo());
//
//
//                pstat.executeUpdate();
//                pstat.close();
//            } else {
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//
//    public void save(Context context) {
//        try {
//            if (!NewVehicle.getInstance().getChassisNo().isEmpty()) {
//                if (!isExists(NewVehicle.getInstance().getChassisNo())) {
//
//                    String query = "insert into newvehicles(Model,Year,Price,ChassisNo,KdvRate,Explanation)values(?,?,?,?,?,?)";
//                    setVehicleWithPrepaeredStatement(query, context);
//
//
//                    Toast.makeText(context, "Araç başarıyla kaydedildi...", Toast.LENGTH_LONG).show();
//
//
////                    JOptionPane.showMessageDialog(null, "Yeni araç başarıyla eklendi.", "  ",
////                            JOptionPane.INFORMATION_MESSAGE);
//                } else {
//                    String query = "UPDATE newvehicles SET  Model=?,Year=?,Price=?,ChassisNo=?,KdvRate=?,Explanation=? WHERE ChassisNo=?";
//                    setVehicleWithPrepaeredStatement(query, context);
//                    Toast.makeText(context, "Araç başarıyla güncellendi...", Toast.LENGTH_LONG).show();
////                    JOptionPane.showMessageDialog(null, "Yeni araç başarıyla güncellendi.", "  ",
////                            JOptionPane.INFORMATION_MESSAGE);
//
//                }
//            } else {
////                JOptionPane.showMessageDialog(null, "Şasi numarası boş bırakılamaz.", " Hata ",
////                        JOptionPane.ERROR_MESSAGE);
//                Toast.makeText(context, "Bir hata var. ", Toast.LENGTH_LONG).show();
//
//            }
//
//        } catch (Exception e) {
//            Toast.makeText(context, "Bir hata var: " + e.getMessage(), Toast.LENGTH_LONG).show();
//
//            e.printStackTrace();
//
//        }
//    }
//
//    public void deleteVehicle(String chassisNo) {
//        try {
//            String query = "Delete from newvehicles where ChassisNo =?";
//
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, chassisNo);
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
//
//    public boolean isExists(String chasisNo) {
//        try {
//            String query = "SELECT EXISTS (SELECT 1 FROM newvehicles WHERE ChassisNo=?) AS var_mi";
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, chasisNo);
//
//            ResultSet rs = pstat.executeQuery();
//            rs.next();
//            int sonuc = rs.getInt("var_mi");
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
//
//    public void setVehicleWithPrepaeredStatement(String query, Context context) {
//        try {
//            NewVehicle vehicle = NewVehicle.getInstance();
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, vehicle.getModel());
//            pstat.setString(2, vehicle.getYear());
//            pstat.setString(3, vehicle.getPrice());
//            pstat.setString(4, vehicle.getChassisNo());
//            pstat.setDouble(5, 10.0);
//            pstat.setString(6, vehicle.getExplanation());
//
//            pstat.executeUpdate();
//            pstat.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getVehicleWithStatement(String query) {
//        NewVehicle vehicle = NewVehicle.getInstance();
//        try {
//
//            stat = conn.createStatement();
//            ResultSet rs = stat.executeQuery(query);
//
//            if (rs.next()) {
//                vehicle.setModel(rs.getString("Model"));
//                vehicle.setYear(rs.getString("Year"));
//                vehicle.setPrice(rs.getString("Price"));
//                vehicle.setChassisNo(rs.getString("ChassisNo"));
//                vehicle.setKdvRate(rs.getDouble("KdvRate"));
//                vehicle.setExplanation(rs.getString("Explanation"));
//
//            }
//            stat.close();
//            rs.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getVehicleWithPreparedStatement(String query, String chassisNo) {
//        NewVehicle vehicle = NewVehicle.getInstance();
//        try {
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, chassisNo);
//            ResultSet rs = pstat.executeQuery(query);
//
//            if (rs.next()) {
//
//                vehicle.setModel(rs.getString("Model"));
//                vehicle.setYear(rs.getString("Year"));
//                vehicle.setPrice(rs.getString("Price"));
//                vehicle.setChassisNo(rs.getString("ChassisNo"));
//                vehicle.setExplanation(rs.getString("Explanation"));
//
//            }
//
//            rs.close();
//            pstat.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
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
//    public double getKdvRate() {
//        return kdvRate;
//    }
//
//    public void setKdvRate(double kdvRate) {
//        this.kdvRate = kdvRate;
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

public class NewVehicle {
    private static NewVehicle instance;
    private DatabaseReference db;
    private String model;
    private String year;
    private String price;
    private String chassisNo;
    private double kdvRate;
    private String explanation;
    private String date;

    List<String> list;

    public NewVehicle() {
        super();
        this.db = FirebaseDatabase.getInstance().getReference("newvehicles");
    }

    public static NewVehicle getInstance() {
        if (instance == null) {
            instance = new NewVehicle();
        }
        return instance;
    }

//    // Firebase Realtime Database'den şasi numarasına göre araç getirme
//    public void setVehicle(String chassisNo, Activity activity) {
//        if (chassisNo != null && !chassisNo.isEmpty()) {
//            try {
//                db.child(chassisNo).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()) {
//                            NewVehicle vehicle = dataSnapshot.getValue(NewVehicle.class);
//                            if (vehicle != null) {
//                                setModel(vehicle.getModel());
//                                setYear(vehicle.getYear());
//                                setPrice(vehicle.getPrice());
//                                setChassisNo(vehicle.getChassisNo());
//                                setKdvRate(vehicle.getKdvRate());
//                                setExplanation(vehicle.getExplanation());
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        databaseError.toException().printStackTrace();
//                        Toast.makeText(activity, "Veritabanı hatası: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
//
//                    }
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public void setVehicle(Activity activity) {
        if (getChassisNo() != null && !getChassisNo().isEmpty()) {
            try {
                db.child(getChassisNo()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            NewVehicle vehicle = dataSnapshot.getValue(NewVehicle.class);
                            if (vehicle != null) {
                                    setModel(vehicle.getModel());
                                    setYear(vehicle.getYear());
                                    setPrice(vehicle.getPrice());
                                    setChassisNo(vehicle.getChassisNo());
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

//    // Firebase Realtime Database'den tüm araç şasi numaralarını getir
//    public List<String> getAllVehicleWithChassisNo() {
//        List<String> list = new ArrayList<>();
//        try {
//            db.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for (DataSnapshot child : dataSnapshot.getChildren()) {
//                        list.add(child.getKey());
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    databaseError.toException().printStackTrace();
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }


    // Firebase Realtime Database'den tüm araç plakalarını getir
    public List<String> getAllVehicleWithChassisNo() {
        List<String> list = new ArrayList<>();
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
        }
        return list;
    }


    // Firebase Realtime Database'a araç kaydetme ve güncelleme
    public void save() {
        try {
            if (!NewVehicle.getInstance().getChassisNo().isEmpty()) {

                Map<String, Object> vehicleData = new HashMap<>();
                vehicleData.put("model", getModel());
                vehicleData.put("year", getYear());
                vehicleData.put("price", getPrice());
                vehicleData.put("chassisNo", getChassisNo());
                vehicleData.put("kdvRate", 10);
                vehicleData.put("explanation", getExplanation());

                db.child(getChassisNo()).setValue(vehicleData);
            } else {
                System.out.println("Şasi numarası boş bırakılamaz.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Firebase Realtime Database'den araç silme
    public void deleteVehicle(String chassisNo) {
        try {
            db.child(chassisNo).removeValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Araç şasi numarasına göre var mı kontrolü
    public boolean isExists(String chassisNo) {
        final boolean[] exists = {false};
        try {
            db.child(chassisNo).addListenerForSingleValueEvent(new ValueEventListener() {
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

    public double getKdvRate() {
        return kdvRate;
    }

    public void setKdvRate(double kdvRate) {
        this.kdvRate = kdvRate;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}