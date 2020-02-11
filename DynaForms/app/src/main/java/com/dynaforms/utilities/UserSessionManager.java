package com.dynaforms.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;


public class UserSessionManager {

    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREFER_NAME = "dynaforams";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String USER_TOKEN = "usertoken";

    public UserSessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLoginSession(String user_name, String password, String usertoken) {
        editor.putString(USER_NAME, user_name);
        editor.putString(PASSWORD, password);
        editor.putString(USER_TOKEN, usertoken);
        editor.commit();
    }


    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(USER_NAME, pref.getString(USER_NAME, null));
        user.put(PASSWORD, pref.getString(PASSWORD, null));
        user.put(USER_TOKEN, pref.getString(USER_TOKEN, null));
        return user;
    }

    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}