package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BottomsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bottoms);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button btnBackToHome = findViewById(R.id.btn_back_to_home);
        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(BottomsActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        Button btnAddToWishlist1 = findViewById(R.id.btn_add_to_wishlist1);
        btnAddToWishlist1.setOnClickListener(v -> {
            int imageResource = R.drawable.footwearm;
            String name = "Super Baggy Fit Jeans - 2023 Acne Studios X Moomin";
            double price = 800;

            HomeWishlist.addItemToWishlist(BottomsActivity.this, imageResource, name, price);

            Intent intent = new Intent(BottomsActivity.this, HomeWishlist.class);
            startActivity(intent);
        });
    }
}