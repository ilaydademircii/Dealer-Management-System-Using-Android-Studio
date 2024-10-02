package com.example.myapplication.model.payments;

import com.example.myapplication.model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


//public class Payment {
//
//    private static Payment instance;
//
//    DatabaseConnection db;
//    private PreparedStatement pstat = null;
//    private Connection conn = null;
////	private Statement stat = null;
//
//
//    List<Payment> list;
//    String customerIdNo;
//    String receivedPayments;
//    String date;
//    List<String> paymentsList;
//
//
//    public Payment() {
//        super();
//        this.db = DatabaseConnection.getInstance();
//        this.conn = db.getConnection();
//        this.paymentsList=new ArrayList<>();
//    }
//
//    public static Payment getInstance() {
//        if (instance == null) {
//            instance = new Payment();
//        }
//        return instance;
//    }
//
//
//    public List<Payment> getAllReceivedPaymentsWithCustomerIdNo(String IdNo) {
//        list = new ArrayList<>();
//
//        try {
//            String query = "SELECT ReceivedPayments,Date FROM receivedpayments where CustomerId=(Select id from customers where IdNo=?)";
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, IdNo);
//            ResultSet rs = pstat.executeQuery();
//
//            while (rs.next()) {
//                Payment payments = new Payment();
//                payments.setReceivedPayments(rs.getString("ReceivedPayments"));
//                payments.setDate(rs.getString("Date"));
//                list.add(payments);
//            }
//
//
//            pstat.close();
//            rs.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return list;
//    }
//
//    public void setReceievedPaymentsWithPrepaeredStatement(String query) {
//        try {
//            Payment rePayment = Payment.getInstance();
//            pstat = conn.prepareStatement(query);
//            pstat.setString(1, rePayment.getCustomerIdNo());
//            pstat.setString(2, rePayment.getReceivedPayment());
//
//            pstat.executeUpdate();
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
//    public void setCustomerIdNo(String customerIdNo) {
//        this.customerIdNo = customerIdNo;
//    }
//
//    public String getReceivedPayment() {
//        return receivedPayments;
//    }
//
//    public void setReceivedPayments(String receivedPayments) {
//        this.receivedPayments = receivedPayments;
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
//    public List<String> getPaymentsList() {
//        return paymentsList;
//    }
//
//    public void setPaymentsList(List<String> paymentsList) {
//        this.paymentsList = paymentsList;
//    }
//}
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Payment {

    private static Payment instance;
    private DatabaseReference db;
    private String customerIdNo;
    private String receivedPayments;
    private String date;
    private List<String> paymentsList;

    private Payment() {
        this.db = FirebaseDatabase.getInstance().getReference("receivedpayments");

        this.paymentsList=new ArrayList<>();

    }

    public static Payment getInstance() {
        if (instance == null) {
            instance = new Payment();
        }
        return instance;
    }

//    // Save or Update payment
//    public void save() {
//        if (this.customerIdNo != null && !this.customerIdNo.isEmpty()) {
//            Map<String, Object> paymentData = new HashMap<>();
//            paymentData.put("customerIdNo", this.customerIdNo);
//            paymentData.put("receivedPayments", this.receivedPayments);
//            paymentData.put("date", this.date);
//
//            db.child("receivedpayments").child(this.customerIdNo)
//                    .setValue(paymentData)
//                    .addOnSuccessListener(aVoid -> System.out.println("Payment successfully saved/updated."))
//                    .addOnFailureListener(e -> e.printStackTrace());
//        }
//    }

//    // Delete payment
//    public void deletePayment(String customerIdNo) {
//        db.child("receivedpayments").child(customerIdNo)
//                .removeValue()
//                .addOnSuccessListener(aVoid -> System.out.println("Payment successfully deleted."))
//                .addOnFailureListener(e -> e.printStackTrace());
//    }

//    // Get payment by customer id
//    public void getPayment(String customerIdNo) {
//        db.child("receivedpayments").child(customerIdNo)
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()) {
//                            Payment payment = dataSnapshot.getValue(Payment.class);
//                            System.out.println("Payment data retrieved.");
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        System.out.println("Error: " + databaseError.getMessage());
//                    }
//                });
//    }

//    // Get all payments
//    public void getAllPayments() {
//        db.child("receivedpayments")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot document : dataSnapshot.getChildren()) {
//                            System.out.println(document.getKey() + " => " + document.getValue());
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        System.out.println("Error: " + databaseError.getMessage());
//                    }
//                });
//    }

    // Get all received payments with customer id
    public List<String> getAllReceivedPaymentsWithCustomerIdNo() {
        List<String> list = new ArrayList<>();

        db.child("receivedpayments").orderByChild("customerId").equalTo(getCustomerIdNo())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot document : dataSnapshot.getChildren()) {
                            Payment payment = document.getValue(Payment.class);
                            String p=document.child("receivedPayment").getValue(String.class);
                            list.add(p);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("Error: " + databaseError.getMessage());
                    }
                });

        return list;
    }


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



    // Getters and Setters
    public String getCustomerIdNo() {
        return customerIdNo;
    }

    public void setCustomerIdNo(String customerIdNo) {
        this.customerIdNo = customerIdNo;
    }

    public String getReceivedPayments() {
        return receivedPayments;
    }

    public void setReceivedPayments(String receivedPayments) {
        this.receivedPayments = receivedPayments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getPaymentsList() {
        return paymentsList;
    }

    public void setPaymentsList(List<String> paymentsList) {
        this.paymentsList = paymentsList;
    }
}