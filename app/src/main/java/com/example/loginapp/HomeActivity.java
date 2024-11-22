package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "ScrollPrefs";
    private static final String SCROLL_POSITION_KEY = "scroll_position";
    private ScrollView scrollView;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);

        scrollView = findViewById(R.id.scroll_view);
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Restore scroll position
        scrollView.post(() -> {
            int scrollPosition = prefs.getInt(SCROLL_POSITION_KEY, 0);
            scrollView.scrollTo(0, scrollPosition);
        });

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
        ImageButton btnMax = findViewById(R.id.maxbutton);
        ImageButton btnFeatured = findViewById(R.id.featured);
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
            }else if (v.getId() == R.id.maxbutton) {
                intent = new Intent(HomeActivity.this, MaxwellsActivity.class);
            }else if (v.getId() == R.id.featured) {
                intent = new Intent(HomeActivity.this, FeaturedActivity.class);
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
        btnMax.setOnClickListener(listener);
        btnFeatured.setOnClickListener(listener);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Save scroll position
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(SCROLL_POSITION_KEY, scrollView.getScrollY());
        editor.apply();
    }
}