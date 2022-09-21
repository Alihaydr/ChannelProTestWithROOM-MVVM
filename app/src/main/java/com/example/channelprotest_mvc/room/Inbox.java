package com.example.channelprotest_mvc.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "inbox_table")
public class Inbox {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String Description;
    private int priority;

    public  Inbox(){


    }
    public Inbox( String title, String description, int priority) {

        this.title = title;
        this.Description = description;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
