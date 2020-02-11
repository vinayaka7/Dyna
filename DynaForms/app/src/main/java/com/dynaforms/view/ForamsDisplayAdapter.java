package com.dynaforms.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;

import com.dynaforms.R;
import com.dynaforms.filters.ForamsDisplayList;
import com.dynaforms.holders.ForamsDisplayHolder;
import com.dynaforms.holders.PatientsListHolder;
import com.dynaforms.model.ForamsModel;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


public class ForamsDisplayAdapter extends RecyclerView.Adapter<ForamsDisplayHolder> implements Filterable {

    public ArrayList<ForamsModel> listModels, filterList;
    ForamsDisplayActivity context;
    LayoutInflater li;
    int resource;
    ForamsDisplayList filter;
   // private static int currentPosition = 0;

    public ForamsDisplayAdapter(ArrayList<ForamsModel> listModels, ForamsDisplayActivity context, int resource) {
        this.listModels = listModels;
        this.context = context;
        this.resource = resource;
        this.filterList = listModels;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public ForamsDisplayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = li.inflate(resource, parent, false);
        ForamsDisplayHolder slh = new ForamsDisplayHolder(layout);
        return slh;
    }

    @Override
    public void onBindViewHolder(final ForamsDisplayHolder holder, final int position) {

        String in_name = listModels.get(position).getName().substring(0, 1).toUpperCase() + listModels.get(position).getName().substring(1);
        holder.tv_forams_name.setText(in_name);
/*        holder.child_item1.setText(listModels.get(0).getChild_list_item1());
        holder.child_item2.setText(listModels.get(0).getChild_list_item2());*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if (holder.child_layout.getVisibility() == View.GONE){
                 Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

                 //toggling visibility
                 holder.child_layout.setVisibility(View.VISIBLE);

                 //adding sliding effect
                 holder.child_layout.startAnimation(slideDown);
             }
             else {
                 holder.child_layout.setVisibility(View.GONE);
             }
                    //creating an animation

                /*holder.child_layout.setVisibility((holder.child_layout.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);*/

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listModels.size();
    }

    static public String firstLetterCaps(String data) {
        String firstLetter = data.substring(0, 1).toUpperCase();
        String restLetters = data.substring(1).toLowerCase();
        return firstLetter + restLetters;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new ForamsDisplayList(filterList, this);
        }
        return filter;
    }

}
