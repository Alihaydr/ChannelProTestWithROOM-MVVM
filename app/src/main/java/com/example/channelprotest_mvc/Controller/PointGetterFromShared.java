package com.example.channelprotest_mvc.Controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.channelprotest_mvc.Model.Points;
import com.example.channelprotest_mvc.Model.User;

public class PointGetterFromShared {

    private int points;

    private static final String SHARED_PREF_NAME = "userPoints";
    private static final String KEY_POINTS_VALUE = "points";


    public static PointGetterFromShared mInstance;
    public static Context mCtx;


    public PointGetterFromShared (Context ctx){

        mCtx=ctx;

    }
    public static synchronized PointGetterFromShared getInstance(Context context){


        if (mInstance == null)
        {

            mInstance = new PointGetterFromShared(context);
        }

        return mInstance;

    }



    public Points getPoints(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

      /*  SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_POINTS_VALUE,999);
        editor.apply();
*/
        return new Points(sharedPreferences.getInt(KEY_POINTS_VALUE,2000));

    }
}
