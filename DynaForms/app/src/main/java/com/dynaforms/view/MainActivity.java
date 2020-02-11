package com.dynaforms.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.dynaforms.R;
import com.dynaforms.utilities.UserSessionManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button sign_in;
    EditText et_username,et_password;
    CheckBox cb_admin;
    UserSessionManager userSessionManager;
    SharedPreferences pref;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userSessionManager = new UserSessionManager(MainActivity.this);
        preferences = getSharedPreferences("USERLOGIN", Context.MODE_PRIVATE);
        editor = preferences.edit();
        sign_in = findViewById(R.id.sign_in);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        cb_admin = findViewById(R.id.cb_admin);
        sign_in.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in:
                if (cb_admin.isChecked()) {
                    if (et_username.getText().toString().equals("")){
                        Toast.makeText(this, "Please provide Username", Toast.LENGTH_SHORT).show();
                    }else if (et_password.getText().toString().equals("")){
                        Toast.makeText(this, "Please provide Passwword", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String user_name = et_username.getText().toString();
                        String password = et_password.getText().toString();
                        userSessionManager.createUserLoginSession(user_name, password, "");

                        editor.putString("user_name", user_name);
                        editor.putString("password", password);
                        editor.putString("user_token", "");
                        editor.putString("admin", "true");
                        editor.apply();
                        Intent intent = new Intent(this, PatientsList.class);
                        startActivity(intent);
                        finish();
                    }

                }else {
                    if (et_username.getText().toString().equals("")){
                        Toast.makeText(this, "Please provide Username", Toast.LENGTH_SHORT).show();
                    }else if (et_password.getText().toString().equals("")){
                        Toast.makeText(this, "Please provide Passwword", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String user_name = et_username.getText().toString();
                        String password = et_password.getText().toString();
                        userSessionManager.createUserLoginSession(user_name, password, "");

                        editor.putString("user_name", user_name);
                        editor.putString("password", password);
                        editor.putString("user_token", "");
                        editor.putString("admin", "false");
                        editor.apply();
                        Intent intent = new Intent(this, ForamsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

        }

    }
}
