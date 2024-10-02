package com.example.myapplication.model.payments;


import android.app.Activity;
import android.widget.Toast;

import com.example.myapplication.model.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


//public class AllRemainingPayments {
//    private static AllRemainingPayments instance;
//    DatabaseConnection db;
//    List<AllRemainingPayments> list;
//    String customerIdNo;
//    String customerName;
//    String customerSurname;
//    String remainingPayments;
//    List<String> allRemainingPaymentsList;
//    //	private PreparedStatement pstat = null;
//    private Connection conn = null;
//    private Statement stat = null;
//
//    public AllRemainingPayments() {
//        super();
//        this.db = DatabaseConnection.getInstance();
//        this.conn = db.getConnection();
//        this.allRemainingPaymentsList=new ArrayList<>();
//    }
//
//
//    public static AllRemainingPayments getInstance() {
//        if (instance == null) {
//            instance = new AllRemainingPayments();
//        }
//        return instance;
//    }
//
//
//    public List<AllRemainingPayments> getAllRemainingPayments() {
//        list = new ArrayList<>();
//
//        try {
//            String query = "SELECT customers.IdNo, customers.Name, customers.Surname, vehiclessold.RemainingPaymentAmount\r\n"
//                    + "FROM vehiclessold\r\n"
//                    + "INNER JOIN customers ON vehiclessold.CustomerId = customers.Id\r\n"
//                    + "WHERE vehiclessold.RemainingPaymentAmount REGEXP '^[0-9]+$' \r\n"
//                    + "AND CAST(vehiclessold.RemainingPaymentAmount AS DECIMAL) > 0; ";
//
//            stat = conn.createStatement();
//            ResultSet rs = stat.executeQuery(query);
//
//            while (rs.next()) {
//                AllRemainingPayments payments = new AllRemainingPayments();
//                payments.setCustomerIdNo(rs.getString("IdNo"));
//                payments.setCustomerName(rs.getString("Name"));
//                payments.setCustomerSurname(rs.getString("Surname"));
//                payments.setRemainingPayments(rs.getString("RemainingPaymentAmount"));
//
//                list.add(payments);
//            }
//
//            stat.close();
//            rs.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return list;
//    }
//
//
//    public String getCustomerIdNo() {
//        return customerIdNo;
//    }
//
//
//    public void setCustomerIdNo(String customerIdNo) {
//        this.customerIdNo = customerIdNo;
//    }
//
//
//    public String getRemainingPayments() {
//        return remainingPayments;
//    }
//
//
//    public void setRemainingPayments(String remainingPayments) {
//        this.remainingPayments = remainingPayments;
//    }
//
//
//    public String getCustomerName() {
//        return customerName;
//    }
//
//
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }
//
//
//    public String getCustomerSurname() {
//        return customerSurname;
//    }
//
//
//    public void setCustomerSurname(String customerSurname) {
//        this.customerSurname = customerSurname;
//    }
//
//    public List<String> getAllRemainingPaymentsList() {
//        return allRemainingPaymentsList;
//    }
//
//    public void setAllRemainingPaymentsList(List<String> allRemainingPaymentsList) {
//        this.allRemainingPaymentsList = allRemainingPaymentsList;
//    }
//}
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllRemainingPayments {
    private static AllRemainingPayments instance;
    private FirebaseDatabase db;
    private DatabaseReference ref;
    List<AllRemainingPayments> list;
    String customerIdNo;
    String customerName;
    String customerSurname;
    String customerPhoneNumber;
    String remainingPayments;
    ArrayList<String> allRemainingPaymentsList;

    public AllRemainingPayments() {
        this.db = FirebaseDatabase.getInstance();
        this.ref = db.getReference("allremainingpayments");
        this.allRemainingPaymentsList = new ArrayList<>();
    }

    public static AllRemainingPayments getInstance() {
        if (instance == null) {
            instance = new AllRemainingPayments();
        }
        return instance;
    }
//
//    // Retrieve all remaining payments from Firebase Realtime Database
//    public List<AllRemainingPayments> getAllRemainingPayments() {
//        list = new ArrayList<>();
//
//        ref.child("vehiclessold").orderByChild("remainingPaymentAmount").startAt("0").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot document : dataSnapshot.getChildren()) {
//                    String customerId = document.child("customerIdNo").getValue(String.class);
//                    if (customerId != null) {
//                        // Fetch customer details
//                        ref.child("customers").child(customerId).addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot customerDocument) {
//                                if (customerDocument.exists()) {
//                                    AllRemainingPayments payments = new AllRemainingPayments();
//                                    payments.setCustomerIdNo(customerDocument.child("customerIdNo").getValue(String.class));
//                                    payments.setCustomerName(customerDocument.child("name").getValue(String.class));
//                                    payments.setCustomerSurname(customerDocument.child("surname").getValue(String.class));
//                                    payments.setRemainingPayments(document.child("remainingPaymentAmount").getValue(String.class));
//
//                                    list.add(payments);
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//                                // Handle error
//                            }
//                        });
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Handle error
//            }
//        });
//        return list;
//    }
//


//    public List<AllRemainingPayments> getAllRemainingPayments(Activity activity) {
//        List<AllRemainingPayments> list = new ArrayList<>();
//        try {
//            ref.child("vehiclessold").orderByChild("remainingPaymentAmount").startAt(1) // 0'dan büyük olan değerleri getir
//                    .addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            if (dataSnapshot.exists()) {
//                                for (DataSnapshot vehicleSnapshot : dataSnapshot.getChildren()) {
//                                    String customerId = vehicleSnapshot.child("customerIdNo").getValue(String.class);
//                                    String remainingPaymentAmount = vehicleSnapshot.child("remainingPaymentAmount").getValue(String.class);
//                                    Toast.makeText(activity, "customerID "+customerId, Toast.LENGTH_LONG).show();
//
//                                    if (customerId != null) {
//                                        // Müşteri bilgilerini getir
//                                        ref.child("customers").child(customerId).addListenerForSingleValueEvent(new ValueEventListener() {
//                                            @Override
//                                            public void onDataChange(DataSnapshot customerSnapshot) {
//                                                if (customerSnapshot.exists()) {
//                                                    AllRemainingPayments payment = new AllRemainingPayments();
//                                                    payment.setCustomerIdNo(customerSnapshot.child("iddNumber").getValue(String.class));
//                                                    payment.setCustomerName(customerSnapshot.child("name").getValue(String.class));
//                                                    payment.setCustomerSurname(customerSnapshot.child("surname").getValue(String.class));
//                                                    payment.setCustomerPhoneNumber((customerSnapshot.child("phoneNumber").getValue(String.class)));
//                                                    payment.setRemainingPayments(remainingPaymentAmount); // vehiclessold'dan alındı
//                                                    Toast.makeText(activity, payment.getCustomerIdNo(), Toast.LENGTH_LONG).show();
//
//                                                    list.add(payment);
//
//                                                }
//                                                if (list.isEmpty()) {
//                                                    Toast.makeText(activity, "lsite boş ", Toast.LENGTH_LONG).show();
//
//                                                }
//
//                                                Toast.makeText(activity, "model Başarılı ", Toast.LENGTH_LONG).show();
//
//                                            }
//
//
//                                            @Override
//                                            public void onCancelled(DatabaseError databaseError) {
//                                                // Hata durumu
//                                            }
//                                        });
//                                    }
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//                            // Hata durumu
//                        }
//                    });
//
//            return list;
//        } catch (Exception e) {
//            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
//
//        }
//        return list;
//    }

    public ArrayList<String> getAllRemainingPayments(Activity activity) {
        ArrayList<String> list = new ArrayList<>();
        Toast.makeText(activity, "model başladı ", Toast.LENGTH_LONG).show();

        try {
            Toast.makeText(activity, "1A", Toast.LENGTH_LONG).show();

            ref.child("vehiclessold").orderByChild("remainingPaymentAmount").startAt(1) // 0'dan büyük olan değerleri getir
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Toast.makeText(activity, "1", Toast.LENGTH_LONG).show();

                            if (dataSnapshot.exists()) {
                                Toast.makeText(activity, "2", Toast.LENGTH_LONG).show();

                                for (DataSnapshot vehicleSnapshot : dataSnapshot.getChildren()) {
                                    String customerId = vehicleSnapshot.child("customerIdNo").getValue(String.class);
                                    String remainingPaymentAmount = vehicleSnapshot.child("remainingPaymentAmount").getValue(String.class);
                                    Toast.makeText(activity, "customerID "+customerId, Toast.LENGTH_LONG).show();
                                    Toast.makeText(activity, "3", Toast.LENGTH_LONG).show();

                                    if (customerId != null) {
                                        Toast.makeText(activity, "4", Toast.LENGTH_LONG).show();

                                        // Müşteri bilgilerini getir
                                        ref.child("customers").child(customerId).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot customerSnapshot) {
                                                if (customerSnapshot.exists()) {
                                                    String rowData = customerSnapshot.child("iddNumber").getValue(String.class)+ " - " + customerSnapshot.child("name").getValue(String.class)+ " - " + customerSnapshot.child("surname").getValue(String.class) + " - " + (customerSnapshot.child("phoneNumber").getValue(String.class))+ " - " + remainingPaymentAmount;
                                                    Toast.makeText(activity, rowData, Toast.LENGTH_LONG).show();

                                                    list.add(rowData);
                                                    Toast.makeText(activity, "mlist"+rowData, Toast.LENGTH_LONG).show();

                                                }
                                                if (list.isEmpty()) {
                                                    Toast.makeText(activity, "lsite boş ", Toast.LENGTH_LONG).show();

                                                }
                                                list.add("aa");
                                                list.add("ba");
                                                Toast.makeText(activity, "model Başarılı ", Toast.LENGTH_LONG).show();

                                            }


                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                Toast.makeText(activity, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                                                // Hata durumu
                                            }
                                        });
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Hata durumu
                            Toast.makeText(activity, databaseError.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });

            return list;
        } catch (Exception e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();

        }
        return list;
    }
//    public interface OnDataReadyCallback {
//        void onDataReady(ArrayList<String> remainingPayments);
//    }//
//    public void getAllRemainingPayments(Activity activity, OnDataRetrieved callback) {
//        ArrayList<String> list = new ArrayList<>();
//        Toast.makeText(activity, "model başladı ", Toast.LENGTH_LONG).show();
//
//        ref.child("vehiclessold").orderByChild("remainingPaymentAmount").startAt(1)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()) {
//                            for (DataSnapshot vehicleSnapshot : dataSnapshot.getChildren()) {
//                                String customerId = vehicleSnapshot.child("customerIdNo").getValue(String.class);
//                                String remainingPaymentAmount = vehicleSnapshot.child("remainingPaymentAmount").getValue(String.class);
//
//                                if (customerId != null) {
//                                    ref.child("customers").child(customerId).addListenerForSingleValueEvent(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(DataSnapshot customerSnapshot) {
//                                            if (customerSnapshot.exists()) {
//                                                String rowData = customerSnapshot.child("iddNumber").getValue(String.class) + " - "
//                                                        + customerSnapshot.child("name").getValue(String.class) + " - "
//                                                        + customerSnapshot.child("surname").getValue(String.class) + " - "
//                                                        + customerSnapshot.child("phoneNumber").getValue(String.class) + " - "
//                                                        + remainingPaymentAmount;
//
//                                                list.add(rowData);
//                                            }
//
//                                            // Tüm veriler alındığında geri çağırma yapılır
//                                            callback.onDataReceived(list);
//                                        }
//
//                                        @Override
//                                        public void onCancelled(DatabaseError databaseError) {
//                                            Toast.makeText(activity, databaseError.getMessage(), Toast.LENGTH_LONG).show();
//                                        }
//                                    });
//                                }
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        Toast.makeText(activity, databaseError.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });
//    }
//
//    // Getters and Setters
//    public interface OnDataRetrieved {
//        void onDataReceived(ArrayList<String> data);
//    }

    public String getCustomerIdNo() {
        return customerIdNo;
    }

    public void setCustomerIdNo(String customerIdNo) {
        this.customerIdNo = customerIdNo;
    }

    public String getRemainingPayments() {
        return remainingPayments;
    }

    public void setRemainingPayments(String remainingPayments) {
        this.remainingPayments = remainingPayments;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public ArrayList<String> getAllRemainingPaymentsList() {
        return allRemainingPaymentsList;
    }

    public void setAllRemainingPaymentsList(ArrayList<String> allRemainingPaymentsList) {
        this.allRemainingPaymentsList = allRemainingPaymentsList;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
}