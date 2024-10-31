package com.example.loginapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class WishlistFolder implements Parcelable {
    private String name;
    private ArrayList<WishlistItem> items;
    // transient keyword prevents this field from being serialized by Gson
    private transient boolean isExpanded;

    // Constructor for creating a new folder
    public WishlistFolder(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.isExpanded = false;
    }

    // Parcel constructor for recreating object from Parcel
    protected WishlistFolder(Parcel in) {
        name = in.readString();
        items = in.createTypedArrayList(WishlistItem.CREATOR);
        isExpanded = in.readByte() != 0;
    }

    // Implementation of Parcelable methods
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

    // Parcelable CREATOR for creating instances of this class from a Parcel
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

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ArrayList<WishlistItem> getItems() { return items; }
    public boolean isExpanded() { return isExpanded; }
    public void setExpanded(boolean expanded) { isExpanded = expanded; }
    public void addItem(WishlistItem item) { items.add(item); }
}