package com.example.shoppinglist;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

// model class for the lists
public class List {

    private ArrayList<String> list;
    private String name, description;

    public List(ArrayList<String> list, String name, String description) {
        this.name = name;
        this.list = list;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
