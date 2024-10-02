package com.example.myapplication.command.customercommands.receivingcustomercommand;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Customer;


public class CustomerrReceivingCommand {

    Customer customer;

    // EditText fields
    EditText editTextCustomerName;
    EditText editTextCustomerSurname;
    EditText editTextIdNumber;
    EditText editTextPhoneNumber;
    EditText editTextAddress;
    Activity activity;

    public CustomerrReceivingCommand(Activity activity) {
        super();
        this.customer = Customer.getInstance();
        this.activity = activity;
        editTextCustomerName = activity.findViewById(R.id.customerName);
        editTextCustomerSurname = activity.findViewById(R.id.customerSurname);
        editTextIdNumber = activity.findViewById(R.id.customerTcNo);
        editTextPhoneNumber = activity.findViewById(R.id.customerPhoneNumber);
        editTextAddress = activity.findViewById(R.id.customerAddress);


    }

    public void execute() {

        // Input validation for all EditText fields
        if (editTextCustomerName.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, "Customer name is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editTextCustomerSurname.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, "Customer surname is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editTextIdNumber.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, "ID Number is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editTextPhoneNumber.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, "Phone Number is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editTextAddress.getText().toString().trim().isEmpty()) {
            Toast.makeText(activity, "Address is required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve text from EditText fields and set them to the Customer instance
        customer.setName(editTextCustomerName.getText().toString().trim());
        customer.setSurname(editTextCustomerSurname.getText().toString().trim());
        customer.setIdNumber(editTextIdNumber.getText().toString().trim());
        customer.setPhoneNumber(editTextPhoneNumber.getText().toString().trim());
        customer.setAddress(editTextAddress.getText().toString().trim());
    }

}