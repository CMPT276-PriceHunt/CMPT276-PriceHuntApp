package com.example.loginapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class SecondActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    private ImageView imgPhoto;
    private EditText etDescription;
    private Button btnAddPhoto, btnSubmit, btnViewWishlist;
    private Bitmap selectedBitmap;
    private ArrayList<WishlistItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imgPhoto = findViewById(R.id.img_photo);
        etDescription = findViewById(R.id.et_description);
        btnAddPhoto = findViewById(R.id.btn_add_photo);
        btnSubmit = findViewById(R.id.btn_submit);
        btnViewWishlist = findViewById(R.id.btn_view_wishlist);

        itemList = new ArrayList<>();

        btnAddPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });

        btnSubmit.setOnClickListener(v -> {
            String description = etDescription.getText().toString();
            if (selectedBitmap != null && !description.isEmpty()) {
                String imagePath = saveImageToInternalStorage(selectedBitmap);
                itemList.add(new WishlistItem(imagePath, description));
                Toast.makeText(SecondActivity.this, "Item added to wishlist", Toast.LENGTH_SHORT).show();
                clearInputs();
            } else {
                Toast.makeText(SecondActivity.this, "Please add a photo and description", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewWishlist.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, WishlistActivity.class);
            intent.putParcelableArrayListExtra("itemList", itemList);
            startActivity(intent);
        });

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if(item.getItemId() == R.id.profile) {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if(item.getItemId() == R.id.wishlist) {
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                selectedBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imgPhoto.setImageBitmap(selectedBitmap);
                imgPhoto.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String saveImageToInternalStorage(Bitmap bitmap) {
        try {
            File dir = new File(getFilesDir(), "images");
            if (!dir.exists()) {
                dir.mkdir();
            }
            String filename = UUID.randomUUID().toString() + ".jpg";
            File file = new File(dir, filename);
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void clearInputs() {
        imgPhoto.setImageBitmap(null);
        imgPhoto.setVisibility(View.GONE);
        etDescription.setText("");
        selectedBitmap = null;
    }
}