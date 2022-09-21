package com.example.channelprotest_mvc.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.channelprotest_mvc.room.Inbox;
import com.example.channelprotest_mvc.room.InboxRepository;
import com.example.channelprotest_mvc.room.News;
import com.example.channelprotest_mvc.room.NewsRepository;

import java.util.List;

public class InboxViewModel extends AndroidViewModel {

    private InboxRepository repository;
    private LiveData<List<Inbox>> allNews;

    public InboxViewModel(@NonNull Application application) {
        super(application);
        repository=new InboxRepository(application);
        allNews=repository.getAllInbox();

    }

    public void insert(Inbox inbox){
        repository.insert(inbox);
    }

    public void update(Inbox inbox){
        repository.update(inbox);
    }

    public void delete(Inbox inbox){
        repository.delete(inbox);
    }

    public void deleteAllInboxes(){
        repository.deleteAllNews();
    }

    public LiveData<List<Inbox>> getAllInboxes() {
        return allNews;
    }
}
