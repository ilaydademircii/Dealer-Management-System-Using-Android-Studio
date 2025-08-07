package com.example.myapplication.controller.payments;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.command.paymentcommands.receivingpaymentcommands.ReceivingPaymentCommand;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.payments.ReceivingPayment;

public class ReceivingPaymentActivity extends AppCompatActivity {


    ReceivingPayment payment;

    Customer customer;

    EditText editTextTcNo;
    EditText editTextRemainingPayment;

    ReceivingPaymentCommand receivingPaymentCommand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receiving_payment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.payment = ReceivingPayment.getInstance();

        this.customer = Customer.getInstance();
        editTextTcNo = findViewById(R.id.customerTcNo);
        editTextRemainingPayment = findViewById(R.id.remainingPayment);

        this.receivingPaymentCommand = new ReceivingPaymentCommand(this);
    }


    public void onSearchButtonClicked(View view) {
        try {

            payment.setCustomerIdNo(editTextTcNo.getText().toString().trim());
            payment.getRemainingPaymentAmount(this);
            editTextRemainingPayment.setText(payment.getRemainingPaymentAmountString());


            Toast.makeText(this, "Başarıyla görüntülendi...", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Bir hata oluştu: " + e.getMessage(), Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);

        }
    }


    public void onPaymentButtonClicked(View view) {
        try {
            receivingPaymentCommand.execute();
            payment.savePayment();
            payment.setRemainingPaymentAmount(this);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Bir hata oluştu: " + e.getMessage(), Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);

        }
    }
}