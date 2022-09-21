package com.example.channelprotest_mvc.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.channelprotest_mvc.Controller.PointGetterFromShared;

public class PointsViewModel extends AndroidViewModel {

    private final int points;

    public PointsViewModel(@NonNull Application application) {
        super(application);


        points=PointGetterFromShared.getInstance(application).getPoints().getPoints();
    }

    public int getPoints(){
        return points;
    }

}
