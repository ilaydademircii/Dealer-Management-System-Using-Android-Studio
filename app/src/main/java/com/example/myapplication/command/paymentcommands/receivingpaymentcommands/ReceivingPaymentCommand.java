package com.example.myapplication.command.paymentcommands.receivingpaymentcommands;

import android.app.Activity;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.model.payments.ReceivingPayment;

public class ReceivingPaymentCommand {

    EditText editTextReceivedPayment;
    ReceivingPayment payment;


    public ReceivingPaymentCommand(Activity activity) {
        super();
        this.payment = ReceivingPayment.getInstance();
        editTextReceivedPayment = activity.findViewById(R.id.receivedPayment);
    }

    public void execute() {
        payment.setReceivedPayment(editTextReceivedPayment.getText().toString().trim());
    }

}
