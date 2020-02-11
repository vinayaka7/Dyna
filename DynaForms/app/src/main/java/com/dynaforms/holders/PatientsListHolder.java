package com.dynaforms.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dynaforms.R;
import com.dynaforms.itemclicklistners.PatientsItemClickListener;

import androidx.recyclerview.widget.RecyclerView;


public class PatientsListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tv_patient_name;
    PatientsItemClickListener eventItemClickListener;

    public PatientsListHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        tv_patient_name = (TextView) itemView.findViewById(R.id.tv_patient_name);

    }

    @Override
    public void onClick(View view) {
        this.eventItemClickListener.onItemClick(view, getLayoutPosition());
    }

    public void setItemClickListener(PatientsItemClickListener ic) {
        this.eventItemClickListener = ic;
    }
}
