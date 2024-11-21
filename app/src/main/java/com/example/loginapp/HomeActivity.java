package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.wishlist) {
                    startActivity(new Intent(getApplicationContext(), HomeWishlist.class));
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

        ImageButton btnBottoms = findViewById(R.id.bottoms);
        ImageButton btnTops = findViewById(R.id.tops);
        ImageButton btnOuterwear = findViewById(R.id.outerwear);
        ImageButton btnFred = findViewById(R.id.fredbutton);
        ImageButton btnCarl = findViewById(R.id.carlbutton);
        View.OnClickListener listener = v -> {
            Intent intent;
            if (v.getId() == R.id.bottoms) {
                intent = new Intent(HomeActivity.this, BottomsActivity.class);
            } else if (v.getId() == R.id.tops) {
                intent = new Intent(HomeActivity.this, TopsActivity.class);
            } else if (v.getId() == R.id.outerwear) {
                intent = new Intent(HomeActivity.this, OuterwearActivity.class);
            }
            else if (v.getId() == R.id.fredbutton) {
                intent = new Intent(HomeActivity.this, FredsActivity.class);
            }else if (v.getId() == R.id.carlbutton) {
                intent = new Intent(HomeActivity.this, CarlsActivity.class);
            }else {
                return;
            }
            startActivity(intent);
            finish();
        };


        btnBottoms.setOnClickListener(listener);
        btnTops.setOnClickListener(listener);
        btnOuterwear.setOnClickListener(listener);
        btnFred.setOnClickListener(listener);
        btnCarl.setOnClickListener(listener);
    }
}