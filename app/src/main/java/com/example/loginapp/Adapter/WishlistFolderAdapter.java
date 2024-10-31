package com.example.loginapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;
import com.example.loginapp.ViewWishlistActivity;
import com.example.loginapp.WishlistFolder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

// Adapter for managing and displaying wishlist folders in a RecyclerView
public class WishlistFolderAdapter extends RecyclerView.Adapter<WishlistFolderAdapter.FolderViewHolder> {
    private ArrayList<WishlistFolder> folders;
    private Context context;

    public WishlistFolderAdapter(ArrayList<WishlistFolder> folders) {
        this.folders = folders;
    }

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_wishlist_folder, parent, false);
        return new FolderViewHolder(view);
    }

    // Bind data and set up interactions for each folder
    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        WishlistFolder folder = folders.get(position);
        holder.tvFolderName.setText(folder.getName());

        // Set up nested RecyclerView for wishlist items
        WishlistAdapter itemAdapter = new WishlistAdapter(folder.getItems());
        holder.rvItems.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.rvItems.setAdapter(itemAdapter);

        // Handle folder expansion state
        holder.rvItems.setVisibility(folder.isExpanded() ? View.VISIBLE : View.GONE);
        holder.btnExpand.setRotation(folder.isExpanded() ? 180 : 0);

        // Set up click listeners for folder actions
        holder.btnExpand.setOnClickListener(v -> {
            folder.setExpanded(!folder.isExpanded());
            notifyItemChanged(position);
        });

        holder.btnDelete.setOnClickListener(v -> {
            showDeleteConfirmationDialog(position);
        });

        holder.itemView.setOnLongClickListener(v -> {
            showRenameFolderDialog(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }

    // Show confirmation dialog for folder deletion
    private void showDeleteConfirmationDialog(int position) {
        WishlistFolder folderToDelete = folders.get(position);

        // Prevent deletion of default wishlist
        if ("Default Wishlist".equals(folderToDelete.getName())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Cannot Delete");
            builder.setMessage("The default wishlist cannot be deleted.");
            builder.setPositiveButton("OK", null);
            builder.show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Wishlist");
        builder.setMessage("Are you sure you want to delete this wishlist? This will remove all items within.");
        builder.setPositiveButton("Delete", (dialog, which) -> {
            folders.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, folders.size());

            if (context instanceof ViewWishlistActivity) {
                ((ViewWishlistActivity) context).saveFolders();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    // Show dialog for renaming a folder
    private void showRenameFolderDialog(int position) {
        WishlistFolder folderToRename = folders.get(position);

        // Prevent renaming of default wishlist
        if ("Default Wishlist".equals(folderToRename.getName())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Cannot Rename");
            builder.setMessage("The default wishlist cannot be renamed.");
            builder.setPositiveButton("OK", null);
            builder.show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Rename Wishlist");

        final TextInputEditText input = new TextInputEditText(context);
        input.setText(folderToRename.getName());
        builder.setView(input);

        builder.setPositiveButton("Confirm", (dialog, which) -> {
            String newName = input.getText().toString().trim();
            if (!newName.isEmpty()) {
                // Check if name already exists
                boolean nameExists = false;
                for (WishlistFolder folder : folders) {
                    if (folder.getName().equals(newName)) {
                        nameExists = true;
                        break;
                    }
                }
                if (nameExists) {
                    Toast.makeText(context, "Name already exists, please choose another name", Toast.LENGTH_SHORT).show();
                } else {
                    folderToRename.setName(newName);
                    notifyItemChanged(position);

                    if (context instanceof ViewWishlistActivity) {
                        ((ViewWishlistActivity) context).saveFolders();
                    }
                }
            } else {
                Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    // ViewHolder for caching folder view references
    static class FolderViewHolder extends RecyclerView.ViewHolder {
        TextView tvFolderName;
        ImageButton btnExpand, btnDelete;
        RecyclerView rvItems;

        public FolderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFolderName = itemView.findViewById(R.id.tv_folder_name);
            btnExpand = itemView.findViewById(R.id.btn_expand);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            rvItems = itemView.findViewById(R.id.rv_items);
        }
    }
}