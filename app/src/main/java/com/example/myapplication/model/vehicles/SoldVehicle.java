package com.example.myapplication.model.vehicles;



//public class SoldVehicle {
//
//
//    private static SoldVehicle instance;
//    DatabaseConnection db;
//    String customerIdNo;
//    String model;
//    String year;
//    String chassisNo;
//    String licensePlate;
//    String receivingPrice;
//    String salePrice;
//    String remainingPaymentAmount;
//    String explanation;
//    String date;
//    String receivedPayment;
//    private PreparedStatement pstat = null;
//    private Connection conn = null;
//    private Statement stat = null;
//
//    public SoldVehicle() {
//        super();
//        this.db = DatabaseConnection.getInstance();
//        this.conn = db.getConnection();
//
//    }
//
//    public static SoldVehicle getInstance() {
//        if (instance == null) {
//            instance = new SoldVehicle();
//        }
//        return instance;
//    }
//
//    public void save() {
//        try {
//            if (!SoldVehicle.getInstance().getChassisNo().isEmpty()) {
//                if (!isExists(SoldVehicle.getInstance().getChassisNo())) {
//
//                    String query = "insert into vehiclessold(CustomerId,Model,Year,ChassisNo,LicensePlate,ReceivingPrice,SalePrice,RemainingPaymentAmount,Explanation)values((Select id from customers where IdNo=?),?,?,?,?,?,?,?,?)";
//                    setRemainingPaymentAmount(getSalePrice());
//                    setVehicleWithPrepaeredStatement(query);
//
////                        JOptionPane.showMessageDialog(null, "Araç başarıyla satıldı.", "  ",
////                                JOptionPane.INFORMATION_MESSAGE);
//                } else {
//                    String query = "UPDATE vehiclessold SET SalePrice=?,RemainingPaymentAmount=?,Explanation=? WHERE ChassisNo=?";
//                    setRemainingPaymentAmount(getSalePrice());
//                    updateVehicleWithPrepaeredStatement(query);
//
////                        JOptionPane.showMessageDialog(null, "Satılan araç güncellendi.", "  ",
////                                JOptionPane.INFORMATION_MESSAGE);
//
//                }
//            } else {
////                    JOptionPane.showMessageDialog(null, "Şasi Numarası boş bırakılamaz.", " Hata ",
////                            JOptionPane.ERROR_MESSAGE);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//
//    public void deleteNewVehicle() {
//        try {
//            if (isExists(NewVehicle.getInstance().getChassisNo())) {
//                String query = "Delete from newvehicles where ChassisNo =?";
//
//                pstat = conn.prepareStatement(query);
//                pstat.setString(1, NewVehicle.getInstance().getChassisNo());
//
//                pstat.executeUpdate();
//                pstat.close();
//
//            } else {
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//
//
//    public void deleteTradeInVehicle() {
//        try {
//            if (isExists(TradeInVehicle.getInstance().getLicensePlate())) {
//                String query = "Delete from tradeinvehicles where LicensePlate =?";
//
//                pstat = conn.prepareStatement(query);
//                pstat.setString(1, TradeInVehicle.getInstance().getLicensePlate());
//
//                pstat.executeUpdate();
//                pstat.close();
//
//            } else {
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//
//    public boolean isExists(String chassisNo) {
//        try {
//            String query = "SELECT EXISTS (SELECT 1 FROM vehiclessold WHERE ChassisNo=?) AS var_mi";
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, chassisNo);
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
//    public List<String> getAllVehicleModels() {
//        List<String> list = new ArrayList<>();
//        try {
//            String query = "SELECT Model FROM vehiclessold";
//            stat = conn.createStatement();
//            ResultSet rs = stat.executeQuery(query);
//
//            while (rs.next()) {
//                list.add(rs.getString("Model"));
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
//
//    public void updateVehicleWithPrepaeredStatement(String query) {
//        try {
//            SoldVehicle vehicle = SoldVehicle.getInstance();
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, vehicle.getSalePrice());
//            pstat.setString(2, vehicle.getRemainingPaymentAmount());
//            pstat.setString(3, vehicle.getExplanation());
//            pstat.setString(4, vehicle.getChassisNo());
//
//            pstat.executeUpdate();
//            pstat.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void setVehicleWithPrepaeredStatement(String query) {
//        try {
//            SoldVehicle vehicle = SoldVehicle.getInstance();
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, getCustomerIdNo());
//            pstat.setString(2, vehicle.getModel());
//            pstat.setString(3, vehicle.getYear());
//            pstat.setString(4, vehicle.getChassisNo());
//            pstat.setString(5, vehicle.getLicensePlate());
//            pstat.setString(6, vehicle.getReceivingPrice());
//            pstat.setString(7, vehicle.getSalePrice());
//            pstat.setString(8, vehicle.getRemainingPaymentAmount());
//            pstat.setString(9, vehicle.getExplanation());
//
//            pstat.executeUpdate();
//            pstat.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
////	public void getVehicleWithStatement(String query) {
////		SoldVehicle vehicle = SoldVehicle.getInstance();
////		try {
////
////			stat = conn.createStatement();
////			ResultSet rs = stat.executeQuery(query);
////
////			if (rs.next()) {
////				vehicle.setCustomerIdNo("IdNo");
////				vehicle.setModel(rs.getString("Model"));
////				vehicle.setYear(rs.getString("Year"));
////				vehicle.setChassisNo(rs.getString("ChassisNo"));
////				vehicle.setLicensePlate(rs.getString("LicensePlate"));
////				vehicle.setReceivingPrice(rs.getString("ReceivingPrice"));
////				vehicle.setSalePrice(rs.getString("SalePrice"));
////				vehicle.setRemainingPaymentAmount(rs.getString("RemainingPaymentAmount"));
////				vehicle.setExplanation(rs.getString("Explanation"));
////
////			}
////			rs.close();
////			stat.close();
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
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
//    public String getReceivingPrice() {
//        return receivingPrice;
//    }
//
//    public void setReceivingPrice(String receivingPrice) {
//        this.receivingPrice = receivingPrice;
//    }
//
//    public String getSalePrice() {
//        return salePrice;
//    }
//
//    public void setSalePrice(String salePrice) {
//        this.salePrice = salePrice;
//    }
//
//    public String getRemainingPaymentAmount() {
//        return remainingPaymentAmount;
//    }
//
//    public void setRemainingPaymentAmount(String remainingPaymentAmount) {
//        this.remainingPaymentAmount = remainingPaymentAmount;
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
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getReceivedPayment() {
//        return receivedPayment;
//    }
//
//    public void setReceivedPayment(String receivedPayment) {
//        this.receivedPayment = receivedPayment;
//    }
//
//}

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SoldVehicle {

    private static SoldVehicle instance;
    private DatabaseReference db;
    private String customerIdNo;
    private String model;
    private String year;
    private String chassisNo;
    private String licensePlate;
    private String receivingPrice;
    private String salePrice;
    private String remainingPaymentAmount;
    private String explanation;
    private String date;
    private String receivedPayment;

    public SoldVehicle() {
        super();
        this.db = FirebaseDatabase.getInstance().getReference("vehiclessold");
    }

    public static SoldVehicle getInstance() {
        if (instance == null) {
            instance = new SoldVehicle();
        }
        return instance;
    }

    // Firebase Realtime Database'den şasi numarasına göre araç getirme
    public void setVehicle(String chassisNo) {
        if (chassisNo != null && !chassisNo.isEmpty()) {
            try {
                db.child(chassisNo).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            SoldVehicle vehicle = dataSnapshot.getValue(SoldVehicle.class);
                            if (vehicle != null) {
                                setModel(vehicle.getModel());
                                setYear(vehicle.getYear());
                                setChassisNo(vehicle.getChassisNo());
                                setLicensePlate(vehicle.getLicensePlate());
                                setReceivingPrice(vehicle.getReceivingPrice());
                                setSalePrice(vehicle.getSalePrice());
                                setRemainingPaymentAmount(vehicle.getRemainingPaymentAmount());
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

    // Firebase Realtime Database'den tüm araç şasi numaralarını getir
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
            if (!SoldVehicle.getInstance().getChassisNo().isEmpty()) {
                Map<String, Object> vehicleData = new HashMap<>();
                vehicleData.put("customerIdNo", getCustomerIdNo());
                vehicleData.put("model", getModel());
                vehicleData.put("year", getYear());
                vehicleData.put("chassisNo", getChassisNo());
                vehicleData.put("licensePlate", getLicensePlate());
                vehicleData.put("receivingPrice", getReceivingPrice());
                vehicleData.put("salePrice", getSalePrice());
                vehicleData.put("remainingPaymentAmount", getRemainingPaymentAmount());
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

    public String getReceivingPrice() {
        return receivingPrice;
    }

    public void setReceivingPrice(String receivingPrice) {
        this.receivingPrice = receivingPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getRemainingPaymentAmount() {
        return remainingPaymentAmount;
    }

    public void setRemainingPaymentAmount(String remainingPaymentAmount) {
        this.remainingPaymentAmount = remainingPaymentAmount;
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

    public String getReceivedPayment() {
        return receivedPayment;
    }

    public void setReceivedPayment(String receivedPayment) {
        this.receivedPayment = receivedPayment;
    }
}