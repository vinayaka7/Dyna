package com.dynaforms.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.dynaforms.R;
import com.dynaforms.adapter.SectionAdapter;
import com.dynaforms.database.PatientsDbHelper;
import com.dynaforms.itemclicklistners.RecyclerClickListener;
import com.dynaforms.model.BaseFormModel;
import com.dynaforms.model.ForamsModel;
import com.dynaforms.model.Section;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ForamsDisplayActivity extends AppCompatActivity {
    ArrayList<ForamsModel> foramsModelArrayList = new ArrayList<ForamsModel>();
    RecyclerView events_recyclerview;
    SectionAdapter eventsAdapter;
    LinearLayoutManager layoutManager;
    SearchView event_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forams_display);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        event_search = (SearchView) findViewById(R.id.event_search);
        events_recyclerview = findViewById(R.id.rv_patients);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.scrollToPositionWithOffset(0, foramsModelArrayList.size());
        events_recyclerview.setLayoutManager(layoutManager);
        events_recyclerview.setItemAnimator(new DefaultItemAnimator());
        events_recyclerview.setNestedScrollingEnabled(false);
        events_recyclerview.setHasFixedSize(false);
        events_recyclerview.setAdapter(eventsAdapter);
        /*event_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                eventsAdapter.getFilter().filter(query);
                return false;
            }
        });*/

        InputStream inputStream = getResources().openRawResource(R.raw.form);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("Text Data", byteArrayOutputStream.toString());
        try {
            // Parse the data into jsonobject to get original data in form of json.
            BaseFormModel baseFormModel = new Gson().fromJson(byteArrayOutputStream.toString(), BaseFormModel.class);

            eventsAdapter = new SectionAdapter(this, baseFormModel.getForm().getSections());
            events_recyclerview.setAdapter(eventsAdapter);
            events_recyclerview.setLayoutManager(new LinearLayoutManager(this));

            eventsAdapter.setRecyclerClickListener(recyclerClickListener);
            eventsAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
                @Override
                public void onParentExpanded(int parentPosition) {
                    Toast.makeText(ForamsDisplayActivity.this, "parentPosition : " + parentPosition, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onParentCollapsed(int parentPosition) {
                    Toast.makeText(ForamsDisplayActivity.this, "onParentCollapsed : " + parentPosition, Toast.LENGTH_SHORT).show();
                    // ...
                }


            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    RecyclerClickListener recyclerClickListener = new RecyclerClickListener() {
        @Override
        public void onItemClick(int parentPos, int childPos) {
            Toast.makeText(ForamsDisplayActivity.this, "onItemClick : " + parentPos + ",child:" + childPos, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ForamsDisplayActivity.this,FormRegisterActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
