package com.example.cmpt276_pricehuntapp.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmpt276_pricehuntapp.R;
import com.example.cmpt276_pricehuntapp.WishlistActivity;
import com.example.cmpt276_pricehuntapp.WishlistFolder;

import java.util.ArrayList;

public class WishlistFolderAdapter extends RecyclerView.Adapter<WishlistFolderAdapter.FolderViewHolder> {
    private ArrayList<WishlistFolder> folders;
    private Context context; // 添加上下文

    public WishlistFolderAdapter(ArrayList<WishlistFolder> folders) {
        this.folders = folders;
    }

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 获取上下文
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_wishlist_folder, parent, false);
        return new FolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        WishlistFolder folder = folders.get(position);
        holder.tvFolderName.setText(folder.getName());

        // Set up nested RecyclerView for items
        WishlistAdapter itemAdapter = new WishlistAdapter(folder.getItems());
        holder.rvItems.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.rvItems.setAdapter(itemAdapter);

        // Handle expansion/collapse
        holder.rvItems.setVisibility(folder.isExpanded() ? View.VISIBLE : View.GONE);
        holder.btnExpand.setRotation(folder.isExpanded() ? 180 : 0);

        holder.btnExpand.setOnClickListener(v -> {
            folder.setExpanded(!folder.isExpanded());
            notifyItemChanged(position);
        });

        // Handle delete button click
        holder.btnDelete.setOnClickListener(v -> {
            showDeleteConfirmationDialog(position);
        });
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }

    private void showDeleteConfirmationDialog(int position) {
        WishlistFolder folderToDelete = folders.get(position);

        if ("Default Wishlist".equals(folderToDelete.getName())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Can't delete");
            builder.setMessage("The default wishlist can't be deleted.");
            builder.setPositiveButton("OK", null);
            builder.show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete WishList");
        builder.setMessage("Are you sure you want to delete this wishlist? This will delete all the items in it.");
        builder.setPositiveButton("delete", (dialog, which) -> {

            folders.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, folders.size());


            if (context instanceof WishlistActivity) {
                ((WishlistActivity) context).saveFolders();
            }
        });
        builder.setNegativeButton("cancel", null);
        builder.show();
    }


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

