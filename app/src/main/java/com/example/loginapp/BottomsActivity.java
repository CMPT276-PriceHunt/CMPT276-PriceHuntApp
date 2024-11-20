package com.example.loginapp;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BottomsActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "WishlistPrefs";
    public static final String FOLDERS_KEY = "folders";
    public static ArrayList<WishlistFolder> folders;
    public int selectedFolderPosition = 0;  // Track the selected position
    public SharedPreferences prefs;
    private Gson gson;
    public String selectedFolderName;

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


        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        gson = new Gson();



        folders = FolderUtils.loadFolders(this, prefs, gson, FOLDERS_KEY);



        Button btnShowDialog1 = findViewById(R.id.btn_show_dialog1);
        Button btnShowDialog2 = findViewById(R.id.btn_show_dialog2);
        Button btnShowDialog3 = findViewById(R.id.btn_show_dialog3);
        btnShowDialog1.setOnClickListener(v -> displayDialog(1));
        btnShowDialog2.setOnClickListener(v -> displayDialog(2));
        btnShowDialog3.setOnClickListener(v -> displayDialog(3));

    }

    private void displayDialog(int var){

        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().widthPixels*0.7);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_adding_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.button_background);


        Spinner dialogSpinner = dialog.findViewById(R.id.spinner_folders);
        SpinnerUtils.setupDialogSpinner(this, dialogSpinner, folders, selectedFolderPosition, folderName -> selectedFolderName = folderName);

        ImageView img = dialog.findViewById(R.id.img_photo);
        if(var == 1)
        {
            img.setImageResource(R.drawable.bottomitem);
        }
        else if(var == 2)
        {
            img.setImageResource(R.drawable.bottomitem2);
        }
        else if(var == 3)
        {
            img.setImageResource(R.drawable.bottomitem3);
        }
        else {
            return;
        }
        Button btnConfirm = dialog.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(v -> {
            Intent intent;
            if(var == 1) {
                int imageResource = R.drawable.bottomitem1png;
                String name = "Acne Studios - Super Baggy Fit Jeans - 2023 Acne Studios X Moomin";
                double price = 1126;

                HomeWishlist.addItemToWishlist(BottomsActivity.this, imageResource, name, price, selectedFolderName);

                intent = new Intent(BottomsActivity.this, HomeWishlist.class);
                startActivity(intent);
            }
            else if(var == 2)
            {
                int imageResource = R.drawable.bottomitem2png;
                String name = "AMBUSH - Denim Work Pants";
                double price = 973;

                HomeWishlist.addItemToWishlist(BottomsActivity.this, imageResource, name, price, selectedFolderName);

                intent = new Intent(BottomsActivity.this, HomeWishlist.class);
                startActivity(intent);
            }else if (var == 3) {
                int imageResource = R.drawable.bottomitem3png;
                String name = "Wooyoungmi - Two-Tuck Wide Denim Pants";
                double price = 690;

                HomeWishlist.addItemToWishlist(BottomsActivity.this, imageResource, name, price, selectedFolderName);

                intent = new Intent(BottomsActivity.this, HomeWishlist.class);
                startActivity(intent);
            }
            else {
                return;
            }
            startActivity(intent);
            finish();
        });

        dialog.getWindow().setLayout(width, height);
        Button btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}