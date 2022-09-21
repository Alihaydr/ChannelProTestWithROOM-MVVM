package com.example.channelprotest_mvc.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HomeRepository {


    private HomeDao homeDao;
    private LiveData<List<Home>> allHome;

    public HomeRepository(Application application)
    {

        NewsDatabase database = NewsDatabase.getInstance(application);

        homeDao = database.homeDao();
        allHome= homeDao.getAllHome();

    }

    public void insert(Home home){

        new InsertHomeAsyncTask(homeDao).execute(home);

    }
    public void update(Home home){
        new UpdateHomeAsyncTask(homeDao).execute(home);


    }
    public void delete(Home home){
        new DeleteHomeAsyncTask(homeDao).execute(home);


    }
    public void deleteAllNews(){

        new DeleteAllAsyncTask(homeDao).execute();

    }

    public LiveData<List<Home>> getAllHome() {
        return allHome;
    }

    private static class UpdateHomeAsyncTask extends AsyncTask<Home,Void,Void>{


        private HomeDao homeDao;

        private UpdateHomeAsyncTask(HomeDao homeDao){

            this.homeDao=homeDao;
        }
        @Override
        protected Void doInBackground(Home... homes) {

            homeDao.update(homes[0]);
            return null;
        }
    }


    private static class InsertHomeAsyncTask extends AsyncTask<Home,Void,Void>{


        private HomeDao homeDao;

        private InsertHomeAsyncTask(HomeDao homeDao){

            this.homeDao=homeDao;
        }
        @Override
        protected Void doInBackground(Home... homes) {

            homeDao.insert(homes[0]);
            return null;
        }
    }

    private static class DeleteHomeAsyncTask extends AsyncTask<Home,Void,Void>{


        private HomeDao homeDao;

        private DeleteHomeAsyncTask(HomeDao homeDao){

            this.homeDao=homeDao;
        }
        @Override
        protected Void doInBackground(Home... homes) {

            homeDao.delete(homes[0]);
            return null;
        }
    }


    private static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{


        private HomeDao homeDao;

        private DeleteAllAsyncTask(HomeDao homeDao){

            this.homeDao=homeDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {

            homeDao.deleteAll();
            return null;
        }
    }

}
