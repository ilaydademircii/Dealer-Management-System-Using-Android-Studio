package com.example.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.controller.payments.AllRemainingPaymentsActivity;
import com.example.myapplication.controller.payments.PaymentActivity;
import com.example.myapplication.controller.payments.ReceivingPaymentActivity;

public class PaymentsMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payments_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void onReceivingPaymentButtonButtonClick(View view) {

        Intent intent = new Intent(PaymentsMenuActivity.this, ReceivingPaymentActivity.class);
        startActivity(intent);
    }

    public void onAllRemainingPaymentButtonClick(View view) {

        Intent intent = new Intent(PaymentsMenuActivity.this, AllRemainingPaymentsActivity.class);
        startActivity(intent);
    }

    public void onPaymentButtonClick(View view) {

        Intent intent = new Intent(PaymentsMenuActivity.this, PaymentActivity.class);
        startActivity(intent);
    }
}