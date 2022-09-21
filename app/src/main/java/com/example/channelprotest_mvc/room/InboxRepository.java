package com.example.channelprotest_mvc.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

public class InboxRepository {


    private InboxDao inboxDao;
    private LiveData<List<Inbox>> allInbox;

    public InboxRepository(Application application)
    {

        NewsDatabase database = NewsDatabase.getInstance(application);

        inboxDao = database.inboxDao();
        allInbox= inboxDao.getAllInboxes();

    }

    public void insert(Inbox inbox){

        new InsertInboxesAsyncTask(inboxDao).execute(inbox);

    }
    public void update(Inbox inbox){
        new UpdateInboxesAsyncTask(inboxDao).execute(inbox);


    }
    public void delete(Inbox inbox){
        new DeleteInboxesAsyncTask(inboxDao).execute(inbox);


    }
    public void deleteAllNews(){

        new DeleteAllAsyncTask(inboxDao).execute();

    }

    public LiveData<List<Inbox>> getAllInbox() {
        return allInbox;
    }

    private static class UpdateInboxesAsyncTask extends AsyncTask<Inbox,Void,Void> {


        private InboxDao inboxDao;

        private UpdateInboxesAsyncTask(InboxDao inboxDao){

            this.inboxDao=inboxDao;
        }
        @Override
        protected Void doInBackground(Inbox... inboxes) {

            inboxDao.update(inboxes[0]);
            return null;
        }
    }


    private static class InsertInboxesAsyncTask extends AsyncTask<Inbox,Void,Void> {


        private InboxDao inboxDao;

        private InsertInboxesAsyncTask(InboxDao inboxDao){

            this.inboxDao=inboxDao;
        }
        @Override
        protected Void doInBackground(Inbox... inboxes) {

            inboxDao.insert(inboxes[0]);
            return null;
        }
    }

    private static class DeleteInboxesAsyncTask extends AsyncTask<Inbox,Void,Void> {


        private InboxDao inboxDao;

        private DeleteInboxesAsyncTask(InboxDao inboxDao){

            this.inboxDao=inboxDao;
        }
        @Override
        protected Void doInBackground(Inbox... inboxes) {

            inboxDao.delete(inboxes[0]);
            return null;
        }
    }



    private static class DeleteAllAsyncTask extends AsyncTask<Inbox,Void,Void> {


        private InboxDao inboxDao;

        private DeleteAllAsyncTask(InboxDao inboxDao){

            this.inboxDao=inboxDao;
        }
        @Override
        protected Void doInBackground(Inbox... inboxes) {

            inboxDao.deleteAll();
            return null;
        }
    }


}

