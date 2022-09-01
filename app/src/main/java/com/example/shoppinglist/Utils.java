package com.example.shoppinglist;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Utils {

    private Context context;
    private SharedPreferences sharedPreferences;
    private static Utils instance;

    // private constructor
    private Utils(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("listDB", Context.MODE_PRIVATE);

    }

    // get the single instance of the class
    public static Utils getInstance(Context context) {
        if(instance == null) {
            instance = new Utils(context);
        }
        return instance;
    }

    public ArrayList<List> getLists() {
        Gson gson = new Gson();

    }
}
