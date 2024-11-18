package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OuterwearActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_outerwear);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnBackToHome = findViewById(R.id.btn_back_to_home);

        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(OuterwearActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        Button btnAddToWishlist1 = findViewById(R.id.btn_add_to_wishlist);
        btnAddToWishlist1.setOnClickListener(v -> {
            int imageResource = R.drawable.outerwearitem1png;
            String name = "Our Legacy - Slight Jacket";
            double price = 875;

            HomeWishlist.addItemToWishlist(OuterwearActivity.this, imageResource, name, price, "testing");

            Intent intent = new Intent(OuterwearActivity.this, HomeWishlist.class);
            startActivity(intent);
        });
    }
}