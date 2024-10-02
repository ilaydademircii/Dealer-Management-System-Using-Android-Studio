package com.example.myapplication.controller;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

public class MainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //hide or show items


        /*navigation drawer menu*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav_drawer, R.string.close_nav_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Menu menu = navigationView.getMenu();

        if (item.getItemId() == R.id.nav_home) {
            // Home seçeneği
            Intent intent = new Intent(MainMenuActivity.this, HomePageActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.contact) {
            // Contact seçeneği
            Intent intent = new Intent(MainMenuActivity.this, HomePageActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.account) {
            // About seçeneği
            Intent intent = new Intent(MainMenuActivity.this, HomePageActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.login) {
            // Login seçeneği
            Intent intent = new Intent(MainMenuActivity.this, HomePageActivity.class);
            startActivity(intent);
            menu.findItem(R.id.login).setVisible(false);
        } else if (item.getItemId() == R.id.logout) {
            Intent intent = new Intent(MainMenuActivity.this, HomePageActivity.class);
            startActivity(intent);
            // Logout seçeneği
            menu.findItem(R.id.logout).setVisible(false);
        } else {
            throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

}
