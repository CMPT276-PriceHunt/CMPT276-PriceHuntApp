package com.example.cmpt276_pricehuntapp;

import android.os.Parcel;
import android.os.Parcelable;

public class WishlistItem implements Parcelable {
    private String imagePath;
    private String name;
    private double price;

    public WishlistItem(String imagePath, String name, double price) {
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
    }

    protected WishlistItem(Parcel in) {
        imagePath = in.readString();
        name = in.readString();
        price = in.readDouble();
    }

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

    public String getImagePath() { return imagePath; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}