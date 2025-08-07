package com.example.myapplication.model;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    //    private static Customer instance;
//    DatabaseConnection db;
//    String name;
//    String surname;
//    String idNumber;
//    String phoneNumber;
//    String address;
//    private PreparedStatement pstat = null;
//    private Connection conn = null;
//    private Statement stat = null;
//
//    public Customer() {
//        super();
//        this.db = DatabaseConnection.getInstance();
//        this.conn = db.getConnection();
//    }
//
//    public static Customer getInstance() {
//        if (instance == null) {
//            instance = new Customer();
//        }
//        return instance;
//    }
//
//    public void save() {
//        try {
//            if (!Customer.getInstance().getIdNumber().isEmpty()) {
//                if (!isExists(Customer.getInstance().getIdNumber())) {
//                    String query = "insert into customers(Name,Surname,IdNo,PhoneNumber,Address)values(?,?,?,?,?)";
//                    setcustomerWithPrepaeredStatement(query);
//
//                }
//            } else {
////                JOptionPane.showMessageDialog(null, "Tc Kimlik numarası boş bırakılamaz.", " Hata ",
////                        JOptionPane.ERROR_MESSAGE);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public boolean isExists(String customerTcIdNo) {
//        try {
//            String query = "SELECT EXISTS (SELECT 1 FROM customers WHERE IdNo=?) AS var_mi";
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, customerTcIdNo);
//
//            ResultSet rs = pstat.executeQuery();
//            rs.next();
//            int sonuc = rs.getInt("var_mi");
//            pstat.close();
//            return sonuc == 1;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//
//        }
//    }
//
//    public void setcustomerWithPrepaeredStatement(String query) {
//        try {
//            Customer customer = Customer.getInstance();
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, customer.getName());
//            pstat.setString(2, customer.getSurname());
//            pstat.setString(3, customer.getIdNumber());
//            pstat.setString(4, customer.getPhoneNumber());
//            pstat.setString(5, customer.getAddress());
//
//            pstat.executeUpdate();
//            pstat.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getCustomerWithStatement(String query) {
//        Customer customer = Customer.getInstance();
//        try {
//
//            stat = conn.createStatement();
//            ResultSet rs = stat.executeQuery(query);
//
//            if (rs.next()) {
//                customer.setName(rs.getString("Name"));
//                customer.setSurname(rs.getString("Surname"));
//                customer.setIdNumber(rs.getString("TcIdNo"));
//                customer.setPhoneNumber(rs.getString("PhoneNumber"));
//                customer.setAddress(rs.getString("Address"));
//
//            }
//            rs.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public String getIdNumber() {
//        return idNumber;
//    }
//
//    public void setIdNumber(String idNumber) {
//        this.idNumber = idNumber;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }

    private static Customer instance;
    // Müşteri bilgileri
    String name;
    String surname;
    String idNumber;
    String phoneNumber;
    String address;
    private DatabaseReference db;
    private String date; // yeni müşteri oluşturulduğunda otomatik olarak atanacak

    private Customer() {
        // Firebase Realtime Database instance oluşturuluyor
        this.db = FirebaseDatabase.getInstance().getReference("customers");
    }

    public static Customer getInstance() {
        if (instance == null) {
            instance = new Customer();
        }
        return instance;
    }

//        public void save() {
//            if (!getIdNumber().isEmpty()) {
//                isExists(getIdNumber(), exists -> {
//                    if (!exists) {
//                        // Müşteri Firebase Realtime Database'e kaydediliyor
//                        Map<String, Object> customerMap = createCustomerMap();
//                        System.out.println("customerMap: " + customerMap);
//                        db.child(getIdNumber())
//                                .setValue(customerMap)
//                                .addOnSuccessListener(aVoid -> {
//                                    // Başarıyla kaydedildi
//                                    System.out.println("Müşteri başarıyla kaydedildi.");
//                                })
//                                .addOnFailureListener(e -> {
//                                    // Hata durumunda
//                                    e.printStackTrace();
//                                    System.err.println("Error saving customer: " + e.getMessage());
//                                });
//                    } else {
//                        System.out.println("Bu kimlik numarasıyla zaten bir müşteri mevcut.");
//                    }
//                });
//            } else {
//                System.out.println("Tc Kimlik numarası boş bırakılamaz.");
//            }
//        }
//
//        private Map<String, Object> createCustomerMap() {
//            Map<String, Object> customer = new HashMap<>();
//            customer.put("Name", getName());
//            customer.put("Surname", getSurname());
//            customer.put("IdNumber", getIdNumber()); // Benzersiz kimlik
//            customer.put("PhoneNumber", getPhoneNumber());
//            customer.put("Address", getAddress());
//            customer.put("date", ServerValue.TIMESTAMP); // Use ServerValue.TIMESTAMP directly
//            return customer;
//        }


    // Firebase Realtime Database'a araç kaydetme ve güncelleme
    public void save() {
        try {
            if (!Customer.getInstance().getIdNumber().isEmpty()) {

                Map<String, Object> customerData = new HashMap<>();

                customerData.put("name", getName());
                customerData.put("surname", getSurname());
                customerData.put("idNumber", getIdNumber()); // Benzersiz kimlik
                customerData.put("phoneNumber", getPhoneNumber());
                customerData.put("address", getAddress());


                db.child(getIdNumber()).setValue(customerData);
            } else {
                System.out.println("Tc No boş bırakılamaz.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Müşteri zaten var mı kontrolü (Asenkron Yapıya Dikkat)
    public void isExists(String customerId, final ExistsCallback callback) {
        db.child(customerId).get().addOnSuccessListener(dataSnapshot -> {
            callback.onResult(dataSnapshot.exists());
        }).addOnFailureListener(e -> {
            System.err.println("Error checking existence: " + e.getMessage());
        });
    }

    // Müşteri bilgilerini Firebase Realtime Database'den getirme
    public void getCustomer(String customerId) {
        db.child(customerId).get().addOnSuccessListener(dataSnapshot -> {
            if (dataSnapshot.exists()) {
                Customer customer = dataSnapshot.getValue(Customer.class);
                if (customer != null) {
                    setName(customer.getName());
                    setSurname(customer.getSurname());
                    setIdNumber(customer.getIdNumber());
                    setPhoneNumber(customer.getPhoneNumber());
                    setAddress(customer.getAddress());
                } else {
                    System.out.println("Müşteri bilgileri alınamadı.");
                }
            } else {
                System.out.println("Müşteri bulunamadı.");
            }
        }).addOnFailureListener(e -> {
            System.err.println("Error retrieving customer: " + e.getMessage());
        });
    }

    // Getter ve Setter'lar
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Callback interface for async operations
    public interface ExistsCallback {
        void onResult(boolean exists);
    }

}
