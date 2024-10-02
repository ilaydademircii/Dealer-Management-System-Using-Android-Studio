package com.example.myapplication.command.paymentcommands.listingpaymentcommands;

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
//        this.customer = Customer.getInstance();
//        this.payment = Payment.getInstance();

        //nesneden paymentList <String> alındı
       // this.paymentList = this.payment.getPaymentsList();
    }


    //Amaç nesneden kullanılacak(activityde) paymentList <String> i düzenleme
    public List<String> execute() {


        // Yeni Liste <Payment>
      //  payment.setCustomerIdNo(customer.getIdNumber());
//        //newPaymentsList = Payment.getInstance().getAllReceivedPaymentsWithCustomerIdNo(customer.getIdNumber());
//
//        //<String > boşaltıldı
//        paymentList.clear();
//        paymentList=payment.getAllReceivedPaymentsWithCustomerIdNo();
//
//        // <Payment> stringe çevrildi. <String> e kaydedildi.
//        for (Payment p : newPaymentsList) {
//            String rowData = p.getReceivedPayments() + " - " + p.getDate();
//            paymentList.add(rowData);
//        }
//
//
//        //nesneye paymentList <String geri atandı>
//        payment.setPaymentsList(paymentList);

        return Payment.getInstance().getAllReceivedPaymentsWithCustomerIdNo();
    }
}
