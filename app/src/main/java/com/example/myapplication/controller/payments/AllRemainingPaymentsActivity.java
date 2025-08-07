package com.example.myapplication.controller.payments;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.command.paymentcommands.listingpaymentcommands.ListingAllRemainingPaymentsCommand;
import com.example.myapplication.model.payments.AllRemainingPayments;

import java.util.ArrayList;
import java.util.List;

public class AllRemainingPaymentsActivity extends AppCompatActivity {

    AllRemainingPayments payment;
    ListingAllRemainingPaymentsCommand listingAllRemainingPaymentsCommand;
    private ListView listViewAllRemainingPayments;
    private ArrayAdapter<String> adapter;
    private List<String> paymentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_remaining_payments);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.payment = AllRemainingPayments.getInstance();
        this.listingAllRemainingPaymentsCommand = new ListingAllRemainingPaymentsCommand(this);
        this.listViewAllRemainingPayments = findViewById(R.id.listView);


        // Adapter tanımlanıyor
        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, paymentList);

        // ListView'e adapter bağlanıyor
        listViewAllRemainingPayments.setAdapter(adapter);
    }


    public void onListButtonClicked(View view) {
        try {

//            paymentList.clear();  // Eski verileri temizliyoruz
            paymentList = listingAllRemainingPaymentsCommand.execute();
            ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, paymentList);
            listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            listViewAllRemainingPayments.setAdapter(listAdapter);
            listAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Başarıyla listelendi...", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Bir hata oluştu: " + e.getMessage(), Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);

        }
    }


}