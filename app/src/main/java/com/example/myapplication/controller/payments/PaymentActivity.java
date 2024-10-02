package com.example.myapplication.controller.payments;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.command.paymentcommands.listingpaymentcommands.ListingPaymentsCommand;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.payments.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    Payment payment;
    Customer customer;

    EditText tcNo;
    ListingPaymentsCommand listingPaymentsCommand;
    private ListView listViewPayments;
    private ArrayAdapter<String> adapter;
    private List<String> paymentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.payment = Payment.getInstance();
        this.customer = Customer.getInstance();

        this.tcNo = findViewById(R.id.customerTcNo);
        this.listingPaymentsCommand = new ListingPaymentsCommand();
        this.listViewPayments = findViewById(R.id.listView);


        // Payment listesi başlatılıyor
        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, paymentList);

        // ListView'i tanımlıyoruz
        listViewPayments.setAdapter(adapter);

    }


    public void onListButtonClicked(View view) {
        try {

            customer.setIdNumber(tcNo.getText().toString());
            payment.setCustomerIdNo(tcNo.getText().toString());
            Toast.makeText(this, "1 listelendi...", Toast.LENGTH_LONG).show();
            paymentList.clear();
            paymentList=listingPaymentsCommand.execute();
            Toast.makeText(this, "2 listelendi...", Toast.LENGTH_LONG).show();
            if(paymentList.isEmpty()){
                Toast.makeText(this, "Boş...", Toast.LENGTH_LONG).show();
                paymentList.add("aa");
            }
//            // Notify adapter
//            listingPaymentsCommand.execute();
//
//            // Güncellenen verileri alıyoruz
//            paymentList.clear();  // Eski verileri temizliyoruz
//            paymentList.addAll(payment.getPaymentsList());  // Yeni verileri ekliyoruz
//            paymentList=payment.getPaymentsList();
//            paymentList.add("aaaa");
//            // Adapter'e yeni verileri gösterdiğini söylüyoruz
//            adapter.notifyDataSetChanged();
//            listViewPayments.setAdapter(adapter);
            ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, paymentList);
            listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            listViewPayments.setAdapter(listAdapter);
            listAdapter.notifyDataSetChanged();
            Toast.makeText(this, "3 listelendi...", Toast.LENGTH_LONG).show();


            Toast.makeText(this, "Başarıyla listelendi...", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Bir hata oluştu: " + e.getMessage(), Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);

        }
    }


}