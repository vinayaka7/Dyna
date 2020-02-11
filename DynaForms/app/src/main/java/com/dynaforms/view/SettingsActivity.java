package com.dynaforms.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dynaforms.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_url,et_minuites;
    Button btn_save,btn_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        et_url = findViewById(R.id.et_url);
        et_minuites = findViewById(R.id.et_minuites);
        btn_save = findViewById(R.id.btn_save);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_save.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_save:
                if (et_url.getText().toString().equals("")){
                    Toast.makeText(this, "Please upload URL", Toast.LENGTH_SHORT).show();
                }else if (et_minuites.getText().toString().equals("")){
                    Toast.makeText(this, "Please provide session time", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
            case R.id.btn_cancel:
                finish();
                return;
        }
    }
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
