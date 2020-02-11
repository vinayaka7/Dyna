package com.dynaforms.filters;

import android.widget.Filter;

import com.dynaforms.model.ForamsModel;
import com.dynaforms.model.PatientsModel;
import com.dynaforms.view.ForamsDisplayAdapter;
import com.dynaforms.view.PatientsListAdapter;

import java.util.ArrayList;


public class ForamsDisplayList extends Filter {

    ForamsDisplayAdapter adapter;
    ArrayList<ForamsModel> filterList;

    public ForamsDisplayList(ArrayList<ForamsModel> filterList, ForamsDisplayAdapter adapter) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();
            ArrayList<ForamsModel> filteredPlayers = new ArrayList<ForamsModel>();
            for (int i = 0; i < filterList.size(); i++) {
                if (filterList.get(i).getName().toUpperCase().contains(constraint)) {
                    filteredPlayers.add(filterList.get(i));
                }
            }
            results.count = filteredPlayers.size();
            results.values = filteredPlayers;
        } else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.listModels = (ArrayList<ForamsModel>) results.values;
        adapter.notifyDataSetChanged();
    }
}
