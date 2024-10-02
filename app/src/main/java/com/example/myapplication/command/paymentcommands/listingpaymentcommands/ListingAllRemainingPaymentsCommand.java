package com.example.myapplication.command.paymentcommands.listingpaymentcommands;

import android.app.Activity;
import android.widget.Toast;

import com.example.myapplication.model.Customer;
import com.example.myapplication.model.payments.AllRemainingPayments;

import java.util.ArrayList;
import java.util.List;

public class ListingAllRemainingPaymentsCommand {

    ArrayList<String> paymentList;

    Activity activity;
    public ListingAllRemainingPaymentsCommand(Activity activity ) {
        super();
        this.activity=activity;
        //nesneden paymentList <String> alındı

    }

    //Amaç nesneden kullanılacak(activityde) paymentList <String> i düzenleme

    public List<String> execute() {
        return AllRemainingPayments.getInstance().getAllRemainingPayments(activity);
    }
}

