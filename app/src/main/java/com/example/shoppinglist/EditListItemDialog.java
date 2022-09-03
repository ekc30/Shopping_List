package com.example.shoppinglist;

import static com.example.shoppinglist.ListActivity.EDIT_ITEM_KEY_ITEM_INDEX;
import static com.example.shoppinglist.ListActivity.EDIT_ITEM_KEY_LIST_INDEX;
import static com.example.shoppinglist.ListActivity.EDIT_ITEM_KEY_STRING;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class EditListItemDialog extends DialogFragment {

    public interface PassEditedItemInterface {
        void passNewItemName(String name, int itemIndex);
    }

    private PassEditedItemInterface passEditedItemInterface;

    private EditText edtTxt;
    private Button btnCancel, btnOK;

    private ArrayList<List> lists;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.edit_list_item_dialog, null);
        initViews(view);
        lists = Utils.getInstance(getActivity()).getLists();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Edit ")
                .setView(view);

        // get the item to be edited
        Bundle bundle = getArguments();
        String item = bundle.getString(EDIT_ITEM_KEY_STRING, null);
        if(item != null) {
            builder.setTitle("Edit " + item);
        }
        // get the list index
        int listIndex = bundle.getInt(EDIT_ITEM_KEY_LIST_INDEX);
        int itemIndex = bundle.getInt(EDIT_ITEM_KEY_ITEM_INDEX);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cancel the dialog
                dismiss();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newItemName = edtTxt.getText().toString();
                if(!newItemName.equals("")) {
                    if(!lists.get(listIndex).getList().contains(newItemName)) {
                        // pass the new name of the item to the calling activity
                        passEditedItemInterface = (PassEditedItemInterface) getActivity();
                        passEditedItemInterface.passNewItemName(newItemName, itemIndex);
                        dismiss();
                    } else {
                        Toast.makeText(getActivity(), "Another item with this name already exists!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please enter a new name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return builder.create();
    }

    private void initViews(View view) {
        edtTxt = view.findViewById(R.id.edtTxtEditItem);
        btnCancel = view.findViewById(R.id.btnEditItemCancel);
        btnOK = view.findViewById(R.id.btnEditItemOK);
    }
}
