package com.example.loginapp.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.loginapp.R;
import com.example.loginapp.WishlistItem;
import java.util.ArrayList;
import java.util.Locale;

// Adapter for displaying wishlist items in a RecyclerView
public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {
    private ArrayList<WishlistItem> itemList;

    // Initialize adapter with list of wishlist items
    public WishlistAdapter(ArrayList<WishlistItem> itemList) {
        this.itemList = itemList;
    }

    // Create new views for items
    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wishlist, parent, false);
        return new WishlistViewHolder(view);
    }

    // Bind data to views for each item position
    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
        WishlistItem item = itemList.get(position);
        // Load and display item image
        if (item.getImageResId() != 0) {
            holder.imgPhoto.setImageResource(item.getImageResId());
        } else if (item.getImagePath() != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(item.getImagePath());
            holder.imgPhoto.setImageBitmap(bitmap);
        }
        // Set item details
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(String.format(Locale.getDefault(), "$%.2f", item.getPrice()));
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // ViewHolder class for caching item view references
    static class WishlistViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvPrice;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
            // Bind UI elements
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}