package com.example.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.model.FirebaseConnection;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    final String username = "";
    final String password = "";
    EditText viewUsername, viewPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //    mAuth= FirebaseAuth.getInstance;
        viewUsername = findViewById(R.id.usernameText);
        viewPassword = findViewById(R.id.passwordText);
// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        FirebaseConnection.initFirebase(this);
    }


    public void login(View view) {
        String controlUsername = viewUsername.getText().toString();
        String controlPassword = viewPassword.getText().toString();
        if (username.equals(controlUsername) && password.equals(controlPassword)) {
            Toast.makeText(this, "Kullanıcı girişi başarılı...", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Kullanıcı girişi başarısız...", Toast.LENGTH_LONG).show();

        }
    }

}
