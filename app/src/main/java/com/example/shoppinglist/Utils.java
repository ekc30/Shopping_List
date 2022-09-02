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

        if(getLists() == null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            editor.putString(LIST_KEY, gson.toJson(new ArrayList<List>()));
            editor.commit();
        }
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

    public boolean addList(List list) {
        ArrayList<List> lists = getLists();
        if(lists != null) {
            if(lists.add(list)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(LIST_KEY);
                editor.putString(LIST_KEY, gson.toJson(lists));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public void removeList(int listIndex) {
        // TODO: finish
    }

    public boolean containsListWithName(String name) {
        ArrayList<List> lists = getLists();
        for(List l : lists) {
            if(l.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean addItemToList(int listIndex, String item) {
        ArrayList<List> lists = getLists();
        if(lists != null) {
            if(lists.size() > listIndex && listIndex > -1) {
                if(lists.get(listIndex) != null) {
                    if(lists.get(listIndex).getList().add(item)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(LIST_KEY);
                        editor.putString(LIST_KEY, gson.toJson(lists));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void removeItemFromList(int listIndex, int itemIndex) {
        ArrayList<List> lists = getLists();
        if(lists != null) {
            if(listIndex < lists.size()) {
                if(lists.get(listIndex) != null) {
                    if(itemIndex < lists.get(listIndex).getList().size()) {
                        lists.get(listIndex).getList().remove(itemIndex);
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(LIST_KEY);
                        editor.putString(LIST_KEY, gson.toJson(lists));
                        editor.commit();
                    }
                }
            }
        }
    }
}
