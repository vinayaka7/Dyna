package com.dynaforms.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dynaforms.R;
import com.dynaforms.database.PatientsDbHelper;
import com.dynaforms.model.PatientsModel;

import java.util.ArrayList;
import java.util.List;

public class PatientsList extends AppCompatActivity implements View.OnClickListener {
    RecyclerView events_recyclerview;
    ArrayList<PatientsModel> patientsModels = new ArrayList<PatientsModel>();
    PatientsListAdapter eventsAdapter;
    LinearLayoutManager layoutManager;
    PatientsDbHelper patientsDbHelper;
    SearchView event_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_list);
        patientsDbHelper = new PatientsDbHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        event_search = (SearchView) findViewById(R.id.event_search);
        events_recyclerview = (RecyclerView) findViewById(R.id.rv_patients);
        eventsAdapter = new PatientsListAdapter(patientsModels, this, R.layout.row_patients_list);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.scrollToPositionWithOffset(0, patientsModels.size());
        events_recyclerview.setLayoutManager(layoutManager);
        events_recyclerview.setItemAnimator(new DefaultItemAnimator());
        events_recyclerview.setNestedScrollingEnabled(false);
        events_recyclerview.setHasFixedSize(false);
        events_recyclerview.setAdapter(eventsAdapter);
        event_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                eventsAdapter.getFilter().filter(query);
                return false;
            }
        });
        patientsList();
    }
    private void patientsList() {

        List<String> patient_name = patientsDbHelper.getPatientName();
        if (patient_name.size() > 0) {

            List<String> password = patientsDbHelper.getPassword();
            for (int i = 0; i < patient_name.size(); i++) {
                PatientsModel em = new PatientsModel();
                em.setPatient_name(patient_name.get(i));
                em.setPassword(password.get(i));
                patientsModels.add(em);
            }
            events_recyclerview.setAdapter(eventsAdapter);
        } else {
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.patients_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.add:
                showDialog(this);
                return true;
                case R.id.logout:
                    SharedPreferences settings = getSharedPreferences("USERLOGIN", Context.MODE_PRIVATE);
                    settings.edit().clear().commit();
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                return true;
                case R.id.settings:
                    Intent s_intent = new Intent(this, SettingsActivity.class);
                    startActivity(s_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


        public void showDialog(final Activity activity){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.add_patients_dialog);
            final EditText et_username = (EditText) dialog.findViewById(R.id.et_username);
            final EditText et_password = (EditText) dialog.findViewById(R.id.et_password);
            Button btn_create = (Button) dialog.findViewById(R.id.btn_create);
            Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
            btn_create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et_username.getText().toString().equals("")){
                        Toast.makeText(activity, "Please add patient name", Toast.LENGTH_SHORT).show();
                    }else if (et_password.getText().toString().equals("")){
                        Toast.makeText(activity, "Please set password", Toast.LENGTH_SHORT).show();
                    }else {
                        patientsDbHelper.emptyDBBucket();
                        ContentValues values = new ContentValues();
                        values.put(PatientsDbHelper.PATIENT_NAME, et_username.getText().toString());
                        values.put(PatientsDbHelper.PASSWORD, et_password.getText().toString());
                        patientsDbHelper.addPatientsList(values);
                        patientsList();
                        dialog.dismiss();
                    }

                }
            });
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }

}
