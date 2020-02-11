package com.dynaforms.utilities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dynaforms.R;
import com.dynaforms.view.ForamsActivity;
import com.dynaforms.view.MainActivity;
import com.dynaforms.view.PatientsList;

import java.util.HashMap;
import java.util.Iterator;

public class SplashScreenActivity extends Activity {
    private static final int SPLASH_DISPLAY_TIME = 2500;
    HashMap<String, String> userDetails;
    UserSessionManager session;
    String username = "",password = "",usertoken = "",admin = "";
    SharedPreferences prfs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView imageView = findViewById(R.id.splash_image);
        AppUtils.loadImageFromApi(imageView, R.drawable.syml_logo);
        userDetails = new HashMap<>();
//        userDetails = session.getUserDetails();
        prfs = getSharedPreferences("USERLOGIN", Context.MODE_PRIVATE);
        username = prfs.getString("user_name", "");
        password = prfs.getString("password", "");
        usertoken = prfs.getString("user_token", "");
        admin = prfs.getString("admin", "");
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (!username.equals("")){
                    if (admin.equals("true")) {
                        Intent intent = new Intent();
                        intent.setClass(SplashScreenActivity.this, PatientsList.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent();
                        intent.setClass(SplashScreenActivity.this, ForamsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else {
                    Intent intent = new Intent();
                    intent.setClass(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, SPLASH_DISPLAY_TIME);
    }
}
