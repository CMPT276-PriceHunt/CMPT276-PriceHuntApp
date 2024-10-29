package com.example.cmpt276_pricehuntapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cmpt276_pricehuntapp.R;
import com.example.cmpt276_pricehuntapp.WishlistFolder;
import java.util.ArrayList;

public class WishlistFolderAdapter extends RecyclerView.Adapter<WishlistFolderAdapter.FolderViewHolder> {
    private ArrayList<WishlistFolder> folders;

    public WishlistFolderAdapter(ArrayList<WishlistFolder> folders) {
        this.folders = folders;
    }

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wishlist_folder, parent, false);
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
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }

    static class FolderViewHolder extends RecyclerView.ViewHolder {
        TextView tvFolderName;
        ImageButton btnExpand;
        RecyclerView rvItems;

        public FolderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFolderName = itemView.findViewById(R.id.tv_folder_name);
            btnExpand = itemView.findViewById(R.id.btn_expand);
            rvItems = itemView.findViewById(R.id.rv_items);
        }
    }
}
