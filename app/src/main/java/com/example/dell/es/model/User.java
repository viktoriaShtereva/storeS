package com.example.dell.es.model;

import android.os.Parcel;
import android.os.Parcelable;


public class User implements Parcelable {

    private String name;
    private String address;
    private String tel;
    private String itemName;

    public User(String name, String address, String tel, String itemName) {
        this.setName( name);
        this.setAddress(address);
        this.setTel(tel);
        this.setItemName(itemName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    protected User(Parcel in) {
        name = in.readString();
        address = in.readString();
        tel = in.readString();
        itemName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(tel);
        dest.writeString(itemName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}