package com.example.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.UUID;

public class HomeWishlist extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final int PICK_IMAGE = 100;
    private static final String PREFS_NAME = "WishlistPrefs";
    private static final String FOLDERS_KEY = "folders";

    private ImageView imgPhoto;
    private EditText etName, etPrice;
    private Button btnAddPhoto, btnSubmit, btnViewWishlist;
    private Spinner spinnerFolders;
    private ArrayAdapter<String> spinnerAdapter;

    private Bitmap selectedBitmap;
    public static ArrayList<WishlistFolder> folders;
    private int selectedFolderPosition = 0;  // Track the selected position
    private SharedPreferences prefs;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_wishlist);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        gson = new Gson();

        initializeViews();
        loadFolders();
        setupSpinner();

        btnAddPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });

        btnSubmit.setOnClickListener(v -> {
            if (folders.isEmpty()) {
                Toast.makeText(HomeWishlist.this, "No folder available", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = etName.getText().toString();
            String priceStr = etPrice.getText().toString();

            if (selectedBitmap != null && !name.isEmpty() && !priceStr.isEmpty()) {
                try {
                    double price = Double.parseDouble(priceStr);
                    String imagePath = saveImageToInternalStorage(selectedBitmap);
                    WishlistItem item = new WishlistItem(imagePath, name, price);

                    // Get the current selected folder and add item
                    WishlistFolder currentFolder = folders.get(selectedFolderPosition);
                    currentFolder.addItem(item);

                    // Update the folder in the list
                    folders.set(selectedFolderPosition, currentFolder);

                    saveFolders();
                    Toast.makeText(HomeWishlist.this, "Item added to " + currentFolder.getName(), Toast.LENGTH_SHORT).show();
                    clearInputs();
                } catch (NumberFormatException e) {
                    Toast.makeText(HomeWishlist.this, "Please enter a valid price", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(HomeWishlist.this, "Please fill in all fields and add a photo", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewWishlist.setOnClickListener(v -> {
            Intent intent = new Intent(HomeWishlist.this, ViewWishlistActivity.class);
            startActivity(intent);
        });

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Wishlist selected
        bottomNavigationView.setSelectedItemId(R.id.wishlist);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if(item.getItemId() == R.id.wishlist) {
                    return true;
                } else if(item.getItemId() == R.id.profile) {
                    startActivity(new Intent(getApplicationContext(), profileInfoActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });
    }

    private void initializeViews() {
        imgPhoto = findViewById(R.id.img_photo);
        etName = findViewById(R.id.et_name);
        etPrice = findViewById(R.id.et_price);
        btnAddPhoto = findViewById(R.id.btn_add_photo);
        btnSubmit = findViewById(R.id.btn_submit);
        btnViewWishlist = findViewById(R.id.btn_view_wishlist);
        spinnerFolders = findViewById(R.id.spinner_folders);
    }

    public static void addItemToWishlist(Context context, int imageResId, String name, double price, String folderName) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String foldersJson = prefs.getString(FOLDERS_KEY, null);
        Type type = new TypeToken<ArrayList<WishlistFolder>>(){}.getType();
        ArrayList<WishlistFolder> folders;

        if (foldersJson != null) {
            folders = gson.fromJson(foldersJson, type);
        } else {
            folders = new ArrayList<>();
            folders.add(new WishlistFolder("Default Wishlist"));
        }

        WishlistItem item = new WishlistItem(imageResId, name, price);
        boolean folderFound = false;

        for (WishlistFolder folder : folders) {
            if (folder.getName().equals(folderName)) {
                folder.addItem(item);
                folderFound = true;
                break;
            }
        }

        if (!folderFound) {
            WishlistFolder newFolder = new WishlistFolder(folderName);
            newFolder.addItem(item);
            folders.add(newFolder);
        }

        String updatedFoldersJson = gson.toJson(folders);
        prefs.edit().putString(FOLDERS_KEY, updatedFoldersJson).apply();

        Toast.makeText(context, "Item added to " + folderName, Toast.LENGTH_SHORT).show();
    }

    public void loadFolders() {
        String foldersJson = prefs.getString(FOLDERS_KEY, null);
        Type type = new TypeToken<ArrayList<WishlistFolder>>(){}.getType();

        if (foldersJson != null) {
            folders = gson.fromJson(foldersJson, type);
            // Ensure selected position is valid
            if (selectedFolderPosition >= folders.size()) {
                selectedFolderPosition = 0;
            }
        } else {
            folders = new ArrayList<>();
            folders.add(new WishlistFolder("Default Wishlist"));
            selectedFolderPosition = 0;
            saveFolders();
        }
    }

    public void setupSpinner() {
        ArrayList<String> folderNames = new ArrayList<>();
        for (WishlistFolder folder : folders) {
            folderNames.add(folder.getName());
        }

        spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                folderNames
        );

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFolders.setAdapter(spinnerAdapter);
        spinnerFolders.setOnItemSelectedListener(this);

        if (!folders.isEmpty()) {
            spinnerFolders.setSelection(selectedFolderPosition);
        }
    }

    private void saveFolders() {
        String foldersJson = gson.toJson(folders);
        prefs.edit().putString(FOLDERS_KEY, foldersJson).apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFolders();
        setupSpinner();
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
        etName.setText("");
        etPrice.setText("");
        selectedBitmap = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedFolderPosition = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selectedFolderPosition = 0;
    }
}