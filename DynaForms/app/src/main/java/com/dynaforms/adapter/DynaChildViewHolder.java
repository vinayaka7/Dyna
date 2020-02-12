package com.dynaforms.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.dynaforms.R;
import com.dynaforms.databinding.DynaRowChildBinding;
import com.dynaforms.itemclicklistners.RecyclerClickListener;
import com.dynaforms.model.ChildList;
import com.dynaforms.utilities.AppConstants;

import java.util.List;

public class DynaChildViewHolder extends ChildViewHolder implements View.OnClickListener {

    private RecyclerClickListener recyclerClickListener;
    private DynaRowChildBinding dynaRowChildBinding;

    public DynaChildViewHolder(DynaRowChildBinding dynaRowChildBinding) {
        super(dynaRowChildBinding.getRoot());
        this.dynaRowChildBinding = dynaRowChildBinding;
        dynaRowChildBinding.viewDob.setOnClickListener(this);
        dynaRowChildBinding.tvName.setOnClickListener(this);
        dynaRowChildBinding.arrowExpandImageview.setOnClickListener(this);
    }

    public void bind(ChildList childList) {
        String childListType = childList.getType();
        dynaRowChildBinding.arrowExpandImageview.setVisibility(childListType.equals(AppConstants.RowTypes.group.name()) ? View.VISIBLE : View.GONE);
        dynaRowChildBinding.tvName.setText(childList.getName());

        Context context = dynaRowChildBinding.getRoot().getContext();
        if (childListType.equals(AppConstants.RowTypes.DropDown.name())) {
            dynaRowChildBinding.spinnerDropDown.setVisibility(View.VISIBLE);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,
                    R.layout.view_spinner, childList.getOptions());
            arrayAdapter.setDropDownViewResource(R.layout.view_spinner);
            dynaRowChildBinding.spinnerDropDown.setAdapter(arrayAdapter);

        } else {
            dynaRowChildBinding.spinnerDropDown.setVisibility(View.GONE);
        }

        if (childListType.equals(AppConstants.RowTypes.DateTime.name())) {
            dynaRowChildBinding.dobLayout.setVisibility(View.VISIBLE);
            if (TextUtils.isEmpty(childList.getDisplayValue())) {
                dynaRowChildBinding.etDob.setText("");
            } else {
                dynaRowChildBinding.etDob.setText(childList.getDisplayValue());
            }
        } else {
            dynaRowChildBinding.dobLayout.setVisibility(View.GONE);
        }

        if (childListType.equals(AppConstants.RowTypes.TextArea.name())) {
            dynaRowChildBinding.etName.setVisibility(View.VISIBLE);
        } else {
            dynaRowChildBinding.etName.setVisibility(View.GONE);
        }

        if (childListType.equals(AppConstants.RowTypes.MultiSelect.name())) {
            dynaRowChildBinding.multiSelectionLayout.setVisibility(View.VISIBLE);

            dynaRowChildBinding.multiSelectionLayout.removeAllViews();
            List<String> options = childList.getOptions();

            for (int i = 0; i < options.size(); i++) {
                CheckBox checkBox = new CheckBox(context);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                checkBox.setLayoutParams(layoutParams);

                checkBox.setText(options.get(i));
                dynaRowChildBinding.multiSelectionLayout.addView(checkBox);
            }
        } else {
            dynaRowChildBinding.multiSelectionLayout.setVisibility(View.GONE);
        }
    }

    public void setRecyclerClickListener(RecyclerClickListener recyclerClickListener) {
        this.recyclerClickListener = recyclerClickListener;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.view_dob) {
            if (recyclerClickListener != null) {
                recyclerClickListener.onClickView(getParentAdapterPosition(), getChildAdapterPosition());
            }
        } else {
            if (recyclerClickListener != null) {
                recyclerClickListener.onItemClick(getParentAdapterPosition(), getChildAdapterPosition());
            }
        }
    }
}