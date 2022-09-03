package com.example.shoppinglist;

import static com.example.shoppinglist.ListsDisplayerRecyclerViewAdapter.LIST_INDEX_TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity implements ListRecViewAdapter.CallEditDialogInterface, EditListItemDialog.PassEditedItemInterface {

    public static final String EDIT_ITEM_KEY_STRING = "editItemKeyString";
    public static final String EDIT_ITEM_KEY_LIST_INDEX = "editItemKeyListIndex";
    public static final String EDIT_ITEM_KEY_ITEM_INDEX = "editItemKeyItemIndex";

    private Button btnAdd;
    private TextView txtListName;
    private EditText edtTxtNewItem;
    private RecyclerView listItemsRecView;

    private ListRecViewAdapter adapter;
    private List list;
    private int listIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initViews();
        Intent intent = getIntent();
        listIndex = intent.getIntExtra(LIST_INDEX_TAG, -1);
        if(listIndex != -1) {
            list = Utils.getInstance(this).getLists().get(listIndex);
        }

        txtListName.setText(list.getName());

        adapter = new ListRecViewAdapter(this);
        listItemsRecView.setAdapter(adapter);
        listItemsRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setElements(list.getList(), listIndex);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add item to the list
                String newItem = edtTxtNewItem.getText().toString();
                if(!newItem.equals("")) {
                    if(!list.getList().contains(newItem)) {
                        if(list.getList().add(newItem)) {
                            // Toast.makeText(ListActivity.this, "Item added", Toast.LENGTH_SHORT).show();
                            Utils.getInstance(ListActivity.this).addItemToList(listIndex, newItem);
                            adapter.notifyItemInserted(list.getList().size());
                            edtTxtNewItem.setText("");
                        }
                    } else {
                        Toast.makeText(ListActivity.this, "List already contains item", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ListActivity.this, "Please enter a new item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        btnAdd = findViewById(R.id.btnAddListItem);
        txtListName = findViewById(R.id.txtListActivityListName);
        edtTxtNewItem = findViewById(R.id.edtTxtNewItem);
        listItemsRecView = findViewById(R.id.listDisplayRecView);
    }

    @Override
    public void callEditDialog(int listIndex, int itemIndex) {
        EditListItemDialog editListItemDialog = new EditListItemDialog();
        Bundle bundle = new Bundle();
        bundle.putString(EDIT_ITEM_KEY_STRING, list.getList().get(itemIndex));
        bundle.putInt(EDIT_ITEM_KEY_LIST_INDEX, listIndex);
        bundle.putInt(EDIT_ITEM_KEY_ITEM_INDEX, itemIndex);
        editListItemDialog.setArguments(bundle);
        editListItemDialog.show(getSupportFragmentManager(), "edit item dialog");
    }

    @Override
    public void passNewItemName(String name, int itemIndex) {
        list.getList().set(itemIndex, name);
        Utils.getInstance(this).changeItemName(listIndex, itemIndex, name);
        adapter.notifyItemChanged(itemIndex);
    }
}