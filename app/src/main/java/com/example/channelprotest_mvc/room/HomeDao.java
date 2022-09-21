package com.example.channelprotest_mvc.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HomeDao {

    @Insert
    void insert(Home home);

    @Update
    void update(Home home);

    @Delete
    void delete(Home home);

    @Query("delete from home_table")
    void deleteAll();

    @Query("select * from home_table ORDER by priority desc")
    LiveData<List<Home>> getAllHome();


}
