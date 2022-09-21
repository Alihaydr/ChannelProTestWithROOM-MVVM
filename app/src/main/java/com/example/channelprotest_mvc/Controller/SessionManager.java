package com.example.channelprotest_mvc.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.channelprotest_mvc.Model.User;
import com.example.channelprotest_mvc.activities.LoginActivity;

public class SessionManager {

    private static final String SHARED_PREF_NAME = "userToken";
    private static final String KEY_FIRST_NAME = "firstname";
    private static final String KEY_LAST_NAME = "lastname";
    private static final String KEY_MOBILE_NUMBER = "mobile";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_COMPANY = "company";
    private static final String KEY_TOKEN = "token";

    private static SessionManager mInstance;
    private static Context mCtx;

    public SessionManager (Context context){

        mCtx = context;
    }

    public static synchronized SessionManager getInstance(Context context){


        if (mInstance == null)
        {

            mInstance = new SessionManager(context);
        }

        return mInstance;

    }

    public void userLogin(User user){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_FIRST_NAME,user.getFirst_name());
        editor.putString(KEY_LAST_NAME,user.getLast_name());
        editor.putString(KEY_MOBILE_NUMBER,user.getMobile_number());
        editor.putString(KEY_EMAIL,user.getEmail());
        editor.putString(KEY_PASSWORD,user.getPassword());
        editor.putString(KEY_COMPANY,user.getCompany());
        editor.putString(KEY_TOKEN,user.getToken());
        editor.apply();

    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        return sharedPreferences.getString(KEY_TOKEN,null) != null;
    }

    public User getToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        return new User(sharedPreferences.getString(KEY_TOKEN,null));

    }

    public void userLogout(){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }

    public User getUserInfo(){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        return new User(
                sharedPreferences.getString(KEY_FIRST_NAME,null),
                sharedPreferences.getString(KEY_LAST_NAME,null),
                sharedPreferences.getString(KEY_MOBILE_NUMBER,null),
                sharedPreferences.getString(KEY_EMAIL,null),
                sharedPreferences.getString(KEY_PASSWORD,null),
                sharedPreferences.getString(KEY_COMPANY,null),
                sharedPreferences.getString(KEY_TOKEN,null)
                );


    }

}
