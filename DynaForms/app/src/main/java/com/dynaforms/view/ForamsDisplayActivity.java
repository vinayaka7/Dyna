package com.dynaforms.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.dynaforms.R;
import com.dynaforms.adapter.SectionAdapter;
import com.dynaforms.itemclicklistners.RecyclerClickListener;
import com.dynaforms.model.BaseFormModel;
import com.dynaforms.model.ChildList;
import com.dynaforms.model.Section;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.dynaforms.utilities.AppConstants.DataExtraUtils.CHILD_LIST;
import static com.dynaforms.utilities.AppConstants.DataExtraUtils.SECTION_NAME;

public class ForamsDisplayActivity extends AppCompatActivity {
    private BaseFormModel baseFormModel;
    private SectionAdapter sectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forams_display);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadDataFromFile();
        initRecyclerView();

    }

    private void initRecyclerView() {
        RecyclerView events_recyclerview = findViewById(R.id.rv_patients);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        events_recyclerview.setLayoutManager(layoutManager);
        events_recyclerview.setItemAnimator(new DefaultItemAnimator());
        events_recyclerview.setNestedScrollingEnabled(false);
        events_recyclerview.setHasFixedSize(false);

        if (baseFormModel != null) {
            sectionAdapter = new SectionAdapter(this, baseFormModel.getForm().getSections());
            events_recyclerview.setAdapter(sectionAdapter);
            events_recyclerview.setLayoutManager(new LinearLayoutManager(this));

            sectionAdapter.setRecyclerClickListener(recyclerClickListener);
            sectionAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
                @Override
                public void onParentExpanded(int parentPosition) {

                }

                @Override
                public void onParentCollapsed(int parentPosition) {
                    // ...
                }


            });
        }
    }

    private void loadDataFromFile() {
        InputStream inputStream = getResources().openRawResource(R.raw.form);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            int ctr = inputStream.read();
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
            baseFormModel = new Gson().fromJson(byteArrayOutputStream.toString(), BaseFormModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    RecyclerClickListener recyclerClickListener = new RecyclerClickListener() {
        @Override
        public void onItemClick(int parentPos, int childPos) {
            try {
                Intent intent = new Intent(ForamsDisplayActivity.this, FormRegisterActivity.class);
                Section section = baseFormModel.getForm().getSections().get(parentPos);
                ArrayList<ChildList> childList = (ArrayList<ChildList>) section.getChildList().get(childPos).getChildList();
                intent.putParcelableArrayListExtra(CHILD_LIST, childList);
                intent.putExtra(SECTION_NAME, section.getName());
                startActivity(intent);
            } catch (Exception e) {
            }
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
