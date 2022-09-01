package com.example.shoppinglist;

import android.content.Context;
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

    private Context context;

    private ArrayList<List> lists = new ArrayList<>();

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
        // navigate user to view items in selected list
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
