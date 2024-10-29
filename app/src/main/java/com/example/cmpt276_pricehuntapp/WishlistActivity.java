package com.example.cmpt276_pricehuntapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmpt276_pricehuntapp.Adapter.WishlistFolderAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class WishlistActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "WishlistPrefs";
    private static final String FOLDERS_KEY = "folders";

    private RecyclerView rvFolders;
    private FloatingActionButton fabAddFolder;
    private ArrayList<WishlistFolder> folders;
    private WishlistFolderAdapter folderAdapter;
    private SharedPreferences prefs;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        gson = new Gson();

        rvFolders = findViewById(R.id.rv_folders);
        fabAddFolder = findViewById(R.id.fab_add_folder);

        // Load folders from SharedPreferences instead of Intent
        loadFolders();

        folderAdapter = new WishlistFolderAdapter(folders);
        rvFolders.setLayoutManager(new LinearLayoutManager(this));
        rvFolders.setAdapter(folderAdapter);

        fabAddFolder.setOnClickListener(v -> showAddFolderDialog());
    }

    private void loadFolders() {
        String foldersJson = prefs.getString(FOLDERS_KEY, null);
        Type type = new TypeToken<ArrayList<WishlistFolder>>(){}.getType();

        if (foldersJson != null) {
            folders = gson.fromJson(foldersJson, type);
        } else {
            folders = new ArrayList<>();
            folders.add(new WishlistFolder("默认收藏夹"));
            saveFolders();
        }
    }

    private void showAddFolderDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("新建收藏夹");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("确定", (dialog, which) -> {
            String folderName = input.getText().toString();
            if (!folderName.isEmpty()) {
                folders.add(new WishlistFolder(folderName));
                folderAdapter.notifyItemInserted(folders.size() - 1);
                saveFolders();
            }
        });
        builder.setNegativeButton("取消", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void saveFolders() {
        String foldersJson = gson.toJson(folders);
        prefs.edit().putString(FOLDERS_KEY, foldersJson).apply();
    }
}