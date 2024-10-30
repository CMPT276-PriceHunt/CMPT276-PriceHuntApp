package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.Adapter.WishlistAdapter;

import java.util.ArrayList;

public class WishlistActivity extends AppCompatActivity {

    private RecyclerView rvItems;
    private ArrayList<WishlistItem> itemList;
    private WishlistAdapter wishlistAdapter;

    private Button home_btn;

    //Initialize user interface and data
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        rvItems = findViewById(R.id.rv_items);
        home_btn = findViewById(R.id.home_btn);

        // Get the item list from the intent
        itemList = getIntent().getParcelableArrayListExtra("itemList");
        if (itemList == null) {
            itemList = new ArrayList<>();
        }

        // Initialize adapter
        wishlistAdapter = new WishlistAdapter(itemList);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        rvItems.setAdapter(wishlistAdapter);

        home_btn.setOnClickListener(v -> {
            Intent intent = new Intent(WishlistActivity.this, MainActivity2.class);
            startActivity(intent);
        });
    }
}