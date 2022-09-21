package com.example.channelprotest_mvc.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    void insert(News news);

    @Update
    void update(News news);

    @Delete
    void delete(News news);

    @Query("delete from news_table")
    void deleteAll();

    @Query("select * from news_table ORDER by priority desc")
    LiveData<List<News>> getAllNotes();


}
