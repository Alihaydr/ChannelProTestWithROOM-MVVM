package com.example.channelprotest_mvc.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.channelprotest_mvc.R;

import java.util.Date;

@Database(entities = {News.class,Inbox.class,Home.class},version = 3)
public abstract class NewsDatabase extends RoomDatabase {

    private static NewsDatabase mInstance;

    public abstract NewsDao newsDao();
    public abstract InboxDao inboxDao();
    public abstract HomeDao homeDao();

    public static synchronized NewsDatabase getInstance(Context context){

        if (mInstance == null){

            mInstance = Room.databaseBuilder(context.getApplicationContext(),NewsDatabase.class,"database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();


        }
        return mInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDBAsyncTask(mInstance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void>{

        private NewsDao newsDao;
        private InboxDao inboxDao;
        private HomeDao homeDao;

        private PopulateDBAsyncTask(NewsDatabase db){

            newsDao = db.newsDao();
            inboxDao = db.inboxDao();
            homeDao = db.homeDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            newsDao.insert(new News("www.google.com","Tech News: 5 Things to Know in Australia Today", R.drawable.iphone14,"www.google.com",1));
            newsDao.insert(new News("www.pro.com","Business energy price cap gives startups breathing space", R.drawable.apple3,"www.facebook.com",2));
            newsDao.insert(new News("www.amazon.com","Internet of Bodies: a data-led healthcare revolution", R.drawable.apple,"www.pro.com",3));

            inboxDao.insert(new Inbox("New Product in your Account","New Product in your Account lets see it",1));
            inboxDao.insert(new Inbox("Message From Admin","New Product in your Account lets see it",2));
            inboxDao.insert(new Inbox("Message From Mark","New Product in your Account lets see it",3));

            homeDao.insert(new Home("www.google.com","Find your dream tech role before the end of 2022", R.drawable.apple2,1));
            homeDao.insert(new Home("www.facebook.com","Professor behind $12 billion empire fuels China’s tech rise", R.drawable.apple3,2));
            homeDao.insert(new Home("www.pro.com","Find your dream tech role before the end of 2022", R.drawable.iphone14,3));

            return null;
        }
    }

}
