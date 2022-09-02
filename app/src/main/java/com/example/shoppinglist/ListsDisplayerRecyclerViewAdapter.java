package com.example.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class ListsDisplayerRecyclerViewAdapter extends RecyclerView.Adapter<ListsDisplayerRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "ListsDisplayerRecyclerV";

    public static final String LIST_INDEX_TAG = "list_index";

    private Context context;

    private ArrayList<List> lists = new ArrayList<>();

    private int listIndex = -1;

    public ListsDisplayerRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lists_display_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // set the ViewHolders to display information about each list
        holder.txtListName.setText(lists.get(holder.getAdapterPosition()).getName());
        holder.txtListDescription.setText(lists.get(holder.getAdapterPosition()).getDescription());
        holder.txtListLength.setText(String.valueOf(lists.get(holder.getAdapterPosition()).getList().size()));

        // navigate user to view items in selected list
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListActivity.class);
                intent.putExtra(LIST_INDEX_TAG, holder.getAdapterPosition());
                listIndex = holder.getAdapterPosition();
                context.startActivity(intent);
            }
        });

        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // display dialog where the user can rename or delete the selected list
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    // set the lists to be displayed in the recycler view
    public void setLists(ArrayList<List> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public int getListIndex() {
        return listIndex;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialCardView parent;
        private TextView txtListName, txtListDescription, txtListLength;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            txtListName = itemView.findViewById(R.id.txtListName);
            txtListDescription = itemView.findViewById(R.id.txtDescription);
            txtListLength = itemView.findViewById(R.id.txtListLength);
        }
    }
}
