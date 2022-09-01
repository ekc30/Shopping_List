package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

// TODO: expand list items to have quantities, ratings etc.

public class MainActivity extends AppCompatActivity {

    private Button btnAddList;
    private RecyclerView listsRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialise components
        initComponents();

    }

    private void initComponents() {
        btnAddList = findViewById(R.id.btnAddList);
        listsRecView = findViewById(R.id.listsRecView);
    }
}