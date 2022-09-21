package com.example.channelprotest_mvc.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NewsRepository {


    private NewsDao newsDao;
    private LiveData<List<News>> allNews;

    public NewsRepository(Application application)
    {

        NewsDatabase database = NewsDatabase.getInstance(application);

        newsDao = database.newsDao();
        allNews= newsDao.getAllNotes();

    }

    public void insert(News news){

        new InsertNewsAsyncTask(newsDao).execute(news);

    }
    public void update(News news){
        new UpdateNewsAsyncTask(newsDao).execute(news);


    }
    public void delete(News news){
        new DeleteNewsAsyncTask(newsDao).execute(news);


    }
    public void deleteAllNews(){

        new DeleteAllNewsAsyncTask(newsDao).execute();

    }

    public LiveData<List<News>> getAllNews() {
        return allNews;
    }

    private static class UpdateNewsAsyncTask extends AsyncTask<News,Void,Void>{


        private NewsDao newsDao;

        private UpdateNewsAsyncTask(NewsDao newsDao){

            this.newsDao=newsDao;
        }
        @Override
        protected Void doInBackground(News... news) {

            newsDao.update(news[0]);
            return null;
        }
    }


    private static class DeleteNewsAsyncTask extends AsyncTask<News,Void,Void>{


        private NewsDao newsDao;

        private DeleteNewsAsyncTask(NewsDao newsDao){

            this.newsDao=newsDao;
        }
        @Override
        protected Void doInBackground(News... news) {

            newsDao.delete(news[0]);
            return null;
        }
    }

    private static class DeleteAllNewsAsyncTask extends AsyncTask<Void,Void,Void>{


        private NewsDao newsDao;

        private DeleteAllNewsAsyncTask(NewsDao newsDao){

            this.newsDao=newsDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {

            newsDao.deleteAll();
            return null;
        }
    }


    private static class InsertNewsAsyncTask extends AsyncTask<News,Void,Void>{


        private NewsDao newsDao;

        private InsertNewsAsyncTask(NewsDao newsDao){

            this.newsDao=newsDao;
        }
        @Override
        protected Void doInBackground(News... news) {

            newsDao.insert(news[0]);
            return null;
        }
    }

}
