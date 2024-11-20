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

public class FredsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_freds);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnBackToHome = findViewById(R.id.btn_back_to_home);
        btnBackToHome.setOnClickListener(v ->

        {
            Intent intent = new Intent(FredsActivity.this, HomeActivity.class);
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
        int height = (int)(getResources().getDisplayMetrics().widthPixels + 125);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_adding_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.button_background);


        Spinner dialogSpinner = dialog.findViewById(R.id.spinner_folders);
        SpinnerUtils.setupDialogSpinner(this, dialogSpinner, folders, selectedFolderPosition, folderName -> selectedFolderName = folderName);

        ImageView img = dialog.findViewById(R.id.img_photo);
        img.setImageResource(R.drawable.freddialog);
        Button btnConfirm = dialog.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(v -> {
            Intent intent;
            int imageResource1 = R.drawable.beltpng;
            String name1 = "Hermès - Collier De Chien 50 Belt";
            double price1 = 5350;

            HomeWishlist.addItemToWishlist(FredsActivity.this, imageResource1, name1, price1, selectedFolderName);

            int imageResource2 = R.drawable.pantspng;
            String name2 = "Bottega Veneta - Grain De Poudre Staroial Wool Slim Trousers";
            double price2 = 1595;

            HomeWishlist.addItemToWishlist(FredsActivity.this, imageResource2, name2, price2, selectedFolderName);
            dialog.dismiss();
        });

        dialog.getWindow().setLayout(width, height);
        Button btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}