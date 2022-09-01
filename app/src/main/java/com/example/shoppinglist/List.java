package com.example.shoppinglist;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

// model class for the lists
public class List implements Parcelable {

    private ArrayList<String> list;
    private String description;

    public List(ArrayList<String> list, String description) {
        this.list = list;
        this.description = description;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "List{" +
                "list=" + list +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.write
    }
}
