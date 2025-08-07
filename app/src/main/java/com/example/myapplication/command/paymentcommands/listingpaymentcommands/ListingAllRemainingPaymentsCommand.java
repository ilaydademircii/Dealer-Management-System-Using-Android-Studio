package com.example.myapplication.command.paymentcommands.listingpaymentcommands;

import android.app.Activity;
import android.util.Log;

import com.example.myapplication.model.payments.AllRemainingPayments;

import java.util.List;

public class ListingAllRemainingPaymentsCommand {

    List<String> paymentList;

    Activity activity;

    public ListingAllRemainingPaymentsCommand(Activity activity) {
        super();
        this.activity = activity;
        //nesneden paymentList <String> alındı

    }

    //Amaç nesneden kullanılacak(activityde) paymentList <String> i düzenleme

    public List<String> execute() {
        Log.e("FirebaseDebug command", "command calisiyor: ");
        paymentList = AllRemainingPayments.getInstance().getAllRemainingPayments(activity);
        for (String p : paymentList) {
            Log.e("FirebaseDebug command", "payment degeri: " + p);


        }
        Log.e("FirebaseDebug command", "liste bos ");
        return paymentList;
    }


}

