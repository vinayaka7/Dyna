package com.dynaforms.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.dynaforms.R;
import com.dynaforms.model.Section;

public class DynaParentViewHolder extends ParentViewHolder {

    private TextView mParentTextView;
    private ImageView mArrowExpandImageView;

    public DynaParentViewHolder(View itemView) {
        super(itemView);
        mParentTextView = itemView.findViewById(R.id.tv_name);
        mArrowExpandImageView = (ImageView) itemView.findViewById(R.id.arrow_expand_imageview);
        mArrowExpandImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded()) {
                    collapseView();
                } else {
                    expandView();
                }
            }
        });
    }

    public void bind(Section recipe) {
        mParentTextView.setText(recipe.getName());
    }

    /*@Override
    public boolean shouldItemViewClickToggleExpansion() {
        return false;
    }*/
}