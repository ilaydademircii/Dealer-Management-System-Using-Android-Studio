package com.example.myapplication.model.payments;

import android.app.Activity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Payment {

    private static Payment instance;
    private DatabaseReference db;
    private String customerIdNo;
    private String receivedPayments;
    private String date;
    private List<String> paymentsList;

    private Payment() {
        this.db = FirebaseDatabase.getInstance().getReference("receivedpayments");

        this.paymentsList = new ArrayList<>();

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
    public List<String> getAllReceivedPaymentsWithCustomerIdNo(Activity activity) {
    try {
        db.orderByChild("customerId").equalTo(getCustomerIdNo())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot document : dataSnapshot.getChildren()) {
                            String p = document.child("receivedPayment").getValue(String.class);
                            paymentsList.add(p);
                        }

                        // Firebase işlemleri bittiğinde gecikme ekleniyor
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // Veri çekme işlemi
                                try {
                                    // Burada veriyi çekmek için sleep ekleyebilirsiniz
                                    Thread.sleep(2000); // 2 saniye bekletme
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                // UI güncellemesi yapmak için
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // UI'deki işlemler
                                    }
                                });
                            }
                        }).start();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("Error: " + databaseError.getMessage());
                    }
                });
    }catch (Exception e){
        Log.e("FirebaseDebug", "gecersiz model: " + e.getMessage());

    }


        return paymentsList;
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