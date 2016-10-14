package com.example.dell.es.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    private String name;
    private String description;
    private String price;
    private String imageUri;


    public Item(){

    }

    public Item(String name, String description, String price, String imageUri) {
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setImageUri(imageUri);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    private void setPrice(String price) {
        this.price = price;
    }

    public String getImageUri() {
        return imageUri;
    }

    private void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    protected Item(Parcel in) {
        name = in.readString();
        description = in.readString();
        price = in.readString();
        imageUri = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(price);
        dest.writeString(imageUri);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}