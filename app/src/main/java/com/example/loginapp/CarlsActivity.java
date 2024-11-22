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

public class CarlsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_carls);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnBackToHome = findViewById(R.id.btn_back_to_home);
        btnBackToHome.setOnClickListener(v ->
        {
            Intent intent = new Intent(CarlsActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });


        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        gson =new Gson();


        folders = FolderUtils.loadFolders(this,prefs,gson,FOLDERS_KEY);


        Button btnShowDialog1 = findViewById(R.id.btn_show_dialog1);
        btnShowDialog1.setOnClickListener(v -> displayDialog());
    }

    private void displayDialog(){
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.7);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_long_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.button_background);


        Spinner dialogSpinner = dialog.findViewById(R.id.spinner_folders);
        SpinnerUtils.setupDialogSpinner(this, dialogSpinner, folders, selectedFolderPosition, folderName -> selectedFolderName = folderName);

        ImageView img = dialog.findViewById(R.id.img_photo);
        img.setImageResource(R.drawable.carlfit);
        Button btnConfirm = dialog.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(v -> {
            int imageResource1 = R.drawable.carlg;
            String name1 = "Cartier - C De Cartier Sunglasses";
            double price1 = 690;

            HomeWishlist.addItemToWishlist(CarlsActivity.this, imageResource1, name1, price1, selectedFolderName);

            int imageResource2 = R.drawable.carlt;
            String name2 = "Bugatchi - Rib Short Sleeve Polo Sweater";
            double price2 = 325;

            HomeWishlist.addItemToWishlist(CarlsActivity.this, imageResource2, name2, price2, selectedFolderName);

            int imageResource3 = R.drawable.carlsuspenders;
            String name3 = "Buyless Fashion - Elastic Suspenders";
            double price3 = 15;
            HomeWishlist.addItemToWishlist(CarlsActivity.this, imageResource3, name3, price3, selectedFolderName);

            int imageResource4 = R.drawable.carlp;
            String name4 = "Gucci x The North Face - Overalls";
            double price4 = 2900;

            HomeWishlist.addItemToWishlist(CarlsActivity.this, imageResource4, name4, price4, selectedFolderName);

            int imageResource5 = R.drawable.carls;
            String name5 = "Rick Owens - Porterville Knee High Socks";
            double price5 = 255;

            HomeWishlist.addItemToWishlist(CarlsActivity.this, imageResource5, name5, price5, selectedFolderName);

            int imageResource6 = R.drawable.carlf;
            String name6 = "John Lobb - Jermynii Monk Strap Shoe";
            double price6 = 3377;
            HomeWishlist.addItemToWishlist(CarlsActivity.this, imageResource6, name6, price6, selectedFolderName);
            dialog.dismiss();
        });

        dialog.getWindow().setLayout(width, height);
        Button btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}