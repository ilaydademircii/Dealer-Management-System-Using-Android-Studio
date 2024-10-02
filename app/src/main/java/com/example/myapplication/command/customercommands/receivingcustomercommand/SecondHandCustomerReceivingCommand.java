package com.example.myapplication.command.customercommands.receivingcustomercommand;


import android.app.Activity;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.model.Customer;

public class SecondHandCustomerReceivingCommand implements CustomerReceivingCommand {

    Customer customer;

    // EditText fields
    EditText editTextCustomerName;
    EditText editTextCustomerSurname;
    EditText editTextIdNumber;
    EditText editTextPhoneNumber;
    EditText editTextAddress;

    public SecondHandCustomerReceivingCommand(Activity activity) {
        super();
        this.customer = Customer.getInstance();

        // Initialize EditText fields from the frame
        editTextCustomerName = activity.findViewById(R.id.customerName);
        editTextCustomerSurname = activity.findViewById(R.id.customerSurname);
        editTextIdNumber = activity.findViewById(R.id.customerTcNo);
        editTextPhoneNumber = activity.findViewById(R.id.customerPhoneNumber);
        editTextAddress = activity.findViewById(R.id.customerAddress);
    }

    @Override
    public void execute() {
        // Retrieve text from EditText fields and set them to the Customer instance
        customer.setName(editTextCustomerName.getText().toString().trim());
        customer.setSurname(editTextCustomerSurname.getText().toString().trim());
        customer.setIdNumber(editTextIdNumber.getText().toString().trim());
        customer.setPhoneNumber(editTextPhoneNumber.getText().toString().trim());
        customer.setAddress(editTextAddress.getText().toString().trim());
    }
}
