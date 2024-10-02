package com.example.myapplication.command.paymentcommands.receivingpaymentcommands;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.payments.ReceivingPayment;

public class ReceivingPaymentForSoldVehicleCommand {

    ReceivingPayment payment;
    String formattedPrice;
    String priceWithoutDot;
    Customer customer;

    EditText editTextReceivedPayment;

    Activity activity;
    public ReceivingPaymentForSoldVehicleCommand(Activity activity) {
        super();
        this.payment = ReceivingPayment.getInstance();
        this.customer = Customer.getInstance();
        editTextReceivedPayment = activity.findViewById(R.id.receivedPayment);
        this.activity=activity;
    }

    public void execute() {
        try {
            formattedPrice = editTextReceivedPayment.getText().toString().trim();
            priceWithoutDot = formattedPrice.replace(".", "").replace(" TL", "");
            payment.setReceivedPayment(priceWithoutDot);
            payment.setCustomerIdNo(customer.getIdNumber());
            Toast.makeText(activity, "command başarılı", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(activity, "command hata: "+ e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }


}
