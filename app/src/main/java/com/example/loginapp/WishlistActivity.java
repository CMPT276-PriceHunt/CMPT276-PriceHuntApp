package com.example.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.Adapter.WishlistFolderAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class WishlistActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "WishlistPrefs";
    private static final String FOLDERS_KEY = "folders";

    // UI Components
    private RecyclerView rvFolders;
    private FloatingActionButton fabAddFolder;
    private Button btnBackToHome;
    // Data and Adapter
    private ArrayList<WishlistFolder> folders;
    private WishlistFolderAdapter folderAdapter;
    // Storage utilities
    private SharedPreferences prefs;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        // Initialize SharedPreferences and Gson for data persistence
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        gson = new Gson();

        rvFolders = findViewById(R.id.rv_folders);
        fabAddFolder = findViewById(R.id.fab_add_folder);
        btnBackToHome = findViewById(R.id.btn_back_to_home);

        loadFolders();

        folderAdapter = new WishlistFolderAdapter(folders);
        rvFolders.setLayoutManager(new LinearLayoutManager(this));
        rvFolders.setAdapter(folderAdapter);

        fabAddFolder.setOnClickListener(v -> showAddFolderDialog());


        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(WishlistActivity.this, SecondActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void loadFolders() {
        String foldersJson = prefs.getString(FOLDERS_KEY, null);
        Type type = new TypeToken<ArrayList<WishlistFolder>>(){}.getType();

        if (foldersJson != null) {
            // Load existing folders from SharedPreferences
            folders = gson.fromJson(foldersJson, type);
        } else {
            folders = new ArrayList<>();
            folders.add(new WishlistFolder("Default Wishlist"));
            saveFolders();
        }
    }

    private void showAddFolderDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Wishlist");

        final EditText input = new EditText(this);
        builder.setView(input);

        // Set up dialog buttons
        builder.setPositiveButton("Confirm", (dialog, which) -> {
            String folderName = input.getText().toString();
            if (!folderName.isEmpty()) {
                folders.add(new WishlistFolder(folderName));
                folderAdapter.notifyItemInserted(folders.size() - 1);
                saveFolders();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    public void saveFolders() {
        String foldersJson = gson.toJson(folders);
        prefs.edit().putString(FOLDERS_KEY, foldersJson).apply();
    }
}
