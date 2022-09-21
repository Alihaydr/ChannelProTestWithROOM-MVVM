package com.example.channelprotest_mvc.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.channelprotest_mvc.room.Home;
import com.example.channelprotest_mvc.room.HomeRepository;
import com.example.channelprotest_mvc.room.Inbox;
import com.example.channelprotest_mvc.room.InboxRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private HomeRepository repository;
    private LiveData<List<Home>> allHome;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository=new HomeRepository(application);
        allHome=repository.getAllHome();

    }

    public void insert(Home home){
        repository.insert(home);
    }

    public void update(Home home){
        repository.update(home);
    }

    public void delete(Home home){
        repository.delete(home);
    }

    public void deleteAllInboxes(){
        repository.deleteAllNews();
    }

    public LiveData<List<Home>> getAllHome() {
        return allHome;
    }
}
