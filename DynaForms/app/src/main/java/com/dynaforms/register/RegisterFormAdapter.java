package com.dynaforms.register;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dynaforms.R;
import com.dynaforms.databinding.ChildRowCheckBoxBinding;
import com.dynaforms.databinding.ChildRowDateTimeBinding;
import com.dynaforms.databinding.ChildRowDropDownBinding;
import com.dynaforms.databinding.ChildRowPlainTextBinding;
import com.dynaforms.databinding.ChildRowTextAreaBinding;
import com.dynaforms.model.ChildList;
import com.dynaforms.utilities.AppConstants;

import java.util.List;

/**
 * Created by PC on 12/6/2017.
 */

public class RegisterFormAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = RegisterFormAdapter.class.getSimpleName();
    private Context context;
    private List<ChildList> childDataList;
    private IStatusClickCallback clickCallback;


    public RegisterFormAdapter(List<ChildList> toothList, Context context, IStatusClickCallback iStatusClickCallback) {
        this.childDataList = toothList;
        this.context = context;
        this.clickCallback = iStatusClickCallback;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        RecyclerView.ViewHolder vh;
        if (viewType == AppConstants.RowTypes.CheckBox.getValue()) {
            ChildRowCheckBoxBinding binding = DataBindingUtil.inflate(layoutInflater,
                    R.layout.child_row_check_box, parent, false);
            vh = new CheckBoxViewHolder(binding);
        } else if (viewType == AppConstants.RowTypes.TextArea.getValue()) {
            ChildRowTextAreaBinding binding = DataBindingUtil.inflate(layoutInflater,
                    R.layout.child_row_text_area, parent, false);
            vh = new TextAreaViewHolder(binding);
        } else if (viewType == AppConstants.RowTypes.DateTime.getValue()) {
            ChildRowDateTimeBinding binding = DataBindingUtil.inflate(layoutInflater,
                    R.layout.child_row_date_time, parent, false);
            vh = new DateTimeViewHolder(binding);
        } else if (viewType == AppConstants.RowTypes.DropDown.getValue()) {
            ChildRowDropDownBinding binding = DataBindingUtil.inflate(layoutInflater,
                    R.layout.child_row_drop_down, parent, false);
            vh = new DropDownViewHolder(binding);

        }
        else if (viewType == AppConstants.RowTypes.MultiSelect.getValue()) {
            ChildRowDateTimeBinding binding = DataBindingUtil.inflate(layoutInflater,
                    R.layout.child_row_date_time, parent, false);
            vh = new DateTimeViewHolder(binding);

        }else if (viewType == AppConstants.RowTypes.SingleSelect.getValue()) {
            ChildRowDateTimeBinding binding = DataBindingUtil.inflate(layoutInflater,
                    R.layout.child_row_date_time, parent, false);
            vh = new DateTimeViewHolder(binding);

        }else {
            ChildRowPlainTextBinding binding = DataBindingUtil.inflate(layoutInflater,
                    R.layout.child_row_plain_text, parent, false);
            vh = new PlainTextViewHolder(binding);
        }
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        String type = childDataList.get(position).getType();
        if (type.equals(AppConstants.RowTypes.TextArea.name())) {
            viewType = AppConstants.RowTypes.TextArea.getValue();
        } else if (type.equals(AppConstants.RowTypes.CheckBox.name())) {
            viewType = AppConstants.RowTypes.CheckBox.getValue();
        } else if (type.equals(AppConstants.RowTypes.DateTime.name())) {
            viewType = AppConstants.RowTypes.DateTime.getValue();
        } else if (type.equals(AppConstants.RowTypes.DropDown.name())) {
            viewType = AppConstants.RowTypes.DropDown.getValue();
        }else if (type.equals(AppConstants.RowTypes.MultiSelect.name())) {
            viewType = AppConstants.RowTypes.MultiSelect.getValue();
        }else if (type.equals(AppConstants.RowTypes.SingleSelect.name())) {
            viewType = AppConstants.RowTypes.SingleSelect.getValue();
        } else {
            viewType = AppConstants.RowTypes.PlainText.getValue();
        }
        return viewType;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChildList childList = childDataList.get(position);
        if (holder.getItemViewType() == AppConstants.RowTypes.TextArea.getValue()) {
            ((TextAreaViewHolder) holder).bind(childList, position);
        } else if (holder.getItemViewType() == AppConstants.RowTypes.CheckBox.getValue()) {
            ((CheckBoxViewHolder) holder).bind(childList, position);
        } else if (holder.getItemViewType() == AppConstants.RowTypes.DateTime.getValue()) {
            ((DateTimeViewHolder) holder).bind(childList, position);
        } else if (holder.getItemViewType() == AppConstants.RowTypes.DropDown.getValue()) {
            ((DropDownViewHolder) holder).bind(childList, position);
        } else if (holder.getItemViewType() == AppConstants.RowTypes.MultiSelect.getValue()) {
            ((DateTimeViewHolder) holder).bind(childList, position);
        } else if (holder.getItemViewType() == AppConstants.RowTypes.SingleSelect.getValue()) {
            ((DateTimeViewHolder) holder).bind(childList, position);
        }else {
            ((PlainTextViewHolder) holder).bind(childList, position);
        }
    }

    @Override
    public int getItemCount() {
        return childDataList.size();
    }

    public class PlainTextViewHolder extends RecyclerView.ViewHolder {
        private final ChildRowPlainTextBinding binding;

        public PlainTextViewHolder(ChildRowPlainTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final ChildList clinicalFindingsBean, int position) {
            binding.tvName.setText(clinicalFindingsBean.getName());
        }
    }

    public class TextAreaViewHolder extends RecyclerView.ViewHolder {
        private final ChildRowTextAreaBinding binding;

        public TextAreaViewHolder(ChildRowTextAreaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final ChildList clinicalFindingsBean, int position) {
            binding.tvName.setText(clinicalFindingsBean.getName());
        }
    }
    public class SingleSelectViewHolder extends RecyclerView.ViewHolder {
        private final ChildRowTextAreaBinding binding;

        public SingleSelectViewHolder(ChildRowTextAreaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final ChildList clinicalFindingsBean, int position) {
            binding.tvName.setText(clinicalFindingsBean.getName());
        }
    }

    public class DropDownViewHolder extends RecyclerView.ViewHolder {
        private final ChildRowDropDownBinding binding;

        public DropDownViewHolder(ChildRowDropDownBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final ChildList childList, int position) {
            binding.tvName.setText(childList.getName());

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,
                    R.layout.view_spinner, childList.getOptions());
            arrayAdapter.setDropDownViewResource(R.layout.view_spinner);
            binding.spinnerDropDown.setAdapter(arrayAdapter);
        }
    }

    public class DateTimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ChildRowDateTimeBinding binding;

        public DateTimeViewHolder(ChildRowDateTimeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.viewDob.setOnClickListener(this);
        }

        public void bind(final ChildList childList, int position) {
            binding.tvName.setText(childList.getName());

            if (!TextUtils.isEmpty(childList.getDisplayValue())) {
                binding.etDob.setText(childList.getDisplayValue());
            }
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            clickCallback.onClickStatus(getAdapterPosition(), childDataList.get(getAdapterPosition()).getType());

        }
    }

    public class CheckBoxViewHolder extends RecyclerView.ViewHolder {
        private final ChildRowCheckBoxBinding binding;

        public CheckBoxViewHolder(ChildRowCheckBoxBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final ChildList clinicalFindingsBean, int position) {
            binding.tvName.setText(clinicalFindingsBean.getName());
        }
    }


}
