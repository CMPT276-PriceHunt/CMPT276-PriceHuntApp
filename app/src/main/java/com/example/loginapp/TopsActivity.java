package com.example.loginapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TopsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tops);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnBackToHome = findViewById(R.id.btn_back_to_home);

        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(TopsActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        /*Button btnAddToWishlist1 = findViewById(R.id.btn_add_to_wishlist);
        btnAddToWishlist1.setOnClickListener(v -> {
            int imageResource = R.drawable.topitem1png;
            String name = "UNDERCOVER - UB0D3804";
            double price = 120;

            HomeWishlist.addItemToWishlist(TopsActivity.this, imageResource, name, price, "testing");

            Intent intent = new Intent(TopsActivity.this, HomeWishlist.class);
            startActivity(intent);
        });*/


        Button btnShowDialog1 = findViewById(R.id.btn_show_dialog1);
        btnShowDialog1.setOnClickListener(v -> displayDialog(1));
    }

    public void displayDialog(int var){

        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().widthPixels*0.6);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_adding_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.button_background);

        ImageView img = dialog.findViewById(R.id.img_photo);
        if(var == 1)
        {
            img.setImageResource(R.drawable.topitem);
        }
        else {
            return;
        }
        Button btnConfirm = dialog.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(v -> {
            Intent intent;
            if(var == 1) {
                int imageResource = R.drawable.topitem1png;
                String name = "UNDERCOVER - UB0D3804";
                double price = 120;

                HomeWishlist.addItemToWishlist(TopsActivity.this, imageResource, name, price, "testing");

                intent = new Intent(TopsActivity.this, HomeWishlist.class);
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