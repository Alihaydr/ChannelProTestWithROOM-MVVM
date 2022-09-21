package com.example.channelprotest_mvc.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface InboxDao {

    @Insert
    void insert(Inbox inbox);

    @Update
    void update(Inbox inbox);

    @Delete
    void delete(Inbox inbox);

    @Query("delete from inbox_table")
    void deleteAll();

    @Query("select * from inbox_table ORDER by priority desc")
    LiveData<List<Inbox>> getAllInboxes();


}
