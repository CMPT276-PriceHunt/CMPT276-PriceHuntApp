package com.example.loginapp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.loginapp.R;
import com.example.loginapp.ViewWishlistActivity;
import com.example.loginapp.WishlistFolder;
import com.example.loginapp.WishlistItem;
import java.util.ArrayList;
import java.util.Locale;

// Adapter for displaying wishlist items in a RecyclerView
public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {
    private ArrayList<WishlistItem> itemList;
    private WishlistFolder parentFolder;
    private Context context;

    // Initialize adapter with list of wishlist items and parent folder
    public WishlistAdapter(ArrayList<WishlistItem> itemList, WishlistFolder parentFolder) {
        this.itemList = new ArrayList<>(itemList); // Create a new ArrayList to avoid reference issues
        this.parentFolder = parentFolder;
    }

    // Create new views for items
    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_wishlist, parent, false);
        return new WishlistViewHolder(view);
    }

    // Bind data to views for each item position
    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
        if (position < 0 || position >= itemList.size()) {
            return; // Prevent index out of bounds
        }

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

        // Set up delete button click listener
        final int itemPosition = position;
        holder.btnDeleteItem.setOnClickListener(v -> showDeleteItemDialog(itemPosition));
    }

    // Show confirmation dialog for item deletion
    private void showDeleteItemDialog(final int position) {
        if (position < 0 || position >= itemList.size()) {
            return; // Additional safety check
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Item");
        builder.setMessage("Are you sure you want to delete this item?");

        // Create dialog with delete and cancel options
        builder.setPositiveButton("Delete", (dialogInterface, which) -> {
            try {
                // Remove from both lists
                parentFolder.setTotal(parentFolder.getTotal() - itemList.get(position).getPrice());
                itemList.remove(position);
                parentFolder.getItems().remove(position);

                // Update the RecyclerView
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, itemList.size());

                // Save changes if in ViewWishlistActivity
                if (context instanceof ViewWishlistActivity) {
                    ((ViewWishlistActivity) context).saveFolders();
                }

                Toast.makeText(context, "Item deleted successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(context, "Error deleting item", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    // Update items in the adapter
    public void updateItems(ArrayList<WishlistItem> newItems) {
        this.itemList = new ArrayList<>(newItems);
        notifyDataSetChanged();
    }

    // ViewHolder class for caching item view references
    static class WishlistViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvPrice;
        ImageButton btnDeleteItem;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
            // Bind UI elements
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            btnDeleteItem = itemView.findViewById(R.id.btn_delete_item);
        }
    }


}