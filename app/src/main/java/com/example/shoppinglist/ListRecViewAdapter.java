package com.example.shoppinglist;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListRecViewAdapter extends RecyclerView.Adapter<ListRecViewAdapter.ViewHolder> {

    private static final String TAG = "ListRecViewAdapter";

    public interface CallEditDialogInterface {
        void callEditDialog(int listIndex, int itemIndex);
    }

    private CallEditDialogInterface callEditDialogInterface;

    private Context context;

    private ArrayList<String> list;
    private int listIndex;

    public ListRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtListItem.setText(list.get(holder.getAdapterPosition()));

        holder.relLayoutListItemParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display dialog to edit or delete item
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Edit or delete " + list.get(holder.getAdapterPosition()));
                builder.setCancelable(true);
                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // edit item
                        callEditDialogInterface = (CallEditDialogInterface) context;
                        callEditDialogInterface.callEditDialog(listIndex, holder.getAdapterPosition());
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // ask for confirmation
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        builder1.setTitle("Delete " + list.get(holder.getAdapterPosition()) + "?");
                        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // delete item
                                if(list.size() > holder.getAdapterPosition()) {
                                    list.remove(holder.getAdapterPosition());
                                    Utils.getInstance(context).removeItemFromList(listIndex, holder.getAdapterPosition());
                                    notifyItemRemoved(holder.getAdapterPosition());
                                }
                            }
                        });
                        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // don't do anything
                            }
                        });
                        builder1.create().show();
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setElements(ArrayList<String> list, int listIndex) {
        this.list = list;
        this.listIndex = listIndex;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtListItem;
        private RelativeLayout relLayoutListItemParent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtListItem = itemView.findViewById(R.id.txtListItem);
            relLayoutListItemParent = itemView.findViewById(R.id.listItemParent);
        }
    }
}
