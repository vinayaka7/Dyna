package com.dynaforms.holders;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dynaforms.R;
import com.dynaforms.itemclicklistners.PatientsItemClickListener;

import androidx.recyclerview.widget.RecyclerView;


public class ForamsDisplayHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tv_forams_name,child_item1,child_item2;
    PatientsItemClickListener eventItemClickListener;
    public LinearLayout child_layout;

    public ForamsDisplayHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        tv_forams_name = (TextView) itemView.findViewById(R.id.tv_forams_name);
        child_item1 = (TextView) itemView.findViewById(R.id.child_item1);
        child_item2 = (TextView) itemView.findViewById(R.id.child_item2);
        child_layout = (LinearLayout) itemView.findViewById(R.id.child_layout);

    }

    @Override
    public void onClick(View view) {
        this.eventItemClickListener.onItemClick(view, getLayoutPosition());
    }

    public void setItemClickListener(PatientsItemClickListener ic) {
        this.eventItemClickListener = ic;
    }
}
