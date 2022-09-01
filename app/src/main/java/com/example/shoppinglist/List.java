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

    // what does this do exactly?
    private List(Parcel in) {
        in.readList(this.list, List.class.getClassLoader());
        this.description = in.readString();
    }

    // what is this?
    public static final Creator<List> CREATOR = new Creator<List>() {
        @Override
        public List createFromParcel(Parcel parcel) {
            return new List(parcel);
        }

        @Override
        public List[] newArray(int i) {
            return new List[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.list);
        parcel.writeString(this.description);
    }
}
