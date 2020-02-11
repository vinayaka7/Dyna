package com.dynaforms.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.dynaforms.filters.PatientsFilterList;
import com.dynaforms.holders.PatientsListHolder;
import com.dynaforms.model.PatientsModel;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


public class PatientsListAdapter extends RecyclerView.Adapter<PatientsListHolder> implements Filterable {

    public ArrayList<PatientsModel> listModels, filterList;
    PatientsList context;
    LayoutInflater li;
    int resource;
    Typeface typeface, typeface2;
    private boolean checkInternet;
    PatientsFilterList filter;
    SharedPreferences prfs1;
    String token = "", username = "", password = "", latitude = "", longitude = "", locationname = "";
    String[] separated;
    SharedPreferences prfs;
    private int lastPosition = -1;
    Typeface face, face1, face2;
    private long lastClickTime = 0;

    public PatientsListAdapter(ArrayList<PatientsModel> listModels, PatientsList context, int resource) {
        this.listModels = listModels;
        this.context = context;
        this.resource = resource;
        this.filterList = listModels;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public PatientsListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = li.inflate(resource, parent, false);
        PatientsListHolder slh = new PatientsListHolder(layout);
        return slh;
    }

    @Override
    public void onBindViewHolder(final PatientsListHolder holder, final int position) {

        String in_name = listModels.get(position).getPatient_name().substring(0, 1).toUpperCase() + listModels.get(position).getPatient_name().substring(1);
        holder.tv_patient_name.setText(in_name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
            filter = new PatientsFilterList(filterList, this);
        }
        return filter;
    }

}
