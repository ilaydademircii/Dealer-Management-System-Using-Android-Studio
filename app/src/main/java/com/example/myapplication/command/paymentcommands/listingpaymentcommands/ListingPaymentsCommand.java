package com.example.myapplication.command.paymentcommands.listingpaymentcommands;

import android.app.Activity;

import com.example.myapplication.model.Customer;
import com.example.myapplication.model.payments.Payment;

import java.util.List;

public class ListingPaymentsCommand {

    Customer customer;
    Payment payment;
    List<Payment> newPaymentsList;
    List<String> paymentList;

    public ListingPaymentsCommand() {
        super();
    }


    //Amaç nesneden kullanılacak(activityde) paymentList <String> i düzenleme
    public List<String> execute(Activity activity) {

        return Payment.getInstance().getAllReceivedPaymentsWithCustomerIdNo(activity );
    }
}
