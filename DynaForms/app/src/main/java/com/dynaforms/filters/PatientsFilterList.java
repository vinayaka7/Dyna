package com.dynaforms.filters;

import android.widget.Filter;

import com.dynaforms.model.PatientsModel;
import com.dynaforms.view.PatientsListAdapter;

import java.util.ArrayList;


public class PatientsFilterList extends Filter {

    PatientsListAdapter adapter;
    ArrayList<PatientsModel> filterList;

    public PatientsFilterList(ArrayList<PatientsModel> filterList, PatientsListAdapter adapter) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();
            ArrayList<PatientsModel> filteredPlayers = new ArrayList<PatientsModel>();
            for (int i = 0; i < filterList.size(); i++) {
                if (filterList.get(i).getPatient_name().toUpperCase().contains(constraint)) {
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
        adapter.listModels = (ArrayList<PatientsModel>) results.values;
        adapter.notifyDataSetChanged();
    }
}
