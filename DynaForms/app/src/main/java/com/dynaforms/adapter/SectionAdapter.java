package com.dynaforms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.dynaforms.R;
import com.dynaforms.databinding.ChildRowPlainTextBinding;
import com.dynaforms.databinding.DynaRowChildBinding;
import com.dynaforms.itemclicklistners.RecyclerClickListener;
import com.dynaforms.model.ChildList;
import com.dynaforms.model.Section;

import java.util.List;

public class SectionAdapter extends ExpandableRecyclerAdapter<Section, ChildList, DynaParentViewHolder, DynaChildViewHolder> {

    private LayoutInflater mInflater;
    private RecyclerClickListener recyclerClickListener;

    public SectionAdapter(Context context, @NonNull List<Section> recipeList) {
        super(recipeList);
        mInflater = LayoutInflater.from(context);
    }

    // onCreate ...
    @Override
    public DynaParentViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View recipeView = mInflater.inflate(R.layout.dyna_row_parent, parentViewGroup, false);
        return new DynaParentViewHolder(recipeView);
    }

    @Override
    public DynaChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        DynaRowChildBinding binding = DataBindingUtil.inflate(mInflater,
                R.layout.dyna_row_child, childViewGroup, false);
        DynaChildViewHolder dynaChildViewHolder = new DynaChildViewHolder(binding);
        dynaChildViewHolder.setRecyclerClickListener(recyclerClickListener);

        return dynaChildViewHolder;
    }

    // onBind ...
    @Override
    public void onBindParentViewHolder(@NonNull DynaParentViewHolder parentViewHolder, int parentPosition, @NonNull Section recipe) {
        parentViewHolder.bind(recipe);
    }

    @Override
    public void onBindChildViewHolder(@NonNull DynaChildViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull ChildList ingredient) {
        childViewHolder.bind(ingredient);
    }


    public void setRecyclerClickListener(RecyclerClickListener recyclerClickListener) {
        this.recyclerClickListener = recyclerClickListener;
    }

    public RecyclerClickListener getRecyclerClickListener() {
        return recyclerClickListener;
    }
}