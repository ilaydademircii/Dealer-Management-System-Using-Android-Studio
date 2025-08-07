package com.example.myapplication.model.payments;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ReceivingPayment {
    private static ReceivingPayment instance;
    private FirebaseDatabase db;
    private DatabaseReference ref;
    private int paymentsId;
    private String receivedPayment;
    private String customerIdNo;
    private String remainingPaymentAmountString = "";
    private String newRemainingPaymentAmountString = "";

    public ReceivingPayment() {
        super();
        this.db = FirebaseDatabase.getInstance();
        this.ref = db.getReference("receivedpayments");
    }

    public static ReceivingPayment getInstance() {
        if (instance == null) {
            instance = new ReceivingPayment();
        }
        return instance;
    }

    public void getRemainingPaymentAmount(Activity activity) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("vehiclessold");
        Query query = databaseRef.orderByChild("customerIdNo").equalTo(getCustomerIdNo());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String amount = "";
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    amount = snapshot.child("remainingPaymentAmount").getValue(String.class);
                }
                // Toast ile kullanıcıya göster
                if (!amount.isEmpty()) {
                    Toast.makeText(activity, "Kalan Ödeme Miktarı: " + amount, Toast.LENGTH_LONG).show();
                    setRemainingPaymentAmountString(amount); // Burada değeri ayarlayın
                } else {
                    Toast.makeText(activity, "Kalan ödeme miktarı bulunamadı.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(activity, "Veri alımında hata oluştu.", Toast.LENGTH_LONG).show();
            }
        });
    }


//    public String getRemainingPaymentAmount(Activity activity) {
//        if (getCustomerIdNo() != null && !getCustomerIdNo().isEmpty()) {
//            ref.child("customers")
//                    .orderByChild("idNumber")
//                    .equalTo(getCustomerIdNo())
//                    .addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            if (dataSnapshot.exists()) {
//                                for (DataSnapshot document : dataSnapshot.getChildren()) {
//                                    String customerId = document.getKey();
//                                    ref.child("vehiclessold")
//                                            .child(customerId)
//                                            .addListenerForSingleValueEvent(new ValueEventListener() {
//                                                @Override
//                                                public void onDataChange(DataSnapshot documentSnapshot) {
//                                                    String remainingPaymentAmount = documentSnapshot.child("remainingPaymentAmount").getValue(String.class);
//                                                    if (remainingPaymentAmount != null) {
//                                                        setRemainingPaymentAmountString(remainingPaymentAmount);
//                                                        // Burada kalan ödeme miktarını kullanabilirsiniz
//                                                        Toast.makeText(activity, "Kalan ödeme miktarı: " + remainingPaymentAmount, Toast.LENGTH_SHORT).show();
//                                                    } else {
//                                                        Toast.makeText(activity, "Kalan ödeme miktarı bulunamadı.", Toast.LENGTH_SHORT).show();
//                                                    }
//                                                }
//
//                                                @Override
//                                                public void onCancelled(DatabaseError databaseError) {
//                                                    Toast.makeText(activity, "Veritabanı hatası: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                                                }
//                                            });
//                                }
//                            } else {
//                                Toast.makeText(activity, "Veritabanında müşteri bulunamadı.", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//                            Toast.makeText(activity, "Veritabanı hatası: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        } else {
//            Toast.makeText(activity, "Geçersiz müşteri numarası.", Toast.LENGTH_SHORT).show();
//        }
//        return getRemainingPaymentAmountString();
//    }


//
//    public String getRemainingPaymentAmount(Activity activity) {
//        final String[] remainingPaymentAmount = { "0" };  // Sonucu depolamak için
//        CountDownLatch latch = new CountDownLatch(1);  // Latch kullanarak bekleme yapıyoruz
//
//        String customerIdNo = ReceivingPayment.getInstance().getCustomerIdNo();
//
//        ref.child("customers").orderByChild("idNumber").equalTo(customerIdNo).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot document : dataSnapshot.getChildren()) {
//                    String customerId = document.getKey();
//                    ref.child("vehiclessold").child(customerId).addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot documentSnapshot) {
//                            remainingPaymentAmount[0] = documentSnapshot.child("remainingPaymentAmount").getValue(String.class);
//                            latch.countDown();  // Veriyi aldıktan sonra latch'i serbest bırak
//                            Toast.makeText(activity, remainingPaymentAmount[0], Toast.LENGTH_LONG).show();
//
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//                            latch.countDown();  // Hata durumunda da latch serbest bırakılmalı
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                latch.countDown();  // Hata durumunda latch serbest bırak
//            }
//        });
//
//        try {
//            latch.await();  // Latch sıfırlanana kadar bekler
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return remainingPaymentAmount[0];  // Firebase'den gelen sonuç döndürülür
//    }


//    // Update remaining payment amount after receiving a payment
//    public void setRemainingPaymentAmount() {
//        try {
//            ref.child("customers").orderByChild("idNumber").equalTo(getCustomerIdNo()).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for (DataSnapshot document : dataSnapshot.getChildren()) {
//                        String customerId = document.getKey();
//                        ref.child("vehiclessold").child(customerId).addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot documentSnapshot) {
//                                String remainingPaymentAmount = documentSnapshot.child("remainingPaymentAmount").getValue(String.class);
//                                double newRemainingAmount = Double.parseDouble(remainingPaymentAmount) - Double.parseDouble(getReceivedPayment());
//                                ref.child("vehiclessold").child(customerId).child("remainingPaymentAmount").setValue(String.valueOf(newRemainingAmount));
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//                                // Handle error
//                            }
//                        });
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    // Handle error
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }


    //
//    public void setRemainingPaymentAmount(Activity activity) {
//        try {
//            DatabaseReference vehiclesRef = ref.child("vehiclessold").child(getCustomerIdNo());
//
//            vehiclesRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot documentSnapshot) {
//                    String remainingPaymentAmount = documentSnapshot.child("remainingPaymentAmount").getValue(String.class);
//                    if (remainingPaymentAmount != null) {
//                        double newRemainingAmount = Double.parseDouble(remainingPaymentAmount) - Double.parseDouble(getReceivedPayment());
//                        vehiclesRef.child("remainingPaymentAmount").setValue(String.valueOf(newRemainingAmount));
//                        Toast.makeText(activity, "Kalan ödeme miktarı güncellendi: " + newRemainingAmount, Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(activity, "Kalan ödeme miktarı bulunamadı.", Toast.LENGTH_LONG).show();
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    Toast.makeText(activity, "Veri alımında hata oluştu.", Toast.LENGTH_LONG).show();
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(activity, "G" +
//                    "üncelleme sırasında hata oluştu.", Toast.LENGTH_LONG).show();
//        }
//    }
//public void setRemainingPaymentAmount(Activity activity) {
//    try {
//        if (getCustomerIdNo() == null || getCustomerIdNo().isEmpty()) {
//            Toast.makeText(activity, "Müşteri ID geçersiz.", Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        DatabaseReference vehiclesRef = ref.child("vehiclessold").child(getCustomerIdNo());
//
//        vehiclesRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot documentSnapshot) {
//                // Daha önce alınan değerleri kullan
//                String remainingPaymentAmount = getRemainingPaymentAmountString();
//                String receivedPayment = getReceivedPayment();
//
//                if (remainingPaymentAmount != null && receivedPayment != null) {
//                    double newRemainingAmount = Double.parseDouble(remainingPaymentAmount) - Double.parseDouble(receivedPayment);
//                    vehiclesRef.child("remainingPaymentAmount").setValue(String.valueOf(newRemainingAmount));
//                    Toast.makeText(activity, "Kalan ödeme miktarı güncellendi: " + newRemainingAmount, Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(activity, "Kalan ödeme miktarı veya alınan ödeme geçersiz.", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(activity, "Veri alımında hata oluştu.", Toast.LENGTH_LONG).show();
//            }
//        });
//    } catch (Exception e) {
//        e.printStackTrace();
//        Toast.makeText(activity, "Güncelleme sırasında hata oluştu: " + e.getMessage(), Toast.LENGTH_LONG).show();
//    }
//}
    public void setRemainingPaymentAmount(Activity activity) {
        try {
            DatabaseReference vehiclesRef = ref.child("vehiclessold");

            vehiclesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean found = false;

                    for (DataSnapshot vehicleSnapshot : dataSnapshot.getChildren()) {
                        String existingCustomerIdNo = vehicleSnapshot.child("customerIdNo").getValue(String.class);
                        if (existingCustomerIdNo != null && existingCustomerIdNo.equals(customerIdNo)) {
                            found = true;

                            // Daha önce alınan değerleri kullan
                            String remainingPaymentAmount = getRemainingPaymentAmountString();
                            String receivedPayment = getReceivedPayment();

                            if (remainingPaymentAmount != null && receivedPayment != null) {
                                double newRemainingAmount = Double.parseDouble(remainingPaymentAmount) - Double.parseDouble(receivedPayment);
                                int roundedAmount = (int) newRemainingAmount;

                                // Kalan ödeme miktarını güncelle
                                setNewRemainingPaymentAmountString(String.valueOf(roundedAmount));
                                vehicleSnapshot.child("remainingPaymentAmount").getRef().setValue(String.valueOf(roundedAmount));
                                Toast.makeText(activity, "Kalan ödeme miktarı güncellendi: " + roundedAmount, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(activity, "Kalan ödeme miktarı veya alınan ödeme geçersiz.", Toast.LENGTH_LONG).show();
                            }
                            break; // Müşteri bulundu, döngüden çık
                        }
                    }

                    if (!found) {
                        Toast.makeText(activity, "Müşteri bulunamadı.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(activity, "Veri alımında hata oluştu.", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "Güncelleme sırasında hata oluştu: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // Save the received payment record
    public void savePayment( ) {
        try {

                        ref.push().setValue(new PaymentRecord(getCustomerIdNo(), getReceivedPayment()));

                        db.getReference("vehiclessold").child(getCustomerIdNo()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot documentSnapshot) {
                                String remainingPaymentAmount = documentSnapshot.child("remainingPaymentAmount").getValue(String.class);
                                double newRemainingAmount = Double.parseDouble(remainingPaymentAmount) - Double.parseDouble(getReceivedPayment());
                                setRemainingPaymentAmountString(String.valueOf(newRemainingAmount));
                                ref.child("vehiclessold").child(getCustomerIdNo()).child("remainingPaymentAmount").setValue(getNewRemainingPaymentAmountString());

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // Handle error
                                Log.e("database errror", "onCancelled: ");
                            }
                        });

        } catch (Exception e) {
            Log.e(" errror", "onCancelled: ");

        }
    }

    public String getCustomerIdNo() {
        return customerIdNo;
    }

    // Getters and Setters

    public void setCustomerIdNo(String customerIdNo) {
        this.customerIdNo = customerIdNo;
    }

    public int getPaymentsId() {
        return paymentsId;
    }

    public void setPaymentsId(int paymentsId) {
        this.paymentsId = paymentsId;
    }

    public String getReceivedPayment() {
        return receivedPayment;
    }

    public void setReceivedPayment(String receivedPayment) {
        this.receivedPayment = receivedPayment;
    }

    public String getRemainingPaymentAmountString() {
        return remainingPaymentAmountString;
    }

    public void setRemainingPaymentAmountString(String remainingPaymentAmount) {
        this.remainingPaymentAmountString = remainingPaymentAmount;
    }

    public String getNewRemainingPaymentAmountString() {
        return newRemainingPaymentAmountString;
    }

    public void setNewRemainingPaymentAmountString(String newRemainingPaymentAmountString) {
        this.newRemainingPaymentAmountString = newRemainingPaymentAmountString;
    }

    // Data class for Firebase records
    public static class PaymentRecord {
        private String customerId;
        private String receivedPayment;

        public PaymentRecord() {
        }

        public PaymentRecord(String customerId, String receivedPayment) {
            this.customerId = customerId;
            this.receivedPayment = receivedPayment;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getReceivedPayment() {
            return receivedPayment;
        }

        public void setReceivedPayment(String receivedPayment) {
            this.receivedPayment = receivedPayment;
        }
    }
}