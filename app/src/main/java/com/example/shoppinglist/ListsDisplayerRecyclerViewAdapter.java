package com.example.shoppinglist;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

public class ListsDisplayerRecyclerViewAdapter {

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
