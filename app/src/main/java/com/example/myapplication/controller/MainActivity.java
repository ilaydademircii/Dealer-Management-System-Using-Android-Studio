package com.example.myapplication.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    final String username = "ilayda";
    final String password = "12345zid";

    EditText viewUsername, viewPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewUsername = findViewById(R.id.username);
        viewPassword = findViewById(R.id.password);
    }

    public void login(View view) {
        String controlUsername = viewUsername.getText().toString();
        String controlPassword = viewPassword.getText().toString();
        if (username.equals(controlUsername) && password.equals(controlPassword)) {
            Toast.makeText(this, "Kullanıcı girişi başarılı...", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Kullanıcı girişi başarısız...", Toast.LENGTH_LONG).show();

        }
    }


}