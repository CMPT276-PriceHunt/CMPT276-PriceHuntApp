package com.example.cmpt276_pricehuntapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class WishlistFolder implements Parcelable {
    private String name;
    private ArrayList<WishlistItem> items;
    private transient boolean isExpanded; // transient so it won't be serialized by Gson

    public WishlistFolder(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.isExpanded = false;
    }

    protected WishlistFolder(Parcel in) {
        name = in.readString();
        items = in.createTypedArrayList(WishlistItem.CREATOR);
        isExpanded = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(items);
        dest.writeByte((byte) (isExpanded ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WishlistFolder> CREATOR = new Creator<WishlistFolder>() {
        @Override
        public WishlistFolder createFromParcel(Parcel in) {
            return new WishlistFolder(in);
        }

        @Override
        public WishlistFolder[] newArray(int size) {
            return new WishlistFolder[size];
        }
    };

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ArrayList<WishlistItem> getItems() { return items; }
    public boolean isExpanded() { return isExpanded; }
    public void setExpanded(boolean expanded) { isExpanded = expanded; }
    public void addItem(WishlistItem item) { items.add(item); }
}