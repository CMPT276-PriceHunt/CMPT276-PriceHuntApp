package com.example.loginapp;

import android.os.Parcel;
import android.os.Parcelable;

public class WishlistItem implements Parcelable {
    // Basic properties of a wishlist item
    private String imagePath;  // Path to item's image
    private String name;       // Name of the item
    private double price;      // Price of the item

    // Standard constructor for creating new items
    public WishlistItem(String imagePath, String name, double price) {
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
    }

    // Parcel constructor for recreating object
    protected WishlistItem(Parcel in) {
        imagePath = in.readString();
        name = in.readString();
        price = in.readDouble();
    }

    // Parcelable implementation
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imagePath);
        dest.writeString(name);
        dest.writeDouble(price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Parcelable CREATOR for object creation from Parcel
    public static final Creator<WishlistItem> CREATOR = new Creator<WishlistItem>() {
        @Override
        public WishlistItem createFromParcel(Parcel in) {
            return new WishlistItem(in);
        }

        @Override
        public WishlistItem[] newArray(int size) {
            return new WishlistItem[size];
        }
    };

    // Getters for item properties
    public String getImagePath() { return imagePath; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}