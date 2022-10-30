package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

// TODO: expand list items to have quantities, ratings etc.

public class MainActivity extends AppCompatActivity implements NewListDialog.PassNewListDataInterface {

    private Button btnAddList;
    private RecyclerView listsRecView;
    private ListsDisplayerRecyclerViewAdapter adapter;

    private ArrayList<List> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialise components
        initComponents();
        lists = Utils.getInstance(this).getLists();

        // setup the recycler view
        adapter = new ListsDisplayerRecyclerViewAdapter(this);
        listsRecView.setAdapter(adapter);
        listsRecView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter.setLists(lists);

        btnAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add new List to list of Lists
                // display dialog to name the list and select whether is should be a database list
                NewListDialog newListDialog = new NewListDialog();
                newListDialog.show(getSupportFragmentManager(), "new list dialog");
            }
        });
    }

    private void initComponents() {
        btnAddList = findViewById(R.id.btnAddList);
        listsRecView = findViewById(R.id.listsRecView);
    }

    @Override
    public void getNewListData(String name, String description) {
        List newList = new List(new ArrayList<>(), name, description);
        if(lists.add(newList)) {
            Utils.getInstance(this).addList(newList);
            // TODO: fix this - not the ideal solution, notify doesn't seem to work for a single
            //  item, need to reset the whole list
            // adapter.notifyItemInserted(lists.size());
            adapter.setLists(lists);
            Toast.makeText(this, "List created", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "List could not be created", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        lists = Utils.getInstance(this).getLists();
        // TODO: not the ideal solution, notify doesn't seem to work with a single item
        adapter.setLists(lists);
        if(adapter.getListIndex() != -1) {
            Toast.makeText(this, "List with index " + adapter.getListIndex() + " modified", Toast.LENGTH_SHORT).show();
            adapter.notifyItemChanged(adapter.getListIndex());
            adapter.setListIndex(-1);
        }
    }
}