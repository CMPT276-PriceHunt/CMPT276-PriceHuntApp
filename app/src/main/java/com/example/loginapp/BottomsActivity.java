package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
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
        Button btnAddToWishlist2 = findViewById(R.id.btn_add_to_wishlist2);
        View.OnClickListener listener = v -> {
            Intent intent;
            if (v.getId() == R.id.btn_add_to_wishlist1) {
                int imageResource = R.drawable.bottomitem1png;
                String name = "Acne Studios - Super Baggy Fit Jeans - 2023 Acne Studios X Moomin";
                double price = 800;

                HomeWishlist.addItemToWishlist(BottomsActivity.this, imageResource, name, price, "testing");

                intent = new Intent(BottomsActivity.this, HomeWishlist.class);
                startActivity(intent);
            } else if (v.getId() == R.id.btn_add_to_wishlist2) {
                int imageResource = R.drawable.bottomitem2png;
                String name = "AMBUSH - Denim Work Pants";
                double price = 973;

                HomeWishlist.addItemToWishlist(BottomsActivity.this, imageResource, name, price, "testing");

                intent = new Intent(BottomsActivity.this, HomeWishlist.class);
                startActivity(intent);
            } else {
                return;
            }
            startActivity(intent);
            finish();
        };

        btnAddToWishlist1.setOnClickListener(listener);
        btnAddToWishlist2.setOnClickListener(listener);
        /*btnAddToWishlist1.setOnClickListener(v -> {
            int imageResource = R.drawable.bottomitem1png;
            String name = "Super Baggy Fit Jeans - 2023 Acne Studios X Moomin";
            double price = 800;

            HomeWishlist.addItemToWishlist(BottomsActivity.this, imageResource, name, price);

            Intent intent = new Intent(BottomsActivity.this, HomeWishlist.class);
            startActivity(intent);
        });*/
        /*
                ImageButton btnBottoms = findViewById(R.id.bottoms);
        ImageButton btnTops = findViewById(R.id.tops);
        ImageButton btnOuterwear = findViewById(R.id.outerwear);
        View.OnClickListener listener = v -> {
            Intent intent;
            if (v.getId() == R.id.bottoms) {
                intent = new Intent(HomeActivity.this, BottomsActivity.class);
            } else if (v.getId() == R.id.tops) {
                intent = new Intent(HomeActivity.this, TopsActivity.class);
            } else if (v.getId() == R.id.outerwear) {
                intent = new Intent(HomeActivity.this, OuterwearActivity.class);
            } else {
                return;
            }
            startActivity(intent);
            finish();
        };


        btnBottoms.setOnClickListener(listener);
        btnTops.setOnClickListener(listener);
        btnOuterwear.setOnClickListener(listener);
         */
    }
}