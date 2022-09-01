package com.example.shoppinglist;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    public static final String LIST_KEY = "lists";
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

    // get all the lists stored in the app
    public ArrayList<List> getLists() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<List>>(){}.getType();
        return gson.fromJson(sharedPreferences.getString(LIST_KEY, null), type);
    }


}
