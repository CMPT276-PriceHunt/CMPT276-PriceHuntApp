package com.example.loginapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.gson.Gson;
import java.util.ArrayList;

public class FeaturedActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_featured);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnBackToHome = findViewById(R.id.btn_back_to_home);
        btnBackToHome.setOnClickListener(v ->
        {
            Intent intent = new Intent(FeaturedActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });


        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        gson =new Gson();


        folders = FolderUtils.loadFolders(this,prefs,gson,FOLDERS_KEY);

        Button btnShowDialog1 = findViewById(R.id.btn_show_dialog1);
        Button btnShowDialog2 = findViewById(R.id.btn_show_dialog2);
        Button btnShowDialog3 = findViewById(R.id.btn_show_dialog3);
        btnShowDialog1.setOnClickListener(v -> displayDialog(1));
        btnShowDialog2.setOnClickListener(v -> displayDialog(2));
        btnShowDialog3.setOnClickListener(v -> displayDialog(3));
        Button btnShowDialog4 = findViewById(R.id.btn_show_dialog4);
        Button btnShowDialog5 = findViewById(R.id.btn_show_dialog5);
        Button btnShowDialog6 = findViewById(R.id.btn_show_dialog6);
        btnShowDialog4.setOnClickListener(v -> displayDialog(4));
        btnShowDialog5.setOnClickListener(v -> displayDialog(5));
        btnShowDialog6.setOnClickListener(v -> displayDialog(6));
        Button btnShowDialog7 = findViewById(R.id.btn_show_dialog7);
        Button btnShowDialog8 = findViewById(R.id.btn_show_dialog8);
        Button btnShowDialog9 = findViewById(R.id.btn_show_dialog9);
        btnShowDialog7.setOnClickListener(v -> displayDialog(7));
        btnShowDialog8.setOnClickListener(v -> displayDialog(8));
        btnShowDialog9.setOnClickListener(v -> displayDialog(9));
    }

    private void displayDialog(int var ){
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
            img.setImageResource(R.drawable.item1);
        }
        else if(var == 2)
        {
            img.setImageResource(R.drawable.item2);
        }
        else if(var == 3)
        {
            img.setImageResource(R.drawable.item3);
        }
        else if(var == 4)
        {
            img.setImageResource(R.drawable.item4);
        }
        else if(var == 5)
        {
            img.setImageResource(R.drawable.item5);
        }
        else if(var == 6)
        {
            img.setImageResource(R.drawable.item6);
        }
        else if(var == 7)
        {
            img.setImageResource(R.drawable.item7);
        }
        else if(var == 8)
        {
            img.setImageResource(R.drawable.item8);
        }
        else if(var == 9)
        {
            img.setImageResource(R.drawable.item9);
        }
        else {
            return;
        }
        Button btnConfirm = dialog.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(v -> {
            if (var == 1) {
                int imageResource = R.drawable.item1png;
                String name = "Lovemetal - 3D Heart Ring";
                double price = 120;
                HomeWishlist.addItemToWishlist(FeaturedActivity.this, imageResource, name, price, selectedFolderName);
            } else if (var == 2) {
                int imageResource = R.drawable.item2png;
                String name = "Gentle Monster - Limitless";
                double price = 500;
                HomeWishlist.addItemToWishlist(FeaturedActivity.this, imageResource, name, price, selectedFolderName);
            } else if (var == 3) {
                int imageResource = R.drawable.item3png;
                String name = "Miu Miu - Star Motif Puffer Jacket";
                double price = 5100;
                HomeWishlist.addItemToWishlist(FeaturedActivity.this, imageResource, name, price, selectedFolderName);
            } else if (var == 4) {
                int imageResource = R.drawable.item4png;
                String name = "WINDOWSEN - Hoodes Backless Active Jacket";
                double price = 1100;
                HomeWishlist.addItemToWishlist(FeaturedActivity.this, imageResource, name, price, selectedFolderName);
            } else if (var == 5) {
                int imageResource = R.drawable.item5png;
                String name = "WINDOWSEN - Cut-out Maxi Skirt";
                double price = 1400;
                HomeWishlist.addItemToWishlist(FeaturedActivity.this, imageResource, name, price, selectedFolderName);
            } else if (var == 6) {
                int imageResource = R.drawable.item6png;
                String name = "Yohei Ohno - Balloon Skirt Bag";
                double price = 500;
                HomeWishlist.addItemToWishlist(FeaturedActivity.this, imageResource, name, price, selectedFolderName);
            } else if (var == 7) {
                int imageResource = R.drawable.item7png;
                String name = "Diesel - 1dr-Iconic Shoulder Bag";
                double price = 1200;
                HomeWishlist.addItemToWishlist(FeaturedActivity.this, imageResource, name, price, selectedFolderName);
            } else if (var == 8) {
                int imageResource = R.drawable.item8png;
                String name = "Moon Boot - Icon Low Swarovski Crystal Boots";
                double price = 4250;
                HomeWishlist.addItemToWishlist(FeaturedActivity.this, imageResource, name, price, selectedFolderName);
            } else if (var == 9) {
                int imageResource = R.drawable.item9png;
                String name = "Melissa x Collina Strada - Puff Sandals";
                double price = 250;
                HomeWishlist.addItemToWishlist(FeaturedActivity.this, imageResource, name, price, selectedFolderName);
            } else {
                return;
            }
            dialog.dismiss();
        });

        dialog.getWindow().setLayout(width, height);
        Button btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}