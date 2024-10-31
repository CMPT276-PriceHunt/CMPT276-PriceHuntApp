package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.loginapp.profileInfoActivity;
import com.example.loginapp.SecondActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.loginapp.R;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.wishlist) {
                    startActivity(new Intent(getApplicationContext(), SecondActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if(item.getItemId() == R.id.profile) {
                    startActivity(new Intent(getApplicationContext(), profileInfoActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if(item.getItemId() == R.id.home) {
                    return true;
                }
                return false;
            }
        });

    }
}