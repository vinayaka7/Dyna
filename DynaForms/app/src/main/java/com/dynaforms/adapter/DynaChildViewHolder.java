package com.dynaforms.adapter;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.dynaforms.R;
import com.dynaforms.itemclicklistners.RecyclerClickListener;
import com.dynaforms.model.ChildList;

public class DynaChildViewHolder extends ChildViewHolder {

    private TextView mChildTextView;
    private RecyclerClickListener recyclerClickListener;

    public DynaChildViewHolder(View itemView) {
        super(itemView);
        mChildTextView = itemView.findViewById(R.id.tv_name);
        mChildTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recyclerClickListener != null) {
                    recyclerClickListener.onItemClick(getParentAdapterPosition(), getChildAdapterPosition());
                }
            }
        });
    }

    public void bind(ChildList childList) {
        mChildTextView.setText(childList.getName());
    }

    public void setRecyclerClickListener(RecyclerClickListener recyclerClickListener) {
        this.recyclerClickListener = recyclerClickListener;
    }

    public RecyclerClickListener getRecyclerClickListener() {
        return recyclerClickListener;
    }
}