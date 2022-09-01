package com.example.shoppinglist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class NewListDialog extends DialogFragment {
    public interface PassNewListDataInterface {
        void getNewListData(String name, String description);
    }

    private PassNewListDataInterface passNewListDataInterface;

    private Button btnCancel, btnOK;
    private EditText edtTxtNewListName;
    private CheckBox checkBoxDatabaseList;

    private ArrayList<List> lists;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.new_list_dialog, null);
        initViews(view);

        lists = Utils.getInstance(getActivity()).getLists();

        // create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Enter new list details")
                .setView(view);

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
                // create a List item and add it to lists
                String listName = String.valueOf(edtTxtNewListName.getText());
                if(!listName.equals("")) {
                    if(!Utils.getInstance(getActivity()).containsListWithName(listName)) {
                        passNewListDataInterface = (PassNewListDataInterface) getActivity();
                        passNewListDataInterface.getNewListData(listName, "");
                        dismiss();
                    } else {
                        Toast.makeText(getActivity(), "A list with this name already exists!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please enter a list name!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return builder.create();
    }

    private void initViews(View view) {
        btnCancel = view.findViewById(R.id.btnNewListDialogCancel);
        btnOK = view.findViewById(R.id.btnNewListDialogAddList);
        edtTxtNewListName = view.findViewById(R.id.edtTxtNewListName);
        checkBoxDatabaseList = view.findViewById(R.id.checkboxNewListDialogDataBaseList);
    }
}
