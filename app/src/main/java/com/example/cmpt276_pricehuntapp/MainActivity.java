package com.example.cmpt276_pricehuntapp;

import android.content.SharedPreferences;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    private static final String PREFS_NAME = "WishlistPrefs";
    private static final String FOLDERS_KEY = "folders";

    private ImageView imgPhoto;
    private EditText etName, etPrice;
    private Button btnAddPhoto, btnSubmit, btnViewWishlist;
    private Bitmap selectedBitmap;
    private ArrayList<WishlistFolder> folders;
    private WishlistFolder currentFolder;
    private SharedPreferences prefs;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SharedPreferences and Gson
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        gson = new Gson();

        initializeViews();
        loadFolders();

        btnAddPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String priceStr = etPrice.getText().toString();

            if (selectedBitmap != null && !name.isEmpty() && !priceStr.isEmpty()) {
                try {
                    double price = Double.parseDouble(priceStr);
                    String imagePath = saveImageToInternalStorage(selectedBitmap);
                    WishlistItem item = new WishlistItem(imagePath, name, price);
                    currentFolder.addItem(item);
                    saveFolders();
                    Toast.makeText(MainActivity.this, "Item added to wishlist", Toast.LENGTH_SHORT).show();
                    clearInputs();
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Please enter a valid price", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Please fill in all fields and add a photo", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewWishlist.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WishlistActivity.class);
            startActivity(intent);
        });
    }

    private void initializeViews() {
        imgPhoto = findViewById(R.id.img_photo);
        etName = findViewById(R.id.et_name);
        etPrice = findViewById(R.id.et_price);
        btnAddPhoto = findViewById(R.id.btn_add_photo);
        btnSubmit = findViewById(R.id.btn_submit);
        btnViewWishlist = findViewById(R.id.btn_view_wishlist);
    }

    private void loadFolders() {
        String foldersJson = prefs.getString(FOLDERS_KEY, null);
        Type type = new TypeToken<ArrayList<WishlistFolder>>(){}.getType();

        if (foldersJson != null) {
            folders = gson.fromJson(foldersJson, type);
        } else {
            folders = new ArrayList<>();
            folders.add(new WishlistFolder("默认收藏夹"));
        }

        currentFolder = folders.get(0);
    }

    private void saveFolders() {
        String foldersJson = gson.toJson(folders);
        prefs.edit().putString(FOLDERS_KEY, foldersJson).apply();
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
}
